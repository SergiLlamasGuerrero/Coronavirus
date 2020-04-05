package com.s.coronavirus

data class LocationsResponseWrapper(
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
