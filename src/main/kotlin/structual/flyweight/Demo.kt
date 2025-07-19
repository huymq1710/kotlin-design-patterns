package structual.flyweight

import structual.flyweight.forest.Forest
import structual.flyweight.trees.TreeFactory
import java.awt.Color
import kotlin.math.floor
import kotlin.random.Random

object Demo {
    private const val CANVAS_SIZE = 500
    private const val TREES_TO_DRAW = 1_000_000
    private const val TREE_TYPES = 2

    @JvmStatic
    fun main(args: Array<String>) {
        val forest = Forest()

        repeat(floor(TREES_TO_DRAW.toDouble() / TREE_TYPES).toInt()) {
            forest.plantTree(
                random(0, CANVAS_SIZE),
                random(0, CANVAS_SIZE),
                "Summer Oak",
                Color.GREEN,
                "Oak texture stub"
            )
            forest.plantTree(
                random(0, CANVAS_SIZE),
                random(0, CANVAS_SIZE),
                "Autumn Oak",
                Color.ORANGE,
                "Autumn Oak texture stub"
            )
        }

        forest.setSize(CANVAS_SIZE, CANVAS_SIZE)
        forest.isVisible = true

        printMemoryUsage(forest)
    }

    private fun printMemoryUsage(forest: Forest) {
        val actualTreeCount = forest.getTreeCount()
        val flyweightCount = TreeFactory.getCreatedFlyweightsCount()

        println("$actualTreeCount trees drawn")
        println("---------------------")
        println("Memory usage:")
        println("Tree size (8 bytes) * $actualTreeCount + TreeTypes size (~30 bytes) * $flyweightCount")
        println("---------------------")

        val optimizedMemory = (actualTreeCount * 8 + flyweightCount * 30) / 1024 / 1024
        val unoptimizedMemory = (actualTreeCount * 38) / 1024 / 1024

        println("Total: ${optimizedMemory}MB (instead of ${unoptimizedMemory}MB)")
        println("Memory saved: ${unoptimizedMemory - optimizedMemory}MB")
    }

    private fun random(min: Int, max: Int): Int = Random.nextInt(min, max + 1)
}

//1000000 trees drawn
//---------------------
//Memory usage:
//Tree size (8 bytes) * 1000000  + TreeTypes size (~30 bytes) * 2
//---------------------
//Total: 7MB (instead of 36MB)
//Memory saved: 29MB