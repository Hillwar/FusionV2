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
    }
    
    mutating func create(from decoder: Decoder, _ context: JSContext) throws -> BoxNode {
        try initFusionView(from: decoder, context)
        try initLayout(from: decoder, context, $state)
        return self
    }
    
    var description: String {
        formatDescription(type: "Box", children: children, viewAttr: viewAttr.description)
    }
}

