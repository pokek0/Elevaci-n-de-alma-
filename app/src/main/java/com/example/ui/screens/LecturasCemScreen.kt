package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CemReadingsData
import com.example.data.model.LiturgicalDay
import com.example.data.model.LiturgicalReading
import com.example.ui.theme.DevotionalRose
import com.example.ui.theme.GuideColor
import com.example.ui.theme.ResponseColor
import com.example.ui.theme.SacredGold

@Composable
fun LecturasCemScreen(
    fontScale: Float,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val days = CemReadingsData.liturgicalDays
    var selectedDayId by remember { mutableStateOf(days.first().id) }
    val currentDay = days.find { it.id == selectedDayId } ?: days.first()

    val liturgicalColorValue = when (currentDay.liturgicalColor.lowercase()) {
        "verde" -> Color(0xFF16A34A)
        "blanco" -> SacredGold
        "morado" -> Color(0xFF7C3AED)
        "rojo" -> Color(0xFFDC2626)
        else -> MaterialTheme.colorScheme.primary
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // CEM Banner Header
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("cem_header_card"),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.5.dp, liturgicalColorValue.copy(alpha = 0.5f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = liturgicalColorValue.copy(alpha = 0.15f)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(liturgicalColorValue)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Color: ${currentDay.liturgicalColor}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = liturgicalColorValue
                                )
                            }
                        }

                        IconButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val fullText = buildString {
                                    appendLine(currentDay.liturgicalTitle)
                                    appendLine(currentDay.dateDisplay)
                                    appendLine()
                                    currentDay.readings.forEach {
                                        appendLine("=== ${it.type} ===")
                                        appendLine(it.citation)
                                        appendLine(it.text)
                                        appendLine()
                                    }
                                    appendLine("=== REFLEXIÓN DEL DÍA (CEM) ===")
                                    appendLine(currentDay.reflectionTitle)
                                    appendLine(currentDay.reflectionText)
                                    appendLine()
                                    appendLine("Oración: ${currentDay.dailyPrayer}")
                                }
                                clipboard.setPrimaryClip(ClipData.newPlainText(currentDay.liturgicalTitle, fullText))
                                Toast.makeText(context, "Lecturas copiadas al portapapeles", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copiar lecturas",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = currentDay.liturgicalTitle,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = (20 * fontScale).sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Leccionario Oficial • ${currentDay.cycle}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Liturgical Day selector chips
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                items(days) { day ->
                    FilterChip(
                        selected = day.id == selectedDayId,
                        onClick = { selectedDayId = day.id },
                        label = { Text(day.dateDisplay, style = MaterialTheme.typography.bodySmall) }
                    )
                }
            }
        }

        // Each reading (Primera Lectura, Salmo, Evangelio)
        items(currentDay.readings) { reading ->
            ReadingCardItem(
                reading = reading,
                fontScale = fontScale,
                liturgicalColor = liturgicalColorValue
            )
        }

        // REFLEXIÓN Y MEDITACIÓN DEL CEM
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("cem_reflection_card"),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                ),
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SacredGold),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "Reflexión",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "REFLEXIÓN Y MEDITACIÓN DEL DÍA",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = SacredGold,
                                letterSpacing = 1.2.sp
                            )
                            Text(
                                text = "Según la Conferencia del Episcopado Mexicano",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = currentDay.reflectionTitle,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = (18 * fontScale).sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = currentDay.reflectionText,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = (16 * fontScale).sp,
                        lineHeight = (26 * fontScale).sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (currentDay.reflectionQuestions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "Preguntas para meditar:",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                currentDay.reflectionQuestions.forEach { q ->
                                    Text(
                                        text = "• $q",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontSize = (14 * fontScale).sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Oración Conclusiva del Día",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall,
                        color = DevotionalRose
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = currentDay.dailyPrayer,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        fontSize = (15 * fontScale).sp,
                        lineHeight = (24 * fontScale).sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
private fun ReadingCardItem(
    reading: LiturgicalReading,
    fontScale: Float,
    liturgicalColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Header tag
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = reading.type,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Text(
                    text = reading.citation,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = liturgicalColor
                )
            }

            if (reading.subtitle.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = reading.subtitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = (17 * fontScale).sp
                )
            }

            if (reading.response.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = reading.response,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = DevotionalRose,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = (15 * fontScale).sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = reading.text,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = (16 * fontScale).sp,
                lineHeight = (26 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (reading.closing.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = reading.closing,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = (15 * fontScale).sp
                )
            }
        }
    }
}
