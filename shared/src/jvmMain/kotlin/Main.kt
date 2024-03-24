import dsl.builder.box
import dsl.core.view.FusionView
import dsl.core.view.viewAttr.Dimension.Companion.fill
import kotlinx.serialization.json.Json
import java.io.File

const val URI = "/Users/kirill.mikhailov/Desktop/FusionV2/server/src/main/kotlin/ru/aliexpress/fusion/example.json"

fun main() {
    val view = box {

        padding {
            left { 12 }
            right { 12 }
        }

        column {

            background {
                color { "#FCFCFD" }
                cornerRadius { 8 }
                border {
                    width { 1 }
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
                maxWidth { fill }
            }

            row {
                image {
                    size {
                        maxWidth { 16 }
                        maxHeight { 16 }
                    }
                    source { "https://cdn-icons-png.flaticon.com/512/732/732250.png" }
                }

                box {
                    size {
                        maxWidth { 4 }
                    }
                }

                text {
                    text { "Санкт-Петербург" }
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
                    text { "Бесплатно" }

                    font {
                        maxLines { 1 }
                        lineHeight { 16 }
                        size { 13 }
                        color { "#32CD32" }
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
                    text { "Бесплатно" }

                    font {
                        maxLines { 1 }
                        lineHeight { 16 }
                        size { 13 }
                        color { "#32CD32" }
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
                    maxWidth { fill }
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
                    text { "Защита покупателей 30 дн." }

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

    val jsonString = Json.encodeToString(FusionView.serializer(), view)
    saveToFile(URI, jsonString)
}


fun saveToFile(fileName: String, content: String) {
    val file = File(fileName)
    file.writeText(content)
}

