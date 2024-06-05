import Foundation
import JavaScriptCore

extension JSContext {
    /// Invoke a JavaScript function with the given name, using both `state` and `localState`.
    /// - Parameters:
    ///   - name: The name of the JavaScript function to call.
    ///   - state: The main state dictionary to pass to the JavaScript function.
    ///   - localState: The local state dictionary to pass to the JavaScript function.
    /// - Returns: A dictionary with the possibly modified state, according to the JavaScript function's return value.
    func invoke(name: String, _ state: [String: String], _ localState: [String: String]) -> [String: String] {
        // Serialize the state dictionary to JSON string.
        guard let jsStateData = try? JSONSerialization.data(withJSONObject: state, options: []),
              let jsStateString = String(data: jsStateData, encoding: .utf8) else {
            print("Failed to serialize state")
            return state
        }

        // Serialize the localState dictionary to JSON string.
        guard let jsLocalStateData = try? JSONSerialization.data(withJSONObject: localState, options: []),
              let jsLocalStateString = String(data: jsLocalStateData, encoding: .utf8) else {
            print("Failed to serialize localState")
            return state
        }

        // Prepare the script to call the JavaScript function with both state and localState.
        let script = "\(name)(\(jsStateString), \(jsLocalStateString))"
        
        // Evaluate the script and attempt to convert the result to a dictionary.
        if let result = self.evaluateScript(script)?.toDictionary() as? [String: String] {
            return result
        }

        // Return the original state if JavaScript evaluation fails or does not return a dictionary.
        return state
    }
}
