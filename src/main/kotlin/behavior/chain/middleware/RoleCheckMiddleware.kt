package behavior.chain.middleware

/**
 * ConcreteHandler. Checks a user's role.
 */
class RoleCheckMiddleware : Middleware() {

    override fun check(email: String, password: String): Boolean {
        return if (email == "admin@example.com") {
            println("Hello, admin!")
            true
        } else {
            println("Hello, user!")
            checkNext(email, password)
        }
    }
}
