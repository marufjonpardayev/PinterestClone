package com.example.pinterestclone.model

import java.io.Serializable

typealias Welcome = ArrayList<WelcomeElement>

data class WelcomeElement (
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val promotedAt: String? = null,
    val width: Long,
    val height: Long,
    val color: String,
    val blurHash: String,
    val description: String? = null,
    val altDescription: Any? = null,
    val urls: Urls,
    val links: WelcomeLinks,
    val categories: List<Any?>,
    val likes: Long,
    val likedByUser: Boolean,
    val currentUserCollections: List<Any?>,
    val sponsorship: Sponsorship? = null,
    val topicSubmissions: TopicSubmissions,
    val user: User
):Serializable

data class WelcomeLinks (
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
)

data class Sponsorship (
    val impressionUrls: List<String>,
    val tagline: String,
    val taglineURL: String,
    val sponsor: User
)

data class User (
    val id: String,
    val updatedAt: String,
    val username: String,
    val name: String,
    val firstName: String,
    val lastName: String? = null,
    val twitterUsername: String? = null,
    val portfolioURL: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val links: UserLinks,
    val profileImage: ProfileImage,
    val instagramUsername: String? = null,
    val totalCollections: Long,
    val totalLikes: Long,
    val totalPhotos: Long,
    val acceptedTos: Boolean,
    val forHire: Boolean,
    val social: Social
)

data class UserLinks (
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
)

data class ProfileImage (
    val small: String,
    val medium: String,
    val large: String
)

data class Social (
    val instagramUsername: String? = null,
    val portfolioURL: String? = null,
    val twitterUsername: String? = null,
    val paypalEmail: Any? = null
)

data class TopicSubmissions (
    val travel: Travel? = null,
    val wallpapers: Travel? = null
)

data class Travel (
    val status: String
)

data class Urls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    val smallS3: String
)
