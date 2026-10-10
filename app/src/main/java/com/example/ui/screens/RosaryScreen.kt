package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.PrayersData
import com.example.data.model.MysteryType
import com.example.ui.components.BeadTracker
import com.example.ui.components.PrayerCard
import com.example.ui.components.RosaryBottomBar
import com.example.ui.theme.DevotionalRose
import com.example.ui.theme.SacredGold
import com.example.ui.viewmodel.RosaryViewModel

@Composable
fun RosaryScreen(
    viewModel: RosaryViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()

    LaunchedEffect(uiState.step, uiState.currentMysteryIndex) {
        scrollState.animateScrollToItem(0)
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 600.dp)
                .padding(horizontal = 18.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (uiState.step) {
                0 -> {
                    item {
                        Step0IntroView(
                            intention = uiState.personalIntention,
                            fontScale = uiState.fontScale,
                            onEditIntention = { viewModel.setShowIntentionDialog(true) },
                            onStart = { viewModel.nextStep() }
                        )
                    }
                }
                1 -> {
                    item {
                        Step1MysterySelectionView(
                            currentSelected = uiState.mysteryType,
                            onSelectMystery = {
                                viewModel.setMysteryType(it)
                                viewModel.nextStep()
                            }
                        )
                    }
                }
                2 -> {
                    item {
                        Step2MysteriesAndDecadeView(
                            mysteryType = uiState.mysteryType,
                            mysteryIndex = uiState.currentMysteryIndex,
                            aveMariaCount = uiState.aveMariaCount,
                            fontScale = uiState.fontScale,
                            onCountAveMaria = { viewModel.countAveMaria() },
                            onSelectBead = { viewModel.setAveMariaCount(it) },
                            onNextMystery = { viewModel.nextMystery() },
                            onPrevMystery = { viewModel.prevMystery() },
                            onSelectMysteryIndex = { viewModel.setMysteryIndex(it) }
                        )
                    }
                }
                3 -> {
                    item {
                        Step3ClosingPrayersView(
                            fontScale = uiState.fontScale,
                            onNext = { viewModel.nextStep() }
                        )
                    }
                }
                4 -> {
                    item {
                        Step4SalveAndLitaniesView(
                            fontScale = uiState.fontScale,
                            onOpenLitanies = { viewModel.setShowLitaniesDialog(true) },
                            onNext = { viewModel.nextStep() }
                        )
                    }
                }
                5 -> {
                    item {
                        Step5OfferingAndFinishView(
                            mysteryType = uiState.mysteryType,
                            intention = uiState.personalIntention,
                            fontScale = uiState.fontScale,
                            onRestart = { viewModel.restart() }
                        )
                    }
                }
            }
        }

        // Floating Step Navigation Bar
        RosaryBottomBar(
            currentStep = uiState.step,
            onPrevStep = { viewModel.prevStep() },
            onNextStep = { viewModel.nextStep() },
            onRestart = { viewModel.restart() },
            onSelectStep = { viewModel.setStep(it) },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

// -------------------------------------------------------------
// STEP 0: 1. ORACIONES INICIALES Y 2. INVOCACIONES (Pág. 13)
// -------------------------------------------------------------
@Composable
private fun Step0IntroView(
    intention: String,
    fontScale: Float,
    onEditIntention: () -> Unit,
    onStart: () -> Unit
) {
    var showCredo by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Hero Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(26.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.bg_rosario_banner),
                    contentDescription = "Virgen del Rosario",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0xCC2A0845),
                                    Color(0xF018042B)
                                )
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = SacredGold
                    ) {
                        Text(
                            text = "GUÍA COMPLETA DEL SANTO ROSARIO • PÁG. 13",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "El Santo Rosario",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        // Intention Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "INTENCIÓN DE ESTE ROSARIO",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 1.1.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = if (intention.isNotBlank()) "\"$intention\"" else "Añade una intención especial...",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (intention.isNotBlank()) FontWeight.SemiBold else FontWeight.Normal,
                        fontStyle = if (intention.isNotBlank()) FontStyle.Italic else FontStyle.Normal,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                OutlinedButton(
                    onClick = onEditIntention,
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(if (intention.isNotBlank()) "Cambiar" else "Agregar")
                }
            }
        }

        // 1. Oraciones iniciales
        PrayerCard(
            title = "1. Por la Señal de la Santa Cruz",
            guide = PrayersData.SENAL_CRUZ_GUIA,
            fontScale = fontScale,
            testTag = "card_senal_cruz"
        )

        PrayerCard(
            title = "Acto de Contrición",
            guide = PrayersData.ACTO_CONTRICION_GUIA,
            response = PrayersData.ACTO_CONTRICION_TODOS,
            fontScale = fontScale,
            testTag = "card_acto_contricion"
        )

        // 2. Invocaciones de Preparación (Pág. 13)
        PrayerCard(
            title = "2. Invocaciones de Preparación",
            fontScale = fontScale,
            testTag = "card_invocaciones"
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row {
                    Text("Guía: ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text(PrayersData.INVOCACION_LABIOS_GUIA, style = MaterialTheme.typography.bodyMedium)
                }
                Row {
                    Text("Todos: ", fontWeight = FontWeight.Bold, color = DevotionalRose)
                    Text(PrayersData.INVOCACION_LABIOS_TODOS, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text("Guía: ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text(PrayersData.INVOCACION_GLORIA_GUIA, style = MaterialTheme.typography.bodyMedium)
                }
                Row {
                    Text("Todos: ", fontWeight = FontWeight.Bold, color = DevotionalRose)
                    Text(PrayersData.INVOCACION_GLORIA_TODOS, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        // Credo
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCredo = !showCredo },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .width(5.dp)
                                .height(20.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Credo de los Apóstoles (Opcional)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = (17 * fontScale).sp
                        )
                    }
                    Icon(
                        imageVector = if (showCredo) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expandir credo"
                    )
                }

                if (showCredo) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Guía: Creo en Dios, Padre Todopoderoso, Creador del cielo y de la tierra.",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = (15 * fontScale).sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = "Todos: Creo en Jesucristo, su único Hijo, Nuestro Señor... Creo en el Espíritu Santo, la santa Iglesia católica, la comunión de los santos, el perdón de los pecados, la resurrección de la carne y la vida eterna. Amén.",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = (15 * fontScale).sp,
                        lineHeight = (22 * fontScale).sp
                    )
                }
            }
        }

        Button(
            onClick = onStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag("start_rosary_button"),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Comenzar")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Comenzar los Misterios",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

// -------------------------------------------------------------
// STEP 1: 3. LOS MISTERIOS (Pág. 14)
// -------------------------------------------------------------
@Composable
private fun Step1MysterySelectionView(
    currentSelected: MysteryType,
    onSelectMystery: (MysteryType) -> Unit
) {
    val todayMystery = remember { MysteryType.getTodayMystery() }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "DEVOCIONARIO • MISTERIOS Y CUENTAS",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = "3. Los Misterios del Santo Rosario",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        MysteryType.values().forEach { mystery ->
            val isSelected = currentSelected == mystery
            val isToday = todayMystery == mystery

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectMystery(mystery) }
                    .testTag("mystery_card_${mystery.name}"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 6.dp else 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Misterios ${mystery.title}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                fontSize = 18.sp
                            )
                            if (isToday) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    shape = CircleShape,
                                    color = if (isSelected) SacredGold else MaterialTheme.colorScheme.primaryContainer
                                ) {
                                    Text(
                                        text = "HOY",
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Black,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = mystery.subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (isSelected) Color.White.copy(alpha = 0.85f) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Días: ${mystery.daysDescription}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Medium,
                            color = if (isSelected) Color.White.copy(alpha = 0.75f) else MaterialTheme.colorScheme.primary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color.White else MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Elegir",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// STEP 2: 4. ESTRUCTURA DE CADA MISTERIO (Pág. 14)
// -------------------------------------------------------------
@Composable
private fun Step2MysteriesAndDecadeView(
    mysteryType: MysteryType,
    mysteryIndex: Int,
    aveMariaCount: Int,
    fontScale: Float,
    onCountAveMaria: () -> Unit,
    onSelectBead: (Int) -> Unit,
    onNextMystery: () -> Unit,
    onPrevMystery: () -> Unit,
    onSelectMysteryIndex: (Int) -> Unit
) {
    val items = PrayersData.mysteries[mysteryType] ?: emptyList()
    val currentMystery = items.getOrNull(mysteryIndex) ?: items.first()
    var showAveMariaText by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Dedicated Mystery Navigation Controls
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("mystery_navigation_bar"),
            shape = RoundedCornerShape(22.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
            shadowElevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = onPrevMystery,
                        enabled = mysteryIndex > 0,
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("prev_mystery_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Misterio Anterior",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Anterior", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }

                    Text(
                        text = "Misterio ${mysteryIndex + 1} de 5",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedButton(
                        onClick = onNextMystery,
                        enabled = mysteryIndex < 4,
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("next_mystery_nav_button")
                    ) {
                        Text("Siguiente", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Siguiente Misterio",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Mystery direct selectors 1..5
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0..4) {
                        val isSelected = i == mysteryIndex
                        Surface(
                            shape = CircleShape,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                            border = BorderStroke(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            ),
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { onSelectMysteryIndex(i) }
                                .testTag("mystery_selector_chip_$i")
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "${i + 1}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // Mystery Badge & Title Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(
                        text = "MISTERIO ${mysteryIndex + 1} DE 5 • ${mysteryType.title.uppercase()}",
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.1.sp
                    )
                }

                Text(
                    text = currentMystery.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = (20 * fontScale).sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = "Cita: ${currentMystery.scripture}",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = currentMystery.meditation,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = (15 * fontScale).sp,
                    lineHeight = (22 * fontScale).sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Fruto del misterio: ${currentMystery.fruit}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = SacredGold
                )
            }
        }

        // Un Padrenuestro
        PrayerCard(
            title = "Un Padrenuestro",
            guide = PrayersData.PADRENUESTRO_GUIA,
            response = PrayersData.PADRENUESTRO_TODOS,
            fontScale = fontScale,
            testTag = "card_padrenuestro"
        )

        // Diez Avemarías
        BeadTracker(
            count = aveMariaCount,
            onCountIncrement = onCountAveMaria,
            onSelectBead = onSelectBead
        )

        // Avemaría Text Box
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showAveMariaText = !showAveMariaText },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .width(5.dp)
                                .height(20.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Oración del Avemaría",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = (17 * fontScale).sp
                        )
                    }
                    Icon(
                        imageVector = if (showAveMariaText) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Ver oración"
                    )
                }

                if (showAveMariaText) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Guía: ${PrayersData.AVEMARIA_GUIA}",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = (15 * fontScale).sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = "Todos: ${PrayersData.AVEMARIA_TODOS}",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = (15 * fontScale).sp,
                        lineHeight = (22 * fontScale).sp
                    )
                }
            }
        }

        // When 10 Avemarías completed: Gloria, Jaculatoria and Oración de Fátima
        AnimatedVisibility(
            visible = aveMariaCount == 10,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                PrayerCard(
                    title = "Gloria y Jaculatorias (Pág. 14)",
                    fontScale = fontScale,
                    testTag = "card_gloria_jaculatorias"
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Un Gloria al Padre",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = (16 * fontScale).sp
                        )
                        Text(
                            text = "Guía: ${PrayersData.GLORIA_GUIA}",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = (15 * fontScale).sp
                        )
                        Text(
                            text = "Todos: ${PrayersData.GLORIA_TODOS}",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = (15 * fontScale).sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Jaculatoria",
                            fontWeight = FontWeight.Bold,
                            color = DevotionalRose,
                            fontSize = (16 * fontScale).sp
                        )
                        Text(
                            text = "Guía: ${PrayersData.JACULATORIA_MADRE_GUIA}",
                            fontStyle = FontStyle.Italic,
                            fontSize = (15 * fontScale).sp
                        )
                        Text(
                            text = "Todos: ${PrayersData.JACULATORIA_MADRE_TODOS}",
                            fontStyle = FontStyle.Italic,
                            fontSize = (15 * fontScale).sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Oración de Fátima",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = (16 * fontScale).sp
                        )
                        Text(
                            text = "Guía: ${PrayersData.JACULATORIA_FATIMA}",
                            fontSize = (15 * fontScale).sp,
                            lineHeight = (22 * fontScale).sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Button(
                    onClick = onNextMystery,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .testTag("next_mystery_button"),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF16A34A)),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
                ) {
                    Text(
                        text = if (mysteryIndex < 4) "Continuar al Misterio ${mysteryIndex + 2} de 5" else "Continuar a Oraciones Finales (Pág. 15)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Siguiente")
                }
            }
        }
    }
}

