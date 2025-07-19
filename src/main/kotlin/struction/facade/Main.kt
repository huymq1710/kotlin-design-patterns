package struction.facade

import struction.facade.facade.VideoConversionFacade

fun main() {
    val converter = VideoConversionFacade()
    val mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4")
    // ...
}
