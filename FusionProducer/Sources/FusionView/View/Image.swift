import Foundation
import JavaScriptCore
import SwiftUI

struct ImageNode: FusionView {
    var source: String = ""
    var viewAttr: ViewAttr = ViewAttr()
    var context: JSContext
    @Binding var state: [String: String]
    @State var localState: [String: String] = [:]
    
    var body: some View {
        AsyncImage(url: URL(string: source.value(with: state, localState: localState))) { image in
            image
                .resizable()
                .scaledToFit()
            } placeholder: {
                ProgressView()
            }
            .applyViewAttributes(viewAttr, state, localState)
    }
    
    mutating func create(from decoder: Decoder, context: JSContext) -> ImageNode {
        do {
            let container = try decoder.container(keyedBy: CodingKeys.self)
            self.source = try container.decode(String.self, forKey: .source)
            try initFusionView(from: decoder, context: context)
            let localState = try container.decodeIfPresent([String: String].self, forKey: .state) ?? [:]
            self._localState = State(initialValue: localState)
        } catch let decodingError {
            assertionFailure("Error decoding Image: \(decodingError)")
        }
        return self
    }
    
    var description: String {
        formatDescription(type: "Image", attributes: ["source": source], viewAttr: viewAttr.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case source
        case state
    }
}


