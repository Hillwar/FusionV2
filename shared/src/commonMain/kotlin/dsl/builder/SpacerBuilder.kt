package dsl.builder

import dsl.core.view.SpacerNode

/**
 * Builder for creating a SpacerNode. Allows specifying a minimum length
 * that the spacer should occupy.
 */
class SpacerBuilder : ViewBuilder<SpacerNode>() {
    private var minLength: Float? = null

    /**
     * Sets the minimum length for the spacer.
     * @param value The minimum length in pixels.
     */
    fun minLength(value: () -> Float) {
        minLength = value.invoke()
    }

    override fun build() = SpacerNode(minLength, viewAttr, state)
}