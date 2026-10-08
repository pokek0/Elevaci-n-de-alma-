package com.example.data.model

enum class PremiumFeature(
    val title: String,
    val description: String,
    val inviteMessage: String
) {
    HORARIO_ORACION(
        title = "Horario Diario de Oración",
        description = "6 momentos canónicos de oración con seguimiento a lo largo de tu jornada.",
        inviteMessage = "¡Eleva tu alma durante todo el día! Accede al Horario Diario de Oración interactivo con seguimiento de horas canónicas y devociones guiadas mediante un pago único de sólo $1.00 USD para siempre."
    ),
    ORACIONES_PERSONALIZADAS(
        title = "Oraciones Personalizadas",
        description = "Bloc personal para redactar, organizar y conservar tus intenciones y plegarias.",
        inviteMessage = "¡Crea tu propio devocionario íntimo! Redacta, organiza por categorías y conserva tus oraciones e intenciones familiares sin límites con un pago único de sólo $1.00 USD para toda la vida."
    )
}

sealed interface PremiumState {
    val isPremium: Boolean

    data class Free(
        val lastInvitedFeature: PremiumFeature? = null
    ) : PremiumState {
        override val isPremium: Boolean = false
    }

    data class Premium(
        val unlockedTimestamp: Long = System.currentTimeMillis()
    ) : PremiumState {
        override val isPremium: Boolean = true
    }
}
