package creational.abstractfactory

import creational.abstractfactory.factory.ArtDecoFurnitureFactory
import creational.abstractfactory.factory.FurnitureFactory
import creational.abstractfactory.factory.ModernFurnitureFactory


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
