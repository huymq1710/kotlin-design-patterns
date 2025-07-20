package behavior.iterator.social_networks.impl

import behavior.iterator.iterators.ProfileIterator
import behavior.iterator.iterators.impl.LinkedInIterator
import behavior.iterator.profile.Profile
import behavior.iterator.social_networks.AbstractSocialNetwork
import kotlinx.coroutines.*

/**
 * LinkedIn social network implementation
 */
class LinkedIn(contacts: List<Profile>? = null) : AbstractSocialNetwork(
    contacts?.toMutableList() ?: mutableListOf()
) {

    suspend fun requestContactInfoFromLinkedInAPI(profileEmail: String): Profile? {
        simulateNetworkLatency()
        println("LinkedIn: Loading profile '$profileEmail' over the network...")
        return findProfile(profileEmail)
    }

    suspend fun requestRelatedContactsFromLinkedInAPI(
        profileEmail: String,
        contactType: String
    ): List<String> {
        simulateNetworkLatency()
        println("LinkedIn: Loading '$contactType' list of '$profileEmail' over the network...")

        return findProfile(profileEmail)?.getContacts(contactType) ?: emptyList()
    }

    override fun createFriendsIterator(profileEmail: String): ProfileIterator {
        return LinkedInIterator(this, "friends", profileEmail)
    }

    override fun createCoworkersIterator(profileEmail: String): ProfileIterator {
        return LinkedInIterator(this, "coworkers", profileEmail)
    }
}
