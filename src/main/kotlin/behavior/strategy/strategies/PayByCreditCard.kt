package behavior.strategy.strategies

/**
 * Concrete strategy. Implements credit card payment method.
 */
class PayByCreditCard : PayStrategy {
    
    private var card: CreditCard? = null

    /**
     * Collect credit card data.
     */
    override fun collectPaymentDetails() {
        print("Enter the card number: ")
        val number = readLine() ?: ""
        print("Enter the card expiration date 'mm/yy': ")
        val date = readLine() ?: ""
        print("Enter the CVV code: ")
        val cvv = readLine() ?: ""
        
        card = CreditCard(number, date, cvv)
        
        // Validate credit card number...
    }

    /**
     * After card validation we can charge customer's credit card.
     */
    override fun pay(paymentAmount: Int): Boolean {
        return card?.let { creditCard ->
            println("Paying $paymentAmount using Credit Card.")
            creditCard.amount -= paymentAmount
            true
        } ?: false
    }
}
