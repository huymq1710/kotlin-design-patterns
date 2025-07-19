package creation.abstractfactory.factory

import creation.abstractfactory.chair.ArtDecoChair
import creation.abstractfactory.chair.Chair
import creation.abstractfactory.chair.Sofa
import creation.abstractfactory.sofa.ArtDecoSofa

class ArtDecoFurnitureFactory: FurnitureFactory {
    override fun createChair(): Chair {
        return ArtDecoChair()
    }

    override fun createSofa(): Sofa {
        return ArtDecoSofa()

    }
}
