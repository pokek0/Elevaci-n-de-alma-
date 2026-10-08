package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.HistoryDialog
import com.example.ui.components.IntentionDialog
import com.example.ui.components.LitaniesDialog
import com.example.ui.components.ProUpgradeDialog
import com.example.ui.components.ThemeSelectionDialog
import com.example.ui.theme.SacredGold
import com.example.ui.theme.ThemeMode
import com.example.ui.viewmodel.LiturgiaSubTab
import com.example.ui.viewmodel.MainTab
import com.example.ui.viewmodel.RosaryViewModel
import com.example.ui.viewmodel.UnifiedRezoTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(
    viewModel: RosaryViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val historyRecords by viewModel.historyRecords.collectAsState()
    val completedCount by viewModel.completedRosariesCount.collectAsState()
    val customPrayers by viewModel.customPrayers.collectAsState()
    val todayScheduleChecks by viewModel.todayScheduleChecks.collectAsState()

    // Back handling: if reading a prayer, close it; if on a sub-step/screen, return to Portada
    BackHandler(
        enabled = uiState.selectedDevotionalPrayer != null ||
                (uiState.currentTab == MainTab.REZOS && uiState.unifiedRezoTab == UnifiedRezoTab.ROSARIO && uiState.step > 0) ||
                (uiState.currentTab == MainTab.REZOS && uiState.unifiedRezoTab == UnifiedRezoTab.CORONILLA && uiState.coronillaStep > 0) ||
                uiState.currentTab != MainTab.PORTADA
    ) {
        if (uiState.selectedDevotionalPrayer != null) {
            viewModel.closePrayer()
        } else if (uiState.currentTab == MainTab.REZOS && uiState.unifiedRezoTab == UnifiedRezoTab.ROSARIO && uiState.step > 0) {
            viewModel.prevStep()
        } else if (uiState.currentTab == MainTab.REZOS && uiState.unifiedRezoTab == UnifiedRezoTab.CORONILLA && uiState.coronillaStep > 0) {
            viewModel.prevCoronillaStep()
        } else {
            viewModel.setTab(MainTab.PORTADA)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Mi devocionario",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            // Liturgical Color indicator pill in header
                            Surface(
                                shape = CircleShape,
                                color = uiState.liturgicalColor.badgeColor,
                                modifier = Modifier
                                    .clickable { viewModel.setShowThemeDialog(true) }
                                    .testTag("liturgical_color_badge")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(5.dp)
                                            .clip(CircleShape)
                                            .background(Color.White)
                                    )
                                    Text(
                                        text = uiState.liturgicalColor.title.uppercase(),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 9.sp
                                    )
                                }
                            }
                        }

                        val subtitle = when (uiState.currentTab) {
                            MainTab.PORTADA -> "ELEVA TU ALMA AL CIELO • ${uiState.liturgicalColor.description.uppercase()}"
                            MainTab.REZOS -> when (uiState.unifiedRezoTab) {
                                UnifiedRezoTab.ROSARIO -> "SANTO ROSARIO • ${uiState.mysteryType.title.uppercase()}"
                                UnifiedRezoTab.CORONILLA -> "CORONILLA DE LA MISERICORDIA"
                                UnifiedRezoTab.VIACRUCIS -> "SANTO VÍA CRUCIS"
                                UnifiedRezoTab.VIA_LUCIS -> "SANTO VÍA LÚCIS"
                            }
                            MainTab.HORARIO -> "JORNADA DE ORACIÓN DIARIA"
                            MainTab.LITURGIA -> if (uiState.liturgiaSubTab == LiturgiaSubTab.HORAS) "LITURGIA DE LAS HORAS • ${uiState.selectedHoraLiturgica.title.uppercase()}" else "LECTURAS CEM Y REFLEXIÓN"
                            MainTab.DEVOCIONARIO -> "DEVOCIONARIO CATÓLICO"
                            MainTab.MIS_ORACIONES -> "MIS ORACIONES PROPIAS"
                        }
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.1.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    // Pro Status / Upgrade button
                    IconButton(
                        onClick = {
                            viewModel.setShowProDialog(
                                true,
                                if (uiState.isProUser) {
                                    "¡Eres miembro PRO! Tienes acceso completo e ilimitado de por vida al Horario de Oración y a Mis Oraciones."
                                } else {
                                    "Desbloquea el Horario de Oración Diaria y la creación de tus Oraciones Propias con un pago único de $1.00 USD."
                                }
                            )
                        },
                        modifier = Modifier.testTag("action_pro_status")
                    ) {
                        Icon(
                            imageVector = Icons.Default.WorkspacePremium,
                            contentDescription = if (uiState.isProUser) "Miembro PRO Activo" else "Desbloquear PRO",
                            tint = if (uiState.isProUser) SacredGold else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Text size cycle
                    IconButton(
                        onClick = { viewModel.cycleFontScale() },
                        modifier = Modifier.testTag("action_font_size")
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatSize,
                            contentDescription = "Tamaño de letra",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Prayer History
                    IconButton(
                        onClick = { viewModel.setShowHistoryDialog(true) },
                        modifier = Modifier.testTag("action_history")
                    ) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = "Historial de Rosarios",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Theme & Liturgical Color selector
                    IconButton(
                        onClick = { viewModel.setShowThemeDialog(true) },
                        modifier = Modifier.testTag("action_theme_toggle")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = "Tema y Color Litúrgico",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.navigationBarsPadding(),
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                // 1. Portada / Inicio
                NavigationBarItem(
                    selected = uiState.currentTab == MainTab.PORTADA,
                    onClick = { viewModel.setTab(MainTab.PORTADA) },
                    icon = { Icon(Icons.Default.Book, contentDescription = "Portada") },
                    label = { Text("Inicio", fontSize = 10.sp, maxLines = 1, fontWeight = if (uiState.currentTab == MainTab.PORTADA) FontWeight.Bold else FontWeight.Normal) },
                    modifier = Modifier.testTag("tab_portada")
                )

                // 2. Rezos Unificados (Rosario, Coronilla, Viacrucis, Via Lucis)
                NavigationBarItem(
                    selected = uiState.currentTab == MainTab.REZOS,
                    onClick = { viewModel.setTab(MainTab.REZOS) },
                    icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "Rezos y Devociones") },
                    label = { Text("Rezos", fontSize = 10.sp, maxLines = 1, fontWeight = if (uiState.currentTab == MainTab.REZOS) FontWeight.Bold else FontWeight.Normal) },
                    modifier = Modifier.testTag("tab_rezos")
                )

                // 3. Horario Diario (PRO)
                NavigationBarItem(
                    selected = uiState.currentTab == MainTab.HORARIO,
                    onClick = {
                        viewModel.requestFeatureAccess(com.example.data.model.PremiumFeature.HORARIO_ORACION) {
                            viewModel.setTab(MainTab.HORARIO)
                        }
                    },
                    icon = { Icon(Icons.Default.Schedule, contentDescription = "Horario Diario") },
                    label = {
                        Text(
                            text = if (uiState.isProUser) "Horario" else "Horario 🔒",
                            fontSize = 10.sp,
                            maxLines = 1,
                            fontWeight = if (uiState.currentTab == MainTab.HORARIO) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("tab_horario")
                )

                // 4. Mis Oraciones Propias (PRO) - In Bottom Bar as requested
                NavigationBarItem(
                    selected = uiState.currentTab == MainTab.MIS_ORACIONES,
                    onClick = {
                        viewModel.requestFeatureAccess(com.example.data.model.PremiumFeature.ORACIONES_PERSONALIZADAS) {
                            viewModel.setTab(MainTab.MIS_ORACIONES)
                        }
                    },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Mis Oraciones") },
                    label = {
                        Text(
                            text = if (uiState.isProUser) "Oraciones" else "Oraciones 🔒",
                            fontSize = 10.sp,
                            maxLines = 1,
                            fontWeight = if (uiState.currentTab == MainTab.MIS_ORACIONES) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("tab_mis_oraciones")
                )

                // 5. Liturgia (Horas y Lecturas CEM)
                NavigationBarItem(
                    selected = uiState.currentTab == MainTab.LITURGIA,
                    onClick = { viewModel.setTab(MainTab.LITURGIA) },
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "Liturgia y Lecturas") },
                    label = { Text("Liturgia", fontSize = 10.sp, maxLines = 1, fontWeight = if (uiState.currentTab == MainTab.LITURGIA) FontWeight.Bold else FontWeight.Normal) },
                    modifier = Modifier.testTag("tab_liturgia")
                )

                // 6. Devocionario Católico Tradicional
                NavigationBarItem(
                    selected = uiState.currentTab == MainTab.DEVOCIONARIO,
                    onClick = { viewModel.setTab(MainTab.DEVOCIONARIO) },
                    icon = { Icon(Icons.Default.Bookmark, contentDescription = "Devocionario Católico") },
                    label = { Text("Devocionario", fontSize = 10.sp, maxLines = 1, fontWeight = if (uiState.currentTab == MainTab.DEVOCIONARIO) FontWeight.Bold else FontWeight.Normal) },
                    modifier = Modifier.testTag("tab_devocionario")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.currentTab) {
                MainTab.PORTADA -> {
                    PortadaScreen(
                        todayMystery = uiState.mysteryType,
                        isProUser = uiState.isProUser,
                        liturgicalColor = uiState.liturgicalColor,
                        onOpenProUpgrade = { prompt ->
                            viewModel.setShowProDialog(true, prompt)
                        },
                        onNavigateToUnifiedRezos = { tab ->
                            viewModel.setUnifiedRezoTab(tab)
                            viewModel.setTab(MainTab.REZOS)
                        },
                        onNavigateToHorario = {
                            viewModel.requestFeatureAccess(com.example.data.model.PremiumFeature.HORARIO_ORACION) {
                                viewModel.setTab(MainTab.HORARIO)
                            }
                        },
                        onNavigateToLiturgia = {
                            viewModel.setTab(MainTab.LITURGIA)
                            viewModel.setLiturgiaSubTab(LiturgiaSubTab.HORAS)
                        },
                        onNavigateToLecturasCem = {
                            viewModel.setTab(MainTab.LITURGIA)
                            viewModel.setLiturgiaSubTab(LiturgiaSubTab.LECTURAS_CEM)
                        },
                        onNavigateToDevocionario = { viewModel.setTab(MainTab.DEVOCIONARIO) },
                        onNavigateToMisOraciones = {
                            viewModel.requestFeatureAccess(com.example.data.model.PremiumFeature.ORACIONES_PERSONALIZADAS) {
                                viewModel.setTab(MainTab.MIS_ORACIONES)
                            }
                        },
                        onOpenPrayer = { viewModel.openPrayer(it) },
                        fontScale = uiState.fontScale
                    )
                }
                MainTab.REZOS -> {
                    RezosUnificadosScreen(
                        viewModel = viewModel
                    )
                }
                MainTab.HORARIO -> {
                    HorarioDiarioScreen(
                        scheduleChecks = todayScheduleChecks,
                        isProUser = uiState.isProUser,
                        onRequestUpgrade = {
                            viewModel.showPremiumInvite(com.example.data.model.PremiumFeature.HORARIO_ORACION)
                        },
                        onToggleSlot = { slotId, current -> viewModel.toggleScheduleSlot(slotId, current) },
                        onOpenRosary = {
                            viewModel.setTab(MainTab.REZOS)
                            viewModel.setUnifiedRezoTab(UnifiedRezoTab.ROSARIO)
                        },
                        onOpenCoronilla = {
                            viewModel.setTab(MainTab.REZOS)
                            viewModel.setUnifiedRezoTab(UnifiedRezoTab.CORONILLA)
                        },
                        onOpenLiturgia = { hora ->
                            viewModel.setTab(MainTab.LITURGIA)
                            viewModel.setLiturgiaSubTab(LiturgiaSubTab.HORAS)
                            viewModel.selectHoraLiturgica(hora)
                        },
                        fontScale = uiState.fontScale
                    )
                }
                MainTab.LITURGIA -> {
                    LiturgiaContainerScreen(
                        viewModel = viewModel
                    )
                }
                MainTab.DEVOCIONARIO -> {
                    DevocionarioScreen(
                        searchQuery = uiState.devocionarioSearch,
                        selectedCategory = uiState.devocionarioCategory,
                        onSearchChange = { viewModel.setDevocionarioSearch(it) },
                        onCategoryChange = { viewModel.setDevocionarioCategory(it) },
                        onPrayerClick = { viewModel.openPrayer(it) },
                        fontScale = uiState.fontScale
                    )
                }
                MainTab.MIS_ORACIONES -> {
                    MisOracionesScreen(
                        customPrayers = customPrayers,
                        isProUser = uiState.isProUser,
                        onRequestUpgrade = {
                            viewModel.showPremiumInvite(com.example.data.model.PremiumFeature.ORACIONES_PERSONALIZADAS)
                        },
                        onAddPrayer = { viewModel.addCustomPrayer(it) },
                        onUpdatePrayer = { viewModel.updateCustomPrayer(it) },
                        onDeletePrayer = { viewModel.deleteCustomPrayer(it) },
                        fontScale = uiState.fontScale
                    )
                }
            }
        }
    }

    // Interactive Prayer Detail Dialog (Angelus, Regina Coeli, El Puente, etc.)
    uiState.selectedDevotionalPrayer?.let { prayer ->
        PrayerDetailDialog(
            prayer = prayer,
            fontScale = uiState.fontScale,
            angelusCount = uiState.angelusCount,
            angelusCompleted = uiState.angelusCompleted,
            reginaStep = uiState.reginaCoeliStep,
            reginaCompleted = uiState.reginaCoeliCompleted,
            onIncrementAngelus = { viewModel.incrementAngelusCount() },
            onResetAngelus = { viewModel.resetAngelus() },
            onAdvanceRegina = { viewModel.advanceReginaCoeli() },
            onResetRegina = { viewModel.resetReginaCoeli() },
            onDismiss = { viewModel.closePrayer() }
        )
    }

    if (uiState.showHistoryDialog) {
        HistoryDialog(
            records = historyRecords,
            totalCompleted = completedCount,
            onDismiss = { viewModel.setShowHistoryDialog(false) },
            onDeleteRecord = { viewModel.deleteHistoryRecord(it) }
        )
    }

    if (uiState.showIntentionDialog) {
        IntentionDialog(
            currentIntention = uiState.personalIntention,
            onDismiss = { viewModel.setShowIntentionDialog(false) },
            onConfirm = { viewModel.setPersonalIntention(it) }
        )
    }

    if (uiState.showLitaniesDialog) {
        LitaniesDialog(
            onDismiss = { viewModel.setShowLitaniesDialog(false) },
            fontScale = uiState.fontScale
        )
    }

    if (uiState.showThemeDialog) {
        ThemeSelectionDialog(
            currentMode = uiState.themeMode,
            currentLiturgicalColor = uiState.liturgicalColor,
            isAutoLiturgicalColor = uiState.isAutoLiturgicalColor,
            onSelectMode = { mode ->
                viewModel.setThemeMode(mode)
            },
            onSelectLiturgicalColor = { color ->
                viewModel.setLiturgicalColor(color)
            },
            onSetAutoLiturgicalColor = { auto ->
                viewModel.setAutoLiturgicalColor(auto)
            },
            onDismiss = { viewModel.setShowThemeDialog(false) }
        )
    }

    if (uiState.showProDialog) {
        ProUpgradeDialog(
            featurePrompt = uiState.proFeaturePrompt,
            onUnlock = { viewModel.unlockProVersion() },
            onDismiss = { viewModel.setShowProDialog(false) }
        )
    }
}
