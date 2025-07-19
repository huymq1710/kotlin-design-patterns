package creation.abstractfactory.factory

import creation.abstractfactory.chair.Chair
import creation.abstractfactory.chair.Sofa

interface FurnitureFactory {
    fun createChair(): Chair
    fun createSofa(): Sofa
}
