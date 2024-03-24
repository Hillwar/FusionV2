package dsl.core.view.viewAttr

import dsl.core.FusionViewDsl
import kotlinx.serialization.Serializable

/**
 * Defines the alignment of views within their containers.
 */
@FusionViewDsl
@Serializable
enum class Gravity {
    CENTER, LEFT, RIGHT, TOP, BOTTOM
}