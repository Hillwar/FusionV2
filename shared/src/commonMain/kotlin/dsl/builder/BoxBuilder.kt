package dsl.builder

import dsl.core.FusionViewDsl
import dsl.core.view.BoxNode

/**
 * Builder for creating [BoxNode] instances. Inherits from [LayoutBuilder] to allow
 * adding child views and setting view attributes in a structured way.
 */
@FusionViewDsl
class BoxBuilder : LayoutBuilder<BoxNode>() {
    /**
     * Builds and returns a [BoxNode] instance with the configured children and view attributes.
     */
    override fun build() = BoxNode(children, viewAttr)
}

/**
 * DSL function for constructing a [BoxNode] using the [BoxBuilder].
 * This allows for a declarative style of defining a box layout in the DSL.
 *
 * @param init A lambda receiver for [BoxBuilder], allowing configuration of the box and its children.
 * @return The constructed [BoxNode] instance.
 */
fun box(init: BoxBuilder.() -> Unit): BoxNode = BoxBuilder().apply(init).build()