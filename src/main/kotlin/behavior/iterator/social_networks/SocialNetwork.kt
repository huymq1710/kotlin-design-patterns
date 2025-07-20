package behavior.iterator.social_networks

import behavior.iterator.iterators.ProfileIterator

/**
 * Common interface for social networks with iterator factory methods
 */
interface SocialNetwork {
    fun createFriendsIterator(profileEmail: String): ProfileIterator
    fun createCoworkersIterator(profileEmail: String): ProfileIterator
}
