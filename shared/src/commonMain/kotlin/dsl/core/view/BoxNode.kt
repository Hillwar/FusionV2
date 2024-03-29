package dsl.core.view

import dsl.core.FusionViewDsl
import dsl.core.view.viewAttr.ViewAttr
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a container that can hold multiple child views in a box-like structure.
 * It is a basic layout component in the DSL for creating UIs, allowing for the grouping
 * and organization of views within a rectangular area.
 *
 * @property children Optional list of child [FusionView]s to be contained within the box.
 * @property viewAttr Optional [ViewAttr] defining the box's attributes like size, background, etc.
 */
@FusionViewDsl
@Serializable
@SerialName("Box")
class BoxNode(
    override val children: List<FusionView>? = null,
    override val viewAttr: ViewAttr? = null,
    override var state: Map<String, String>? = null,
) : Layout()