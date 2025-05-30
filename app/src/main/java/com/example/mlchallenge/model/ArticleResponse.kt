package com.example.mlchallenge.model

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val permalink: String
)

data class SearchResponseDto(
    @SerializedName("results")
    val results: List<ArticleDto>
)

data class ProductResponseDto(
    val results: List<ProductDto>
)

data class ProductDto(
    val id: String,
    val name: String,
    val pictures: List<PictureDto>
)

data class PictureDto(
    val url: String
)
