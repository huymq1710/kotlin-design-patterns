package creational.abstractfactory.factory

import creational.abstractfactory.chair.Chair
import creational.abstractfactory.chair.Sofa

interface FurnitureFactory {
    fun createChair(): Chair
    fun createSofa(): Sofa
}
