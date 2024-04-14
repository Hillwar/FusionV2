package rating

import Molecule
import dsl.builder.row
import dsl.core.view.RootFusionView
import dsl.core.view.imageAttr.ResizeMode
import dsl.core.view.viewAttr.Dimension.Companion.fill
import dsl.core.view.viewAttr.Gravity
import readScript

private val state = mutableMapOf(
    "$.url" to "https://ae04.alicdn.com/kf/S57df2f4985ad49f5b8d734577a2178b0h.jpg_640x640.jpg",
    "$.star_url" to "https://st.aliexpress.ru/resources/pdpv3/rating_icon.png",

    )

private val rootView = row {

    padding {
        left { 16 }
        right { 16 }
    }

    spacing { 5 }

    image {
        size {
            maxHeight { 16 }
            maxWidth { 16 }
        }
        resizeMode { ResizeMode.ASPECT_FIT }
        source { "$.star_url" }
    }

    text {
        text { "4.8" }
        font {
            size { 13 }
            lineHeight { 16 }
        }
    }

    text {
        size {
            maxHeight { 16 }
        }
        padding {
            left { 4 }
            right { 4 }
        }
        text { "ТОП" }
        font {
            size { 11 }
        }
        background { cornerRadius { 4 } }
        border {
            width { 1 }
            color { "#d6d6db" }
        }
    }

    text {
        text { "5 145 отзывов" }
        font {
            size { 13 }
            lineHeight { 16 }
        }
    }

    text {
        text { "56 995 купили" }
        font {
            color { "#a0a0ab" }
            size { 13 }
            lineHeight { 16 }
        }
    }
}

private val view = RootFusionView(rootView, state, readScript("rating"))

val rating = Molecule("rating", view)

