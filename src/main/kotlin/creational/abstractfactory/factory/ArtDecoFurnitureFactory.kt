package creational.abstractfactory.factory

import creational.abstractfactory.chair.ArtDecoChair
import creational.abstractfactory.chair.Chair
import creational.abstractfactory.chair.Sofa
import creational.abstractfactory.sofa.ArtDecoSofa

class ArtDecoFurnitureFactory: FurnitureFactory {
    override fun createChair(): Chair {
        return ArtDecoChair()
    }

    override fun createSofa(): Sofa {
        return ArtDecoSofa()

    }
}
