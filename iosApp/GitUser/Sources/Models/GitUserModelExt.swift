//
//  GitUserModel.swift
//  iosApp
//
//  Created by Long Do on 12/30/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import ComposeApp

//typealias GitUserModel = GituserGitUserModel
//typealias GitUserDetailModel = GituserGitUserDetailUiModel

extension GituserGitUserModel {
    func toGitUserModel() -> GitUserModel {
        return .init(
            id: id,
            login: login,
            avatarUrl: avatarUrl,
            htmlUrl: htmlUrl
        )
    }
}

extension GituserGitUserDetailUiModel {
    func toGitUserDetailModel() -> GitUserDetailModel {
        return .init(
            login: login,
            name: name,
            avatarUrl: avatarUrl,
            blog: blog,
            location: location,
            followers: followers,
            following: following
        )
    }
}
