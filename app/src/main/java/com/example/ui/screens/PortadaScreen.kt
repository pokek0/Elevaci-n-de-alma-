package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.DevocionarioData
import com.example.data.model.DevotionalPrayer
import com.example.data.model.MysteryType
import com.example.ui.theme.DevotionalRose
import com.example.ui.theme.SacredGold
import com.example.ui.viewmodel.UnifiedRezoTab

@Composable
fun PortadaScreen(
    todayMystery: MysteryType,
    isProUser: Boolean,
    liturgicalColor: com.example.data.model.LiturgicalColor = com.example.data.model.LiturgicalColor.VERDE,
    onOpenProUpgrade: (String) -> Unit,
    onNavigateToUnifiedRezos: (UnifiedRezoTab) -> Unit,
    onNavigateToHorario: () -> Unit,
    onNavigateToLiturgia: () -> Unit,
    onNavigateToLecturasCem: () -> Unit,
    onNavigateToDevocionario: () -> Unit,
    onNavigateToMisOraciones: () -> Unit,
    onOpenPrayer: (DevotionalPrayer) -> Unit,
    fontScale: Float,
    modifier: Modifier = Modifier
) {
    val calendar = java.util.Calendar.getInstance()
    val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)

    val suggestedPrayer = when {
        hour in 11..14 -> DevocionarioData.prayers.find { it.id == "el_angelus" }
        hour >= 20 || hour < 5 -> DevocionarioData.prayers.find { it.id == "angel_guarda" }
        hour in 5..10 -> DevocionarioData.prayers.find { it.id == "consagracion_maria" }
        else -> DevocionarioData.prayers.find { it.id == "magnificat" }
    } ?: DevocionarioData.prayers.first()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Booklet Cover Hero Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .testTag("booklet_cover_card"),
                shape = RoundedCornerShape(26.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.bg_devocionario_cover),
                        contentDescription = "Portada Mi Devocionario",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Gradient Overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x22000000),
                                        Color(0x771E1B4B),
                                        Color(0xF01E1B4B)
                                    )
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(18.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = liturgicalColor.badgeColor
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                            .background(Color.White)
                                    )
                                    Text(
                                        text = liturgicalColor.title.uppercase(),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 10.sp
                                    )
                                }
                            }

                            Surface(
                                shape = CircleShape,
                                color = SacredGold
                            ) {
                                Text(
                                    text = "ELEVA TU ALMA AL CIELO",
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White,
                                    letterSpacing = 1.1.sp,
                                    fontSize = 10.sp
                                )
                            }

                            if (isProUser) {
                                Surface(
                                    shape = CircleShape,
                                    color = Color(0xFF16A34A)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.WorkspacePremium,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Text(
                                            text = "PRO ACTIVO",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Black,
                                            color = Color.White,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Mi devocionario",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Black,
                            fontStyle = FontStyle.Italic,
                            color = Color.White,
                            fontSize = 30.sp
                        )

                        Text(
                            text = "Santo Rosario, Coronilla, Vía Crucis, Vía Lúcis y Horario de oración",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }

        // 2. Pro Upgrade Promo Banner (if not pro user)
        if (!isProUser) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenProUpgrade("Acceso al Horario de Oración Diaria y Mis Oraciones Propias") }
                        .testTag("banner_pro_promo"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = SacredGold.copy(alpha = 0.12f)
                    ),
                    border = BorderStroke(1.5.dp, SacredGold)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(SacredGold),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.WorkspacePremium,
                                    contentDescription = "Pro",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = "MI DEVOCIONARIO PRO",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Black,
                                    color = SacredGold,
                                    letterSpacing = 1.1.sp
                                )
                                Text(
                                    text = "Desbloquea el Horario y tus Oraciones",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Pago único de $1.00 USD para siempre",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = SacredGold
                        ) {
                            Text(
                                text = "$1.00 USD",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        // 3. Unified Prayers Highlight Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToUnifiedRezos(UnifiedRezoTab.ROSARIO) }
                    .testTag("banner_rezos_unificados"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Surface(
                            shape = CircleShape,
                            color = Color.White.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "SECCIÓN UNIFICADA",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                letterSpacing = 1.1.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Rezos y Devociones",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                        Text(
                            text = "Rosario • Coronilla • Vía Crucis • Vía Lúcis",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Abrir Rezos",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        // Section Title: Grid de Secciones
        item {
            Text(
                text = "SECCIONES DEL DEVOCIONARIO",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.2.sp
            )
        }

        // Grid Row 1: Santo Rosario & Coronilla de la Misericordia
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GridSectionCard(
                    title = "Santo Rosario",
                    subtitle = "Hoy: ${todayMystery.title}",
                    icon = Icons.Default.PlayArrow,
                    iconBgColor = MaterialTheme.colorScheme.primary,
                    iconTint = Color.White,
                    onClick = { onNavigateToUnifiedRezos(UnifiedRezoTab.ROSARIO) },
                    modifier = Modifier.weight(1f),
                    testTag = "grid_rosario"
                )

                GridSectionCard(
                    title = "La Coronilla",
                    subtitle = "Señor de la Misericordia",
                    icon = Icons.Default.VolunteerActivism,
                    iconBgColor = DevotionalRose,
                    iconTint = Color.White,
                    onClick = { onNavigateToUnifiedRezos(UnifiedRezoTab.CORONILLA) },
                    modifier = Modifier.weight(1f),
                    testTag = "grid_coronilla"
                )
            }
        }

        // Grid Row 2: Vía Crucis & Vía Lúcis
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GridSectionCard(
                    title = "Vía Crucis",
                    subtitle = "14 Estaciones de Pasión",
                    icon = Icons.Default.Layers,
                    iconBgColor = DevotionalRose.copy(alpha = 0.15f),
                    iconTint = DevotionalRose,
                    onClick = { onNavigateToUnifiedRezos(UnifiedRezoTab.VIACRUCIS) },
                    modifier = Modifier.weight(1f),
                    testTag = "grid_viacrucis"
                )

                GridSectionCard(
                    title = "Vía Lúcis",
                    subtitle = "Camino de la Luz Pascual",
                    icon = Icons.Default.WbSunny,
                    iconBgColor = SacredGold,
                    iconTint = Color.White,
                    onClick = { onNavigateToUnifiedRezos(UnifiedRezoTab.VIA_LUCIS) },
                    modifier = Modifier.weight(1f),
                    testTag = "grid_vialucis"
                )
            }
        }

        // Grid Row 3: Horario de Oración (PRO) & Mis Oraciones (PRO)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GridSectionCard(
                    title = "Horario Diario",
                    subtitle = "6 momentos del día",
                    icon = Icons.Default.Schedule,
                    iconBgColor = if (isProUser) SacredGold else MaterialTheme.colorScheme.surfaceVariant,
                    iconTint = if (isProUser) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                    isPro = !isProUser,
                    proBadgeText = "PRO $1 USD",
                    onClick = {
                        if (isProUser) {
                            onNavigateToHorario()
                        } else {
                            onOpenProUpgrade("Horario de Oración Diaria: Las 6 horas canónicas con registro interactivo durante todo el día.")
                        }
                    },
                    modifier = Modifier.weight(1f),
                    testTag = "grid_horario"
                )

                GridSectionCard(
                    title = "Mis Oraciones",
                    subtitle = "Bloc personal de fe",
                    icon = Icons.Default.Favorite,
                    iconBgColor = if (isProUser) SacredGold else MaterialTheme.colorScheme.surfaceVariant,
                    iconTint = if (isProUser) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                    isPro = !isProUser,
                    proBadgeText = "PRO $1 USD",
                    onClick = {
                        if (isProUser) {
                            onNavigateToMisOraciones()
                        } else {
                            onOpenProUpgrade("Mis Oraciones Propias: Redacta, organiza y guarda tus intenciones y oraciones personales sin límites.")
                        }
                    },
                    modifier = Modifier.weight(1f),
                    testTag = "grid_mis_oraciones"
                )
            }
        }

        // Grid Row 4: Liturgia de las Horas & Lecturas CEM
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GridSectionCard(
                    title = "Liturgia Horas",
                    subtitle = "Laudes, Vísperas, Noche",
                    icon = Icons.Default.MenuBook,
                    iconBgColor = MaterialTheme.colorScheme.primaryContainer,
                    iconTint = MaterialTheme.colorScheme.primary,
                    onClick = onNavigateToLiturgia,
                    modifier = Modifier.weight(1f),
                    testTag = "grid_liturgia"
                )

                GridSectionCard(
                    title = "Lecturas CEM",
                    subtitle = "Evangelio y reflexión",
                    icon = Icons.Default.AutoAwesome,
                    iconBgColor = SacredGold,
                    iconTint = Color.White,
                    onClick = onNavigateToLecturasCem,
                    modifier = Modifier.weight(1f),
                    testTag = "grid_lecturas_cem"
                )
            }
        }

        // Devocionario Tradicional Card (Full width)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onNavigateToDevocionario)
                    .testTag("grid_devocionario_catolico"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "Devocionario",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "Devocionario Católico",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "12 oraciones tradicionales (Págs. 2 a 12 del devocionario)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Abrir",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        // Suggested Prayer of the Hour
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenPrayer(suggestedPrayer) },
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(SacredGold),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Hora",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = "ORACIÓN SUGERIDA PARA ESTE MOMENTO",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                letterSpacing = 1.1.sp
                            )
                            Text(
                                text = suggestedPrayer.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Pág. ${suggestedPrayer.pageNumber} • ${suggestedPrayer.subtitle}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Rezar",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GridSectionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconBgColor: Color,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPro: Boolean = false,
    proBadgeText: String = "PRO",
    testTag: String = ""
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .testTag(testTag),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.2.dp,
            if (isPro) SacredGold.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(iconBgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = iconTint,
                        modifier = Modifier.size(22.dp)
                    )
                }

                if (isPro) {
                    Surface(
                        shape = CircleShape,
                        color = SacredGold
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Bloqueado",
                                tint = Color.White,
                                modifier = Modifier.size(10.dp)
                            )
                            Text(
                                text = proBadgeText,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                color = Color.White,
                                fontSize = 9.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                maxLines = 1
            )
        }
    }
}
