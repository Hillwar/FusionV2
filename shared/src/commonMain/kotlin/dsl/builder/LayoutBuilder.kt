@file:Suppress("FunctionName")
package dsl.builder

import dsl.core.FusionViewDsl
import dsl.core.view.*

/**
 * Base class for builders that construct layouts, providing mechanisms to include child views.
 * This class extends [ViewBuilder], allowing for common view attributes to be set on the layout,
 * and introduces methods to add child views such as boxes, columns, rows, text, and images.
 */
@FusionViewDsl
abstract class LayoutBuilder<T: FusionView> : ViewBuilder<T>() {
    val children: MutableList<FusionView> by lazy { mutableListOf() }

    /**
     * Adds a box layout with specified configuration to the children of this layout.
     *
     * @param init Configuration lambda for the [BoxBuilder].
     * @return The constructed [BoxNode] added to this layout's children.
     */
    fun box(init: BoxBuilder.() -> Unit): BoxNode = addView(BoxBuilder(), init)

    /**
     * Adds a column layout with specified configuration to the children of this layout.
     *
     * @param init Configuration lambda for the [ColumnBuilder].
     * @return The constructed [ColumnNode] added to this layout's children.
     */
    fun column(init: ColumnBuilder.() -> Unit): ColumnNode = addView(ColumnBuilder(), init)

    /**
     * Adds a row layout with specified configuration to the children of this layout.
     *
     * @param init Configuration lambda for the [RowBuilder].
     * @return The constructed [RowNode] added to this layout's children.
     */
    fun row(init: RowBuilder.() -> Unit): RowNode = addView(RowBuilder(), init)

    /**
     * Adds a text element with specified configuration to the children of this layout.
     *
     * @param init Configuration lambda for the [TextBuilder].
     * @return The constructed [TextNode] added to this layout's children.
     */
    fun text(init: TextBuilder.() -> Unit): TextNode = addView(TextBuilder(), init)

    /**
     * Adds an image element with specified configuration to the children of this layout.
     *
     * @param init Configuration lambda for the [ImageBuilder].
     * @return The constructed [ImageNode] added to this layout's children.
     */
    fun image(init: ImageBuilder.() -> Unit): ImageNode = addView(ImageBuilder(), init)

    /**
     * Adds a spacer element with specified configuration to the children of this layout.
     *
     * @param init Configuration lambda for the [SpacerBuilder].
     * @return The constructed [SpacerNode] added to this layout's children.
     */
    fun spacer(init: SpacerBuilder.() -> Unit): SpacerNode = addView(SpacerBuilder(), init)

    /**
     * Generic method to initialize a view builder, apply the provided configuration, build the view,
     * and add it to this layout's children.
     *
     * @param B The type of the [ViewBuilder] for the view being added.
     * @param T The type of the [FusionView] being constructed.
     * @param builder An instance of the view builder.
     * @param init Configuration lambda for the builder.
     * @return The constructed view of type [T].
     */
    inline fun <T : FusionView, B : ViewBuilder<T>> addView(builder: B, init: B.() -> Unit): T {
        val view = builder.apply(init).build().also { children.add(it) }
        return view
    }
}


