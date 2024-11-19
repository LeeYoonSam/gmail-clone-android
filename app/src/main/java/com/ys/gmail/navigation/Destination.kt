package com.ys.gmail.navigation

import kotlinx.serialization.Serializable

@Serializable
object EmailList

@Serializable
data class EmailDetails(
    val from: String,
    val profileImage: String?,
    val subject: String,
    val isPromotional: Boolean,
    val isStarred: Boolean
)