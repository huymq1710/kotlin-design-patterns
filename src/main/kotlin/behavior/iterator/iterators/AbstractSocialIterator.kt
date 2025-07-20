package behavior.iterator.iterators

import behavior.iterator.profile.Profile
import kotlinx.coroutines.*

/**
 * Abstract base class for social network iterators with lazy loading and caching
 */
abstract class AbstractSocialIterator<T>(
    protected val socialNetwork: T,
    protected val contactType: String,
    protected val email: String
) : ProfileIterator {

    protected var currentPosition = 0
    protected val emails = mutableListOf<String>()
    protected val profiles = mutableListOf<Profile?>()
    protected var isLoaded = false

    protected abstract suspend fun loadContactEmails(): List<String>
    protected abstract suspend fun loadProfile(email: String): Profile?

    private suspend fun lazyLoad() {
        if (!isLoaded) {
            val contactEmails = loadContactEmails()
            emails.addAll(contactEmails)
            // Initialize profiles list with nulls for lazy loading
            profiles.addAll(List(contactEmails.size) { null })
            isLoaded = true
        }
    }

    override suspend fun hasNextAsync(): Boolean {
        lazyLoad()
        return currentPosition < emails.size
    }

    override fun hasNext(): Boolean = runBlocking { hasNextAsync() }

    override suspend fun nextAsync(): Profile {
        if (!hasNextAsync()) {
            throw NoSuchElementException("No more profiles available")
        }

        val friendEmail = emails[currentPosition]
        var friendProfile = profiles[currentPosition]

        if (friendProfile == null) {
            friendProfile = loadProfile(friendEmail)
            profiles[currentPosition] = friendProfile
        }

        currentPosition++
        return friendProfile ?: throw IllegalStateException("Failed to load profile for $friendEmail")
    }

    override fun next(): Profile = runBlocking { nextAsync() }

    override fun reset() {
        currentPosition = 0
    }
}
