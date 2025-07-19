package behavior.chain.middleware

abstract class Middleware {
    private var next: Middleware? = null

    companion object {
        /**
         * Builds chains of middleware objects.
         */
        fun link(first: Middleware, vararg chain: Middleware): Middleware {
            var head = first
            chain.forEach { nextInChain ->
                head.next = nextInChain
                head = nextInChain
            }
            return first
        }
    }

    /**
     * Subclasses will implement this method with concrete checks.
     */
    abstract fun check(email: String, password: String): Boolean

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected fun checkNext(email: String, password: String): Boolean {
        return next?.check(email, password) ?: true
    }
}
