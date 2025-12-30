//
//  GitUserList.swift
//  Git Users
//
//  Created by Long Do on 31/12/2025.
//

import ComposeApp
import SwiftUI

struct GitUserList: View {
    var users: [GitUserModel]
    var onClick: (GitUserModel) -> Void
    var onLoadMore: () -> Void

    @State private var isNearBottom: Bool = false

    var body: some View {
        ScrollView {
            LazyVStack(spacing: 10) { // Use LazyVStack instead of VStack
                ForEach(users) { user in
                    NavigationLink(destination: {
//                        GitUserDetailScreen(login: user.login)
                    }, label: {
                        GitUserListCard(user: user, onClick: onClick)
                            .onAppear {
                                if user == users.last {
                                    // Trigger load more when the last item appears
                                    onLoadMore()
                                }
                            }
                            .padding(.horizontal)
                    })
                }
                Spacer().frame(height: 16)
            }
            .padding(.top, 8)
        }
    }
}

struct GitUserList_Previews: PreviewProvider {
    static var previews: some View {
        GitUserList(
            users: [
                GitUserModel(
                    id: 1,
                    login: "longdt57",
                    avatarUrl: "https://avatars.githubusercontent.com/u/1?v=4",
                    htmlUrl: "https://github.com/longdt57"
                )
            ],
            onClick: { _ in },
            onLoadMore: { print("Load more") }
        )
        .frame(maxWidth: .infinity, maxHeight: 500)
        .previewLayout(.sizeThatFits)
    }
}
