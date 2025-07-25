package behavior.chain.middleware

/**
 * ConcreteHandler. Checks whether there are too many failed login requests.
 */
class ThrottlingMiddleware(private val requestPerMinute: Int) : Middleware() {
    private var request = 0
    private var currentTime = System.currentTimeMillis()

    /**
     * Please note that checkNext() call can be inserted both in the beginning
     * of this method and in the end.
     *
     * This gives much more flexibility than a simple loop over all middleware
     * objects. For instance, an element of a chain can change the order of
     * checks by running its check after all other checks.
     */
    override fun check(email: String, password: String): Boolean {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0
            currentTime = System.currentTimeMillis()
        }

        request++

        if (request > requestPerMinute) {
            println("Request limit exceeded!")
            // Note: Thread.stop() is deprecated and unsafe. Using exception instead.
            throw SecurityException("Too many requests")
        }

        return checkNext(email, password)
    }
}
