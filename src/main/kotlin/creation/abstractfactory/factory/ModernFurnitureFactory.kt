package creation.abstractfactory.factory

import creation.abstractfactory.chair.Chair
import creation.abstractfactory.chair.ModernChair
import creation.abstractfactory.chair.Sofa
import creation.abstractfactory.sofa.ModernSofa

class ModernFurnitureFactory: FurnitureFactory {
    override fun createChair(): Chair {
        return ModernChair()
    }

    override fun createSofa(): Sofa {
        return ModernSofa()

    }
}
