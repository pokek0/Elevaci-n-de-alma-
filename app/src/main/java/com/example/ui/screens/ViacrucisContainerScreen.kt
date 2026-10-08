package com.example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DevotionalRose
import com.example.ui.theme.SacredGold
import com.example.ui.viewmodel.RosaryViewModel
import com.example.ui.viewmodel.ViacrucisSubTab

@Composable
fun ViacrucisContainerScreen(
    viewModel: RosaryViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = if (uiState.viacrucisSubTab == ViacrucisSubTab.VIACRUCIS) 0 else 1,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                val tabIdx = if (uiState.viacrucisSubTab == ViacrucisSubTab.VIACRUCIS) 0 else 1
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIdx]),
                    color = if (uiState.viacrucisSubTab == ViacrucisSubTab.VIACRUCIS) DevotionalRose else SacredGold
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(
                selected = uiState.viacrucisSubTab == ViacrucisSubTab.VIACRUCIS,
                onClick = { viewModel.setViacrucisSubTab(ViacrucisSubTab.VIACRUCIS) },
                text = {
                    Text(
                        text = "Vía Crucis (14 Estaciones)",
                        fontWeight = if (uiState.viacrucisSubTab == ViacrucisSubTab.VIACRUCIS) FontWeight.Bold else FontWeight.Normal,
                        color = if (uiState.viacrucisSubTab == ViacrucisSubTab.VIACRUCIS) DevotionalRose else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                }
            )

            Tab(
                selected = uiState.viacrucisSubTab == ViacrucisSubTab.VIA_LUCIS,
                onClick = { viewModel.setViacrucisSubTab(ViacrucisSubTab.VIA_LUCIS) },
                text = {
                    Text(
                        text = "Vía Lúcis (Camino de la Luz)",
                        fontWeight = if (uiState.viacrucisSubTab == ViacrucisSubTab.VIA_LUCIS) FontWeight.Bold else FontWeight.Normal,
                        color = if (uiState.viacrucisSubTab == ViacrucisSubTab.VIA_LUCIS) SacredGold else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                }
            )
        }

        when (uiState.viacrucisSubTab) {
            ViacrucisSubTab.VIACRUCIS -> {
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
            ViacrucisSubTab.VIA_LUCIS -> {
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
