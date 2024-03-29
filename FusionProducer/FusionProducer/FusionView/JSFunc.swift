import Foundation
import JavaScriptCore

extension JSContext {
    func invoke(name: String, _ state: [String: String], _ localState: [String: String]) -> [String: String]{
        if let jsState = try? JSONSerialization.data(withJSONObject: state, options: []),
           let jsStateString = String(data: jsState, encoding: .utf8) {
            if let newState = self.evaluateScript("\(name)(\(jsStateString))").toDictionary() as? [String: String] {
                return newState
            }
        }
        return state
    }
}
