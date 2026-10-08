package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VolunteerActivism
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CoronillaMisericordiaData
import com.example.ui.components.PrayerCard
import com.example.ui.theme.DevotionalRose
import com.example.ui.theme.SacredGold

@Composable
fun CoronillaScreen(
    step: Int,
    decadeIndex: Int,
    beadCount: Int,
    santoDiosCount: Int,
    fontScale: Float,
    onStepChange: (Int) -> Unit,
    onNextStep: () -> Unit,
    onPrevStep: () -> Unit,
    onCountBead: () -> Unit,
    onSelectBead: (Int) -> Unit,
    onNextDecade: () -> Unit,
    onPrevDecade: () -> Unit,
    onCountSantoDios: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 10.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Step Progress Indicator
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
                                text = "DIVINA MISERICORDIA",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                color = DevotionalRose,
                                letterSpacing = 1.1.sp
                            )
                        }

                        IconButton(
                            onClick = onReset,
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reiniciar",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    val stepTitle = when (step) {
                        0 -> "Paso 1 de 4: Oraciones Iniciales"
                        1 -> "Paso 2 de 4: Decena ${decadeIndex + 1} de 5"
                        2 -> "Paso 3 de 4: Trisagio (Santo Dios x3)"
                        else -> "Paso 4 de 4: Oración Conclusiva"
                    }

                    Text(
                        text = stepTitle,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = {
                            when (step) {
                                0 -> 0.1f
                                1 -> 0.2f + (decadeIndex * 0.12f) + (beadCount * 0.012f)
                                2 -> 0.85f + (santoDiosCount * 0.04f)
                                else -> 1.0f
                            }
                        },
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

        when (step) {
            0 -> {
                // Initial Prayers
                item {
                    PrayerCard(
                        title = "1. Invocación Inicial",
                        fontScale = fontScale,
                        testTag = "coronilla_invocacion"
                    ) {
                        Text(
                            text = CoronillaMisericordiaData.INVOCACION_INICIAL,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (24 * fontScale).sp
                        )
                    }
                }

                item {
                    PrayerCard(
                        title = "2. Oración de las Tres de la Tarde",
                        fontScale = fontScale,
                        testTag = "coronilla_tres_tarde"
                    ) {
                        Text(
                            text = CoronillaMisericordiaData.ORACION_TRES_DE_LA_TARDE,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (24 * fontScale).sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = CoronillaMisericordiaData.JACULATORIA_SANGRE_Y_AGUA,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = DevotionalRose
                        )
                    }
                }

                item {
                    PrayerCard(
                        title = "3. Padre Nuestro",
                        fontScale = fontScale,
                        testTag = "coronilla_padre_nuestro"
                    ) {
                        Text(
                            text = CoronillaMisericordiaData.PADRE_NUESTRO,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (24 * fontScale).sp
                        )
                    }
                }

                item {
                    PrayerCard(
                        title = "4. Ave María",
                        fontScale = fontScale,
                        testTag = "coronilla_ave_maria"
                    ) {
                        Text(
                            text = CoronillaMisericordiaData.AVE_MARIA,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (24 * fontScale).sp
                        )
                    }
                }

                item {
                    PrayerCard(
                        title = "5. Credo de los Apóstoles",
                        fontScale = fontScale,
                        testTag = "coronilla_credo"
                    ) {
                        Text(
                            text = CoronillaMisericordiaData.CREDO,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (24 * fontScale).sp
                        )
                    }
                }

                item {
                    Button(
                        onClick = onNextStep,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .testTag("coronilla_btn_iniciar_decenas"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DevotionalRose)
                    ) {
                        Text("Iniciar las 5 Decenas", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Avanzar")
                    }
                }
            }

            1 -> {
                // The 5 Decades
                item {
                    // Decade selector chip row
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(5) { idx ->
                            val isSelected = idx == decadeIndex
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = if (isSelected) DevotionalRose else MaterialTheme.colorScheme.surfaceVariant,
                                border = if (isSelected) BorderStroke(1.5.dp, DevotionalRose) else null,
                                modifier = Modifier
                                    .clickable {
                                        // jump to decade
                                    }
                            ) {
                                Text(
                                    text = "Decena ${idx + 1}",
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                // Large Bead (Padre Eterno)
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                        ),
                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary
                            ) {
                                Text(
                                    text = "EN LA CUENTA GRANDE (PADRE NUESTRO)",
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = CoronillaMisericordiaData.CUENTA_GRANDE_PADRE_ETERNO,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = (16 * fontScale).sp,
                                lineHeight = (24 * fontScale).sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }

                // 10 Small Beads Counter (Por su dolorosa Pasión)
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.5.dp, DevotionalRose.copy(alpha = 0.4f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = DevotionalRose.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "EN LAS 10 CUENTAS PEQUEÑAS (X10)",
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = DevotionalRose
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "«${CoronillaMisericordiaData.CUENTA_PEQUENA_DECENA}»",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            // 10 Beads visualizer
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                for (i in 1..10) {
                                    val isCounted = i <= beadCount
                                    val isNext = i == beadCount + 1

                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(
                                                when {
                                                    isCounted -> DevotionalRose
                                                    isNext -> DevotionalRose.copy(alpha = 0.25f)
                                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                                }
                                            )
                                            .clickable { onSelectBead(i) },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (isCounted) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        } else {
                                            Text(
                                                text = "$i",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "$beadCount de 10 cuentas completadas",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = DevotionalRose
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            // Count Button
                            Button(
                                onClick = onCountBead,
                                enabled = beadCount < 10,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp)
                                    .testTag("coronilla_btn_contar_cuenta"),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = DevotionalRose)
                            ) {
                                Icon(imageVector = Icons.Default.VolunteerActivism, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (beadCount < 10) "Contar Cuenta (${beadCount + 1}/10)" else "¡Decena Completa!",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Next Decade or Next Step
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                if (decadeIndex > 0) onPrevDecade() else onPrevStep()
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Anterior")
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Anterior")
                        }

                        Button(
                            onClick = onNextDecade,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = DevotionalRose)
                        ) {
                            Text(if (decadeIndex < 4) "Decena ${decadeIndex + 2}" else "Ir a Santo Dios")
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Siguiente")
                        }
                    }
                }
            }

            2 -> {
                // Santo Dios Trisagion (x3)
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(2.dp, DevotionalRose.copy(alpha = 0.5f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(22.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = DevotionalRose
                            ) {
                                Text(
                                    text = "TRISAGIO FINAL (REPETIR 3 VECES)",
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "«${CoronillaMisericordiaData.SANTO_DIOS_TRISAGIO}»",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // 3 chips indicator
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                for (i in 1..3) {
                                    val isDone = i <= santoDiosCount
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (isDone) DevotionalRose else MaterialTheme.colorScheme.surfaceVariant,
                                        modifier = Modifier.size(52.dp, 44.dp),
                                        border = if (isDone) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            if (isDone) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.White
                                                )
                                            } else {
                                                Text(
                                                    text = "x$i",
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(18.dp))

                            Button(
                                onClick = onCountSantoDios,
                                enabled = santoDiosCount < 3,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = DevotionalRose)
                            ) {
                                Text(
                                    text = if (santoDiosCount < 3) "Contar Santo Dios (${santoDiosCount + 1}/3)" else "¡Trisagio Completado!",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                item {
                    Button(
                        onClick = onNextStep,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DevotionalRose)
                    ) {
                        Text("Oración Conclusiva", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Avanzar")
                    }
                }
            }

            3 -> {
                // Final celebration & promises
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = DevotionalRose.copy(alpha = 0.12f)
                        ),
                        border = BorderStroke(1.5.dp, DevotionalRose)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(DevotionalRose),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Completado",
                                    tint = Color.White,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "¡Coronilla Concluida!",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Has implorado la Divina Misericordia para ti y para el mundo entero.",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                item {
                    PrayerCard(
                        title = "Oración Conclusiva",
                        fontScale = fontScale
                    ) {
                        Text(
                            text = CoronillaMisericordiaData.ORACION_CONCLUSIVA,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (25 * fontScale).sp
                        )
                    }
                }

                item {
                    PrayerCard(
                        title = "Jaculatoria Final",
                        fontScale = fontScale
                    ) {
                        Text(
                            text = CoronillaMisericordiaData.JESUS_EN_TI_CONFIO,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black,
                            color = DevotionalRose,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.dp, SacredGold.copy(alpha = 0.5f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Text(
                                text = "PROMESAS DE JESÚS A SANTA FAUSTINA",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                color = SacredGold,
                                letterSpacing = 1.1.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CoronillaMisericordiaData.PROMESAS.forEach { promesa ->
                                Text(
                                    text = "• $promesa",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(bottom = 6.dp)
                                )
                            }
                        }
                    }
                }

                item {
                    Button(
                        onClick = onReset,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DevotionalRose)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Rezar de nuevo")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Rezar la Coronilla Nuevamente", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
