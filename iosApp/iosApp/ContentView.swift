import SwiftUI

struct ContentView: View {
    @ObservedObject var viewModel = ViewModel()

    var body: some View {
        VStack {
            if let fusionView = viewModel.fusionView {
                fusionView
            } else {
                Text("Loading...")
                    .onAppear(perform: viewModel.fetchMolecule)
            }
        }
        .onTapGesture {
            viewModel.fetchMolecule()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
