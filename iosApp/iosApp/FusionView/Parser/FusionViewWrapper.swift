import Foundation
import SwiftUI
import JavaScriptCore

struct FusionViewWrapper {
    var view: any FusionView
    
    init(from decoder: Decoder, _ context: JSContext, _ state: Binding<[String: String]>) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        let type = try container.decode(String.self, forKey: .type)
        
        switch type {
        case "Box":
            var box = BoxNode(context: context, state: state)
            view = try box.create(from: decoder, context)
        case "Column":
            var column = ColumnNode(context: context, state: state)
            view = try column.create(from: decoder, context)
        case "Row":
            var row = RowNode(context: context, state: state)
            view = try row.create(from: decoder, context)
        case "Text":
            var text = TextNode(context: context, state: state)
            view = try text.create(from: decoder, context)
        case "Spacer":
            view = try SpacerNode(from: decoder, context, state)
        case "Image":
            view = try ImageNode(from: decoder, context, state)
        default:
            throw DecodingError.dataCorruptedError(forKey: .type, in: container, debugDescription: "Unknown type: \(type)")
        }
    }
    
    private enum CodingKeys: String, CodingKey {
        case type
    }
}
