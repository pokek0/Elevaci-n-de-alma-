package com.example.data.model

import androidx.compose.ui.graphics.Color
import java.util.Calendar

enum class LiturgicalColor(
    val title: String,
    val description: String,
    val meaning: String,
    val primaryLight: Color,
    val primaryDark: Color,
    val primaryContainerLight: Color,
    val primaryContainerDark: Color,
    val onPrimaryContainerLight: Color,
    val onPrimaryContainerDark: Color,
    val accentLight: Color,
    val accentDark: Color,
    val badgeColor: Color
) {
    VERDE(
        title = "Verde",
        description = "Tiempo Ordinario",
        meaning = "Esperanza, vida cristiana y perseverancia en el seguimiento de Cristo.",
        primaryLight = Color(0xFF15803D), // Verde esmeralda profundo
        primaryDark = Color(0xFF4ADE80),  // Verde claro accesible
        primaryContainerLight = Color(0xFFDCFCE7),
        primaryContainerDark = Color(0xFF14532D),
        onPrimaryContainerLight = Color(0xFF14532D),
        onPrimaryContainerDark = Color(0xFFDCFCE7),
        accentLight = Color(0xFF166534),
        accentDark = Color(0xFF86EFAC),
        badgeColor = Color(0xFF16A34A)
    ),
    BLANCO(
        title = "Blanco / Dorado",
        description = "Pascua, Navidad y Fiestas de María y del Señor",
        meaning = "Pureza, gozo pascual, luz divina y gloria de la Resurrección.",
        primaryLight = Color(0xFFB45309), // Dorado / Ámbar sacro
        primaryDark = Color(0xFFFBBF24),  // Oro resplandeciente
        primaryContainerLight = Color(0xFFFEF3C7),
        primaryContainerDark = Color(0xFF451A03),
        onPrimaryContainerLight = Color(0xFF78350F),
        onPrimaryContainerDark = Color(0xFFFEF3C7),
        accentLight = Color(0xFFD97706),
        accentDark = Color(0xFFFDE68A),
        badgeColor = Color(0xFFD97706)
    ),
    MORADO(
        title = "Morado",
        description = "Adviento y Cuaresma",
        meaning = "Penitencia, conversión, preparación interior y espera gozosa.",
        primaryLight = Color(0xFF6D28D9), // Violeta sacro
        primaryDark = Color(0xFFA78BFA),  // Violeta luminoso
        primaryContainerLight = Color(0xFFEDE9FE),
        primaryContainerDark = Color(0xFF3B0764),
        onPrimaryContainerLight = Color(0xFF4C1D95),
        onPrimaryContainerDark = Color(0xFFEDE9FE),
        accentLight = Color(0xFF7C3AED),
        accentDark = Color(0xFFC4B5FD),
        badgeColor = Color(0xFF7C3AED)
    ),
    ROJO(
        title = "Rojo",
        description = "Pasión del Señor, Pentecostés y Mártires",
        meaning = "Fuego del Espíritu Santo, amor supremo y sangre derramada por Cristo.",
        primaryLight = Color(0xFFB91C1C), // Rojo carmesí
        primaryDark = Color(0xFFF87171),  // Rojo suave
        primaryContainerLight = Color(0xFFFEE2E2),
        primaryContainerDark = Color(0xFF450A0A),
        onPrimaryContainerLight = Color(0xFF7F1D1D),
        onPrimaryContainerDark = Color(0xFFFEE2E2),
        accentLight = Color(0xFFDC2626),
        accentDark = Color(0xFFFCA5A5),
        badgeColor = Color(0xFFDC2626)
    ),
    ROSA(
        title = "Rosa",
        description = "Domingos Gaudete y Laetare",
        meaning = "Alegría anticipada en medio del camino penitencial.",
        primaryLight = Color(0xFFBE185D), // Rosa litúrgico
        primaryDark = Color(0xFFF472B6),  // Rosa suave
        primaryContainerLight = Color(0xFFFCE7F3),
        primaryContainerDark = Color(0xFF500724),
        onPrimaryContainerLight = Color(0xFF831843),
        onPrimaryContainerDark = Color(0xFFFCE7F3),
        accentLight = Color(0xFFDB2777),
        accentDark = Color(0xFFF9A8D4),
        badgeColor = Color(0xFFDB2777)
    );

    companion object {
        fun fromName(name: String): LiturgicalColor {
            return when (name.trim().lowercase()) {
                "verde" -> VERDE
                "blanco", "dorado", "oro" -> BLANCO
                "morado", "violeta", "púrpura", "purpura" -> MORADO
                "rojo" -> ROJO
                "rosa", "rosado" -> ROSA
                else -> VERDE
            }
        }

        fun getTodayLiturgicalColor(): LiturgicalColor {
            val calendar = Calendar.getInstance()
            val month = calendar.get(Calendar.MONTH) // 0 = Jan, 9 = Oct
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            // October 7: Nuestra Señora del Rosario -> Blanco
            if (month == Calendar.OCTOBER && dayOfMonth == 7) return BLANCO
            // December 8: Inmaculada Concepción -> Blanco
            if (month == Calendar.DECEMBER && dayOfMonth == 8) return BLANCO
            // December 12: Nuestra Señora de Guadalupe -> Blanco
            if (month == Calendar.DECEMBER && dayOfMonth == 12) return BLANCO
            // December 25 - January 6: Navidad -> Blanco
            if ((month == Calendar.DECEMBER && dayOfMonth >= 25) || (month == Calendar.JANUARY && dayOfMonth <= 6)) return BLANCO
            // Advent (December 1 to 24) -> Morado
            if (month == Calendar.DECEMBER && dayOfMonth in 1..24) return MORADO

            // If day matches liturgical catalog from CEM:
            val cemDay = com.example.data.CemReadingsData.liturgicalDays.firstOrNull()
            return if (cemDay != null) fromName(cemDay.liturgicalColor) else VERDE
        }
    }
}
