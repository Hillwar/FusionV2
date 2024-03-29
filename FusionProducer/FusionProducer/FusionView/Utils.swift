import Foundation

func formatDescription(type: String, attributes: [String: String?] = [:], children: [any FusionView]? = [], viewAttr: String?) -> String {
    var result = "\(type) {\n"

    if !attributes.isEmpty {
        for (key, value) in attributes {
            if let value = value {
                result += "    \(key): \(value)\n"
            }
        }
    }
    
    if let children = children, !children.isEmpty {
        result += "    children: [\n"
        for child in children {
            let childDescription = child.description.split(separator: "\n")
                                          .map { "        \($0)" }
                                          .joined(separator: "\n")
            result += "\(childDescription),\n"
        }
        result += "    ]\n"
    }

    let formattedViewAttr = viewAttr?.split(separator: "\n")
                                     .map { "    \($0)" }
                                     .joined(separator: "\n")
    if let formattedViewAttr = formattedViewAttr, !formattedViewAttr.isEmpty {
        result += "    viewAttr: {\n\(formattedViewAttr)\n    }\n"
    }
    result += "}"
    
    return result
}
