package dsl.builder

import dsl.core.view.TextNode
import dsl.core.view.textAttr.AlignMode
import dsl.core.view.textAttr.Font
import dsl.core.view.textAttr.OverflowMode
import dsl.core.view.textAttr.TextAttr


/**
 * Builder class for creating [TextNode] instances, allowing for detailed configuration
 * of text properties, including content, alignment, overflow behavior, and styling.
 */
class TextBuilder : ViewBuilder<TextNode>() {

    private val textAttrDelegate = LazyInitTracker { TextAttr() }
    private val _textAttr: TextAttr by textAttrDelegate
    private val textAttr: TextAttr? get() = if (textAttrDelegate.isInitialized()) _textAttr else null
    private var text: String? = null

    /**
     * Sets the text content.
     *
     * @param value Lambda returning the text content as a [String].
     */
    fun text(value: () -> String) {
        text = value.invoke()
    }

    /**
     * Sets the alignment of the text.
     *
     * @param value Lambda returning the [AlignMode].
     */
    fun align(value: () -> AlignMode) {
        _textAttr.align = value.invoke()
    }

    /**
     * Sets how text overflow is handled.
     *
     * @param value Lambda returning the [OverflowMode].
     */
    fun overflow(value: () -> OverflowMode) {
        _textAttr.overflow = value.invoke()
    }

    /**
     * Sets the maximum number of lines for the text.
     *
     * @param value Lambda returning the maximum number of lines as an [Int].
     */
    fun maxLines(value: () -> Int) {
        _textAttr.maxLines = value.invoke()
    }

    /**
     * Sets the text color.
     *
     * @param value Lambda returning the color as a [String].
     */
    fun color(value: () -> String) {
        _textAttr.color = value.invoke()
    }

    /**
     * Specifies whether the text should be underlined.
     *
     * @param value Lambda returning a [Boolean] indicating underline status.
     */
    fun isUnderline(value: () -> Boolean) {
        _textAttr.isUnderline = value.invoke()
    }

    /**
     * Configures the font attributes.
     *
     * @param value Configuration lambda for the [FontBuilder].
     */
    fun font(value: FontBuilder.() -> Unit) {
        _textAttr.font = FontBuilder().apply(value).build()
    }

    /**
     * Builds and returns a [TextNode] with the specified configuration.
     */
    override fun build() = TextNode(text, textAttr, viewAttr, state)

    /**
     * Builder class for configuring font attributes.
     */
    inner class FontBuilder {
        private var size: Float? = null
        private var lineHeight: Int? = null
        private var letterSpacing: Int? = null
        private var color: String? = null

        fun size(value: () -> Number) {
            size = value.invoke().toFloat()
        }

        fun lineHeight(value: () -> Int) {
            lineHeight = value.invoke()
        }

        fun letterSpacing(value: () -> Int) {
            letterSpacing = value.invoke()
        }

        fun color(value: () -> String) {
            color = value.invoke()
        }

        fun build() = Font(size, lineHeight, letterSpacing, this.color)
    }
}