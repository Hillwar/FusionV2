import Foundation
import JavaScriptCore
import SwiftUI

protocol FusionView: CustomStringConvertible, View {
    var viewAttr: ViewAttr { get set }
    var context: JSContext { get set }
    var state: [String: String] { get set }
    var localState: [String: String] { get set }
}

extension FusionView {
    mutating func initFusionView(from decoder: Decoder, context: JSContext) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.viewAttr = try container.decodeIfPresent(ViewAttr.self, forKey: .viewAttr) ?? ViewAttr()
        self.context = context
    }
}

enum CodingKeys: String, CodingKey {
    case viewAttr
}

extension View {
    func applyViewAttributes(_ viewAttr: ViewAttr, _ state: [String: String], _ localState: [String: String]) -> some View {
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
                maxHeight: viewAttr.size?.maxHeight?.swiftUIValue
            )
            .opacity(opacity)
            .padding(viewAttr.paddings?.insets ?? EdgeInsets())
            .background(Color(hex: viewAttr.background?.color?.value(with: state, localState: localState)))
            .overlay(
                RoundedRectangle(cornerRadius: cornerRadius)
                    .stroke(borderColor, lineWidth: borderWidth)
            )
            .cornerRadius(cornerRadius)
        
    }
}

extension View {
    func asAnyView() -> AnyView { AnyView(self) }
}

extension CustomStringConvertible {
    var description: String {
        let typeOf = "\(type(of: self)):\n"
        var description = typeOf
        let mirror = Mirror(reflecting: self)
        
        for child in mirror.children.compactMap({ $0 }) {
            if ("\(child.value)" == "nil") {
                continue
            }
            if let label = child.label {
                description += "        \(label): \(child.value)\n"
            }
        }
        if description == typeOf {
            return ""
        }
        return description.trimmingCharacters(in: .whitespacesAndNewlines)
    }
}

struct ViewAttr: Decodable, CustomStringConvertible {
    var size: Size?
    var gravity: Gravity?
    var background: Background?
    var border: Border?
    var alpha: Double?
    var paddings: Borders?
    var isVisible: Bool?
    var onTap: String?
}

extension String {
    func value(with state: [String: String], localState: [String: String]) -> String {
        if starts(with: "$."), let value = state[self] ?? localState[self] {
            return value
        }
        return self
    }
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
    
    var horizontalAlignment: HorizontalAlignment {
        switch self {
        case .CENTER: return .center
        case .LEFT: return .leading
        case .RIGHT: return .trailing
        case .TOP: return .center
        case .BOTTOM: return .center
        }
    }
    
    var verticalAlignment: VerticalAlignment {
        switch self {
        case .CENTER: return .center
        case .LEFT: return .center
        case .RIGHT: return .center
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
