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
       Text(text.get(state, localState))
            .applyTextAttr(textAttr)
            .applyViewAttributes(viewAttr, state, localState)
            .onTapGesture {
                if let onTap = viewAttr.onTap {
                    state = context.invoke(name: onTap, state, localState)
                }
            }
    }
    
    mutating func create (from decoder: Decoder, _ context: JSContext) throws -> TextNode {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.text = try container.decode(String.self, forKey: .text)
        self.textAttr = (try? container.decode(TextAttr.self, forKey: .textAttr)) ?? TextAttr()
        try initFusionView(from: decoder, context)
        self._localState = State(initialValue: (try? container.decode([String: String].self, forKey: .state)) ?? [:])
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
        return content
            .font(.system(size: fontSize))
            .foregroundColor(Color(hex: color))
            .multilineTextAlignment(align)
            .lineLimit(maxLines)
            .asAnyView()
    }
}

struct Font : Decodable {
    var size: CGFloat?
    var lineHeight: Int?
    var letterSpacing: Int?
    var color: String?
}
