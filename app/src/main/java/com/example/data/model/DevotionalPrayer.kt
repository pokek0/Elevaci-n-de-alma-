package com.example.data.model

data class DevotionalPrayer(
    val id: String,
    val pageNumber: Int,
    val title: String,
    val subtitle: String = "",
    val category: String,
    val content: String,
    val verses: List<Pair<String, String>> = emptyList(),
    val finalPrayer: String = "",
    val author: String = "",
    val isInteractiveAngelus: Boolean = false,
    val isInteractiveReginaCoeli: Boolean = false
)
