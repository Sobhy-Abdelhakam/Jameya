package dev.sobhy.jameya.data.mappers

import dev.sobhy.jameya.data.dto.UserDto
import dev.sobhy.jameya.domain.model.User
import kotlinx.datetime.LocalDate

fun UserDto.toDomain(): User = User(
    id = id,
    name = fullName,
    image = image,
    createdAt = LocalDate.parse(createdAt),
    updatedAt = LocalDate.parse(updatedAt)
)