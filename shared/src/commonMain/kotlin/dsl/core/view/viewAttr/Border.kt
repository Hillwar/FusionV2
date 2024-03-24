package dsl.core.view.viewAttr

import dsl.core.FusionViewDsl
import kotlinx.serialization.Serializable

@FusionViewDsl
@Serializable
data class Border(var color: String? = null, var width: Dimension? = null)