import Foundation
import SwiftUI
import JavaScriptCore

struct DecodingHelper {
    static func decodeChildren(from decoder: Decoder, context: JSContext, state: Binding<[String: String]>) throws -> [any FusionView] {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        var childrenContainer = try container.nestedUnkeyedContainer(forKey: .children)
        var childrenArray = [any FusionView]()
        
        while !childrenContainer.isAtEnd {
            let childDecoder = try childrenContainer.superDecoder()
            let child = try FusionViewWrapper(from: childDecoder, context: context, state: state).view
            childrenArray.append(child)
        }
        
        return childrenArray
    }
    
    private enum CodingKeys: String, CodingKey {
        case children
    }
}
