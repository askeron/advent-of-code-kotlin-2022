fun main() {
    fun getDayInstance(dayNumber: Int): Day<*>? {
        return try {
            Class.forName("Day${dayNumber.toString().padStart(2, '0')}")
                .getDeclaredConstructor().newInstance() as Day<*>
        } catch (e: ClassNotFoundException) {
            null
        }
    }

    (25 downTo 1).asSequence()
        .mapNotNull { getDayInstance(it) }
        .first()
        .run()
}
