package struction.proxy.mediaLibrary

import kotlin.random.Random

class ThirdPartyYouTubeClass : ThirdPartyYouTubeLib {

    override fun popularVideos(): Map<String, Video> {
        connectToServer("http://www.youtube.com")
        return getRandomVideos()
    }

    override fun getVideo(videoId: String): Video {
        connectToServer("http://www.youtube.com/$videoId")
        return getSomeVideo(videoId)
    }

    // Fake methods to simulate network activity. They are as slow as real life.
    private fun random(min: Int, max: Int): Int = Random.nextInt(min, max + 1)

    private fun experienceNetworkLatency() {
        val randomLatency = random(5, 10)
        repeat(randomLatency) {
            try {
                Thread.sleep(100)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
        }
    }

    private fun connectToServer(server: String) {
        print("Connecting to $server... ")
        experienceNetworkLatency()
        println("Connected!")
    }

    private fun getRandomVideos(): Map<String, Video> {
        print("Downloading populars... ")
        experienceNetworkLatency()

        val videos = mapOf(
            "catzzzzzzzzz" to Video("sadgahasgdas", "Catzzzz.avi"),
            "mkafksangasj" to Video("mkafksangasj", "Dog play with ball.mp4"),
            "dancesvideoo" to Video("asdfas3ffasd", "Dancing video.mpq"),
            "dlsdk5jfslaf" to Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"),
            "3sdfgsd1j333" to Video("3sdfgsd1j333", "Programming lesson#1.avi")
        )

        println("Done!")
        return videos
    }

    private fun getSomeVideo(videoId: String): Video {
        print("Downloading video... ")
        experienceNetworkLatency()

        val video = Video(videoId, "Some video title")
        println("Done!")
        return video
    }
}
