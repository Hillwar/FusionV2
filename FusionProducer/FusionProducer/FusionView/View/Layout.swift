import Foundation
import JavaScriptCore
import SwiftUI

protocol Layout: FusionView {
    var children: [any FusionView] { get set }
}

extension Layout {
    mutating func initLayout(from decoder: Decoder, _ context: JSContext, _ state: Binding<[String: String]>) throws {
        self.children = try DecodingHelper.decodeChildren(from: decoder, context, state)
    }
}
