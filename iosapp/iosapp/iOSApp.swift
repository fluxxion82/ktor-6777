import SwiftUI
import http

@main
struct iOSApp: App {
    init() {
        startKoin()
        
        Task {
            do {
                var fooClient = KotlinDependencies.shared.fooClient
                var response = try await fooClient.getFooResponse()
                print("get foo response:", response as Any)
            } catch {
                print(error)
            }
        }
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
