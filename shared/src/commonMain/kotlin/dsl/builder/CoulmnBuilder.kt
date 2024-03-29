package dsl.builder

import dsl.core.FusionViewDsl
import dsl.core.view.ColumnNode


/**
 * Builder for creating a [ColumnNode]. Inherits from [LayoutBuilder] to facilitate
 * adding child views and setting view attributes in a structured and intuitive manner.
 */
@FusionViewDsl
class ColumnBuilder : LayoutBuilder<ColumnNode>() {
    /**
     * Constructs a [ColumnNode] with the specified children and view attributes.
     * This method finalizes the configuration and produces the layout component.
     *
     * @return The constructed [ColumnNode] instance with configured properties.
     */
    override fun build() = ColumnNode(children, viewAttr, state)
}

/**
 * DSL function to define a column layout. It provides a declarative way to specify
 * how child views should be vertically arranged within a column.
 *
 * @param init Configuration lambda with [ColumnBuilder] as its receiver, allowing for
 *             the detailed setup of the column and its children.
 * @return The constructed [ColumnNode] reflecting the specified configuration
 */
fun column(init: ColumnBuilder.() -> Unit): ColumnNode = ColumnBuilder().apply(init).build()