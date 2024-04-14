package price

import Molecule
import dsl.builder.box
import dsl.core.view.RootFusionView
import dsl.core.view.textAttr.Weight
import dsl.core.view.viewAttr.Dimension
import dsl.core.view.viewAttr.Gravity
import readScript

private val state = mutableMapOf(
    "$.url" to "https://ae04.alicdn.com/kf/S57df2f4985ad49f5b8d734577a2178b0h.jpg_640x640.jpg",
    "$.star_url" to "https://st.aliexpress.ru/resources/pdpv3/rating_icon.png",

    )

private val rootView = box {

    padding {
        left { 12 }
        right { 12 }
    }

    column {

        background {
            color { "#FCFCFD" }
            cornerRadius { 8 }
            border {
                width { 2 }
                color { "#E6EAF0" }
            }
        }

        gravity { Gravity.LEFT }

        padding {
            all { 12 }
        }

        size {
            maxWidth { Dimension.fill }
        }

        text {
            text { "189 ₽" }
            font {
                size { 21 }
               weight { Weight.semiBold }
            }
        }

        box {
            size {
                maxHeight { 8 }
            }
        }

        text {
            text { "1 108,88 ₽" }
            font {
                maxLines { 1 }
                lineHeight { 16 }
                size { 13 }
                color { "#18181B" }
            }
        }

        box {
            size {
                maxHeight { 8 }
            }
        }

        row {
            text {
                text { "Цена за 1 лот" }

                font {
                    maxLines { 3 }
                    lineHeight { 12 }
                    size { 11 }
                    color { "#A0A0AB" }
                }
            }
            spacer { }
        }
    }
}

private val view = RootFusionView(rootView, state, readScript("price"))

val price = Molecule("price", view)

