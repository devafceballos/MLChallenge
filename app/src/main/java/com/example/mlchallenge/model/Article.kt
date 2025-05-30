package com.example.mlchallenge.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val id: String,
    val name: String,
    val pictureUrl: String?
) : Parcelable



