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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ScheduleActionType
import com.example.data.ScheduleData
import com.example.data.ScheduleSlot
import com.example.data.model.ScheduleCheck
import com.example.ui.components.PrayerCard
import com.example.ui.theme.SacredGold

@Composable
fun HorarioDiarioScreen(
    scheduleChecks: List<ScheduleCheck>,
    isProUser: Boolean = false,
    onRequestUpgrade: () -> Unit = {},
    onToggleSlot: (String, Boolean) -> Unit,
    onOpenRosary: () -> Unit,
    onOpenCoronilla: () -> Unit,
    onOpenLiturgia: (com.example.data.HoraLiturgica) -> Unit,
    fontScale: Float,
    modifier: Modifier = Modifier
) {
    val currentHour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    val completedCount = scheduleChecks.count { it.isCompleted }
    val totalSlots = ScheduleData.slots.size
    val progressFraction = if (totalSlots > 0) completedCount.toFloat() / totalSlots else 0f

    val calendar = java.util.Calendar.getInstance()
    val dayFormat = java.text.SimpleDateFormat("EEEE, d 'de' MMMM", java.util.Locale("es", "ES"))
    val dateString = dayFormat.format(calendar.time).replaceFirstChar { it.uppercase() }

    var slotToRead: ScheduleSlot? = null
    androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf<ScheduleSlot?>(null) }.let { state ->
        slotToRead = state.value

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header: Today's date and summary
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("schedule_header_card"),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Surface(
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.primary
                                ) {
                                    Text(
                                        text = "HORARIO DIARIO DE ORACIÓN",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Black,
                                        color = Color.White,
                                        letterSpacing = 1.1.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = dateString,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Black,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(CircleShape)
                                    .background(SacredGold.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Schedule,
                                    contentDescription = "Horario",
                                    tint = SacredGold,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Progress status
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Progreso de hoy: $completedCount de $totalSlots momentos",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "${(progressFraction * 100).toInt()}%",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        LinearProgressIndicator(
                            progress = { progressFraction },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(CircleShape),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )

                        if (completedCount == totalSlots) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "✨ ¡Bendita jornada! Has completado todas tus oraciones de hoy.",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color(0xFF16A34A),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Premium Invite Banner (when not Pro user)
            if (!isProUser) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onRequestUpgrade() }
                            .testTag("schedule_premium_invite_card"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = SacredGold.copy(alpha = 0.12f)
                        ),
                        border = BorderStroke(1.5.dp, SacredGold)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.WorkspacePremium,
                                        contentDescription = "Premium",
                                        tint = SacredGold,
                                        modifier = Modifier.size(22.dp)
                                    )
                                    Text(
                                        text = "FUNCIÓN PREMIUM • PAGO ÚNICO",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Black,
                                        color = SacredGold,
                                        letterSpacing = 1.1.sp
                                    )
                                }
                                Surface(
                                    shape = CircleShape,
                                    color = SacredGold
                                ) {
                                    Text(
                                        text = "$1.00 USD",
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Black,
                                        color = Color.White
                                    )
                                }
                            }

                            Text(
                                text = "¡Eleva tu alma durante todo el día! Accede al Horario Diario de Oración interactivo con seguimiento de horas canónicas y devociones guiadas mediante un pago único de sólo $1.00 USD para siempre.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Button(
                                onClick = onRequestUpgrade,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("btn_schedule_unlock_pro"),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SacredGold)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Desbloquear Horario y Oraciones ($1.00 USD)",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }

            // Section label
            item {
                Text(
                    text = "MOMENTOS DEL DÍA (6 HORAS CANÓNICAS)",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 1.2.sp
                )
            }

            // The 6 slots
            items(ScheduleData.slots, key = { it.slotId }) { slot ->
                val isCompleted = scheduleChecks.any { it.slotId == slot.slotId && it.isCompleted }
                val isCurrentTime = currentHour in slot.startHour..slot.endHour

                ScheduleSlotCard(
                    slot = slot,
                    isCompleted = isCompleted,
                    isCurrentTime = isCurrentTime,
                    fontScale = fontScale,
                    onToggle = {
                        if (isProUser) {
                            onToggleSlot(slot.slotId, isCompleted)
                        } else {
                            onRequestUpgrade()
                        }
                    },
                    onReadPrayers = { state.value = slot },
                    onAction = {
                        when (slot.actionType) {
                            ScheduleActionType.OPEN_ROSARY -> onOpenRosary()
                            ScheduleActionType.OPEN_CORONILLA -> onOpenCoronilla()
                            ScheduleActionType.OPEN_LITURGIA_LAUDES -> onOpenLiturgia(com.example.data.HoraLiturgica.LAUDES)
                            ScheduleActionType.OPEN_LITURGIA_VISPERAS -> onOpenLiturgia(com.example.data.HoraLiturgica.VISPERAS)
                            ScheduleActionType.OPEN_LITURGIA_COMPLETAS -> onOpenLiturgia(com.example.data.HoraLiturgica.COMPLETAS)
                            ScheduleActionType.READ_IN_PLACE -> state.value = slot
                        }
                    }
                )
            }
        }

        // Bottom Sheet to read prayers of the slot in-place
        slotToRead?.let { slot ->
            ScheduleSlotPrayersSheet(
                slot = slot,
                fontScale = fontScale,
                onDismiss = { state.value = null }
            )
        }
    }
}

