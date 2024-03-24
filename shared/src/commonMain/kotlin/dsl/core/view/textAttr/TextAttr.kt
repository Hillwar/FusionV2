package dsl.core.view.textAttr

import kotlinx.serialization.Serializable

/**
 * Defines text-specific attributes such as font properties, text alignment, and appearance.
 *
 * @property font The font attributes including size and color.
 * @property align The alignment mode of the text.
 * @property overflow How text overflow is handled.
 * @property maxLines The maximum number of lines for the text to span.
 * @property color Text color.
 * @property isUnderline Whether the text is underlined.
 */
@Serializable
data class TextAttr(
    var font: Font? = null,
    var align: AlignMode? = null,
    var overflow: OverflowMode? = null,
    var maxLines: Int? = null,
    var color: String? = null,
    var isUnderline: Boolean? = null
)