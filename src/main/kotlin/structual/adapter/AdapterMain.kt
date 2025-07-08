package structual.adapter

import structual.adapter.adapters.SquarePegAdapter
import structual.adapter.round.RoundHole
import structual.adapter.round.RoundPeg
import structual.adapter.square.SquarePeg

fun main() {
    val hole = RoundHole(5.0)
    val roundPeg = RoundPeg(5.0)

    if (hole.fits(roundPeg)) {
        println("Round peg r5 fits round hole r5.")
    }

    val smallSqPeg = SquarePeg(2.0)
    val largeSqPeg = SquarePeg(20.0)

    // The following won't compile because SquarePeg isn't a RoundPeg:
    // hole.fits(smallSqPeg)

    val smallSqAdapter = SquarePegAdapter(smallSqPeg)
    val largeSqAdapter = SquarePegAdapter(largeSqPeg)

    if (hole.fits(smallSqAdapter)) {
        println("Square peg w2 fits round hole r5.")
    }

    if (!hole.fits(largeSqAdapter)) {
        println("Square peg w20 does not fit into round hole r5.")
    }
}
