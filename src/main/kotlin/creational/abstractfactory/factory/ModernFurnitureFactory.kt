package creational.abstractfactory.factory

import creational.abstractfactory.chair.Chair
import creational.abstractfactory.chair.ModernChair
import creational.abstractfactory.chair.Sofa
import creational.abstractfactory.sofa.ModernSofa

class ModernFurnitureFactory: FurnitureFactory {
    override fun createChair(): Chair {
        return ModernChair()
    }

    override fun createSofa(): Sofa {
        return ModernSofa()

    }
}
