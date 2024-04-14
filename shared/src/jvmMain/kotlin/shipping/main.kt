package shipping

import Molecule
import dsl.builder.box
import dsl.core.view.RootFusionView
import dsl.core.view.viewAttr.Dimension
import readScript

private val state = mutableMapOf(
    "$.city" to "Санкт-Петербург",
    "$.free" to "Бесплатно",
    "$.protect" to "Защита покупателей 30 дн."
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

        padding {
            left { 12 }
            top { 12 }
            right { 12 }
            bottom { 12 }
        }

        size {
            maxWidth { Dimension.fill }
        }

        row {
            image {
                size {
                    maxWidth { 16 }
                    maxHeight { 16 }
                }
                source { "https://st.aliexpress.ru/resources/pdpv3/delivery.png" }
            }

            box {
                size {
                    maxWidth { 4 }
                }
            }

            text {
                text { "$.city" }
                font {
                    maxLines { 1 }
                    lineHeight { 16 }
                    size { 13 }
                    color { "#18181B" }
                }
            }

            spacer { }
        }

        box {
            size {
                maxHeight { 14 }
            }
        }

        row {
            text {
                text { "4 апреля" }
                font {
                    maxLines { 1 }
                    lineHeight { 16 }
                    size { 13 }
                    color { "#18181B" }
                }
            }

            box {
                size {
                    maxWidth { 4 }
                }
            }

            text {
                text { "почтой" }
                font {
                    maxLines { 1 }
                    lineHeight { 16 }
                    size { 13 }
                    color { "#A0A0AB" }
                }
            }

            spacer {}

            text {
                text { "$.free" }

                font {
                    maxLines { 1 }
                    lineHeight { 16 }
                    size { 13 }
                    color { "#4EAB45" }
                }
            }
        }

        box {
            size {
                maxHeight { 14 }
            }
        }

        row {
            text {
                text { "31 марта" }
                font {
                    maxLines { 1 }
                    lineHeight { 16 }
                    size { 13 }
                    color { "#18181B" }
                }
            }

            box {
                size {
                    maxWidth { 4 }
                }
            }

            text {
                text { "пункт выдачи" }
                font {
                    maxLines { 1 }
                    lineHeight { 16 }
                    size { 13 }
                    color { "#A0A0AB" }
                }
            }

            spacer {}

            text {
                text { "$.free" }

                font {
                    maxLines { 1 }
                    lineHeight { 16 }
                    size { 13 }
                    color { "#4EAB45" }
                }
            }
        }

        box {
            size {
                maxHeight { 12 }
            }
        }

        box {
            size {
                maxWidth { Dimension.fill }
                maxHeight { 1 }
            }
            background {
                color { "#E6EAF0" }
            }
        }

        box {
            size {
                maxHeight { 12 }
            }
        }

        row {
            text {
                text { "$.protect" }

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


private val view = RootFusionView(rootView, state, readScript("shipping"))

val shipping = Molecule("shipping", view)

