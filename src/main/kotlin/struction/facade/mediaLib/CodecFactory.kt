package struction.facade.mediaLib

object CodecFactory {
    fun extract(file: VideoFile): Codec {
        return when (file.codecType) {
            "mp4" -> {
                println("CodecFactory: extracting mpeg audio...")
                MPEG4CompressionCodec()
            }
            else -> {
                println("CodecFactory: extracting ogg audio...")
                OggCompressionCodec()
            }
        }
    }
}
