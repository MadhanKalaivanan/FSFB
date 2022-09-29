package com.fsfb.module.dto

import com.google.gson.annotations.SerializedName

data class HomeScreenListResponse(
    @SerializedName("results")
    val result: ArrayList<DataClass>?
)

data class DataClass(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("name")
    val name: Name?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("login")
    val login: Login?,
    @SerializedName("picture")
    val picture: Picture?,
    @SerializedName("dob")
    val dob: Dob?,
    var isAccept: Boolean = false,
    var isDeclined: Boolean = false
)

data class Name(
    @SerializedName("title")
    val title: String?,
    @SerializedName("first")
    val first: String?,
    @SerializedName("last")
    val last: String?
)

data class Location(
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("country")
    val country: String?
)

data class Dob(
    @SerializedName("age")
    val age: String?
)

data class Picture(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)

data class Login(
    @SerializedName("uuid")
    val uuid: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("md5")
    val md5: String?,
    @SerializedName("salt")
    val salt: String?,
    @SerializedName("sha1")
    val sha1: String?,
    @SerializedName("sha256")
    val sha256: String?
)
