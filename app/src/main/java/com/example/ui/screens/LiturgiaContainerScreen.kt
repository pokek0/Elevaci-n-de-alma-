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
import com.example.ui.viewmodel.LiturgiaSubTab
import com.example.ui.viewmodel.RosaryViewModel

@Composable
fun LiturgiaContainerScreen(
    viewModel: RosaryViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = if (uiState.liturgiaSubTab == LiturgiaSubTab.HORAS) 0 else 1,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                val tabIdx = if (uiState.liturgiaSubTab == LiturgiaSubTab.HORAS) 0 else 1
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIdx]),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(
                selected = uiState.liturgiaSubTab == LiturgiaSubTab.HORAS,
                onClick = { viewModel.setLiturgiaSubTab(LiturgiaSubTab.HORAS) },
                text = {
                    Text(
                        text = "Liturgia de las Horas",
                        fontWeight = if (uiState.liturgiaSubTab == LiturgiaSubTab.HORAS) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 13.sp
                    )
                }
            )

            Tab(
                selected = uiState.liturgiaSubTab == LiturgiaSubTab.LECTURAS_CEM,
                onClick = { viewModel.setLiturgiaSubTab(LiturgiaSubTab.LECTURAS_CEM) },
                text = {
                    Text(
                        text = "Lecturas CEM y Reflexión",
                        fontWeight = if (uiState.liturgiaSubTab == LiturgiaSubTab.LECTURAS_CEM) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 13.sp
                    )
                }
            )
        }

        when (uiState.liturgiaSubTab) {
            LiturgiaSubTab.HORAS -> {
                LiturgiaHorasScreen(
                    selectedHora = uiState.selectedHoraLiturgica,
                    onSelectHora = { viewModel.selectHoraLiturgica(it) },
                    fontScale = uiState.fontScale
                )
            }
            LiturgiaSubTab.LECTURAS_CEM -> {
                LecturasCemScreen(fontScale = uiState.fontScale)
            }
        }
    }
}
