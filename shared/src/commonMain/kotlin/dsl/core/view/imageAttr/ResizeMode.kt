package dsl.core.view.imageAttr

/**
 * Defines how an image is resized to fit its allocated space.
 */
enum class ResizeMode {
    ASPECT_FIT,  // Scales the image to fit the space while maintaining aspect ratio.
    SCALE_TO_FILL,  // Scales the image to completely fill the space, possibly altering aspect ratio.
    ASPECT_FILL  // Scales the image to fill the space while maintaining aspect ratio, cropping if necessary.
}