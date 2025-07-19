package struction.decorator.decorators

interface DataSource {
    fun writeData(data: String)
    fun readData(): String
}
