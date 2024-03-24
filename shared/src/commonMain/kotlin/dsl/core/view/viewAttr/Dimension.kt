package dsl.core.view.viewAttr

import dsl.core.FusionViewDsl
import kotlinx.serialization.Serializable

/**
 * Represents dimensions in numerical values. Includes standard values for fill and wrap content.
 */
@FusionViewDsl
@Serializable
class Dimension(var value: Float) {
    companion object {
        val wrap = Dimension(-1f)  // For wrapping content.
        val fill = Dimension(-2f)  // For filling the available space.
    }
}