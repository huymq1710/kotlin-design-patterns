package behavior.iterator.iterators.impl

import behavior.iterator.iterators.AbstractSocialIterator
import behavior.iterator.profile.Profile
import behavior.iterator.social_networks.impl.Facebook

/**
 * Facebook-specific iterator implementation
 */
class FacebookIterator(
    facebook: Facebook,
    contactType: String,
    email: String
) : AbstractSocialIterator<Facebook>(facebook, contactType, email) {

    override suspend fun loadContactEmails(): List<String> {
        return socialNetwork.requestProfileFriendsFromFacebook(email, contactType)
    }

    override suspend fun loadProfile(email: String): Profile? {
        return socialNetwork.requestProfileFromFacebook(email)
    }
}
