package dsl.core.view.viewAttr

import dsl.core.FusionViewDsl
import kotlinx.serialization.Serializable

/**
 * Describes the borders for views, defining padding on each side.
 */
@FusionViewDsl
@Serializable
data class Borders(val left: Float, val right: Float, val top: Float, val bottom: Float)