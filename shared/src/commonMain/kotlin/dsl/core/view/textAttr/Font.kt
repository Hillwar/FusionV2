package dsl.core.view.textAttr

import kotlinx.serialization.Serializable

/**
 * Defines font attributes including size, line height, letter spacing, and color.
 */
@Serializable
data class Font(
    val size: Float? = null,
    val weight: Weight? = null,
    val lineHeight: Int? = null,
    val letterSpacing: Int? = null,
    val color: String? = null
)