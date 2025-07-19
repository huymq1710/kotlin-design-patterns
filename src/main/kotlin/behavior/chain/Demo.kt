package behavior.chain

import behavior.chain.middleware.Middleware
import behavior.chain.middleware.RoleCheckMiddleware
import behavior.chain.middleware.ThrottlingMiddleware
import behavior.chain.middleware.UserExistsMiddleware
import behavior.chain.server.Server

object Demo {
    private lateinit var server: Server

    private fun init() {
        server = Server().apply {
            register("admin@example.com", "admin_pass")
            register("user@example.com", "user_pass")
        }

        // All checks are linked. Client can build various chains using the same components.
        val middleware = Middleware.link(
            ThrottlingMiddleware(2),
            UserExistsMiddleware(server),
            RoleCheckMiddleware()
        )

        // Server gets a chain from client code.
        server.setMiddleware(middleware)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        init()

        var success: Boolean
        do {
            try {
                print("Enter email: ")
                val email = readLine() ?: ""
                print("Input password: ")
                val password = readLine() ?: ""
                success = server.logIn(email, password)
            } catch (e: SecurityException) {
                println("Security error: ${e.message}")
                success = false
                break // Exit on security violation
            }
        } while (!success)
    }
}
