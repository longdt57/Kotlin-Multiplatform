//
//  Bool+Extensions.swift
//  Git Users
//
//  Created by Do, LongThanh | MDSD on 2024/11/25.
//

extension Optional where Wrapped == Bool {
    public var orFalse: Bool {
        self ?? false
    }
}

extension Bool {
    public func not() -> Bool {
        !self
    }
}
