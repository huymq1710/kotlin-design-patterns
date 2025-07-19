package creation.abstractfactory.sofa

import creation.abstractfactory.chair.Sofa

class ArtDecoSofa: Sofa {
    override fun buy() {
        println("You have bought ArtDeco Sofa")
    }
}
