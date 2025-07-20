package behavior.iterator.spammer

import behavior.iterator.iterators.ProfileIterator
import behavior.iterator.social_networks.SocialNetwork

/**
 * Message sending application with coroutine support
 */
class SocialSpammer(private val network: SocialNetwork) {
    private var iterator: ProfileIterator? = null

    suspend fun sendSpamToFriends(profileEmail: String, message: String) {
        println("\nIterating over friends...\n")
        iterator = network.createFriendsIterator(profileEmail)
        sendSpamToAll(message)
    }

    suspend fun sendSpamToCoworkers(profileEmail: String, message: String) {
        println("\nIterating over coworkers...\n")
        iterator = network.createCoworkersIterator(profileEmail)
        sendSpamToAll(message)
    }

    private suspend fun sendSpamToAll(message: String) {
        iterator?.let { iter ->
            while (iter.hasNextAsync()) {
                val profile = iter.nextAsync()
                sendMessage(profile.email, message)
            }
        }
    }

    private fun sendMessage(email: String, message: String) {
        println("Sent message to: '$email'. Message body: '$message'")
    }
}
