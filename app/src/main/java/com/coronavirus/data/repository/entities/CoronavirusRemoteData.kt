package com.coronavirus.data.repository.entities

import kotlinx.serialization.Serializable

@Serializable
data class CoronavirusRemoteData(
  val latest: LatestRemoteEntity,
  val locations: List<LocationRemoteEntity>
)

@Serializable
data class LatestRemoteEntity(
  val confirmed: Int?,
  val deaths: Int?,
  val recovered: Int?
)

@Serializable
data class LocationRemoteEntity(
  val id: String?,
  val country: String?,
  val latest: LatestRemoteEntity
)
