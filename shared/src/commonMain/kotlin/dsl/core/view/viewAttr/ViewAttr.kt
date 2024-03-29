package dsl.core.view.viewAttr

import dsl.core.FusionViewDsl
import kotlinx.serialization.Serializable

/**
 * Describes attributes common to all views, including size, background, padding, etc.
 */
@FusionViewDsl
@Serializable
data class ViewAttr(
    var size: Size? = null,
    var gravity: Gravity? = null,
    var background: Background? = null,
    var border: Border? = null,
    var alpha: Double? = null,
    var paddings: Borders? = null,
    var isVisible: Boolean? = null,
    var onTap: String? = null,
)
