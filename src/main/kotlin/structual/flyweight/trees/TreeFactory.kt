package structual.flyweight.trees

import java.awt.Color

// TreeFactory.kt: Encapsulates complexity of flyweight creation
object TreeFactory {
    private val treeTypes = mutableMapOf<String, TreeType>()

    fun getTreeType(name: String, color: Color, otherTreeData: String): TreeType {
        return treeTypes.getOrPut(name) {
            TreeType(name, color, otherTreeData)
        }
    }

    // Utility function to get the number of flyweight objects created
    fun getCreatedFlyweightsCount(): Int = treeTypes.size
}
