package dsl.builder

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A delegate for lazy property initialization that tracks if the property has been initialized.
 * This is particularly useful for knowing whether a lazy-initialized property has been accessed.
 *
 * @param T The type of the property being lazily initialized.
 * @param initializer The lambda that initializes the property.
 */
internal class LazyInitTracker<T>(initializer: () -> T) : ReadOnlyProperty<Any?, T> {
    private var initialized = false

    private val lazyValue: Lazy<T> = lazy {
        initialized = true
        initializer()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = lazyValue.value

    /**
     * Checks if the property has been initialized.
     *
     * @return True if the property has been initialized, false otherwise.
     */
    fun isInitialized() = initialized
}
