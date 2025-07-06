package creational.abstractfactory.sofa

import creational.abstractfactory.chair.Sofa

class ModernSofa: Sofa {
    override fun buy() {
        println("You have bought Modern Sofa")
    }
}
