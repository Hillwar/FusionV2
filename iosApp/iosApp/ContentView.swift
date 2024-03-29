import SwiftUI

struct ContentView: View {
    @ObservedObject var supplier = MoleculeSupplier()

    var body: some View {
        VStack {
            if let fusionView = supplier.fusionView {
                fusionView
            } else {
                Text("Loading...")
                    .onAppear {
                        supplier.fetchMolecule(from: "http://0.0.0.0:8080/molecule")
                    }
            }
        }
        .onTapGesture {
            supplier.fetchMolecule(from: "http://0.0.0.0:8080/molecule")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
