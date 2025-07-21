package behavior.templatemethod.networks

/**
 * Base class of social network.
 * 
 * This class defines the template method that implements the algorithm's skeleton.
 * Subclasses should override abstract methods to provide specific implementations.
 */
abstract class Network {
    protected var userName: String = ""
    protected var password: String = ""

    /**
     * Template method that defines the algorithm skeleton.
     * Publish the data to whatever network.
     */
    fun post(message: String): Boolean {
        // Authenticate before posting. Every network uses a different
        // authentication method.
        return if (logIn(userName, password)) {
            // Send the post data.
            val result = sendData(message.toByteArray())
            logOut()
            result
        } else {
            false
        }
    }

    /**
     * Abstract method to be implemented by concrete classes.
     * Each social network has its own authentication mechanism.
     */
    protected abstract fun logIn(userName: String, password: String): Boolean

    /**
     * Abstract method to be implemented by concrete classes.
     * Each social network has its own data sending protocol.
     */
    protected abstract fun sendData(data: ByteArray): Boolean

    /**
     * Abstract method to be implemented by concrete classes.
     * Each social network has its own logout procedure.
     */
    protected abstract fun logOut()
}
