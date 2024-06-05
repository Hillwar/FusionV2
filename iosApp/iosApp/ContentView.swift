import SwiftUI

struct ContentView: View {
    @ObservedObject var viewModel = MoleculeSupplier()
    
    var body: some View {
        ScrollView {
            if let fusionView = viewModel.fusionView {
                fusionView
            } else {
                Text("Loading...")
                    .onAppear {
                        viewModel.fetchMolecule(from: "http://0.0.0.0:8080/molecule/root")
                    }
            }
        }
        .refreshable {
            viewModel.fetchMolecule(from: "http://0.0.0.0:8080/molecule/root")
        }
        .edgesIgnoringSafeArea(.all) 
        .padding(.top)
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
