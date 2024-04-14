package dsl.builder

import dsl.core.FusionViewDsl
import dsl.core.view.ImageNode
import dsl.core.view.imageAttr.ResizeMode


/**
 * Builder class for creating [ImageNode] instances. Allows for the configuration
 * of image-specific properties such as source, tint, and resize mode in addition
 * to common view attributes.
 */
@FusionViewDsl
class ImageBuilder : ViewBuilder<ImageNode>() {

    private lateinit var source: String
    private var tint: String? = null
    private var resizeMode: ResizeMode? = null

    /**
     * Sets the source of the image. This is typically a URI or a path to the image resource.
     *
     * @param value Lambda that returns the image source as a [String].
     */
    fun source(value: () -> String) {
        source = value.invoke()
    }

    /**
     * Applies a tint color to the image. The color should be specified in a format
     * recognized by the platform (e.g., hex codes).
     *
     * @param value Lambda that returns the tint color as a [String].
     */
    fun tint(value: () -> String) {
        tint = value.invoke()
    }

    /**
     * Specifies how the image should be resized to fit within its bounds.
     *
     * @param value Lambda that returns the desired [ResizeMode].
     */
    fun resizeMode(value: () -> ResizeMode) {
        resizeMode = value.invoke()
    }

    /**
     * Constructs an [ImageNode] with the specified source, tint, resize mode, and
     * common view attributes. This method finalizes the image configuration.
     *
     * @return The constructed [ImageNode] instance.
     */
    override fun build() = ImageNode(source, tint, resizeMode, viewAttr, state)
}

@FusionViewDsl
fun image(init: ImageBuilder.() -> Unit): ImageNode = ImageBuilder().apply(init).build()