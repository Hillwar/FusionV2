import Foundation
import SwiftUI

protocol FusionView: Decodable, CustomStringConvertible, View {
    var viewAttr: ViewAttr? { get }
}

extension View {
    func applyViewAttributes(_ viewAttr: ViewAttr?) -> some View {
           guard let viewAttr = viewAttr else { return AnyView(self) }
           
           let opacity = viewAttr.isVisible ?? true ? viewAttr.alpha ?? 1.0 : 0.0
           let borderColor = Color(hex: viewAttr.border?.color)
           let borderWidth = viewAttr.border?.width?.swiftUIValue ?? 1
           let cornerRadius = viewAttr.background?.cornerRadius ?? 0
           
           return self
               .frame(
                   minWidth: viewAttr.size?.minWidth?.swiftUIValue,
                   idealWidth: viewAttr.size?.width?.swiftUIValue,
                   maxWidth: viewAttr.size?.maxWidth?.swiftUIValue,
                   minHeight: viewAttr.size?.minHeight?.swiftUIValue,
                   idealHeight: viewAttr.size?.height?.swiftUIValue,
                   maxHeight: viewAttr.size?.maxHeight?.swiftUIValue,
                   alignment: viewAttr.gravity?.alignment ?? .center
               )
               .opacity(opacity)
               .padding(viewAttr.paddings?.insets ?? EdgeInsets())
               .background(Color(hex: viewAttr.background?.color))
               .overlay(
                   RoundedRectangle(cornerRadius: cornerRadius)
                       .stroke(borderColor, lineWidth: borderWidth)
               )               .cornerRadius(cornerRadius)
               .asAnyView()
       }
}

extension View {
    func asAnyView() -> AnyView { AnyView(self) }
}

extension CustomStringConvertible {
    var description: String {
        var description = "\(type(of: self)):\n"
        let mirror = Mirror(reflecting: self)

        for child in mirror.children.compactMap({ $0 }) {
            if ("\(child.value)" == "nil") {
                continue
            }
            if let label = child.label {
                description += "        \(label): \(child.value)\n"
            }
        }

        return description.trimmingCharacters(in: .whitespacesAndNewlines)
    }
}

struct ViewAttr: Decodable, CustomStringConvertible {
    var size: Size?
    var weight: Float?
    var gravity: Gravity?
    var background: Background?
    var border: Border?
    var alpha: Double?
    var paddings: Borders?
    var isVisible: Bool?
    var onTap: String?
    var state: [String: String]? = [:]
}

struct Size : Decodable, CustomStringConvertible {
    var width: Dimension?
    var height: Dimension?
    var minWidth: Dimension?
    var minHeight: Dimension?
    var maxWidth: Dimension?
    var maxHeight: Dimension?
}

struct Dimension: Decodable, CustomStringConvertible {
    let value: CGFloat

    static let wrap = Dimension(value: -1)
    static let fill = Dimension(value: -2)
    
    var swiftUIValue: CGFloat {
        switch value {
        case Dimension.wrap.value: return CGFloat.nan
        case Dimension.fill.value: return CGFloat.infinity
        default: return value
        }
    }
}

enum Gravity: String, Decodable {
    case CENTER, LEFT, RIGHT, TOP, BOTTOM
    
    var alignment: Alignment {
        switch self {
            case .CENTER: return .center
            case .LEFT: return .leading
            case .RIGHT: return .trailing
            case .TOP: return .top
            case .BOTTOM: return .bottom
        }
    }
}

struct Background: Decodable, CustomStringConvertible {
    var color: String?
    var cornerRadius: CGFloat?
}

struct Border: Decodable, CustomStringConvertible {
    var color: String?
    var width: Dimension?
}

struct Borders: Decodable, CustomStringConvertible {
    var left: CGFloat?
    var right: CGFloat?
    var top: CGFloat?
    var bottom: CGFloat?
    var insets: EdgeInsets? {
        get {
            return EdgeInsets(top: top ?? .zero, leading: left ?? .zero, bottom: bottom ?? .zero, trailing: right ?? .zero)
        }
    }
}
