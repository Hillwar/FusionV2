package dsl.core.view

import dsl.core.FusionViewDsl
import dsl.core.view.viewAttr.ViewAttr
import kotlinx.serialization.Serializable

/**
 * The base abstract class for all view components within the DSL. This class establishes
 * a common foundation for defining attributes that are shared across different types of views,
 * such as layout parameters, styling, and common behaviors.
 *
 * Each view component that extends FusionView should provide its own specific attributes
 * and functionalities, while also inheriting common attributes defined in this base class.
 *
 * @property viewAttr The common attributes for the view, encapsulated in [ViewAttr].
 *                    This includes layout parameters, styling options, and other common view properties.
 *                    Can be null if the view does not require any specific attributes.
 */
@FusionViewDsl
@Serializable
sealed class FusionView {
    abstract val viewAttr: ViewAttr?
}
