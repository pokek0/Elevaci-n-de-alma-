package com.example.data.model

data class LiturgicalReading(
    val type: String, // "PRIMERA LECTURA", "SALMO RESPONSORIAL", "SEGUNDA LECTURA", "ACLAMACIÓN", "EVANGELIO"
    val citation: String,
    val subtitle: String = "",
    val text: String,
    val response: String = "", // Antífona para salmo o aclamación
    val closing: String = "" // "Palabra de Dios", "Palabra del Señor"
)

data class LiturgicalDay(
    val id: String,
    val dateDisplay: String,
    val liturgicalTitle: String,
    val liturgicalSeason: String,
    val liturgicalColor: String, // "Verde", "Blanco", "Morado", "Rojo"
    val cycle: String, // "Ciclo B - Año Par"
    val readings: List<LiturgicalReading>,
    val reflectionTitle: String,
    val reflectionText: String,
    val reflectionQuestions: List<String>,
    val dailyPrayer: String
)
