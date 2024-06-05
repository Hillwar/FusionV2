package image

import Molecule
import dsl.builder.image
import dsl.core.view.RootFusionView
import dsl.core.view.viewAttr.Dimension.Companion.fill
import readScript

private val state = mutableMapOf(
    "$.url" to "https://ae04.alicdn.com/kf/S57df2f4985ad49f5b8d734577a2178b0h.jpg_640x640.jpg",
)

private val rootView = image {
    source { "$.url" }
}

private val view = RootFusionView(rootView, state, readScript("image"))

val image = Molecule("image", view)

