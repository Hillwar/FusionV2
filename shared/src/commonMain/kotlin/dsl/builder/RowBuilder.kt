package dsl.builder

import dsl.core.view.RowNode

/**
 * Builder class for creating instances of [RowNode]. This builder inherits from [LayoutBuilder],
 * enabling the addition of child views to the row and the setting of common view attributes.
 */
class RowBuilder : LayoutBuilder<RowNode>() {

    private var spacing: Int = 0

    /**
     * Constructs and returns a [RowNode] instance based on the configured properties.
     * This includes the child views added to the row and any view attributes set.
     *
     * @return The constructed [RowNode] instance.
     */

    fun spacing(value: () -> Int): RowBuilder = apply { spacing = value() }

    override fun build(): RowNode = RowNode(spacing, children, viewAttr, state)
}

/**
 * DSL function to define a row layout within the user interface. It allows for a declarative way
 * of specifying how child views should be arranged horizontally within a row.
 *
 * @param init A lambda with [RowBuilder] as its receiver, enabling the detailed configuration
 *             of the row and its child views.
 * @return The constructed [RowNode] reflecting the specified configuration.
 */
fun row(init: RowBuilder.() -> Unit): RowNode = RowBuilder().apply(init).build()