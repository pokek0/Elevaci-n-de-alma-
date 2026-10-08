package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.VolunteerActivism
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DevotionalRose
import com.example.ui.viewmodel.PrayerCounterType
import com.example.ui.viewmodel.RosaryViewModel

@Composable
fun RosarioCoronillaContainerScreen(
    viewModel: RosaryViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        // Top Tab Selector
        TabRow(
            selectedTabIndex = if (uiState.prayerCounterType == PrayerCounterType.ROSARIO) 0 else 1,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                val tabIdx = if (uiState.prayerCounterType == PrayerCounterType.ROSARIO) 0 else 1
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIdx]),
                    color = if (uiState.prayerCounterType == PrayerCounterType.ROSARIO) MaterialTheme.colorScheme.primary else DevotionalRose
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(
                selected = uiState.prayerCounterType == PrayerCounterType.ROSARIO,
                onClick = { viewModel.setPrayerCounterType(PrayerCounterType.ROSARIO) },
                text = {
                    Text(
                        text = "Santo Rosario",
                        fontWeight = if (uiState.prayerCounterType == PrayerCounterType.ROSARIO) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
            )

            Tab(
                selected = uiState.prayerCounterType == PrayerCounterType.CORONILLA,
                onClick = { viewModel.setPrayerCounterType(PrayerCounterType.CORONILLA) },
                text = {
                    Text(
                        text = "Coronilla de la Misericordia",
                        fontWeight = if (uiState.prayerCounterType == PrayerCounterType.CORONILLA) FontWeight.Bold else FontWeight.Normal,
                        color = if (uiState.prayerCounterType == PrayerCounterType.CORONILLA) DevotionalRose else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.sp
                    )
                }
            )
        }

        when (uiState.prayerCounterType) {
            PrayerCounterType.ROSARIO -> {
                RosaryScreen(viewModel = viewModel)
            }
            PrayerCounterType.CORONILLA -> {
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
        }
    }
}
