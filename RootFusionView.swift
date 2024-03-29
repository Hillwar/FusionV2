import Foundation
import SwiftUI
import JavaScriptCore

struct RootFusionView: Decodable, View {
    @State var state: [String: String] = [:]
    var script: String = ""
    let context: JSContext
    let decoder: Decoder
    
    var body: some View {
        try? FusionViewWrapper(from: decoder, context, $state).view.asAnyView()
    }
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.decoder = try container.superDecoder(forKey: .rootView)
        let script = (try? container.decode(String.self, forKey: .script)) ?? ""
        self.context = JSContext()!
        context.evaluateScript(script)
        self._state = State(initialValue: (try? container.decode([String: String].self, forKey: .state)) ?? [:])
    
    }
    
    private enum CodingKeys: String, CodingKey {
        case rootView
        case state
        case script
    }
}
