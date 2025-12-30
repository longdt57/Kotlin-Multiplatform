//
//  UserCard.swift
//  Git Users
//
//  Created by Long Do on 31/12/2025.
//

import SwiftUI

struct UserCard<Content: View>: View {

    init(@ViewBuilder content: () -> Content) {
        self.content = content()
    }

    let content: Content

    var body: some View {
        ZStack {
            // Background
            RoundedRectangle(cornerRadius: 8)
                .fill(Color.white) // Card background color (you can adjust)
                .shadow(radius: 4) // Card shadow (elevation effect)

            content
        }
    }
}

struct UserCard_Previews: PreviewProvider {
    static var previews: some View {
        UserCard {
            HStack {
                // Example of user card content
                Text("User Name")
                    .font(.headline)
                Spacer()
                Text("Details")
                    .font(.subheadline)
            }
            .padding(16)
        }
        .padding(16)
        .frame(height: 80)
    }
}
