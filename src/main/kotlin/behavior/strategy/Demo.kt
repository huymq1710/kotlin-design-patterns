package behavior.strategy

import behavior.strategy.order.Order
import behavior.strategy.strategies.PayByCreditCard
import behavior.strategy.strategies.PayByPayPal
import behavior.strategy.strategies.PayStrategy

/**
 * World first console e-commerce application.
 */
object Demo {
    private val priceOnProducts = mapOf(
        1 to 2200,
        2 to 1850,
        3 to 1100,
        4 to 890
    )
    
    private val order = Order()
    private var strategy: PayStrategy? = null

    @JvmStatic
    fun main(args: Array<String>) {
        while (!order.isClosed()) {
            var cost: Int
            var continueChoice: String = ""

            do {
                println("""
                    Please, select a product:
                    1 - Mother board
                    2 - CPU
                    3 - HDD
                    4 - Memory
                """.trimIndent())
                
                val choice = readLine()?.toIntOrNull() ?: continue
                cost = priceOnProducts[choice] ?: continue
                
                print("Count: ")
                val count = readLine()?.toIntOrNull() ?: continue
                order.addTotalCost(cost * count)
                
                print("Do you wish to continue selecting products? Y/N: ")
                continueChoice = readLine() ?: ""
            } while (continueChoice.equals("Y", ignoreCase = true))

            if (strategy == null) {
                println("""
                    Please, select a payment method:
                    1 - PayPal
                    2 - Credit Card
                """.trimIndent())
                
                val paymentMethod = readLine()

                // Client creates different strategies based on input from user,
                // application configuration, etc.
                strategy = when (paymentMethod) {
                    "1" -> PayByPayPal()
                    "2" -> PayByCreditCard()
                    else -> PayByCreditCard() // default
                }
            }

            // Order object delegates gathering payment data to strategy object,
            // since only strategies know what data they need to process a
            // payment.
            strategy?.let { payStrategy ->
                order.processOrder(payStrategy)

                print("Pay ${order.getTotalCost()} units or Continue shopping? P/C: ")
                val proceed = readLine()
                
                if (proceed.equals("P", ignoreCase = true)) {
                    // Finally, strategy handles the payment.
                    if (payStrategy.pay(order.getTotalCost())) {
                        println("Payment has been successful.")
                    } else {
                        println("FAIL! Please, check your data.")
                    }
                    order.setClosed()
                }
            }
        }
    }
}

/**
 * Example output:
 *
 * C:\Program Files\Java\jdk-22\bin\java.exe ... behavior.strategy.Demo
 * Please, select a product:
 * 1 - Mother board
 * 2 - CPU
 * 3 - HDD
 * 4 - Memory
 * 2
 * Count: 3
 * Do you wish to continue selecting products? Y/N: y
 * Please, select a product:
 * 1 - Mother board
 * 2 - CPU
 * 3 - HDD
 * 4 - Memory
 * 3
 * Count: 1
 * Do you wish to continue selecting products? Y/N: y
 * Please, select a product:
 * 1 - Mother board
 * 2 - CPU
 * 3 - HDD
 * 4 - Memory
 * 1
 * Count: 1
 * Do you wish to continue selecting products? Y/N: n
 * Please, select a payment method:
 * 1 - PayPal
 * 2 - Credit Card
 * 1
 * Enter the user's email: huy17101999@gmail.com
 * Enter the password: huy1710
 * Wrong email or password!
 * Enter the user's email: john@amazon.eu
 * Enter the password: qwerty
 * Data verification has been successful.
 * Pay 8850 units or Continue shopping? P/C: p
 * Paying 8850 using PayPal.
 * Payment has been successful.
 */
