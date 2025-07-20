package behavior.iterator.social_networks

import behavior.iterator.profile.Profile
import kotlinx.coroutines.*

/**
 * Abstract base class with common functionality for social networks
 */
abstract class AbstractSocialNetwork(
    protected val profiles: MutableList<Profile> = mutableListOf()
) : SocialNetwork {

    protected fun findProfile(profileEmail: String): Profile? {
        return profiles.find { it.email == profileEmail }
    }

    protected suspend fun simulateNetworkLatency() {
        delay(2500) // Using coroutines instead of Thread.sleep
    }
}
