import Combine
import SwiftUI

public class MoleculeSupplier: ObservableObject {
    @Published public var fusionView: AnyView? = nil
    
    public init() {}
    
    public func fetchMolecule(from urlString: String) {
        guard let url = URL(string: urlString) else {
            self.handleFetchError(.invalidURL)
            return
        }
        
        URLSession.shared.dataTask(with: url) { [weak self] data, response, error in
            DispatchQueue.main.async {
                if let error = error {
                    self?.handleFetchError(.networkError(error))
                    return
                }
                
                guard let httpResponse = response as? HTTPURLResponse else {
                    self?.handleFetchError(.invalidResponse)
                    return
                }
                
                guard (200...299).contains(httpResponse.statusCode) else {
                    self?.handleFetchError(.statusCode(httpResponse.statusCode))
                    return
                }
                
                guard let data = data else {
                    self?.handleFetchError(.noData)
                    return
                }
                
                do {
                    let decoder = JSONDecoder()
                    let decodedView = try decoder.decode(RootFusionView.self, from: data)
                    self?.fusionView = decodedView.asAnyView()
                } catch {
                    self?.handleFetchError(.decodingError(error))
                }
            }
        }.resume()
    }
    
    private func handleFetchError(_ error: FetchError) {
        switch error {
        case .invalidURL:
            print("Invalid URL")
        case .networkError(let error):
            print("Network error: \(error.localizedDescription)")
        case .invalidResponse:
            print("Invalid response from server")
        case .statusCode(let code):
            print("Received HTTP status code \(code)")
        case .noData:
            print("No data received from server")
        case .decodingError(let error):
            print("JSON decoding error: \(error.localizedDescription)")
        }
    }
}

enum FetchError: Error {
    case invalidURL
    case networkError(Error)
    case invalidResponse
    case statusCode(Int)
    case noData
    case decodingError(Error)
}
