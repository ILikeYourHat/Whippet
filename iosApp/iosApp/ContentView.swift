import UIKit
import SwiftUI
import CommonApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        WhippetViewControllerKt.create()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(edges: .all)
            .ignoresSafeArea(.keyboard)
    }
}
