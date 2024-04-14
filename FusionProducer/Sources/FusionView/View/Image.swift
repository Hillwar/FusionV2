import Foundation
import JavaScriptCore
import SwiftUI

struct ImageNode: FusionView {
    var source: String = ""
    var viewAttr: ViewAttr = ViewAttr()
    var context: JSContext
    @Binding var state: [String: String]
    @State var localState: [String: String] = [:]
    @State private var imageData: Data?
    @State private var isAnimating = true
    
    var body: some View {
        Group {
            if let imageData = imageData, let uiImage = UIImage(data: imageData) {
                Image(uiImage: uiImage)
                    .resizable()
                    .scaledToFit()
            } else {
                ActivityIndicator(isAnimating: $isAnimating, style: .medium)
                    .onAppear {
                        loadImage()
                    }
            }
        }
        .applyViewAttributes(viewAttr, state, localState)
    }
    
    mutating func create(from decoder: Decoder, context: JSContext) -> ImageNode {
        do {
            let container = try decoder.container(keyedBy: CodingKeys.self)
            self.source = try container.decode(String.self, forKey: .source)
            try initFusionView(from: decoder, context: context)
            let localState = try container.decodeIfPresent([String: String].self, forKey: .state) ?? [:]
            self._localState = State(initialValue: localState)
        } catch let decodingError {
            assertionFailure("Error decoding Image: \(decodingError)")
        }
        return self
    }
    
    var description: String {
        formatDescription(type: "Image", attributes: ["source": source], viewAttr: viewAttr.description)
    }
    
    private enum CodingKeys: String, CodingKey {
        case source
        case state
    }
    
    func loadImage() {
        guard let url = URL(string: source.value(with: state, localState: localState)) else {
            return
        }
        URLSession.shared.dataTask(with: url) { data, response, error in
            DispatchQueue.main.async {
                if let data = data {
                    self.imageData = data
                }
                self.isAnimating = false
            }
        }.resume()
    }
}


