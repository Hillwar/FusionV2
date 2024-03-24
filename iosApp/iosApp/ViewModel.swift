import Combine
import SwiftUI

class ViewModel: ObservableObject {
    @Published var fusionView: AnyView?
    
    func fetchMolecule() {
        let urlString = "http://0.0.0.0:8080/molecule"
        
        guard let url = URL(string: urlString) else {
            print("Invalid URL")
            return
        }
        
        URLSession.shared.dataTask(with: url) { [weak self] data, response, error in
            DispatchQueue.main.async {
                guard let data = data, error == nil,
                      let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
                    print("Error fetching data: \(error?.localizedDescription ?? "Unknown error")")
                    return
                }
                
                do {
                    let decoder = JSONDecoder()
                    // Update this part according to how your JSON is structured and how your FusionView conforms
                    let decodedView = try decoder.decode(FusionViewWrapper.self, from: data)
                    print(decodedView.view.description)
                    self?.fusionView = decodedView.view.body.asAnyView()
                } catch {
                    print("Failed to decode JSON: \(error)")
                }
            }
        }.resume()
    }
    
    func example() -> AnyView {
        do {
            let decoder = JSONDecoder()
            let string = """
        {
            "type": "Box",
            "children": [],
            "viewAttr": {
              "size": {
                "maxWidth": {
                  "value": 200.0
                },
                "maxHeight": {
                  "value": 200.0
                }
              },
              "background": {
                "color": "#FFFFFF"
              }
            }
          }
"""
            guard let data = string.data(using: .utf8) else {
                return AnyView(EmptyView())
            }
            let decodedView = try decoder.decode(FusionViewWrapper.self, from: data)
            return decodedView.view.body.asAnyView()
        } catch {
            return AnyView(EmptyView())
        }
    }
}

