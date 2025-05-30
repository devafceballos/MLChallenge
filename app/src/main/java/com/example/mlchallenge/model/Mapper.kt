package com.example.mlchallenge.model

fun ArticleDataResponse.Result.toArticle(): Article? {
    val safeId = id ?: return null
    val safeName = name ?: return null
    val imageUrl = pictures?.firstOrNull()?.url


    return Article(
        id = safeId,
        name = safeName,
        pictureUrl = imageUrl
    )
}

fun ArticleDataResponse.toArticleList(): List<Article> {
    return this.results.mapNotNull { it.toArticle() }
}
