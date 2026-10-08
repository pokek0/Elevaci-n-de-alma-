package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.CemReadingsData
import com.example.data.DevocionarioData
import com.example.data.PrayersData
import com.example.data.local.RosaryDatabase
import com.example.data.model.CustomPrayer
import com.example.data.model.MysteryType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Mi devocionario", appName)
    }

    @Test
    fun `verify all mystery types have 5 mysteries`() {
        MysteryType.values().forEach { mysteryType ->
            val list = PrayersData.mysteries[mysteryType]
            assertNotNull(list)
            assertEquals(5, list?.size)
        }
    }

    @Test
    fun `verify devocionario prayers loaded correctly`() {
        assertTrue(DevocionarioData.prayers.isNotEmpty())
        val angelus = DevocionarioData.prayers.find { it.id == "el_angelus" }
        assertNotNull(angelus)
        assertTrue(angelus?.isInteractiveAngelus == true)

        val puente = DevocionarioData.prayers.find { it.id == "el_puente" }
        assertNotNull(puente)
        assertEquals(2, puente?.pageNumber)
    }

    @Test
    fun `verify cem daily readings and reflection loaded`() {
        val days = CemReadingsData.liturgicalDays
        assertTrue(days.isNotEmpty())
        val today = days.first()
        assertTrue(today.readings.isNotEmpty())
        assertTrue(today.reflectionTitle.isNotBlank())
        assertTrue(today.reflectionText.isNotBlank())
        assertTrue(today.dailyPrayer.isNotBlank())
    }

    @Test
    fun `verify custom prayer room persistence`() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = RosaryDatabase.getDatabase(context)
        val dao = db.rosaryDao()

        val custom = CustomPrayer(
            title = "Por la salud de mi abuela",
            subtitle = "Oración familiar",
            category = "Salud",
            content = "Señor, dale consuelo y fortaleza..."
        )
        val id = dao.insertCustomPrayer(custom)
        assertTrue(id > 0)

        val list = dao.getAllCustomPrayers().first()
        assertTrue(list.any { it.title == "Por la salud de mi abuela" })
    }

    @Test
    fun `verify viacrucis and via lucis stations loaded`() {
        assertEquals(15, com.example.data.ViacrucisData.stations.size)
        assertEquals(14, com.example.data.ViaLucisData.stations.size)
        assertTrue(com.example.data.ViacrucisData.stations.all { it.title.isNotBlank() && it.prayer.isNotBlank() })
        assertTrue(com.example.data.ViaLucisData.stations.all { it.title.isNotBlank() && it.meditation.isNotBlank() })
    }

    @Test
    fun `verify liturgia de las horas and coronilla data`() {
        assertEquals(4, com.example.data.HoraLiturgica.values().size)
        val laudes = com.example.data.LiturgiaHorasData.laudes
        assertTrue(laudes.salmodia.isNotEmpty())
        assertTrue(laudes.canticoEvangelico.third.isNotBlank())

        assertTrue(com.example.data.CoronillaMisericordiaData.CUENTA_GRANDE_PADRE_ETERNO.isNotBlank())
        assertTrue(com.example.data.CoronillaMisericordiaData.CUENTA_PEQUENA_DECENA.isNotBlank())
        assertTrue(com.example.data.CoronillaMisericordiaData.SANTO_DIOS_TRISAGIO.isNotBlank())
    }

    @Test
    fun `verify schedule slots and persistence`() = runBlocking {
        assertEquals(6, com.example.data.ScheduleData.slots.size)
        assertTrue(com.example.data.ScheduleData.slots.all { it.prayersList.isNotEmpty() })

        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = RosaryDatabase.getDatabase(context)
        val dao = db.rosaryDao()

        val check = com.example.data.model.ScheduleCheck(
            id = "2026-10-06_DESPERTAR",
            dateString = "2026-10-06",
            slotId = "DESPERTAR",
            isCompleted = true
        )
        dao.insertScheduleCheck(check)

        val checks = dao.getScheduleChecksForDate("2026-10-06").first()
        assertTrue(checks.any { it.slotId == "DESPERTAR" && it.isCompleted })
    }

    @Test
    fun `verify pro upgrade workflow`() {
        val application = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = com.example.ui.viewmodel.RosaryViewModel(application)

        viewModel.setShowProDialog(true, "Horario de oración")
        assertTrue(viewModel.uiState.value.showProDialog)
        assertEquals("Horario de oración", viewModel.uiState.value.proFeaturePrompt)

        viewModel.unlockProVersion()
        assertTrue(viewModel.uiState.value.isProUser)
        org.junit.Assert.assertFalse(viewModel.uiState.value.showProDialog)
    }

    @Test
    fun `verify unified rezos navigation tabs`() {
        val application = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = com.example.ui.viewmodel.RosaryViewModel(application)

        viewModel.setUnifiedRezoTab(com.example.ui.viewmodel.UnifiedRezoTab.CORONILLA)
        assertEquals(com.example.ui.viewmodel.UnifiedRezoTab.CORONILLA, viewModel.uiState.value.unifiedRezoTab)

        viewModel.setUnifiedRezoTab(com.example.ui.viewmodel.UnifiedRezoTab.VIACRUCIS)
        assertEquals(com.example.ui.viewmodel.UnifiedRezoTab.VIACRUCIS, viewModel.uiState.value.unifiedRezoTab)
    }

    @Test
    fun `verify liturgical colors and dynamic selection`() {
        val application = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = com.example.ui.viewmodel.RosaryViewModel(application)

        assertNotNull(viewModel.uiState.value.liturgicalColor)

        viewModel.setLiturgicalColor(com.example.data.model.LiturgicalColor.MORADO)
        assertEquals(com.example.data.model.LiturgicalColor.MORADO, viewModel.uiState.value.liturgicalColor)
        org.junit.Assert.assertFalse(viewModel.uiState.value.isAutoLiturgicalColor)

        viewModel.setAutoLiturgicalColor(true)
        assertTrue(viewModel.uiState.value.isAutoLiturgicalColor)
    }

    @Test
    fun `verify mis oraciones navigation tab`() {
        val application = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = com.example.ui.viewmodel.RosaryViewModel(application)

        viewModel.setTab(com.example.ui.viewmodel.MainTab.MIS_ORACIONES)
        assertEquals(com.example.ui.viewmodel.MainTab.MIS_ORACIONES, viewModel.uiState.value.currentTab)
    }

    @Test
    fun `verify premium access state logic and feature invitation`() {
        val application = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = com.example.ui.viewmodel.RosaryViewModel(application)

        // Initially not premium
        org.junit.Assert.assertFalse(viewModel.canAccessFeature(com.example.data.model.PremiumFeature.HORARIO_ORACION))
        org.junit.Assert.assertFalse(viewModel.canAccessFeature(com.example.data.model.PremiumFeature.ORACIONES_PERSONALIZADAS))

        // Request access to HORARIO_ORACION when locked
        var horarioCallbackCalled = false
        val grantedSchedule = viewModel.requestFeatureAccess(com.example.data.model.PremiumFeature.HORARIO_ORACION) {
            horarioCallbackCalled = true
        }
        org.junit.Assert.assertFalse(grantedSchedule)
        org.junit.Assert.assertFalse(horarioCallbackCalled)
        assertTrue(viewModel.uiState.value.showProDialog)
        assertEquals(com.example.data.model.PremiumFeature.HORARIO_ORACION, viewModel.uiState.value.activePremiumFeature)
        assertEquals(com.example.data.model.PremiumFeature.HORARIO_ORACION.inviteMessage, viewModel.uiState.value.proFeaturePrompt)

        // Dismiss invite
        viewModel.dismissPremiumInvite()
        org.junit.Assert.assertFalse(viewModel.uiState.value.showProDialog)

        // Request access to ORACIONES_PERSONALIZADAS
        var prayersCallbackCalled = false
        val grantedPrayers = viewModel.requestFeatureAccess(com.example.data.model.PremiumFeature.ORACIONES_PERSONALIZADAS) {
            prayersCallbackCalled = true
        }
        org.junit.Assert.assertFalse(grantedPrayers)
        org.junit.Assert.assertFalse(prayersCallbackCalled)
        assertTrue(viewModel.uiState.value.showProDialog)
        assertEquals(com.example.data.model.PremiumFeature.ORACIONES_PERSONALIZADAS, viewModel.uiState.value.activePremiumFeature)
        assertEquals(com.example.data.model.PremiumFeature.ORACIONES_PERSONALIZADAS.inviteMessage, viewModel.uiState.value.proFeaturePrompt)

        // Unlock one-time payment ($1 USD)
        viewModel.unlockProVersion()
        assertTrue(viewModel.uiState.value.isProUser)
        assertTrue(viewModel.uiState.value.premiumState.isPremium)
        org.junit.Assert.assertFalse(viewModel.uiState.value.showProDialog)

        // Now access is granted directly and callback runs
        var postUnlockCallbackRan = false
        val grantedAfterUnlock = viewModel.requestFeatureAccess(com.example.data.model.PremiumFeature.HORARIO_ORACION) {
            postUnlockCallbackRan = true
        }
        assertTrue(grantedAfterUnlock)
        assertTrue(postUnlockCallbackRan)
    }
}