// -------------------------------------------------------------
// STEP 3: 5. ORACIONES FINALES (Pág. 15)
// -------------------------------------------------------------
@Composable
private fun Step3ClosingPrayersView(
    fontScale: Float,
    onNext: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "DEVOCIONARIO • ORACIONES FINALES",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = "5. Oraciones Finales",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        PrayerCard(
            title = "Oh Soberano Santuario",
            guide = PrayersData.SOBERANO_SANTUARIO_GUIA,
            response = PrayersData.SOBERANO_SANTUARIO_TODOS,
            fontScale = fontScale,
            testTag = "card_soberano_santuario"
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text("Guía: ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(PrayersData.EMPERATRIZ_PODEROSA_GUIA, style = MaterialTheme.typography.bodyMedium)
            }
            Row {
                Text("Todos: ", fontWeight = FontWeight.Bold, color = DevotionalRose)
                Text(PrayersData.EMPERATRIZ_PODEROSA_TODOS, style = MaterialTheme.typography.bodyMedium)
            }
        }

        PrayerCard(
            title = "Por las Intenciones del Santo Padre",
            fontScale = fontScale,
            testTag = "card_papa"
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = PrayersData.PADRENUESTRO_PAPA_DESCRIPCION,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Guía: ${PrayersData.PADRENUESTRO_GUIA}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = (15 * fontScale).sp
                )
                Text(
                    text = "Todos: ${PrayersData.PADRENUESTRO_TODOS}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = (15 * fontScale).sp
                )
            }
        }

        PrayerCard(
            title = "Las Tres Avemarías de Pureza",
            fontScale = fontScale,
            testTag = "card_pureza_maria"
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PrayersData.PUREZA_MARIA.forEachIndexed { index, pair ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "${index + 1}. Guía: ${pair.first}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = (14 * fontScale).sp,
                                lineHeight = (20 * fontScale).sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Todos: ${pair.second}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = DevotionalRose,
                                fontSize = (14 * fontScale).sp
                            )
                        }
                    }
                }
            }
        }

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag("to_salve_button"),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Continuar a La Salve y Letanías (Pág. 15-16)",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Siguiente")
        }
    }
}

