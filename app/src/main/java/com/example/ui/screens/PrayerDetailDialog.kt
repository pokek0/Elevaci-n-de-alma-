package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PrayersData
import com.example.data.model.DevotionalPrayer
import com.example.ui.theme.DevotionalRose
import com.example.ui.theme.GuideColor
import com.example.ui.theme.ResponseColor
import com.example.ui.theme.SacredGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrayerDetailDialog(
    prayer: DevotionalPrayer,
    fontScale: Float,
    angelusCount: Int,
    angelusCompleted: Boolean,
    reginaStep: Int,
    reginaCompleted: Boolean,
    onIncrementAngelus: () -> Unit,
    onResetAngelus: () -> Unit,
    onAdvanceRegina: () -> Unit,
    onResetRegina: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAveMariaText by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 8.dp)
                .testTag("prayer_detail_sheet")
        ) {
            // Header with page number badge, title, close and copy buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = "DEVOCIONARIO • PÁGINA ${prayer.pageNumber}",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val fullText = buildString {
                                appendLine(prayer.title)
                                if (prayer.subtitle.isNotBlank()) appendLine(prayer.subtitle)
                                appendLine()
                                if (prayer.content.isNotBlank()) appendLine(prayer.content)
                                if (prayer.verses.isNotEmpty()) {
                                    prayer.verses.forEach {
                                        appendLine("${it.first}\n${it.second}\n")
                                    }
                                }
                                if (prayer.finalPrayer.isNotBlank()) appendLine(prayer.finalPrayer)
                            }
                            clipboard.setPrimaryClip(ClipData.newPlainText(prayer.title, fullText))
                            Toast.makeText(context, "Oración copiada al portapapeles", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copiar oración",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = prayer.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = (22 * fontScale).sp
            )

            if (prayer.subtitle.isNotBlank()) {
                Text(
                    text = prayer.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = (14 * fontScale).sp
                )
            }

            if (prayer.author.isNotBlank()) {
                Text(
                    text = "— ${prayer.author}",
                    style = MaterialTheme.typography.labelMedium,
                    fontStyle = FontStyle.Italic,
                    color = SacredGold,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(14.dp))

            // Scrollable Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 500.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Interactive Ángelus Mode
                if (prayer.isInteractiveAngelus) {
                    item {
                        InteractiveAngelusContent(
                            verses = prayer.verses,
                            finalPrayer = prayer.finalPrayer,
                            count = angelusCount,
                            isCompleted = angelusCompleted,
                            fontScale = fontScale,
                            showAveMariaText = showAveMariaText,
                            onToggleAveMaria = { showAveMariaText = !showAveMariaText },
                            onIncrement = onIncrementAngelus,
                            onReset = onResetAngelus
                        )
                    }
                }
                // Interactive Regina Coeli Mode
                else if (prayer.isInteractiveReginaCoeli) {
                    item {
                        InteractiveReginaCoeliContent(
                            verses = prayer.verses,
                            finalPrayer = prayer.finalPrayer,
                            step = reginaStep,
                            isCompleted = reginaCompleted,
                            fontScale = fontScale,
                            onAdvance = onAdvanceRegina,
                            onReset = onResetRegina
                        )
                    }
                }
                // Standard Prayer Reading Mode (El Puente, Magníficat, Consagración, Padre Chino, etc.)
                else {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
                        ) {
                            Text(
                                text = prayer.content,
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = (16 * fontScale).sp,
                                lineHeight = (26 * fontScale).sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(18.dp)
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun InteractiveAngelusContent(
    verses: List<Pair<String, String>>,
    finalPrayer: String,
    count: Int,
    isCompleted: Boolean,
    fontScale: Float,
    showAveMariaText: Boolean,
    onToggleAveMaria: () -> Unit,
    onIncrement: () -> Unit,
    onReset: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Tracker Banner
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "ORACIÓN INTERACTIVA",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.1.sp
                    )
                    Text(
                        text = "3 Avemarías con sus versículos",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // 3 mini beads
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    for (i in 1..3) {
                        val isDone = i <= count
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(if (isDone) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isDone) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Hecho",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            } else {
                                Text(
                                    text = i.toString(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3 Versicle & Response Sections
        for (i in 0 until minOf(3, verses.size)) {
            val (v, r) = verses[i]
            val isCurrentOrPast = count >= i
            val isChecked = count > i

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isChecked) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = if (count == i) 1.5.dp else 1.dp,
                    color = if (count == i) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${i + 1}. ",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = v,
                            fontWeight = FontWeight.SemiBold,
                            color = GuideColor,
                            fontSize = (15 * fontScale).sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = r,
                        fontWeight = FontWeight.Medium,
                        color = ResponseColor,
                        fontSize = (15 * fontScale).sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    ) {
                        Text(
                            text = if (isChecked) "✓ Avemaría rezada" else "• Se reza el Ave María",
                            style = MaterialTheme.typography.labelSmall,
                            fontStyle = FontStyle.Italic,
                            color = if (isChecked) Color(0xFF16A34A) else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }

        // Tap to count Avemaría button
        if (!isCompleted) {
            Button(
                onClick = onIncrement,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Rezar Avemaría (${count + 1}/3)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        // 4th Versicle: Ruega por nosotros...
        if (verses.size > 3) {
            val (v4, r4) = verses[3]
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(text = v4, fontWeight = FontWeight.SemiBold, color = GuideColor, fontSize = (15 * fontScale).sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = r4, fontWeight = FontWeight.Medium, color = ResponseColor, fontSize = (15 * fontScale).sp)
                }
            }
        }

        // Final Oremos
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
        ) {
            Text(
                text = finalPrayer,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = (15 * fontScale).sp,
                lineHeight = (23 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (isCompleted) {
            Button(
                onClick = onReset,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reiniciar")
                Spacer(modifier = Modifier.width(6.dp))
                Text("Rezar el Ángelus de nuevo")
            }
        }
    }
}

@Composable
private fun InteractiveReginaCoeliContent(
    verses: List<Pair<String, String>>,
    finalPrayer: String,
    step: Int,
    isCompleted: Boolean,
    fontScale: Float,
    onAdvance: () -> Unit,
    onReset: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        verses.forEachIndexed { index, (v, r) ->
            val isPast = step > index
            val isCurrent = step == index

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isPast) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f) else MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = if (isCurrent) 1.5.dp else 1.dp,
                    color = if (isCurrent) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = v,
                        fontWeight = FontWeight.SemiBold,
                        color = GuideColor,
                        fontSize = (15 * fontScale).sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = r,
                        fontWeight = FontWeight.Medium,
                        color = ResponseColor,
                        fontSize = (15 * fontScale).sp
                    )
                }
            }
        }

        if (!isCompleted) {
            Button(
                onClick = onAdvance,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Avanzar aleluya (${step + 1}/${verses.size})", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Avanzar")
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Text(
                text = finalPrayer,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = (15 * fontScale).sp,
                lineHeight = (23 * fontScale).sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (isCompleted) {
            Button(
                onClick = onReset,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reiniciar")
                Spacer(modifier = Modifier.width(6.dp))
                Text("Rezar Regina Coeli de nuevo")
            }
        }
    }
}
