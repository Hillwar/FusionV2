
package dsl.core.view

import dsl.core.FusionViewDsl
import kotlinx.serialization.Serializable

/**
 * Represents the base class for all layout components within the DSL.
 * Layouts are specialized views that contain and arrange other view components based on specific rules.
 * This abstract class defines a common structure for layouts by specifying a list of child views.
 *
 * Extend this class to create custom layout types that organize child views in various ways (e.g., linearly, grid, etc.).
 *
 * @property children An optional list of child [FusionView] components contained within the layout.
 *                    Child components can be any view that extends from [FusionView], including other layouts.
 *                    This allows for building complex nested structures. Can be null if the layout does not contain any children initially.
 */
@FusionViewDsl
@Serializable
sealed class Layout : FusionView() {
    abstract val children: List<FusionView>?
}


