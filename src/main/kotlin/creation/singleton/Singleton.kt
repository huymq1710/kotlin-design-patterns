package creation.singleton

class Singleton private constructor(val value: String) {

    companion object {
        private var instance: Singleton? = null

        fun getInstance(value: String): Singleton {
            if (instance == null) {
                Thread.sleep(1000) // Simulate slow initialization
                instance = Singleton(value)
            }
            return instance!!
        }
    }
}
