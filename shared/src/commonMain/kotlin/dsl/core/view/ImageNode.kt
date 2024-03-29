package dsl.core.view

import dsl.core.FusionViewDsl
import dsl.core.view.imageAttr.ResizeMode
import dsl.core.view.viewAttr.ViewAttr
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an image view within the DSL, allowing for the inclusion of graphical content.
 * This class provides properties for specifying the image source, optional tint color, and resizing behavior,
 * alongside common view attributes defined in [ViewAttr].
 *
 * @property source The URI or path to the image resource. Can be null if dynamically set after initialization.
 * @property tint Optional tint color applied over the image. Specified as a string (e.g., "#RRGGBB" or "red").
 *                Can be null if no tint is applied.
 * @property resizeMode Determines how the image fits within the view bounds. See [ResizeMode] for options.
 * @property viewAttr Common attributes for the view, encapsulated in [ViewAttr], including layout parameters,
 *                    styling options, and other common view properties.
 */
@FusionViewDsl
@Serializable
@SerialName("Image")
class ImageNode(
    val source: String,
    val tint: String? = null,
    val resizeMode: ResizeMode? = null,
    override val viewAttr: ViewAttr? = null,
    override var state: Map<String, String>? = null,
) : FusionView()


