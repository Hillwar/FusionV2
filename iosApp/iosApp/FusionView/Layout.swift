import Foundation
import SwiftUI

protocol Layout: FusionView {
    var children: [any FusionView]? { get }
    init(from decoder: Decoder) throws
}

enum CodingKeys: String, CodingKey {
    case children, viewAttr
}
