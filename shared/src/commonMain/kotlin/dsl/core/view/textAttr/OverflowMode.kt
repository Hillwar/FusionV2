package dsl.core.view.textAttr

import kotlinx.serialization.Serializable

/**
 * Enumeration for handling text overflow.
 */
@Serializable
enum class OverflowMode {
    ELLIPSIS, CLIP
}