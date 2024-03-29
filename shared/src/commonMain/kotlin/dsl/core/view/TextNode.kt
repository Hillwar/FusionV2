package dsl.core.view

import dsl.core.FusionViewDsl
import dsl.core.view.textAttr.TextAttr
import dsl.core.view.viewAttr.ViewAttr
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a text element within the DSL, allowing for the inclusion of textual content.
 * This class provides properties for specifying the text content, text attributes (like font, alignment),
 * and common view attributes.
 *
 * @property text The textual content to display. Can be null if set dynamically after initialization.
 * @property textAttr Optional attributes specific to text rendering, such as font and alignment.
 * @property viewAttr Common attributes for the view, encapsulated in [ViewAttr].
 */
@FusionViewDsl
@Serializable
@SerialName("Text")
class TextNode(
    val text: String? = null,
    private val textAttr: TextAttr? = null,
    override val viewAttr: ViewAttr? = null,
    override var state: Map<String, String>? = null,
) : FusionView()

