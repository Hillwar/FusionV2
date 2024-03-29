import Foundation
import JavaScriptCore
import SwiftUI

struct RowNode: Layout {
    var children: [any FusionView] = []
    var viewAttr: ViewAttr = ViewAttr()
    var context: JSContext
    @Binding var state: [String: String]
    @State var localState: [String: String] = [:]
    
    var body: some View {
        let alignment = viewAttr.gravity?.verticalAlignment ?? .center
        HStack(alignment: alignment, spacing: 0) {
            ForEach(0..<(children.count), id: \.self) { index in
                children[index].asAnyView()
            }
        }
        .applyViewAttributes(viewAttr, state, localState)
    }

    mutating func create(from decoder: Decoder, _ context: JSContext) throws -> RowNode {
        try initFusionView(from: decoder, context)
        try initLayout(from: decoder, context, $state)
        return self
    }
    
    var description: String {
        formatDescription(type: "Column", children: children, viewAttr: viewAttr.description)
    }
}

