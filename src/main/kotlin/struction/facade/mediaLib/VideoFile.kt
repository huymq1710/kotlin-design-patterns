package struction.facade.mediaLib

// VideoFile.kt
class VideoFile(private val name: String) {
    val codecType: String = name.substringAfterLast(".")

    fun getName(): String = name
}
