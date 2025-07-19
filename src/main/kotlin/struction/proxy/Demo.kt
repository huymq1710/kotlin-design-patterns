package struction.proxy

import struction.proxy.downloader.YouTubeDownloader
import struction.proxy.mediaLibrary.ThirdPartyYouTubeClass
import struction.proxy.proxy.YouTubeCacheProxy
import kotlin.system.measureTimeMillis

object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        val naiveDownloader = YouTubeDownloader(ThirdPartyYouTubeClass())
        val smartDownloader = YouTubeDownloader(YouTubeCacheProxy())

        val naiveTime = test(naiveDownloader)
        val smartTime = test(smartDownloader)

        println("Time saved by caching proxy: ${naiveTime - smartTime}ms")
    }

    private fun test(downloader: YouTubeDownloader): Long {
        return measureTimeMillis {
            // User behavior in our app:
            downloader.renderPopularVideos()
            downloader.renderVideoPage("catzzzzzzzzz")
            downloader.renderPopularVideos()
            downloader.renderVideoPage("dancesvideoo")

            // Users might visit the same page quite often.
            downloader.renderVideoPage("catzzzzzzzzz")
            downloader.renderVideoPage("someothervid")
        }.also { time ->
            println("Time elapsed: ${time}ms")
        }
    }
}

//...
//Connecting to http://www.youtube.com/someothervid... Connected!
//Downloading video... Done!
//Retrieved video 'someothervid' from cache.
//
//-------------------------------
//Video page (imagine fancy HTML)
//ID: someothervid
//Title: Some video title
//Video: Random video.
//-------------------------------
//
//Time elapsed: 7271ms
//Time saved by caching proxy: 3459ms
