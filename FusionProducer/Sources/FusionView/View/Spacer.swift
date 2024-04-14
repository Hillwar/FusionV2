import Foundation
import JavaScriptCore

import Foundation
import SwiftUI

struct SpacerNode: FusionView {
    var minLength: CGFloat?
    var viewAttr: ViewAttr = ViewAttr()
    var context: JSContext
    @Binding var state: [String : String]
    @State var localState: [String: String] = [:]
    
    var body: some View {
        Spacer(minLength: minLength)
            .applyViewAttributes(viewAttr, state, localState)
    }
    
    mutating func create(from decoder: Decoder, context: JSContext) -> SpacerNode {
        do {
            let container = try decoder.container(keyedBy: CodingKeys.self)
            self.minLength = try container.decodeIfPresent(CGFloat.self, forKey: .minLength)
            try initFusionView(from: decoder, context: context)
            let localState = try container.decodeIfPresent([String: String].self, forKey: .state) ?? [:]
            self._localState = State(initialValue: localState)
        } catch let decodingError {
            assertionFailure("Error decoding Spacer: \(decodingError)")
        }
        return self
    }
    
    var description: String {
        formatDescription(type: "Spacer", attributes: ["minLength": minLength?.description], viewAttr: viewAttr.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case minLength
        case state
    }
}

