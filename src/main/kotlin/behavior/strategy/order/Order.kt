package behavior.strategy.order

import behavior.strategy.strategies.PayStrategy

/**
 * Order class. Doesn't know the concrete payment method (strategy) user has
 * picked. It uses common strategy interface to delegate collecting payment data
 * to strategy object. It can be used to save order to database.
 */
class Order {
    private var totalCost: Int = 0
    private var isClosed: Boolean = false

    fun processOrder(strategy: PayStrategy) {
        strategy.collectPaymentDetails()
        // Here we could collect and store payment data from the strategy.
    }

    fun addTotalCost(cost: Int) {
        totalCost += cost
    }

    fun getTotalCost(): Int = totalCost

    fun isClosed(): Boolean = isClosed

    fun setClosed() {
        isClosed = true
    }
}
