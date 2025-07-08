package structual.adapter.adapters

import structual.adapter.round.RoundPeg
import structual.adapter.square.SquarePeg
import kotlin.math.sqrt

class SquarePegAdapter(private val peg: SquarePeg) : RoundPeg() {
    // Duong cheo cua hinh vuong = √(width² + width²)
    // radius = Duong cheo / 2 = √( (width / 2)² × 2 )
    override val radius: Double
        get() = sqrt((peg.width / 2).let { it * it * 2 })
}
