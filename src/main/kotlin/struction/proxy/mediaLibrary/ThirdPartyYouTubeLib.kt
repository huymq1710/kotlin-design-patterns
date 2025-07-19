package struction.proxy.mediaLibrary

interface ThirdPartyYouTubeLib {
    fun popularVideos(): Map<String, Video>
    fun getVideo(videoId: String): Video
}

data class Video(
    val id: String,
    val title: String,
    val data: String = "Random video."
)
