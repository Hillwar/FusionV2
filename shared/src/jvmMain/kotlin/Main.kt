
import dsl.core.view.RootFusionView
import kotlinx.serialization.json.Json
import root.root
import java.io.File

const val URI = "/Users/kirill.mikhailov/Desktop/FusionV2/server/src/main/kotlin/ru/aliexpress/fusion/molecules/"

fun main() {
    val jsonString = Json.encodeToString(RootFusionView.serializer(), root.view)
    saveToFile(URI + "/${root.name}.json", jsonString)
}

fun saveToFile(fileName: String, content: String) {
    val file = File(fileName)
    file.writeText(content)
}

fun readFileToString(filePath: String): String {
    return File(filePath).readText(Charsets.UTF_8)
}

fun readScript(path: String): String {
    val filePath = "src/jvmMain/kotlin/$path/script.js"
    val fileContent = readFileToString(filePath)
    return fileContent
}

