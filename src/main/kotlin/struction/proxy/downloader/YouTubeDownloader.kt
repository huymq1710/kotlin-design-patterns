package struction.proxy.downloader

import struction.proxy.mediaLibrary.ThirdPartyYouTubeLib

// YouTubeDownloader.kt: Media downloader app
class YouTubeDownloader(private val api: ThirdPartyYouTubeLib) {

    fun renderVideoPage(videoId: String) {
        val video = api.getVideo(videoId)
        println("""
            
            -------------------------------
            Video page (imagine fancy HTML)
            ID: ${video.id}
            Title: ${video.title}
            Video: ${video.data}
            -------------------------------
            
        """.trimIndent())
    }

    fun renderPopularVideos() {
        val videoList = api.popularVideos()
        println("""
            
            -------------------------------
            Most popular videos on YouTube (imagine fancy HTML)""".trimIndent())

        videoList.values.forEach { video ->
            println("ID: ${video.id} / Title: ${video.title}")
        }

        println("-------------------------------\n")
    }
}
