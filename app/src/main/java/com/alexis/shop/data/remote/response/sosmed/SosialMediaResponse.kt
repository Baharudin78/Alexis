package com.alexis.shop.data.remote.response.sosmed

import com.alexis.shop.domain.model.sosmed.SosialMediaModel

data class SosialMediaResponse(
    val code: Int,
    val `data`: SosialMediaItem,
    val error: Any,
    val meta: Any,
    val status: String
)

data class SosialMediaItem(
    val item: SosialMedia? = null
)

data class SosialMedia(
    val facebook_icon: String? = null,
    val facebook_link: String? = null,
    val google_play_icon: String? = null,
    val google_play_link: String? = null,
    val id: Int? = null,
    val instagram_icon: String? = null,
    val instagram_link: String? = null,
    val tiktok_icon: String? = null,
    val tiktok_link: String? = null,
    val youtube_icon: String? = null,
    val youtube_link: String? = null
)

fun SosialMedia.toSosialMediaModel() : SosialMediaModel{
    return SosialMediaModel(
        facebookIcon = facebook_icon,
        facebookLink = facebook_link,
        googlePlayIcon = google_play_icon,
        googlePlayLink = google_play_link,
        id = id,
        instagramIcon = instagram_icon,
        instagramLink = instagram_link,
        tiktokIcon = tiktok_icon,
        tiktokLink = tiktok_link,
        youtubeIcon = youtube_icon,
        youtubeLink = youtube_link
    )
}