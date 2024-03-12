import SwiftUI
import http

struct ContentView: View {
    var body: some View {
        VStack {
            Spacer()
            HStack {
                
                
                
                Text("test")
                
            }.padding(15).background(RoundedRectangle(cornerRadius: 200).fill(.white).opacity(0.95)).padding(15)
        }
    }
}

let gradient = LinearGradient(
        colors: [
            Color(red: 0.933, green: 0.937, blue: 0.953),
            Color(red: 0.902, green: 0.941, blue: 0.949)
        ],
        startPoint: .topLeading, endPoint: .bottomTrailing
)
