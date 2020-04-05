package com.coronavirus.data.repository.entities

data class CoronavirusRemoteData(
  val latest: LatestRemoteEntity,
  val locations: List<LocationRemoteEntity>
)

data class LatestRemoteEntity(
  val confirmed: Int?,
  val deaths: Int?,
  val recovered: Int?
)

data class LocationRemoteEntity(
  val id: String?,
  val country: String?,
  val latest: LatestRemoteEntity
)
