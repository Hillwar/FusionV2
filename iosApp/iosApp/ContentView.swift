import SwiftUI
import FusionProducer

struct ContentView: View {
    @ObservedObject var viewModel = MoleculeSupplier()

    var body: some View {
        VStack {
            if let fusionView = viewModel.fusionView {
                fusionView
            } else {
                Text("Loading...")
                    .onAppear {
                        viewModel.fetchMolecule(from: "http://0.0.0.0:8080/molecule")
                    }
            }
        }
        .onTapGesture {
            viewModel.fetchMolecule(from: "http://0.0.0.0:8080/molecule")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
