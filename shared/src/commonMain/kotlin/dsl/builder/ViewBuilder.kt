package dsl.builder

import dsl.core.FusionViewDsl
import dsl.core.view.FusionView
import dsl.core.view.viewAttr.*

/**
 * `ViewBuilder` serves as a foundational class for UI construction within the framework, providing
 * an abstract base for building UI components. Designed to be extended by specific builders like
 * LayoutBuilder and BoxBuilder, it facilitates the declarative assembly and configuration of UI elements.
 *
 * It encapsulates common functionality needed for UI elements' manipulation, such as setting attributes
 * (size, margins, color, etc.) and supports embedding and composition of different interface components.
 * Additionally, `ViewBuilder` integrates with a DSL for UI development, promoting a cleaner and more
 * intuitive codebase by enabling a declarative approach to UI design.
 *
 * By serving as a base for custom UI component builders, `ViewBuilder` enhances modularity and reusability
 * in the app development process, ensuring flexibility and scalability in UI design.
 */
@FusionViewDsl
abstract class ViewBuilder<T: FusionView> {
    private val viewAttrDelegate = LazyInitTracker { ViewAttr() }
    private val _viewAttr: ViewAttr by viewAttrDelegate
    val viewAttr: ViewAttr? get() = if (viewAttrDelegate.isInitialized()) _viewAttr else null
    var state: Map<String, String>? = null

    /**
     * Must be implemented by subclasses to build and return an instance of the view this builder is for.
     * @return An instance of type T, which extends FusionView, with all configured properties applied.
     */
    abstract fun build(): T

    /**
     * Configures the size attributes of the view.
     * @param size A lambda to configure the SizeBuilder, allowing setting of width, height, etc.
     */
    fun size(size: SizeBuilder.() -> Unit) {
        _viewAttr.size = SizeBuilder().apply(size).build()
    }

    /**
     * Sets the gravity of the view.
     * @param value A lambda returning the Gravity enum, determining how the view is positioned within its parent.
     */
    fun gravity(value: () -> Gravity) {
        _viewAttr.gravity = value.invoke()
    }

    /**
     * Configures the background of the view.
     * @param value A lambda to configure the BackgroundBuilder for setting color, radius, etc.
     */
    fun background(value: BackgroundBuilder.() -> Unit) {
        _viewAttr.background = BackgroundBuilder().apply(value).build()
    }

    /**
     * Configures the border of the view.
     * @param builder A lambda to configure the BorderBuilder for setting color and width.
     */
    fun border(builder: BorderBuilder.() -> Unit) {
        _viewAttr.border = BorderBuilder().apply(builder).build()
    }

    /**
     * Sets the transparency of the view.
     * @param value A lambda returning the alpha value as a Float (0.0 to 1.0, where 1.0 is fully opaque).
     */
    fun alpha(value: () -> Number) {
        _viewAttr.alpha = value.invoke().toDouble()
    }


    /**
     * Sets the visibility of the view.
     * @param value A lambda returning a Boolean indicating if the view is visible (true) or not (false).
     */
    fun isVisible(value: () -> Boolean) {
        _viewAttr.isVisible = value.invoke()
    }

    /**
     * Sets an action to be performed when the view is tapped.
     * @param value A lambda returning the action identifier or command as a String.
     */
    fun onTap(value: () -> String) {
        _viewAttr.onTap = value.invoke()
    }

    /**
     * Sets the state of the view. Can be used to define custom properties or states.
     * @param map A lambda returning a Map of state properties and values.
     */
    fun state(vararg map: Pair<String, Any>) {
        state = map.toMap().mapValues { it.value.toString()}
    }


    /**
     * A builder class designed for constructing and managing the size properties of a UI component.
     * This includes dynamic dimensions like width, height, and their minimum and maximum values.
     */
    inner class SizeBuilder {

        private val sizeDelegate = LazyInitTracker { Size() }
        private val _size: Size by sizeDelegate
        private val size: Size? get() = if (sizeDelegate.isInitialized()) _size else null

        /**
         * Sets the width of the UI component.
         * @param value A lambda that returns the width value as Any type, which is then converted to Dimension.
         */
        fun width(value: () -> Any) {
            _size.width = getSize(value.invoke())
        }

        /**
         * Sets the height of the UI component.
         * @param value A lambda that returns the height value as Any type, which is then converted to Dimension.
         */
        fun height(value: () -> Any) {
            _size.height = getSize(value.invoke())
        }

        /**
         * Sets the minimum width of the UI component.
         * @param value A lambda that returns the minimum width value as Any type, which is then converted to Dimension.
         */
        fun minWidth(value: () -> Any) {
            _size.minWidth = getSize(value.invoke())
        }

        /**
         * Sets the minimum height of the UI component.
         * @param value A lambda that returns the minimum height value as Any type, which is then converted to Dimension.
         */
        fun minHeight(value: () -> Any) {
            _size.minHeight = getSize(value.invoke())
        }

        /**
         * Sets the maximum width of the UI component.
         * @param value A lambda that returns the maximum width value as Any type, which is then converted to Dimension.
         */
        fun maxWidth(value: () -> Any) {
            _size.maxWidth = getSize(value.invoke())
        }

        /**
         * Sets the maximum height of the UI component.
         * @param value A lambda that returns the maximum height value as Any type, which is then converted to Dimension.
         */
        fun maxHeight(value: () -> Any) {
            _size.maxHeight = getSize(value.invoke())
        }

        /**
         * Builds and returns the configured Size object, or null if it hasn't been initialized.
         * @return The configured Size object or null.
         */
        fun build() = size
    }

