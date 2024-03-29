import Foundation
import JavaScriptCore

import Foundation
import SwiftUI

struct SpacerNode: FusionView {
    var minLength: CGFloat?
    var viewAttr: ViewAttr
    var context: JSContext
    var state: [String : String]
    @State var localState: [String: String] = [:]
    
    var body: some View {
        Spacer(minLength: minLength)
            .applyViewAttributes(viewAttr, state, localState)
    }
    
    init(from decoder: Decoder, _ context: JSContext, _ state: Binding<[String: String]>) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.minLength = try container.decode(CGFloat.self, forKey: .minLength)
        self.viewAttr = (try? container.decode(ViewAttr.self, forKey: .viewAttr)) ?? ViewAttr()
        self.state = (try? container.decode([String: String].self, forKey: .state)) ?? [:]
        self.context = context
    }
    
    var description: String {
        formatDescription(type: "Spacer", attributes: ["minLength": minLength?.description], viewAttr: viewAttr.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case minLength
        case state
        case viewAttr
        case script
    }
}

