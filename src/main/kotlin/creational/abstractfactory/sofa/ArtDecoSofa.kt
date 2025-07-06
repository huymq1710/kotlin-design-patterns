package creational.abstractfactory.sofa

import creational.abstractfactory.chair.Sofa

class ArtDecoSofa: Sofa {
    override fun buy() {
        println("You have bought ArtDeco Sofa")
    }
}
