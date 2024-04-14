import Foundation
import SwiftUI
import JavaScriptCore

public struct RootFusionView: Decodable, View {
    @State private var state: [String: String]
    private var script: String
    private var context: JSContext?
    private var decoder: Decoder
    
    public var body: some View {
        guard let context = context, let fusionView = try? FusionViewWrapper(from: decoder, context: context, state: $state).view else {
            return AnyView(Text("Unable to load the view."))
        }
        print(fusionView.description)
        return fusionView.asAnyView()
    }
    
    public init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.decoder = try container.superDecoder(forKey: .rootView)
        self.script = try container.decode(String.self, forKey: .script)
        let state = try container.decode([String: String].self, forKey: .state)
        self._state = State(initialValue: state)
        self.context = JSContext()
        self.context?.exceptionHandler = { context, exception in
            print("JS Error: \(exception?.toString() ?? "unknown error")")
        }
        context?.evaluateScript(script)
    }
    
    private enum CodingKeys: String, CodingKey {
        case rootView
        case state
        case script
    }
}
