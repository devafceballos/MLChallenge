package com.example.mlchallenge.data.remote

import com.example.mlchallenge.model.ArticleDataResponse
import com.example.mlchallenge.model.ProductResponseDto
import com.example.mlchallenge.model.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiService {

    @GET("products/search")
    suspend fun searchProducts(
        @Query("status") status: String = "active",
        @Query("site_id") siteId: String = "MLA",
        @Query("q") query: String
    ): ArticleDataResponse
}



///items/$ITEM_ID para mostrar detalle producto