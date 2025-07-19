package struction.proxy.proxy

import struction.proxy.mediaLibrary.ThirdPartyYouTubeClass
import struction.proxy.mediaLibrary.ThirdPartyYouTubeLib
import struction.proxy.mediaLibrary.Video

// YouTubeCacheProxy.kt: Caching proxy
class YouTubeCacheProxy : ThirdPartyYouTubeLib {
    private val youtubeService: ThirdPartyYouTubeLib = ThirdPartyYouTubeClass()
    private val cachePopular = mutableMapOf<String, Video>()
    private val cacheAll = mutableMapOf<String, Video>()

    override fun popularVideos(): Map<String, Video> {
        return if (cachePopular.isEmpty()) {
            val videos = youtubeService.popularVideos()
            cachePopular.putAll(videos)
            cachePopular
        } else {
            println("Retrieved list from cache.")
            cachePopular
        }
    }

    override fun getVideo(videoId: String): Video {
        return cacheAll.getOrPut(videoId) {
            youtubeService.getVideo(videoId)
        }.also { video ->
            if (cacheAll.containsKey(videoId) && cacheAll[videoId] == video) {
                // Video was already in cache
                println("Retrieved video '$videoId' from cache.")
            }
        }
    }

    fun reset() {
        cachePopular.clear()
        cacheAll.clear()
    }
}
