package com.example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DevotionalRose
import com.example.ui.theme.SacredGold
import com.example.ui.viewmodel.RosaryViewModel
import com.example.ui.viewmodel.UnifiedRezoTab

@Composable
fun RezosUnificadosScreen(
    viewModel: RosaryViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val tabs = listOf(
        Pair(UnifiedRezoTab.ROSARIO, "Santo Rosario"),
        Pair(UnifiedRezoTab.CORONILLA, "La Coronilla"),
        Pair(UnifiedRezoTab.VIACRUCIS, "Vía Crucis"),
        Pair(UnifiedRezoTab.VIA_LUCIS, "Vía Lúcis")
    )

    val selectedIndex = tabs.indexOfFirst { it.first == uiState.unifiedRezoTab }.coerceAtLeast(0)

    Column(modifier = modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            edgePadding = 12.dp,
            indicator = { tabPositions ->
                val indicatorColor = when (uiState.unifiedRezoTab) {
                    UnifiedRezoTab.ROSARIO -> MaterialTheme.colorScheme.primary
                    UnifiedRezoTab.CORONILLA -> DevotionalRose
                    UnifiedRezoTab.VIACRUCIS -> DevotionalRose
                    UnifiedRezoTab.VIA_LUCIS -> SacredGold
                }
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    color = indicatorColor
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, (tab, label) ->
                val isSelected = index == selectedIndex
                Tab(
                    selected = isSelected,
                    onClick = { viewModel.setUnifiedRezoTab(tab) },
                    text = {
                        Text(
                            text = label,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp,
                            color = when {
                                !isSelected -> MaterialTheme.colorScheme.onSurfaceVariant
                                tab == UnifiedRezoTab.CORONILLA || tab == UnifiedRezoTab.VIACRUCIS -> DevotionalRose
                                tab == UnifiedRezoTab.VIA_LUCIS -> SacredGold
                                else -> MaterialTheme.colorScheme.primary
                            }
                        )
                    }
                )
            }
        }

        when (uiState.unifiedRezoTab) {
            UnifiedRezoTab.ROSARIO -> {
                RosaryScreen(viewModel = viewModel)
            }
            UnifiedRezoTab.CORONILLA -> {
                CoronillaScreen(
                    step = uiState.coronillaStep,
                    decadeIndex = uiState.coronillaDecadeIndex,
                    beadCount = uiState.coronillaCount,
                    santoDiosCount = uiState.coronillaSantoDiosCount,
                    fontScale = uiState.fontScale,
                    onStepChange = { viewModel.setCoronillaStep(it) },
                    onNextStep = { viewModel.nextCoronillaStep() },
                    onPrevStep = { viewModel.prevCoronillaStep() },
                    onCountBead = { viewModel.countCoronillaBead() },
                    onSelectBead = { viewModel.countCoronillaBead() },
                    onNextDecade = { viewModel.nextCoronillaDecade() },
                    onPrevDecade = { viewModel.prevCoronillaDecade() },
                    onCountSantoDios = { viewModel.countSantoDios() },
                    onReset = { viewModel.resetCoronilla() }
                )
            }
            UnifiedRezoTab.VIACRUCIS -> {
                ViacrucisScreen(
                    currentStationIndex = uiState.viacrucisStationIndex,
                    isStepByStep = uiState.isViacrucisStepByStep,
                    fontScale = uiState.fontScale,
                    onSelectStation = { viewModel.setViacrucisStation(it) },
                    onNextStation = { viewModel.nextViacrucisStation() },
                    onPrevStation = { viewModel.prevViacrucisStation() },
                    onToggleMode = { viewModel.toggleViacrucisStepByStep() }
                )
            }
            UnifiedRezoTab.VIA_LUCIS -> {
                ViaLucisScreen(
                    currentStationIndex = uiState.viaLucisStationIndex,
                    isStepByStep = uiState.isViaLucisStepByStep,
                    fontScale = uiState.fontScale,
                    onSelectStation = { viewModel.setViaLucisStation(it) },
                    onNextStation = { viewModel.nextViaLucisStation() },
                    onPrevStation = { viewModel.prevViaLucisStation() },
                    onToggleMode = { viewModel.toggleViaLucisStepByStep() }
                )
            }
        }
    }
}
