import SwiftUI
//import ComposeApp

@main
struct iOSApp: App {
    init() {
            // ✅ Initialize Koin before anything else uses it
//            KoinKt.doInitKoinIos()
        }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
