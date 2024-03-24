import Foundation

struct FusionViewWrapper: Decodable {
    let view: any FusionView

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        let type = try container.decode(String.self, forKey: .type)
        
        switch type {
        case "Box":
            view = try BoxNode(from: decoder)
        case "Column":
            view = try ColumnNode(from: decoder)
        case "Row":
            view = try RowNode(from: decoder)
        case "Text":
            view = try TextNode(from: decoder)
        case "Spacer":
            view = try SpacerNode(from: decoder)
        case "Image":
            view = try ImageNode(from: decoder)
        default:
            throw DecodingError.dataCorruptedError(forKey: .type, in: container, debugDescription: "Unknown type: \(type)")
        }
    }

    private enum CodingKeys: String, CodingKey {
        case type
    }
}
