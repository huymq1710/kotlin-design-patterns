package struction.facade

interface Codec

class MPEG4CompressionCodec : Codec {
    val type = "mp4"
}

// OggCompressionCodec.kt
class OggCompressionCodec : Codec {
    val type = "ogg"
}
