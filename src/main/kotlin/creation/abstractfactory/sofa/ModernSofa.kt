package creation.abstractfactory.sofa

import creation.abstractfactory.chair.Sofa

class ModernSofa: Sofa {
    override fun buy() {
        println("You have bought Modern Sofa")
    }
}
