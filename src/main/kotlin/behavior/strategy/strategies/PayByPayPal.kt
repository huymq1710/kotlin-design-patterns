package behavior.strategy.strategies

/**
 * Concrete strategy. Implements PayPal payment method.
 */
class PayByPayPal : PayStrategy {
    
    companion object {
        private val DATABASE = mapOf(
            "amanda1985" to "amanda@ya.com",
            "qwerty" to "john@amazon.eu"
        )
    }
    
    private var email: String = ""
    private var password: String = ""
    private var signedIn: Boolean = false

    /**
     * Collect customer's data.
     */
    override fun collectPaymentDetails() {
        while (!signedIn) {
            print("Enter the user's email: ")
            email = readLine() ?: ""
            print("Enter the password: ")
            password = readLine() ?: ""
            
            if (verify()) {
                println("Data verification has been successful.")
            } else {
                println("Wrong email or password!")
            }
        }
    }

    private fun verify(): Boolean {
        signedIn = email == DATABASE[password]
        return signedIn
    }

    /**
     * Process payment if user is signed in.
     */
    override fun pay(paymentAmount: Int): Boolean {
        return if (signedIn) {
            println("Paying $paymentAmount using PayPal.")
            true
        } else {
            false
        }
    }
}
