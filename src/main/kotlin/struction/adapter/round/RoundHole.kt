package struction.adapter.round

class RoundHole(private val radius: Double) {
    fun fits(peg: RoundPeg): Boolean = radius >= peg.radius
}

open class RoundPeg(open val radius: Double = 0.0)
