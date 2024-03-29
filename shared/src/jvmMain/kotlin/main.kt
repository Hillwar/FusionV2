
import dsl.builder.column
import dsl.core.view.RootFusionView
import kotlinx.serialization.json.Json
import java.io.File

const val URI = "/Users/kirill.mikhailov/Desktop/FusionV2/server/src/main/kotlin/ru/aliexpress/fusion/example.json"

val state = mutableMapOf(
    "$.color" to "#0000FF"
)

fun main() {
    val view = column {

        size {
            maxWidth { 100 }
            maxHeight { 100 }
        }

        text {

            background {
                color { "$.color" }
            }

            text { "$.text" }

            onTap {
                "onTextTap"
            }

            state(
                "$.text" to "Old value"
            )
        }

        text {

            text { "text2" }
        }


    }

    val root = RootFusionView(view, state, readScript())
    val jsonString = Json.encodeToString(RootFusionView.serializer(), root)
    saveToFile(URI, jsonString)
}


fun saveToFile(fileName: String, content: String) {
    val file = File(fileName)
    file.writeText(content)
}

fun readFileToString(filePath: String): String {
    return File(filePath).readText(Charsets.UTF_8)
}

fun readScript(): String {
    val filePath = "src/jvmMain/kotlin/script.js"
    val fileContent = readFileToString(filePath)
    return fileContent
}

