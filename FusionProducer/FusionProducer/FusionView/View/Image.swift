import Foundation
import JavaScriptCore
import SwiftUI

struct ImageNode: FusionView {
    var source: URL
    var viewAttr: ViewAttr
    var context: JSContext
    @Binding var state: [String: String]
    @State var localState: [String: String] = [:]
    
    var body: some View {
        AsyncImage(url: source) { image in
            image
                .resizable()
                .scaledToFit()
            } placeholder: {
                ProgressView()
            }
            .applyViewAttributes(viewAttr, state, localState)
    }
    
    init(from decoder: Decoder, _ context: JSContext, _ state: Binding<[String: String]>) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.source = try container.decode(URL.self, forKey: .source)
        self.viewAttr = (try? container.decode(ViewAttr.self, forKey: .viewAttr)) ?? ViewAttr()
        self.context = context
        self._state = state
    }
    
    var description: String {
        formatDescription(type: "Image", attributes: ["source": source.absoluteString], viewAttr: viewAttr.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case source
        case viewAttr
        case state
        case script
    }
}


