package behavior.templatemethod

import behavior.templatemethod.networks.Facebook
import behavior.templatemethod.networks.Network
import behavior.templatemethod.networks.Twitter

/**
 * Demo class showcasing the Template Method pattern.
 * 
 * This demonstrates how different social networks implement the same 
 * posting algorithm with their own specific steps.
 */
object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        print("Input user name: ")
        val userName = readlnOrNull() ?: return
        
        print("Input password: ")
        val password = readlnOrNull() ?: return

        print("Input message: ")
        val message = readlnOrNull() ?: return

        println("""
            
            Choose social network for posting message:
            1 - Facebook
            2 - Twitter
        """.trimIndent())

        val choice = readlnOrNull()?.toIntOrNull()

        // Create proper network object and send the message using when expression
        val network: Network? = when (choice) {
            1 -> Facebook(userName, password)
            2 -> Twitter(userName, password)
            else -> {
                println("Invalid choice!")
                null
            }
        }

        network?.post(message) ?: println("Failed to create network instance")
    }
}

/**
 * Example output:
 *
 * Input user name: Jhonatan
 * Input password: qswe
 * Input message: Helloooo Huy Mac
 *
 * Choose social network for posting message:
 * 1 - Facebook
 * 2 - Twitter
 * 1
 *
 * Checking user's parameters
 * Name: Jhonatan
 * Password: ****
 * ..........
 *
 * LogIn success on Facebook
 * Message: 'Helloooo Huy Mac' was posted on Facebook
 * User: 'Jhonatan' was logged out from Facebook
 *
 * Process finished with exit code 0
 */
