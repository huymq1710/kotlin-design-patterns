package behavior.chain.server

import behavior.chain.middleware.Middleware

class Server {
    private val users = mutableMapOf<String, String>()
    private var middleware: Middleware? = null

    /**
     * Client passes a chain of object to server. This improves flexibility and
     * makes testing the server class easier.
     */
    fun setMiddleware(middleware: Middleware) {
        this.middleware = middleware
    }

    /**
     * Server gets email and password from client and sends the authorization
     * request to the chain.
     */
    fun logIn(email: String, password: String): Boolean {
        return middleware?.check(email, password)?.also { success ->
            if (success) {
                println("Authorization has been successful!")
                // Do something useful here for authorized users.
            }
        } ?: false
    }

    fun register(email: String, password: String) {
        users[email] = password
    }

    fun hasEmail(email: String): Boolean = users.containsKey(email)

    fun isValidPassword(email: String, password: String): Boolean = users[email] == password
}