@Composable
fun ScheduleSlotCard(
    slot: ScheduleSlot,
    isCompleted: Boolean,
    isCurrentTime: Boolean,
    fontScale: Float,
    onToggle: () -> Unit,
    onReadPrayers: () -> Unit,
    onAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor by animateColorAsState(
        targetValue = when {
            isCompleted -> Color(0xFF16A34A).copy(alpha = 0.5f)
            isCurrentTime -> SacredGold
            else -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        },
        label = "border_color"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("slot_card_${slot.slotId}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrentTime) {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = BorderStroke(if (isCurrentTime) 2.dp else 1.dp, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isCurrentTime) 4.dp else 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val icon = getSlotIcon(slot.slotId)
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(
                                if (isCompleted) Color(0xFF16A34A).copy(alpha = 0.15f)
                                else MaterialTheme.colorScheme.primaryContainer
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = slot.periodName,
                            tint = if (isCompleted) Color(0xFF16A34A) else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = slot.timeLabel,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = slot.periodName,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isCurrentTime) {
                        Surface(
                            shape = CircleShape,
                            color = SacredGold
                        ) {
                            Text(
                                text = "AHORA",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    // Checkbox button
                    IconButton(
                        onClick = onToggle,
                        modifier = Modifier.testTag("toggle_slot_${slot.slotId}")
                    ) {
                        Icon(
                            imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Check,
                            contentDescription = if (isCompleted) "Rezado" else "Marcar rezado",
                            tint = if (isCompleted) Color(0xFF16A34A) else MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = slot.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = (17 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = slot.subtitle,
                style = MaterialTheme.typography.bodySmall,
                fontSize = (13 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = onReadPrayers,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = "Leer",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Ver Oraciones", fontSize = 12.sp)
                }

                val actionLabel = when (slot.actionType) {
                    ScheduleActionType.OPEN_ROSARY -> "Santo Rosario"
                    ScheduleActionType.OPEN_CORONILLA -> "Rezar Coronilla"
                    ScheduleActionType.OPEN_LITURGIA_LAUDES -> "Rezar Laudes"
                    ScheduleActionType.OPEN_LITURGIA_VISPERAS -> "Rezar Vísperas"
                    ScheduleActionType.OPEN_LITURGIA_COMPLETAS -> "Rezar Completas"
                    ScheduleActionType.READ_IN_PLACE -> "Rezar Ahora"
                }

                Button(
                    onClick = onAction,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCurrentTime) SacredGold else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Rezar",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(actionLabel, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ScheduleSlotPrayersSheet(
    slot: ScheduleSlot,
    fontScale: Float,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                Text(
                    text = "${slot.timeLabel} • ${slot.periodName}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = slot.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(slot.prayersList) { item ->
                    PrayerCard(
                        title = item.name,
                        fontScale = fontScale
                    ) {
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = (15 * fontScale).sp,
                            lineHeight = (22 * fontScale).sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Cerrar")
            }
        }
    )
}

fun getSlotIcon(slotId: String): ImageVector {
    return when (slotId) {
        "DESPERTAR" -> Icons.Default.WbSunny
        "MANANA_LAUDES" -> Icons.Default.WbSunny
        "MEDIODIA_ANGELUS" -> Icons.Default.AccessTime
        "HORA_MISERICORDIA" -> Icons.Default.AutoAwesome
        "TARDE_ROSARIO" -> Icons.Default.WbTwilight
        "NOCHE_COMPLETAS" -> Icons.Default.Nightlight
        else -> Icons.Default.Schedule
    }
}
