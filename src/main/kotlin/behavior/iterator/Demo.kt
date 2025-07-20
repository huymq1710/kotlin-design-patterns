package behavior.iterator

import behavior.iterator.profile.Profile
import behavior.iterator.social_networks.SocialNetwork
import behavior.iterator.social_networks.impl.Facebook
import behavior.iterator.social_networks.impl.LinkedIn
import behavior.iterator.spammer.SocialSpammer
import behavior.iterator.iterators.forEachAsync
import kotlinx.coroutines.runBlocking

/**
 * Demo application showcasing the Iterator pattern in Kotlin
 */
object Demo {

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        println("Please specify social network to target spam tool (default: Facebook):")
        println("1. Facebook")
        println("2. LinkedIn")

        val choice = readLine() ?: "1"

        val network: SocialNetwork = when (choice) {
            "2" -> LinkedIn(createTestProfiles())
            else -> Facebook(createTestProfiles())
        }

        val spammer = SocialSpammer(network)

        // Send spam to friends
        spammer.sendSpamToFriends(
            "anna.smith@bing.com",
            "Hey! This is Anna's friend Josh. Can you do me a favor and like this post [link]?"
        )

        // Send spam to coworkers
        spammer.sendSpamToCoworkers(
            "anna.smith@bing.com",
            "Hey! This is Anna's boss Jason. Anna told me you would be interested in [link]."
        )

        // Demonstrate advanced iterator usage
        println("\n=== Advanced Iterator Usage ===")
        demonstrateAdvancedUsage(network)
    }

    private suspend fun demonstrateAdvancedUsage(network: SocialNetwork) {
        val friendsIterator = network.createFriendsIterator("anna.smith@bing.com")

        // Using extension functions
        println("\nUsing forEachAsync:")
        friendsIterator.forEachAsync { profile ->
            println("Friend: ${profile.name} (${profile.email})")
        }

        // Reset and use as sequence
        friendsIterator.reset()
        println("\nUsing asSequence with standard Kotlin operations:")
        friendsIterator.asSequence()
            .take(2)
            .forEach { profile ->
                println("First 2 friends: ${profile.name}")
            }
    }

    private fun createTestProfiles(): List<Profile> {
        return listOf(
            Profile(
                "anna.smith@bing.com",
                "Anna Smith",
                "friends:mad_max@ya.com",
                "friends:catwoman@yahoo.com",
                "coworkers:sam@amazon.com"
            ),
            Profile(
                "mad_max@ya.com",
                "Maximilian",
                "friends:anna.smith@bing.com",
                "coworkers:sam@amazon.com"
            ),
            Profile(
                "bill@microsoft.eu",
                "Billie",
                "coworkers:avanger@ukr.net"
            ),
            Profile(
                "avanger@ukr.net",
                "John Day",
                "coworkers:bill@microsoft.eu"
            ),
            Profile(
                "sam@amazon.com",
                "Sam Kitting",
                "coworkers:anna.smith@bing.com",
                "coworkers:mad_max@ya.com",
                "friends:catwoman@yahoo.com"
            ),
            Profile(
                "catwoman@yahoo.com",
                "Liza",
                "friends:anna.smith@bing.com",
                "friends:sam@amazon.com"
            )
        )
    }
}

/**
Please specify social network to target spam tool (default: Facebook):
1. Facebook
2. LinkedIn
1

Iterating over friends...

Facebook: Loading 'friends' list of 'anna.smith@bing.com' over the network...
Facebook: Loading profile 'mad_max@ya.com' over the network...
Sent message to: 'mad_max@ya.com'. Message body: 'Hey! This is Anna's friend Josh. Can you do me a favor and like this post [link]?'
Facebook: Loading profile 'catwoman@yahoo.com' over the network...
Sent message to: 'catwoman@yahoo.com'. Message body: 'Hey! This is Anna's friend Josh. Can you do me a favor and like this post [link]?'

Iterating over coworkers...

Facebook: Loading 'coworkers' list of 'anna.smith@bing.com' over the network...
Facebook: Loading profile 'sam@amazon.com' over the network...
Sent message to: 'sam@amazon.com'. Message body: 'Hey! This is Anna's boss Jason. Anna told me you would be interested in [link].'

=== Advanced Iterator Usage ===

Using forEachAsync:
Facebook: Loading 'friends' list of 'anna.smith@bing.com' over the network...
Facebook: Loading profile 'mad_max@ya.com' over the network...
Friend: Maximilian (mad_max@ya.com)
Facebook: Loading profile 'catwoman@yahoo.com' over the network...
Friend: Liza (catwoman@yahoo.com)

Using asSequence with standard Kotlin operations:
First 2 friends: Maximilian
First 2 friends: Liza
 */
