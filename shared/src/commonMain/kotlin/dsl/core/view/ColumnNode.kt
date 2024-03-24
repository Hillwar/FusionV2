package dsl.core.view

import dsl.core.FusionViewDsl
import dsl.core.view.viewAttr.ViewAttr
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a vertical container that arranges its child views in a column layout.
 * It's a fundamental layout component for organizing UI elements vertically,
 * allowing for sequential placement of child views one below the other.
 *
 * @property children Optional list of child [FusionView]s to be contained within the column.
 *                    Child views will be arranged vertically in the order they appear in the list.
 * @property viewAttr Optional [ViewAttr] defining the column's attributes like size, padding, etc.,
 *                    allowing customization of the column's appearance and layout behavior.
 */
@FusionViewDsl
@Serializable
@SerialName("Column")
class ColumnNode(
    override val children: List<FusionView>? = null,
    override val viewAttr: ViewAttr? = null,
) : Layout()