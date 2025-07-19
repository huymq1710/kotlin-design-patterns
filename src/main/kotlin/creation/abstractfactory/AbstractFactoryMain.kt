package creation.abstractfactory

import creation.abstractfactory.factory.ArtDecoFurnitureFactory
import creation.abstractfactory.factory.FurnitureFactory
import creation.abstractfactory.factory.ModernFurnitureFactory


fun main() {
    val option = "Modern"

    val factory: FurnitureFactory = when (option) {
        "Modern" -> ModernFurnitureFactory()
        "ArtDeco" -> ArtDecoFurnitureFactory()
        else -> throw Exception("Not supported yet")
    }

    val cart = factory.createChair()
    cart.buy() // "You have bought Modern Chair"
}
