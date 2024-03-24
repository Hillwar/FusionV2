import Foundation

struct DecodingHelper {
    static func decodeChildren(from decoder: Decoder) throws -> [any FusionView] {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        var childrenContainer = try container.nestedUnkeyedContainer(forKey: .children)
        var childrenArray = [any FusionView]()
        
        while !childrenContainer.isAtEnd {
            let childDecoder = try childrenContainer.superDecoder()
            let child = try FusionViewWrapper(from: childDecoder).view
            childrenArray.append(child)
        }
        
        return childrenArray
    }
    
    private enum CodingKeys: String, CodingKey {
        case children
    }
}
