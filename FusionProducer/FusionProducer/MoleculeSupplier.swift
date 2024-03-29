import Combine
import SwiftUI

public class MoleculeSupplier: ObservableObject {
    @Published public var fusionView: AnyView? = nil
    
    public init() {}
    
    public func fetchMolecule(from url: String) {
        guard let url = URL(string: url) else {
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
                    let decodedView = try decoder.decode(RootFusionView.self, from: data)
                    print(decodedView.body)
                    self?.fusionView = decodedView.asAnyView()
                } catch {
                    print("Failed to decode JSON: \(error)")
                }
            }
        }.resume()
    }
}

