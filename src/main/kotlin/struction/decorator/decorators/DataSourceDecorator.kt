package struction.decorator.decorators

abstract class DataSourceDecorator(
    private val wrappee: DataSource
) : DataSource {

    override fun writeData(data: String) {
        wrappee.writeData(data)
    }

    override fun readData(): String {
        return wrappee.readData()
    }
}
