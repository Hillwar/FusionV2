package dsl.core.view.textAttr

import kotlinx.serialization.Serializable

@Serializable
class Weight(val value: String) {
    companion object {
        val regular = Weight("regular")
        val medium = Weight("medium")
        val semiBold = Weight("semiBold")
        val bold = Weight("bold")
    }
}