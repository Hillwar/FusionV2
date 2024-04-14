import Foundation
import SwiftUI
import JavaScriptCore

struct TextNode: FusionView {
    var text: String = ""
    var textAttr: TextAttr = TextAttr()
    var viewAttr: ViewAttr = ViewAttr()
    var context: JSContext
    @Binding var state: [String: String]
    @State var localState: [String: String] = [:]
    
    var body: some View {
        Text(text.value(with: state, localState: localState))
            .applyTextAttr(textAttr)
            .applyViewAttributes(viewAttr, state, localState)
            .onTapGesture {
                if let onTap = viewAttr.onTap {
                    state = context.invoke(name: onTap, state, localState)
                }
            }
    }
    
    mutating func create(from decoder: Decoder, context: JSContext) -> TextNode {
        do {
            let container = try decoder.container(keyedBy: CodingKeys.self)
            self.text = try container.decode(String.self, forKey: .text)
            self.textAttr = try container.decodeIfPresent(TextAttr.self, forKey: .textAttr) ?? TextAttr()
            try initFusionView(from: decoder, context: context)
            let localState = try container.decodeIfPresent([String: String].self, forKey: .state) ?? [:]
            self._localState = State(initialValue: localState)
        } catch let decodingError {
            assertionFailure("Error decoding Text: \(decodingError)")
        }
        return self
    }
    
    var description: String {
        formatDescription(type: "Text", attributes: ["text": text, "textAttr": textAttr.description], viewAttr: viewAttr.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case text
        case textAttr
        case state
    }
}

extension Text {
    func applyTextAttr(_ textAttr: TextAttr) -> some View {
        return self.modifier(textAttr)
    }
}

enum AlignMode: String, Codable {
    case start = "START", end = "END", justify = "JUSTIFY", center = "CENTER"
}

enum OverflowMode: String, Codable {
    case ellipsis = "ELLIPSIS", clip = "CLIP"
}

struct TextAttr: Decodable, CustomStringConvertible, ViewModifier {
    var font: Font?
    var align: AlignMode?
    var overflow: OverflowMode?
    var maxLines: Int?
    var isUnderline: Bool?
    
    func body(content: Content) -> some View {
        let fontSize = font?.size ?? 12
        let color = font?.color ?? "#000000"
        let align = align?.textAlignment ?? .center
        let maxLines = maxLines
        let weight = font?.weight?.swiftUIValue ?? .regular
        return content
            .font(.system(size: fontSize, weight: weight))
            .foregroundColor(Color(hex: color))
            .multilineTextAlignment(align)
            .lineLimit(maxLines)
            .asAnyView()
    }
}

struct Font : Decodable {
    var size: CGFloat?
    var weight: Weight?
    var lineHeight: Int?
    var letterSpacing: Int?
    var color: String?
}

struct Weight: Decodable {
    let value: String?
    
    var swiftUIValue: SwiftUI.Font.Weight {
        switch value {
        case "regular": return .regular
        case "medium": return .medium
        case "semiBold": return .semibold
        case "bold": return .bold
        default: return .regular
        }
    }
}
