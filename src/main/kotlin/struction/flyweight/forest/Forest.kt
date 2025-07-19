package struction.flyweight.forest

import struction.flyweight.trees.Tree
import struction.flyweight.trees.TreeFactory
import javax.swing.JFrame
import java.awt.Color
import java.awt.Graphics

// Forest.kt: Forest, which we draw
class Forest : JFrame() {
    private val trees = mutableListOf<Tree>()

    fun plantTree(x: Int, y: Int, name: String, color: Color, otherTreeData: String) {
        val type = TreeFactory.getTreeType(name, color, otherTreeData)
        val tree = Tree(x, y, type)
        trees.add(tree)
    }

    override fun paint(graphics: Graphics) {
        trees.forEach { tree ->
            tree.draw(graphics)
        }
    }

    fun getTreeCount(): Int = trees.size
}
