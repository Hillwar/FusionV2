package title

import Molecule
import dsl.builder.text
import dsl.core.view.RootFusionView
import dsl.core.view.textAttr.AlignMode
import dsl.core.view.textAttr.Weight.Companion.semiBold
import readScript

private val state: Map<String, String> = mutableMapOf()

private val rootView = text {
    state("$.name" to "Консоль для видеоигр Sony PlayStation 5 PS5, японская версия, PS 5 PC игры, ультра высокая скорость, PlayStation 5",)
    text { "$.name" }
    padding {
        left { 16 }
        right { 16 }
    }
    font {
        size { 15 }
        align { AlignMode.START }
        weight { semiBold }
        lineHeight { 18 }
        letterSpacing { 0 }
    }
}

private val view = RootFusionView(rootView, state, readScript("title"))

val title = Molecule("title", view)

