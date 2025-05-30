package com.example.mlchallenge.model


import com.google.gson.annotations.SerializedName

data class ArticleDataResponse(
    @SerializedName("keywords")
    val keywords: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("results")
    val results: List<Result> = emptyList()
) {
    data class Paging(
        @SerializedName("limit")
        val limit: Int?,
        @SerializedName("offset")
        val offset: Int?,
        @SerializedName("total")
        val total: Int?
    )

    data class Result(
        @SerializedName("attributes")
        val attributes: List<Attribute?>?,
        @SerializedName("children_ids")
        val childrenIds: List<Any?>?,
        @SerializedName("domain_id")
        val domainId: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("main_features")
        val mainFeatures: List<Any?>?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("parent_id")
        val parentId: String?,
        @SerializedName("pictures")
        val pictures: List<Picture?>?,
        @SerializedName("settings")
        val settings: Settings?,
        @SerializedName("status")
        val status: String?
    ) {
        data class Attribute(
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("value_id")
            val valueId: String?,
            @SerializedName("value_name")
            val valueName: String?
        )

        data class Picture(
            @SerializedName("id")
            val id: String?,
            @SerializedName("url")
            val url: String?
        )

        data class Settings(
            @SerializedName("listing_strategy")
            val listingStrategy: String?
        )
    }
}