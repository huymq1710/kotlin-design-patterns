package behavior.strategy.strategies

/**
 * Common interface for all payment strategies.
 */
interface PayStrategy {
    fun pay(paymentAmount: Int): Boolean
    fun collectPaymentDetails()
}
