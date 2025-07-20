package behavior.iterator.iterators

import behavior.iterator.profile.Profile

/**
 * Profile iterator interface extending Kotlin's Iterator with additional features
 */
interface ProfileIterator : Iterator<Profile> {
    fun reset()
    suspend fun hasNextAsync(): Boolean = hasNext()
    suspend fun nextAsync(): Profile = next()
}
