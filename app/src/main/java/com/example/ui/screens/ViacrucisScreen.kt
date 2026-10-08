package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ViacrucisData
import com.example.data.ViacrucisStation
import com.example.ui.components.PrayerCard
import com.example.ui.theme.DevotionalRose
import com.example.ui.theme.GuideColor
import com.example.ui.theme.ResponseColor
import com.example.ui.theme.SacredGold

@Composable
fun ViacrucisScreen(
    currentStationIndex: Int,
    isStepByStep: Boolean,
    fontScale: Float,
    onSelectStation: (Int) -> Unit,
    onNextStation: () -> Unit,
    onPrevStation: () -> Unit,
    onToggleMode: () -> Unit,
    modifier: Modifier = Modifier
) {
    val totalStations = ViacrucisData.stations.size
    val currentStation = ViacrucisData.stations.getOrElse(currentStationIndex) { ViacrucisData.stations.first() }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 10.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Mode & Station Header
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = DevotionalRose.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "SANTO VÍA CRUCIS",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                color = DevotionalRose,
                                letterSpacing = 1.1.sp
                            )
                        }

                        // Toggle Mode button
                        OutlinedButton(
                            onClick = onToggleMode,
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = if (isStepByStep) Icons.Default.FormatListBulleted else Icons.Default.Layers,
                                contentDescription = "Modo",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isStepByStep) "Ver Lista" else "Paso a Paso",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isStepByStep) "Estación ${currentStation.romanNumeral} de ${ViacrucisData.stations.size}" else "Las 14 Estaciones y Resurrección",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = { ((currentStationIndex + 1).toFloat() / totalStations).coerceIn(0f, 1f) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(CircleShape),
                        color = DevotionalRose,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        // Horizontal Stations Selector Bar
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ViacrucisData.stations) { st ->
                    val isSelected = st.number - 1 == currentStationIndex
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) DevotionalRose else MaterialTheme.colorScheme.surfaceVariant,
                        border = if (isSelected) BorderStroke(1.5.dp, DevotionalRose) else null,
                        modifier = Modifier
                            .clickable { onSelectStation(st.number - 1) }
                    ) {
                        Text(
                            text = st.romanNumeral,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Normal,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Preparatory Prayer (shown on station 1 in step-by-step or top of list)
        if (!isStepByStep || currentStationIndex == 0) {
            item {
                PrayerCard(
                    title = "Oración Preparatoria",
                    fontScale = fontScale,
                    testTag = "viacrucis_oracion_preparatoria"
                ) {
                    Text(
                        text = ViacrucisData.ORACION_PREPARATORIA,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = (15 * fontScale).sp,
                        lineHeight = (23 * fontScale).sp
                    )
                }
            }
        }

        if (isStepByStep) {
            // STEP BY STEP SINGLE STATION VIEW
            item {
                StationCardView(
                    station = currentStation,
                    fontScale = fontScale
                )
            }

            // Navigation Buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onPrevStation,
                        enabled = currentStationIndex > 0,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Anterior")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Estación Anterior")
                    }

                    Button(
                        onClick = onNextStation,
                        enabled = currentStationIndex < totalStations - 1,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DevotionalRose)
                    ) {
                        Text("Siguiente")
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Siguiente")
                    }
                }
            }

            if (currentStationIndex == totalStations - 1) {
                // Final Prayer at last station
                item {
                    PrayerCard(
                        title = "Oración Final del Vía Crucis",
                        fontScale = fontScale
                    ) {
                        Text(
                            text = ViacrucisData.ORACION_FINAL,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (24 * fontScale).sp
                        )
                    }
                }
            }
        } else {
            // FULL LIST MODE
            items(ViacrucisData.stations) { st ->
                StationCardView(
                    station = st,
                    fontScale = fontScale
                )
            }

            item {
                PrayerCard(
                    title = "Oración Final del Vía Crucis",
                    fontScale = fontScale
                ) {
                    Text(
                        text = ViacrucisData.ORACION_FINAL,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = (16 * fontScale).sp,
                        lineHeight = (24 * fontScale).sp
                    )
                }
            }
        }
    }
}

@Composable
fun StationCardView(
    station: ViacrucisStation,
    fontScale: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Roman numeral badge and Station Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = DevotionalRose
                ) {
                    Text(
                        text = station.romanNumeral,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                }

                Text(
                    text = station.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = (17 * fontScale).sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Traditional Invocation
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "V. ${ViacrucisData.INVOCACION_GUIA}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = GuideColor,
                        fontSize = (14 * fontScale).sp
                    )
                    Text(
                        text = "R. ${ViacrucisData.INVOCACION_TODOS}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = ResponseColor,
                        fontSize = (14 * fontScale).sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Scripture
            Text(
                text = "Palabra de Dios (${station.scriptureCitation})",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = station.scriptureText,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                fontSize = (15 * fontScale).sp,
                lineHeight = (22 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Meditation
            Text(
                text = "Meditación",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = SacredGold,
                letterSpacing = 1.1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = station.meditation,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = (15 * fontScale).sp,
                lineHeight = (23 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Prayer
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {
                    Text(
                        text = "Oración:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = station.prayer,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = (15 * fontScale).sp,
                        lineHeight = (22 * fontScale).sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Jaculatoria
            Text(
                text = ViacrucisData.JACULATORIA_FINAL_ESTACION,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
