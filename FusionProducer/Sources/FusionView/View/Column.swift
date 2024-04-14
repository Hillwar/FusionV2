import Foundation
import JavaScriptCore
import SwiftUI

struct ColumnNode: Layout {
    var children: [any FusionView] = []
    var viewAttr: ViewAttr = ViewAttr()
    var context: JSContext
    @Binding var state: [String: String]
    @State var localState: [String: String] = [:]
    
    var body: some View {
        let alignment = viewAttr.gravity?.horizontalAlignment ?? .center
        VStack(alignment: alignment, spacing: 0) {
            ForEach(0..<(children.count ), id: \.self) { index in
                children[index].asAnyView()
            }
        }
        .applyViewAttributes(viewAttr, state, localState)
    }

    mutating func create(from decoder: Decoder, context: JSContext) -> ColumnNode {
        do {
            let container = try decoder.container(keyedBy: CodingKeys.self)
            try initFusionView(from: decoder, context: context)
            try initLayout(from: decoder, context: context, state: $state)
            let localState = try container.decodeIfPresent([String: String].self, forKey: .state) ?? [:]
            self._localState = State(initialValue: localState)
        } catch let decodingError {
            assertionFailure("Error decoding Column: \(decodingError)")
        }
        return self
    }
    
    var description: String {
        formatDescription(type: "Column", children: children, viewAttr: viewAttr.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case state
    }
}

