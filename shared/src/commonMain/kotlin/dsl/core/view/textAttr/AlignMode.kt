package dsl.core.view.textAttr

import kotlinx.serialization.Serializable

/**
 * Enumeration for text alignment modes.
 */
@Serializable
enum class AlignMode {
    START, END, JUSTIFY, CENTER
}