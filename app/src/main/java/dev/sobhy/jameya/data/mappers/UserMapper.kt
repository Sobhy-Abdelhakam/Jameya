package dev.sobhy.jameya.data.mappers

import dev.sobhy.jameya.data.dto.UserDto
import dev.sobhy.jameya.domain.model.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun convertSupabaseTimestamp(timestamp: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return localDateTime.atZone(ZoneId.systemDefault()).toLocalDate()
}
fun convertTimeZone(timeStamp: String): LocalDate {
    val zonedTimeDate = ZonedDateTime.parse(timeStamp, DateTimeFormatter.ISO_ZONED_DATE_TIME)
    val localDate = zonedTimeDate.toLocalDate()
    return localDate
}

fun UserDto.toDomain(): User = User(
    id = id,
    name = fullName ?: "New User",
    image = image,
    createdAt = convertTimeZone(createdAt),
    updatedAt = convertTimeZone(updatedAt)
)