package dsl.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Value

@Serializable
@SerialName("StateValue")
class StateValue(val code: String) : Value()

@Serializable
@SerialName("PrimitiveValue")
class PrimitiveValue(val value: String) : Value()