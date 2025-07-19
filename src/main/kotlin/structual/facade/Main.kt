package structual.facade

fun main() {
    val converter = VideoConversionFacade()
    val mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4")
    // ...
}
