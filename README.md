# FusionV2

## Overview

This iOS UI framework introduces a custom Domain-Specific Language (DSL) tailored for streamlining the development of SwiftUI interfaces. By abstracting the complexities of UI construction, the framework facilitates the creation of intricate, adaptive UIs efficiently and intuitively. Future enhancements will include JSON-driven UI logic definition and execution through JavaScriptCore, broadening the framework's versatility and capability.

## Features

- **Custom DSL for UI Development:** Simplify SwiftUI view creation with an intuitive, readable syntax.
- **Efficient Lazy Initialization:** Optimize performance through intelligent component initialization.
- **Flexible Sizing and Borders:** Effortlessly configure dimensions and border aesthetics for UI elements.
- **Enhanced Background Customization:** Define background colors and corner rounding with minimal code.
- **Modular Architecture:** Foster code reusability and clarity with a builder-pattern-driven structure.

## Getting Started

### Requirements

- iOS 14.0+
- Xcode 12.0+
- Swift 5.3+


## Example Usage

### Creating a Simple View

You can easily define a view with a text element using our DSL:

```kotlin
val myView = column {
        background {
            color { "#0000FF" }
        }
    
        text {
            text {
                "Hello, World!"
            }
        }
    }
```

## License

This project is licensed under the MIT License. For more details, see the [LICENSE](LICENSE) file in the repository.
