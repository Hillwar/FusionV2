package dsl.core.view.viewAttr

import dsl.core.FusionViewDsl
import kotlinx.serialization.Serializable

/**
 * Defines the size for views, including minimum and maximum values.
 */
@FusionViewDsl
@Serializable
data class Size(
    var width: Dimension? = null,
    var height: Dimension? = null,
    var minWidth: Dimension? = null,
    var minHeight: Dimension? = null,
    var maxWidth: Dimension? = null,
    var maxHeight: Dimension? = null
)