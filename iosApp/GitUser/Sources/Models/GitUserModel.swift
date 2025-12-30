//
//  GitUserModel.swift
//  Domain
//
//  Created by Long Do on 30/12/2024.

//

import Foundation

public struct GitUserModel: Identifiable, Equatable {
    public let id: Int64
    public let login: String
    public let avatarUrl: String?
    public let htmlUrl: String?

    public init(id: Int64, login: String, avatarUrl: String?, htmlUrl: String?) {
        self.id = id
        self.login = login
        self.avatarUrl = avatarUrl
        self.htmlUrl = htmlUrl
    }
}
