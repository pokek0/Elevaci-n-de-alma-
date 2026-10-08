package com.example.data.model

enum class MysteryType(
    val title: String,
    val subtitle: String,
    val daysDescription: String,
    val iconName: String
) {
    GOZO(
        title = "Gozosos",
        subtitle = "La Encarnación y Vida Oculta de Jesús",
        daysDescription = "Lunes y Sábado",
        iconName = "child_friendly"
    ),
    DOLOR(
        title = "Dolorosos",
        subtitle = "La Pasión y Muerte de Nuestro Señor",
        daysDescription = "Martes y Viernes",
        iconName = "favorite"
    ),
    GLORIA(
        title = "Gloriosos",
        subtitle = "La Resurrección y Triunfo Celestial",
        daysDescription = "Miércoles y Domingo",
        iconName = "auto_awesome"
    ),
    LUZ(
        title = "Luminosos",
        subtitle = "La Vida Pública y Misterios de la Luz",
        daysDescription = "Jueves",
        iconName = "light_mode"
    );

    companion object {
        fun getTodayMystery(): MysteryType {
            val calendar = java.util.Calendar.getInstance()
            return when (calendar.get(java.util.Calendar.DAY_OF_WEEK)) {
                java.util.Calendar.MONDAY, java.util.Calendar.SATURDAY -> GOZO
                java.util.Calendar.TUESDAY, java.util.Calendar.FRIDAY -> DOLOR
                java.util.Calendar.THURSDAY -> LUZ
                java.util.Calendar.WEDNESDAY, java.util.Calendar.SUNDAY -> GLORIA
                else -> GOZO
            }
        }
    }
}

data class MysteryItem(
    val number: Int,
    val title: String,
    val scripture: String,
    val fruit: String,
    val meditation: String
)
