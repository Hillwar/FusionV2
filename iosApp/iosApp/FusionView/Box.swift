import Foundation
import SwiftUI

struct BoxNode: Layout {    
    var children: [any FusionView]?
    var viewAttr: ViewAttr? 

    var body: some View {
        ZStack() {
            ForEach(0..<(children?.count ?? 0), id: \.self) { index in
                children?[index].body.asAnyView()
            }
        }
        .applyViewAttributes(viewAttr)
    }

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.viewAttr = try? container.decode(ViewAttr.self, forKey: .viewAttr)
        self.children = try? DecodingHelper.decodeChildren(from: decoder)
    }
    
    var description: String {
        formatDescription(type: "Box", children: children, viewAttr: viewAttr?.description)
    }
}

