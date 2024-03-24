import Foundation

import Foundation
import SwiftUI

struct SpacerNode: FusionView {
    var minLength: CGFloat?
    var viewAttr: ViewAttr?

    var body: some View {
        Spacer(minLength: minLength)
           .applyViewAttributes(viewAttr)
    }

   var description: String {
       formatDescription(type: "Spacer", attributes: ["minLength": minLength?.description], viewAttr: viewAttr?.description)
   }
}

