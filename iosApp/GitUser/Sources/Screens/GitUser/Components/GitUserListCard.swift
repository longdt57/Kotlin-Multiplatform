//
//  GitUserListCard.swift
//  Git Users
//
//  Created by Long Do on 31/12/2025.
//

import SwiftUI

struct GitUserListCard: View {
    var user: GitUserModel
    var onClick: (GitUserModel) -> Void

    var body: some View {
        UserCard {
            GitUserListItem(
                title: user.login,
                avatarUrl: user.avatarUrl,
                htmlUrl: user.htmlUrl
            )
            .padding(12)
        }
    }
}

struct GitUserListCard_Previews: PreviewProvider {
    static var previews: some View {
        GitUserListCard(
            user: GitUserModel(
                id: 1,
                login: "longdt57",
                avatarUrl: "https://avatars.githubusercontent.com/u/1?v=4",
                htmlUrl: "https://github.com/longdt57"
            ),
            onClick: { _ in }
        )
        .previewLayout(.sizeThatFits)
    }
}
