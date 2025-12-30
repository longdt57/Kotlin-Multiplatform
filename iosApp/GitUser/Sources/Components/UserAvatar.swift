//
//  UserAvatar.swift
//  Git Users
//
//  Created by Long Do on 31/12/2025.
//

import SwiftUI

struct UserAvatar: View {

    var avatarUrl: String?

    var body: some View {
        AsyncImage(url: URL(string: avatarUrl.orEmpty())) { phase in
            switch phase {
            case let .success(image):
                image.resizable()
            default:
                Image("ImageAvatarPlaceHolder").resizable()
            }
        }
        .scaledToFill()
        .clipShape(Circle())
        .padding(4)
        .background(RoundedRectangle(cornerRadius: 8).fill(Color.gray.opacity(0.2)))
    }
}

struct UserAvatar_Previews: PreviewProvider {
    static var previews: some View {
        UserAvatar(avatarUrl: "https://example.com/avatar.jpg")
            .frame(width: 100, height: 100)
            .padding()
    }
}
