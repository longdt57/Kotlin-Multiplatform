//
//  GitUserDetailModel.swift
//  Domain
//
//  Created by Long Do on 31/12/2024.
//

import Foundation

public struct GitUserDetailModel: Identifiable, Equatable {
    public var id: String { login }

    public var login: String
    public var name: String?
    public var avatarUrl: String?
    public var blog: String?
    public var location: String?
    public var followers: String
    public var following: String

    // Public initializer
    public init(
        login: String,
        name: String? = nil,
        avatarUrl: String? = nil,
        blog: String? = nil,
        location: String? = nil,
        followers: String,
        following: String
    ) {
        self.login = login
        self.name = name
        self.avatarUrl = avatarUrl
        self.blog = blog
        self.location = location
        self.followers = followers
        self.following = following
    }
}