    /**
     * A builder class for constructing the border dimensions of a UI component.
     * It allows setting individual border thickness for left, right, top, and bottom sides.
     */
    inner class BordersBuilder {

        private var left: Float = 0f
        private var right: Float = 0f
        private var top: Float = 0f
        private var bottom: Float = 0f

        /**
         * Sets the thickness of the left border.
         * @param value A lambda that returns the thickness as an Int.
         */
        fun left(value: () -> Number) {
            left = value.invoke().toFloat()
        }

        /**
         * Sets the thickness of the right border.
         * @param value A lambda that returns the thickness as an Int.
         */
        fun right(value: () -> Number) {
            right = value.invoke().toFloat()
        }

        /**
         * Sets the thickness of the top border.
         * @param value A lambda that returns the thickness as an Int.
         */
        fun top(value: () -> Number) {
            top = value.invoke().toFloat()
        }

        /**
         * Sets the thickness of the bottom border.
         * @param value A lambda that returns the thickness as an Int.
         */
        fun bottom(value: () -> Number) {
            bottom = value.invoke().toFloat()
        }

        /**
         * Sets the thickness of all borders (left, right, top, bottom) to the same value.
         * @param value A lambda that returns the thickness as an Int.
         */
        fun all(value: () -> Number) {
            val thickness = value.invoke().toFloat()
            left = thickness
            right = thickness
            top = thickness
            bottom = thickness
        }

        /**
         * Builds and returns a [Borders] object with the specified border thicknesses.
         * @return A [Borders] object encapsulating the border thicknesses for all four sides.
         */
        fun build() = Borders(left, right, top, bottom)
    }


    /**
     * A builder class for setting up the background properties of a UI component.
     * This includes the background color and corner radius for rounded corners.
     */
    inner class BackgroundBuilder {

        private val backgroundDelegate = LazyInitTracker { Background() }
        private val _background: Background by backgroundDelegate
        private val border: Background? get() = if (backgroundDelegate.isInitialized()) _background else null

        /**
         * Sets the background color of the UI component.
         * @param value A lambda that returns the color value as a String.
         */
        fun color(value: () -> String) {
            _background.color = value.invoke()
        }

        /**
         * Sets the corner radius for the background of the UI component.
         * This value determines how rounded the corners should be.
         * @param value A lambda that returns the radius value as an Int.
         */
        fun cornerRadius(value: () -> Number) {
            _background.cornerRadius = value.invoke().toFloat()
        }

        /**
         * Builds and returns a [Background] object configured with the specified color and radius.
         * This object can then be used to apply the background settings to a UI component.
         * @return A [Background] object containing the configured background color and corner radius.
         */
        fun build() = border
    }

    inner class BorderBuilder {

        private val borderDelegate = LazyInitTracker { Border() }
        private val _border: Border by borderDelegate
        private val border: Border? get() = if (borderDelegate.isInitialized()) _border else null

        /**
         * Sets the width of the border component.
         * @param value A lambda that returns the width value as Any type, which is then converted to Dimension.
         */
        fun width(value: () -> Any) {
            _border.width = getSize(value.invoke())
        }

        /**
         * Sets the color of the border component.
         * @param value A lambda that returns the height value as Any type, which is then converted to Dimension.
         */
        fun color(value: () -> String) {
            _border.color = value.invoke()
        }

        /**
         * Builds and returns the configured Size object, or null if it hasn't been initialized.
         * @return The configured Size object or null.
         */
        fun build() = border
    }

    /**
     * Converts the input size value to a Dimension, supporting both Int and Dimension types.
     * @param size The size value to convert.
     * @return The converted Dimension.
     */
    private fun getSize(size: Any): Dimension =
        when (size) {
            is Number -> Dimension(size.toFloat())
            is Dimension -> size
            else -> Dimension.wrap
        }
}
