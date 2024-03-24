package dsl.core.view.viewAttr

import dsl.core.FusionViewDsl
import kotlinx.serialization.Serializable

/**
 * Describes background attributes of views, such as color and corner radius.
 */
@FusionViewDsl
@Serializable
data class Background(var color: String? = null, var cornerRadius: Float? = null)