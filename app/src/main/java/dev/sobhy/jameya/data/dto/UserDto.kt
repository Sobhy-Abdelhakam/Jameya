package dev.sobhy.jameya.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("image")
    val image: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)
