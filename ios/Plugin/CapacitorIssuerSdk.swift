import Foundation

@objc public class CapacitorIssuerSdk: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