// -------------------------------------------------------------
// STEP 4: LA SALVE Y 6. LETANÍAS (Pág. 15 y 16)
// -------------------------------------------------------------
@Composable
private fun Step4SalveAndLitaniesView(
    fontScale: Float,
    onOpenLitanies: () -> Unit,
    onNext: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "DEVOCIONARIO • LETANÍAS LAURETANAS",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        PrayerCard(
            title = "La Salve",
            guide = PrayersData.SALVE_GUIA,
            response = PrayersData.SALVE_TODOS,
            fontScale = fontScale,
            testTag = "card_salve"
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Letanías",
                    tint = SacredGold,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "6. Letanías Lauretanas (Pág. 16)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Todas las invocaciones marianas y los Corderos de Dios",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onOpenLitanies,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SacredGold),
                    modifier = Modifier.testTag("open_litanies_sheet_button")
                ) {
                    Icon(imageVector = Icons.Default.MenuBook, contentDescription = "Leer")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Abrir Letanías Completas", fontWeight = FontWeight.Bold)
                }
            }
        }

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag("to_final_button"),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "7. Conclusión y Ofrecimiento (Pág. 17)",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Final")
        }
    }
}

// -------------------------------------------------------------
// STEP 5: 7. CONCLUSIÓN Y OFRECIMIENTO (Pág. 17)
// -------------------------------------------------------------
@Composable
private fun Step5OfferingAndFinishView(
    mysteryType: MysteryType,
    intention: String,
    fontScale: Float,
    onRestart: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "DEVOCIONARIO • CIERRE Y DESPEDIDA",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)),
            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
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
                        .background(Color(0xFF16A34A)),
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
                    text = "¡Santo Rosario Concluido!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Misterios ${mysteryType.title} rezados con devoción",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                )
                if (intention.isNotBlank()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Ofrecido por: \"$intention\"",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        PrayerCard(
            title = "Bajo Tu Amparo",
            fontScale = fontScale,
            testTag = "card_bajo_amparo"
        ) {
            Text(
                text = PrayersData.BAJO_TU_AMPARO,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = (16 * fontScale).sp,
                lineHeight = (25 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        PrayerCard(
            title = "Oración Final",
            fontScale = fontScale,
            testTag = "card_oracion_final"
        ) {
            Text(
                text = PrayersData.ORACION_FINAL_SACERDOTAL,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = (16 * fontScale).sp,
                lineHeight = (25 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        PrayerCard(
            title = "Ofrecimiento del Rosario",
            fontScale = fontScale,
            testTag = "card_ofrecimiento"
        ) {
            Text(
                text = PrayersData.OFRECIMIENTO_FOLLETO,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = (16 * fontScale).sp,
                lineHeight = (25 * fontScale).sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        PrayerCard(
            title = "Despedida",
            fontScale = fontScale,
            testTag = "card_despedida"
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row {
                    Text("Guía: ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text(PrayersData.DESPEDIDA_1_GUIA, style = MaterialTheme.typography.bodyMedium)
                }
                Row {
                    Text("Todos: ", fontWeight = FontWeight.Bold, color = DevotionalRose)
                    Text(PrayersData.DESPEDIDA_1_TODOS, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text("Guía: ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text(PrayersData.DESPEDIDA_2_GUIA, style = MaterialTheme.typography.bodyMedium)
                }
                Row {
                    Text("Todos: ", fontWeight = FontWeight.Bold, color = DevotionalRose)
                    Text(PrayersData.DESPEDIDA_2_TODOS, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text("Guía: ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text(PrayersData.DESPEDIDA_3_GUIA, style = MaterialTheme.typography.bodyMedium)
                }
                Row {
                    Text("Todos: ", fontWeight = FontWeight.Bold, color = DevotionalRose)
                    Text(PrayersData.DESPEDIDA_3_TODOS, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "En el nombre del Padre, † y del Hijo, y del Espíritu Santo. Amén.",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = (16 * fontScale).sp
                )
            }
        }

        Button(
            onClick = onRestart,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .testTag("restart_rosary_button"),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurface)
        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Rezar de nuevo")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Rezar de nuevo",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}
