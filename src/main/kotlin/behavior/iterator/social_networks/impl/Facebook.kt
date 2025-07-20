package behavior.iterator.social_networks.impl

import behavior.iterator.iterators.ProfileIterator
import behavior.iterator.iterators.impl.FacebookIterator
import behavior.iterator.profile.Profile
import behavior.iterator.social_networks.AbstractSocialNetwork

/**
 * Facebook social network implementation
 */
class Facebook(profiles: List<Profile>? = null) : AbstractSocialNetwork(
    profiles?.toMutableList() ?: mutableListOf()
) {

    suspend fun requestProfileFromFacebook(profileEmail: String): Profile? {
        simulateNetworkLatency()
        println("Facebook: Loading profile '$profileEmail' over the network...")
        return findProfile(profileEmail)
    }

    suspend fun requestProfileFriendsFromFacebook(
        profileEmail: String,
        contactType: String
    ): List<String> {
        simulateNetworkLatency()
        println("Facebook: Loading '$contactType' list of '$profileEmail' over the network...")

        return findProfile(profileEmail)?.getContacts(contactType) ?: emptyList()
    }

    override fun createFriendsIterator(profileEmail: String): ProfileIterator {
        return FacebookIterator(this, "friends", profileEmail)
    }

    override fun createCoworkersIterator(profileEmail: String): ProfileIterator {
        return FacebookIterator(this, "coworkers", profileEmail)
    }
}
