package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.RosaryDatabase
import com.example.data.model.DevotionalPrayer
import com.example.data.model.MysteryType
import com.example.data.model.PrayerRecord
import com.example.data.model.CustomPrayer
import com.example.data.repository.RosaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class MainTab {
    PORTADA,
    REZOS,
    HORARIO,
    LITURGIA,
    DEVOCIONARIO,
    MIS_ORACIONES
}

enum class UnifiedRezoTab {
    ROSARIO,
    CORONILLA,
    VIACRUCIS,
    VIA_LUCIS
}

enum class PrayerCounterType {
    ROSARIO,
    CORONILLA
}

enum class ViacrucisSubTab {
    VIACRUCIS,
    VIA_LUCIS
}

enum class LiturgiaSubTab {
    HORAS,
    LECTURAS_CEM
}

data class RosaryUiState(
    val currentTab: MainTab = MainTab.PORTADA,
    val isProUser: Boolean = false,
    val showProDialog: Boolean = false,
    val proFeaturePrompt: String = "",
    val unifiedRezoTab: UnifiedRezoTab = UnifiedRezoTab.ROSARIO,
    val step: Int = 0,
    val mysteryType: MysteryType = MysteryType.getTodayMystery(),
    val currentMysteryIndex: Int = 0, // 0..4
    val aveMariaCount: Int = 0, // 0..10
    val personalIntention: String = "",
    val themeMode: com.example.ui.theme.ThemeMode = com.example.ui.theme.ThemeMode.SYSTEM,
    val showThemeDialog: Boolean = false,
    val hapticEnabled: Boolean = true,
    val fontScale: Float = 1.0f,
    val showHistoryDialog: Boolean = false,
    val showIntentionDialog: Boolean = false,
    val showLitaniesDialog: Boolean = false,
    val isRosaryCompleted: Boolean = false,
    // Devocionario booklet state
    val selectedDevotionalPrayer: DevotionalPrayer? = null,
    val devocionarioCategory: String = "Todas",
    val devocionarioSearch: String = "",
    // Ángelus interactive decade/counts
    val angelusCount: Int = 0, // 0..3 Avemarías
    val angelusCompleted: Boolean = false,
    val reginaCoeliStep: Int = 0,
    val reginaCoeliCompleted: Boolean = false,
    // Prayer Counter mode (Rosario vs Coronilla de la Misericordia)
    val prayerCounterType: PrayerCounterType = PrayerCounterType.ROSARIO,
    // Coronilla al Señor de la Misericordia
    val coronillaStep: Int = 0, // 0: Inicio, 1: Decenas, 2: Cierre Santo Dios, 3: Conclusión
    val coronillaDecadeIndex: Int = 0, // 0..4 (5 decenas)
    val coronillaCount: Int = 0, // 0..10 cuentas pequeñas
    val coronillaSantoDiosCount: Int = 0, // 0..3 repeticiones
    val isCoronillaCompleted: Boolean = false,
    // Vía Crucis & Vía Lúcis
    val viacrucisSubTab: ViacrucisSubTab = ViacrucisSubTab.VIACRUCIS,
    val viacrucisStationIndex: Int = 0, // 0..14
    val isViacrucisStepByStep: Boolean = true,
    val viaLucisStationIndex: Int = 0, // 0..13
    val isViaLucisStepByStep: Boolean = true,
    // Liturgia de las Horas
    val liturgiaSubTab: LiturgiaSubTab = LiturgiaSubTab.HORAS,
    val selectedHoraLiturgica: com.example.data.HoraLiturgica = com.example.data.HoraLiturgica.getRecommendedHora(java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)),
    // Horario de Oración Diaria
    val selectedScheduleSlot: com.example.data.ScheduleSlot? = null,
    val showScheduleSlotDetail: Boolean = false,
    // Liturgical Color of the Day
    val liturgicalColor: com.example.data.model.LiturgicalColor = com.example.data.model.LiturgicalColor.getTodayLiturgicalColor(),
    val isAutoLiturgicalColor: Boolean = true,
    // Premium state management
    val premiumState: com.example.data.model.PremiumState = if (isProUser) com.example.data.model.PremiumState.Premium() else com.example.data.model.PremiumState.Free(),
    val activePremiumFeature: com.example.data.model.PremiumFeature? = null
) {
    val isDarkMode: Boolean
        get() = themeMode == com.example.ui.theme.ThemeMode.DARK
}

class RosaryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RosaryRepository
    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = application.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vibratorManager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        application.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

    private val prefs = application.getSharedPreferences("mi_devocionario_prefs", Context.MODE_PRIVATE)

    private val savedColorOverride = prefs.getString("liturgical_color_override", null)?.let {
        try { com.example.data.model.LiturgicalColor.valueOf(it) } catch (_: Exception) { null }
    }

    private val isInitialPro = prefs.getBoolean("is_pro_user", false)

    private val _uiState = MutableStateFlow(
        RosaryUiState(
            isProUser = isInitialPro,
            premiumState = if (isInitialPro) com.example.data.model.PremiumState.Premium() else com.example.data.model.PremiumState.Free(),
            liturgicalColor = savedColorOverride ?: com.example.data.model.LiturgicalColor.getTodayLiturgicalColor(),
            isAutoLiturgicalColor = savedColorOverride == null
        )
    )
    val uiState: StateFlow<RosaryUiState> = _uiState.asStateFlow()

    init {
        val database = RosaryDatabase.getDatabase(application)
        repository = RosaryRepository(database.rosaryDao())
    }

    val historyRecords: StateFlow<List<PrayerRecord>> = repository.allRecords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val completedRosariesCount: StateFlow<Int> = repository.completedCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val customPrayers: StateFlow<List<CustomPrayer>> = repository.allCustomPrayers
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val todayDateString: String = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())

    val todayScheduleChecks: StateFlow<List<com.example.data.model.ScheduleCheck>> = repository
        .getScheduleChecksForDate(todayDateString)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Premium State Management ---
    fun canAccessFeature(feature: com.example.data.model.PremiumFeature): Boolean {
        return _uiState.value.isProUser
    }

    fun requestFeatureAccess(feature: com.example.data.model.PremiumFeature, onGranted: () -> Unit = {}): Boolean {
        if (_uiState.value.isProUser) {
            onGranted()
            return true
        } else {
            showPremiumInvite(feature)
            return false
        }
    }

    fun showPremiumInvite(feature: com.example.data.model.PremiumFeature) {
        _uiState.update {
            it.copy(
                showProDialog = true,
                activePremiumFeature = feature,
                proFeaturePrompt = feature.inviteMessage,
                premiumState = com.example.data.model.PremiumState.Free(lastInvitedFeature = feature)
            )
        }
    }

    fun dismissPremiumInvite() {
        _uiState.update { it.copy(showProDialog = false, activePremiumFeature = null) }
    }

    fun unlockProVersion() {
        prefs.edit().putBoolean("is_pro_user", true).apply()
        _uiState.update {
            it.copy(
                isProUser = true,
                showProDialog = false,
                activePremiumFeature = null,
                premiumState = com.example.data.model.PremiumState.Premium()
            )
        }
        triggerHaptic(isCompletion = true)
    }

    fun restorePurchases() {
        val isPurchased = prefs.getBoolean("is_pro_user", false)
        if (isPurchased) {
            _uiState.update {
                it.copy(
                    isProUser = true,
                    showProDialog = false,
                    premiumState = com.example.data.model.PremiumState.Premium()
                )
            }
        }
    }

    fun setShowProDialog(show: Boolean, featurePrompt: String = "") {
        _uiState.update { it.copy(showProDialog = show, proFeaturePrompt = featurePrompt) }
    }

    fun setUnifiedRezoTab(tab: UnifiedRezoTab) {
        _uiState.update { it.copy(unifiedRezoTab = tab) }
    }

    fun setTab(tab: MainTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun openPrayer(prayer: DevotionalPrayer) {
        _uiState.update {
            it.copy(
                selectedDevotionalPrayer = prayer,
                angelusCount = 0,
                angelusCompleted = false,
                reginaCoeliStep = 0,
                reginaCoeliCompleted = false
            )
        }
    }

    fun closePrayer() {
        _uiState.update { it.copy(selectedDevotionalPrayer = null) }
    }

    fun setDevocionarioCategory(category: String) {
        _uiState.update { it.copy(devocionarioCategory = category) }
    }

    fun setDevocionarioSearch(search: String) {
        _uiState.update { it.copy(devocionarioSearch = search) }
    }

    // Ángelus interactive actions
    fun incrementAngelusCount() {
        val current = _uiState.value.angelusCount
        if (current < 3) {
            val next = current + 1
            _uiState.update { it.copy(angelusCount = next, angelusCompleted = next == 3) }
            triggerHaptic(isCompletion = next == 3)
        }
    }

    fun resetAngelus() {
        _uiState.update { it.copy(angelusCount = 0, angelusCompleted = false) }
    }

    // Regina Coeli interactive actions
    fun advanceReginaCoeli() {
        val current = _uiState.value.reginaCoeliStep
        if (current < 3) {
            val next = current + 1
            _uiState.update { it.copy(reginaCoeliStep = next, reginaCoeliCompleted = next == 3) }
            triggerHaptic(isCompletion = next == 3)
        }
    }

    fun resetReginaCoeli() {
        _uiState.update { it.copy(reginaCoeliStep = 0, reginaCoeliCompleted = false) }
    }

    fun setStep(step: Int) {
        _uiState.update { it.copy(step = step.coerceIn(0, 5)) }
    }

    fun nextStep() {
        _uiState.update {
            val next = (it.step + 1).coerceAtMost(5)
            if (next == 5 && !it.isRosaryCompleted) {
                viewModelScope.launch {
                    repository.saveCompletedRosary(
                        mysteryType = it.mysteryType.name,
                        intention = it.personalIntention
                    )
                }
                it.copy(step = next, isRosaryCompleted = true)
            } else {
                it.copy(step = next)
            }
        }
    }

    fun prevStep() {
        _uiState.update { it.copy(step = (it.step - 1).coerceAtLeast(0)) }
    }

    fun setMysteryType(type: MysteryType) {
        _uiState.update {
            it.copy(
                mysteryType = type,
                currentMysteryIndex = 0,
                aveMariaCount = 0
            )
        }
    }

    fun countAveMaria() {
        val current = _uiState.value.aveMariaCount
        if (current < 10) {
            val next = current + 1
            _uiState.update { it.copy(aveMariaCount = next) }
            triggerHaptic(isCompletion = next == 10)
        }
    }

    fun setAveMariaCount(count: Int) {
        _uiState.update { it.copy(aveMariaCount = count.coerceIn(0, 10)) }
        triggerHaptic(isCompletion = count == 10)
    }

    fun nextMystery() {
        val currentIdx = _uiState.value.currentMysteryIndex
        if (currentIdx < 4) {
            _uiState.update {
                it.copy(
                    currentMysteryIndex = currentIdx + 1,
                    aveMariaCount = 0
                )
            }
            triggerHaptic(isCompletion = false)
        } else {
            nextStep()
        }
    }

    fun prevMystery() {
        val currentIdx = _uiState.value.currentMysteryIndex
        if (currentIdx > 0) {
            _uiState.update {
                it.copy(
                    currentMysteryIndex = currentIdx - 1,
                    aveMariaCount = 0
                )
            }
            triggerHaptic(isCompletion = false)
        }
    }

    fun setMysteryIndex(index: Int) {
        val clamped = index.coerceIn(0, 4)
        _uiState.update {
            it.copy(
                currentMysteryIndex = clamped,
                aveMariaCount = 0
            )
        }
        triggerHaptic(isCompletion = false)
    }

    fun restart() {
        _uiState.update {
            it.copy(
                step = 0,
                currentMysteryIndex = 0,
                aveMariaCount = 0,
                isRosaryCompleted = false
            )
        }
    }

    fun setPersonalIntention(intention: String) {
        _uiState.update { it.copy(personalIntention = intention) }
    }

    fun setThemeMode(mode: com.example.ui.theme.ThemeMode) {
        _uiState.update { it.copy(themeMode = mode) }
    }

    fun setShowThemeDialog(show: Boolean) {
        _uiState.update { it.copy(showThemeDialog = show) }
    }

    fun cycleThemeMode() {
        _uiState.update {
            val next = when (it.themeMode) {
                com.example.ui.theme.ThemeMode.SYSTEM -> com.example.ui.theme.ThemeMode.LIGHT
                com.example.ui.theme.ThemeMode.LIGHT -> com.example.ui.theme.ThemeMode.DARK
                com.example.ui.theme.ThemeMode.DARK -> com.example.ui.theme.ThemeMode.SYSTEM
            }
            it.copy(themeMode = next)
        }
    }

    fun toggleDarkMode() {
        _uiState.update {
            val next = if (it.themeMode == com.example.ui.theme.ThemeMode.DARK) {
                com.example.ui.theme.ThemeMode.LIGHT
            } else {
                com.example.ui.theme.ThemeMode.DARK
            }
            it.copy(themeMode = next)
        }
    }

    fun setLiturgicalColor(color: com.example.data.model.LiturgicalColor) {
        prefs.edit().putString("liturgical_color_override", color.name).apply()
        _uiState.update { it.copy(liturgicalColor = color, isAutoLiturgicalColor = false) }
    }

    fun setAutoLiturgicalColor(enabled: Boolean) {
        if (enabled) {
            prefs.edit().remove("liturgical_color_override").apply()
            _uiState.update {
                it.copy(
                    isAutoLiturgicalColor = true,
                    liturgicalColor = com.example.data.model.LiturgicalColor.getTodayLiturgicalColor()
                )
            }
        } else {
            _uiState.update { it.copy(isAutoLiturgicalColor = false) }
        }
    }

    fun toggleHaptic() {
        _uiState.update { it.copy(hapticEnabled = !it.hapticEnabled) }
    }

    fun cycleFontScale() {
        _uiState.update {
            val nextScale = when (it.fontScale) {
                1.0f -> 1.15f
                1.15f -> 1.3f
                else -> 1.0f
            }
            it.copy(fontScale = nextScale)
        }
    }

    fun setShowHistoryDialog(show: Boolean) {
        _uiState.update { it.copy(showHistoryDialog = show) }
    }

    fun setShowIntentionDialog(show: Boolean) {
        _uiState.update { it.copy(showIntentionDialog = show) }
    }

    fun setShowLitaniesDialog(show: Boolean) {
        _uiState.update { it.copy(showLitaniesDialog = show) }
    }

    fun deleteHistoryRecord(id: Long) {
        viewModelScope.launch {
            repository.deleteRecord(id)
        }
    }

    fun addCustomPrayer(prayer: CustomPrayer) {
        viewModelScope.launch {
            repository.addCustomPrayer(prayer)
        }
    }

    fun updateCustomPrayer(prayer: CustomPrayer) {
        viewModelScope.launch {
            repository.updateCustomPrayer(prayer)
        }
    }

    fun deleteCustomPrayer(id: Long) {
        viewModelScope.launch {
            repository.deleteCustomPrayer(id)
        }
    }

    // --- Horario de Oración Diaria ---
    fun toggleScheduleSlot(slotId: String, currentChecked: Boolean) {
        viewModelScope.launch {
            repository.toggleScheduleCheck(todayDateString, slotId, currentChecked)
            triggerHaptic(isCompletion = !currentChecked)
        }
    }

    fun openScheduleSlot(slot: com.example.data.ScheduleSlot) {
        _uiState.update { it.copy(selectedScheduleSlot = slot, showScheduleSlotDetail = true) }
    }

    fun closeScheduleSlot() {
        _uiState.update { it.copy(selectedScheduleSlot = null, showScheduleSlotDetail = false) }
    }

    // --- Coronilla al Señor de la Misericordia ---
    fun setPrayerCounterType(type: PrayerCounterType) {
        _uiState.update { it.copy(prayerCounterType = type) }
    }

    fun setCoronillaStep(step: Int) {
        _uiState.update { it.copy(coronillaStep = step.coerceIn(0, 3)) }
    }

    fun nextCoronillaStep() {
        _uiState.update {
            val next = (it.coronillaStep + 1).coerceAtMost(3)
            if (next == 3 && !it.isCoronillaCompleted) {
                viewModelScope.launch {
                    repository.saveCompletedRosary(
                        mysteryType = "Coronilla de la Divina Misericordia",
                        intention = it.personalIntention.ifBlank { "Por la Misericordia del mundo entero" }
                    )
                }
                it.copy(coronillaStep = next, isCoronillaCompleted = true)
            } else {
                it.copy(coronillaStep = next)
            }
        }
    }

    fun prevCoronillaStep() {
        _uiState.update { it.copy(coronillaStep = (it.coronillaStep - 1).coerceAtLeast(0)) }
    }

    fun countCoronillaBead() {
        val current = _uiState.value.coronillaCount
        if (current < 10) {
            val next = current + 1
            _uiState.update { it.copy(coronillaCount = next) }
            triggerHaptic(isCompletion = next == 10)
        }
    }

    fun nextCoronillaDecade() {
        val current = _uiState.value.coronillaDecadeIndex
        if (current < 4) {
            _uiState.update {
                it.copy(
                    coronillaDecadeIndex = current + 1,
                    coronillaCount = 0
                )
            }
            triggerHaptic(isCompletion = false)
        } else {
            nextCoronillaStep()
        }
    }

    fun prevCoronillaDecade() {
        val current = _uiState.value.coronillaDecadeIndex
        if (current > 0) {
            _uiState.update {
                it.copy(
                    coronillaDecadeIndex = current - 1,
                    coronillaCount = 10
                )
            }
        }
    }

    fun countSantoDios() {
        val current = _uiState.value.coronillaSantoDiosCount
        if (current < 3) {
            val next = current + 1
            _uiState.update { it.copy(coronillaSantoDiosCount = next) }
            triggerHaptic(isCompletion = next == 3)
        }
    }

    fun resetCoronilla() {
        _uiState.update {
            it.copy(
                coronillaStep = 0,
                coronillaDecadeIndex = 0,
                coronillaCount = 0,
                coronillaSantoDiosCount = 0,
                isCoronillaCompleted = false
            )
        }
    }

    // --- Vía Crucis & Vía Lúcis ---
    fun setViacrucisSubTab(tab: ViacrucisSubTab) {
        _uiState.update { it.copy(viacrucisSubTab = tab) }
    }

    fun setViacrucisStation(index: Int) {
        _uiState.update { it.copy(viacrucisStationIndex = index.coerceIn(0, 14)) }
    }

    fun nextViacrucisStation() {
        val current = _uiState.value.viacrucisStationIndex
        if (current < 14) {
            val next = current + 1
            _uiState.update { it.copy(viacrucisStationIndex = next) }
            triggerHaptic(isCompletion = next == 14)
        }
    }

    fun prevViacrucisStation() {
        val current = _uiState.value.viacrucisStationIndex
        if (current > 0) {
            _uiState.update { it.copy(viacrucisStationIndex = current - 1) }
        }
    }

    fun toggleViacrucisStepByStep() {
        _uiState.update { it.copy(isViacrucisStepByStep = !it.isViacrucisStepByStep) }
    }

    fun setViaLucisStation(index: Int) {
        _uiState.update { it.copy(viaLucisStationIndex = index.coerceIn(0, 13)) }
    }

    fun nextViaLucisStation() {
        val current = _uiState.value.viaLucisStationIndex
        if (current < 13) {
            val next = current + 1
            _uiState.update { it.copy(viaLucisStationIndex = next) }
            triggerHaptic(isCompletion = next == 13)
        }
    }

    fun prevViaLucisStation() {
        val current = _uiState.value.viaLucisStationIndex
        if (current > 0) {
            _uiState.update { it.copy(viaLucisStationIndex = current - 1) }
        }
    }

    fun toggleViaLucisStepByStep() {
        _uiState.update { it.copy(isViaLucisStepByStep = !it.isViaLucisStepByStep) }
    }

    // --- Liturgia de las Horas ---
    fun setLiturgiaSubTab(tab: LiturgiaSubTab) {
        _uiState.update { it.copy(liturgiaSubTab = tab) }
    }

    fun selectHoraLiturgica(hora: com.example.data.HoraLiturgica) {
        _uiState.update { it.copy(selectedHoraLiturgica = hora) }
    }

    private fun triggerHaptic(isCompletion: Boolean) {
        if (!_uiState.value.hapticEnabled || vibrator == null || !vibrator.hasVibrator()) return
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (isCompletion) {
                    val timings = longArrayOf(0, 70, 70, 110)
                    val amplitudes = intArrayOf(0, 180, 0, 255)
                    vibrator.vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1))
                } else {
                    vibrator.vibrate(VibrationEffect.createOneShot(45, VibrationEffect.DEFAULT_AMPLITUDE))
                }
            } else {
                @Suppress("DEPRECATION")
                if (isCompletion) {
                    vibrator.vibrate(longArrayOf(0, 70, 70, 110), -1)
                } else {
                    vibrator.vibrate(45)
                }
            }
        } catch (_: Exception) {
            // Ignore if vibration not permitted
        }
    }
}
