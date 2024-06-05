package sku

import Molecule
import dsl.builder.column
import dsl.core.view.RootFusionView
import dsl.core.view.viewAttr.Gravity
import readScript

private val state = mutableMapOf(
    "$.url" to "https://ae04.alicdn.com/kf/S57df2f4985ad49f5b8d734577a2178b0h.jpg_650x650.jpg",
    "$.selectedIndex" to "1",
    "$.color0" to "#E6EAF0",
    "$.color1" to "#E6EAF0",
    "$.color2" to "#E6EAF0"
)

private val rootView = column {
    padding {
        left { 12 }
    }


    onAppear {
        "appear"
    }

    gravity { Gravity.LEFT }

    row {
        text {
            text { "Цвет: " }

            font {
                maxLines { 1 }
                lineHeight { 16 }
                size { 13 }
                color { "#18181B" }
            }
        }

        text {
            text { "$.selectedSku" }

            font {
                maxLines { 1 }
                lineHeight { 16 }
                size { 13 }
                color { "#18181B" }
            }
        }
    }

    box {
        size {
            minHeight { 4 }
        }
    }

    row {
        image {
            source { "https://ae04.alicdn.com/kf/S57df2f4985ad49f5b8d734577a2178b0h.jpg_650x650.jpg" }
            size {
                maxWidth { 50 }
            }
            background {
                cornerRadius { 6 }
                border {
                    color { "$.color0" }
                    width { 3 }
                }
            }
            onTap { "onSkuTap0" }
        }

        box {
            size {
                minWidth { 6 }
            }
        }

        image {
            source { "https://ae04.alicdn.com/kf/S2b864cf62b17462aa382869ce020fa03s.jpg_640x640.jpg" }
            size {
                maxWidth { 50 }
            }
            background {
                cornerRadius { 6 }
                border {
                    color { "$.color1" }
                    width { 3 }
                }
            }
            onTap { "onSkuTap1" }
        }

        box {
            size {
                minWidth { 6 }
            }
        }

        image {
            source { "https://ae04.alicdn.com/kf/S57df2f4985ad49f5b8d734577a2178b0h.jpg_650x650.jpg" }
            size {
                maxWidth { 50 }
            }
            background {
                cornerRadius { 6 }
                border {
                    color { "$.color2" }
                    width { 3 }
                }
            }
            onTap { "onSkuTap2" }
        }
    }
}

private val view = RootFusionView(rootView, state, readScript("sku"))

val sku = Molecule("sku", view)

