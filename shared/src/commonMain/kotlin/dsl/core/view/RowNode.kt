
package dsl.core.view

import dsl.core.FusionViewDsl
import dsl.core.view.viewAttr.ViewAttr
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a horizontal container that arranges its child views in a row layout.
 * This class is a fundamental layout component for organizing UI elements horizontally,
 * allowing for the placement of child views side by side.
 *
 * @property children An optional list of child [FusionView] components to be contained within the row.
 *                    Child views will be arranged horizontally in the order they appear in the list.
 * @property viewAttr Optional [ViewAttr] defining the row's attributes, such as size, padding, and alignment,
 *                    allowing customization of the row's appearance and layout behavior.
 */
@FusionViewDsl
@Serializable
@SerialName("Row")
class RowNode(
    override val children: List<FusionView>? = null,
    override val viewAttr: ViewAttr? = null,
    override var state: Map<String, String>? = null,
) : Layout()
