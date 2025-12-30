//
//  GitUserListItem.swift
//  Git Users
//
//  Created by Long Do on 31/12/2025.
//

import SwiftUI

struct GitUserListItem: View {
    let title: String
    let avatarUrl: String?
    let htmlUrl: String?

    var body: some View {
        HStack(alignment: .top) {
            UserAvatar(avatarUrl: avatarUrl)
                .frame(width: 120, height: 120)

            Spacer().frame(width: 12)

            VStack(alignment: .leading) {
                GitUserTitle(title: title)
                AppHorizontalDivider()
                if let htmlUrl = htmlUrl {
                    LinkText(url: htmlUrl)
                }
            }
        }
    }
}

struct GitUserTitle: View {
    let title: String

    var body: some View {
        Text(title)
            .font(.title2)
            .fontWeight(.semibold)
            .foregroundColor(.black)
    }
}

struct GitUserListItem_Previews: PreviewProvider {
    static var previews: some View {
        GitUserListItem(
            title: "longdt57",
            avatarUrl: "https://avatars.githubusercontent.com/u/1?v=4",
            htmlUrl: "https://github.com/longdt57"
        )
        .padding(8)
        .previewLayout(.sizeThatFits)
    }
}
