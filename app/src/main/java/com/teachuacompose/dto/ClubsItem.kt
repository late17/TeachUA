package com.teachuacompose.dto

data class ClubsItem(
    val ageFrom: Int,
    val ageTo: Int,
    val categories: List<Category>,
    val center: Center,
    val contacts: List<Contact>,
    val description: String,
    val feedbackCount: Int,
    val id: Int,
    val isApproved: Any,
    val isOnline: Boolean,
    val locations: List<Any>,
    val name: String,
    val rating: Any,
    val urlBackground: String,
    val urlGallery: List<Any>,
    val urlLogo: String,
    val urlWeb: Any,
    val user: UserX,
    val workTime: Any
)