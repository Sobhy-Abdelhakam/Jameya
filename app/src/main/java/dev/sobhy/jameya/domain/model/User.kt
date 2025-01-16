package dev.sobhy.jameya.domain.model

import kotlinx.datetime.LocalDate

data class User(
    val id: String,
    val name: String,
    val image: String?,
    val createdAt: LocalDate,
    val updatedAt: LocalDate
)
