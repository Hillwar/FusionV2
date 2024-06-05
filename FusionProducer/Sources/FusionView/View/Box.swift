import Foundation
import JavaScriptCore
import SwiftUI

struct BoxNode: Layout {
    var children: [any FusionView] = []
    var viewAttr: ViewAttr = ViewAttr()
    var context: JSContext
    @Binding var state: [String: String]
    @State var localState: [String: String] = [:]
    
    var body: some View {
        let alignment = viewAttr.gravity?.alignment ?? .center
        ZStack(alignment: alignment) {
            ForEach(0..<(children.count), id: \.self) { index in
                children[index].asAnyView()
            }
        }
        .applyViewAttributes(viewAttr, state, localState)
        .onTapGesture {
            if let onTap = viewAttr.onTap {
                (state, localState) = context.invoke(name: onTap, state, localState)
            }
        }
        .onAppear {
            if let onAppear = viewAttr.onAppear {
                (state, localState) = context.invoke(name: onAppear, state, localState)
            }
        }
    }
    
    mutating func create(from decoder: Decoder, context: JSContext) -> BoxNode {
        do {
            let container = try decoder.container(keyedBy: CodingKeys.self)
            try initFusionView(from: decoder, context: context)
            try initLayout(from: decoder, context: context, state: $state)
            let localState = try container.decodeIfPresent([String: String].self, forKey: .state) ?? [:]
            self._localState = State(initialValue: localState)
        } catch let decodingError {
            assertionFailure("Error decoding Box: \(decodingError)")
        }
        return self
    }
    
    var description: String {
        formatDescription(type: "Box", children: children, viewAttr: viewAttr.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case state
    }
}

