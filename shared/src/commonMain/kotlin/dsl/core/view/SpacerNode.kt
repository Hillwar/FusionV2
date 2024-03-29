package dsl.core.view


import dsl.core.FusionViewDsl
import dsl.core.view.viewAttr.ViewAttr
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a spacer component that expands to fill available space,
 * with an optional minimum length constraint.
 *
 * @property minLength The minimum length the spacer should occupy, in pixels.
 */
@FusionViewDsl
@Serializable
@SerialName("Spacer")
class SpacerNode(
    private val minLength: Float? = null,
    override val viewAttr: ViewAttr? = null,
    override var state: Map<String, String>? = null,
) : FusionView()