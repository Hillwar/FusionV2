import Foundation
import SwiftUI
import JavaScriptCore

struct FusionViewWrapper {
    var view: any FusionView
    
    init(from decoder: Decoder, context: JSContext, state: Binding<[String: String]>) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        let type = try container.decode(String.self, forKey: .type)
        
        switch type {
        case "Box":
            var box = BoxNode(context: context, state: state)
            view = box.create(from: decoder, context: context)
        case "Column":
            var column = ColumnNode(context: context, state: state)
            view =  column.create(from: decoder, context: context)
        case "Row":
            var row = RowNode(context: context, state: state)
            view =  row.create(from: decoder, context: context)
        case "Text":
            var text = TextNode(context: context, state: state)
            view =  text.create(from: decoder, context: context)
        case "Spacer":
            var spacer =  SpacerNode(context: context, state: state)
            view =  spacer.create(from: decoder, context: context)
        case "Image":
            var image = ImageNode(context: context, state: state)
            view =  image.create(from: decoder, context: context)
        default:
            throw DecodingError.dataCorruptedError(forKey: .type, in: container, debugDescription: "Unknown type: \(type)")
        }
    }
    
    private enum CodingKeys: String, CodingKey {
        case type
    }
}
