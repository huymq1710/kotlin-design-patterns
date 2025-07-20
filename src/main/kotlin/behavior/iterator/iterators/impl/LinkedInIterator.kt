package behavior.iterator.iterators.impl

import behavior.iterator.iterators.AbstractSocialIterator
import behavior.iterator.profile.Profile
import behavior.iterator.social_networks.impl.LinkedIn

/**
 * LinkedIn-specific iterator implementation
 */
class LinkedInIterator(
    linkedIn: LinkedIn,
    contactType: String,
    email: String
) : AbstractSocialIterator<LinkedIn>(linkedIn, contactType, email) {

    override suspend fun loadContactEmails(): List<String> {
        return socialNetwork.requestRelatedContactsFromLinkedInAPI(email, contactType)
    }

    override suspend fun loadProfile(email: String): Profile? {
        return socialNetwork.requestContactInfoFromLinkedInAPI(email)
    }
}
