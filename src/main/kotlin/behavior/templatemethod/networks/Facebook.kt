package behavior.templatemethod.networks

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Concrete implementation of Network for Facebook.
 * 
 * This class implements the abstract methods defined in the Network base class
 * with Facebook-specific behavior.
 */
class Facebook(userName: String, password: String) : Network() {
    
    init {
        this.userName = userName
        this.password = password
    }

    override fun logIn(userName: String, password: String): Boolean {
        println("\nChecking user's parameters")
        println("Name: $userName")
        print("Password: ")
        repeat(password.length) { print("*") }
        
        simulateNetworkLatency()
        println("\n\nLogIn success on Facebook")
        return true
    }

    override fun sendData(data: ByteArray): Boolean {
        val messagePosted = true
        return if (messagePosted) {
            println("Message: '${String(data)}' was posted on Facebook")
            true
        } else {
            false
        }
    }

    override fun logOut() {
        println("User: '$userName' was logged out from Facebook")
    }

    /**
     * Simulates network latency using Kotlin coroutines.
     * This is a more idiomatic Kotlin approach compared to Thread.sleep().
     */
    private fun simulateNetworkLatency() {
        runBlocking {
            println()
            repeat(10) {
                print(".")
                delay(500)
            }
        }
    }
}
