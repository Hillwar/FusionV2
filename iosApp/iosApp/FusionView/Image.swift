import Foundation
import SwiftUI

struct ImageNode: FusionView {
    var source: URL
    var viewAttr: ViewAttr?
    
    var body: some View {
        AsyncImage(url: source) { image in
            image
                .resizable()
                .scaledToFit()
            } placeholder: {
                ProgressView()
            }
            .applyViewAttributes(viewAttr)
    }
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        self.viewAttr = try? container.decode(ViewAttr.self, forKey: .viewAttr)
        self.source = try container.decode(URL.self, forKey: .source)
    }
    
    var description: String {
        formatDescription(type: "Image", attributes: ["source": source.absoluteString], viewAttr: viewAttr?.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case source
        case viewAttr
    }
}


