import Foundation
import SwiftUI
import JavaScriptCore

struct TextNode: FusionView {
    var text: String
    var textAttr: TextAttr?
    var viewAttr: ViewAttr?
    let context = JSContext()
    
    var body: some View {
        Text(text)
            .applyTextAttr(textAttr)
            .applyViewAttributes(viewAttr)
            .onTapGesture {
                let script = "var num = 5 + 5; num;"
                if let result = context?.evaluateScript(script) {
                    print("result", result.toInt32())
                }
            }
    }

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.text = try container.decode(String.self, forKey: .text)
        self.textAttr = try? container.decode(TextAttr.self, forKey: .textAttr)
        self.viewAttr = try? container.decode(ViewAttr.self, forKey: .viewAttr)
    }
    
    var description: String {
        formatDescription(type: "Text", attributes: ["text": text, "textAttr": textAttr?.description], viewAttr: viewAttr?.description)
    }
    
    enum CodingKeys: String, CodingKey {
        case text
        case textAttr
        case viewAttr
    }
}

extension Text {
    func applyTextAttr(_ textAttr: TextAttr?) -> some View {
        guard let textAttr = textAttr else {
            return AnyView(self)
        }

        let fontSize = textAttr.font?.size ?? 12
        let color = textAttr.font?.color ?? "#FFFFFF"
        let align = textAttr.align?.textAlignment ?? .center
        let maxLines = textAttr.maxLines
        return self
            .font(.system(size: fontSize))
            .foregroundColor(Color(hex: color))
            .multilineTextAlignment(align)
            .lineLimit(maxLines)
            .asAnyView()
    }
}

enum AlignMode: String, Codable {
    case start = "START", end = "END", justify = "JUSTIFY", center = "CENTER"
}

enum OverflowMode: String, Codable {
    case ellipsis = "ELLIPSIS", clip = "CLIP"
}

struct TextAttr: Decodable, CustomStringConvertible {
    var font: Font?
    var align: AlignMode?
    var overflow: OverflowMode?
    var maxLines: Int?
    var isUnderline: Bool?
}

struct Font : Decodable {
    var size: CGFloat?
    var lineHeight: Int?
    var letterSpacing: Int?
    var color: String?
}
