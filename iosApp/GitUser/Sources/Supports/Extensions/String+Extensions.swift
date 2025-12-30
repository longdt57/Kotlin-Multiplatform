//
//  String+Extensions.swift
// Git Users
//
//  Created by Do, LongThanh | MDSD on 2024/11/25.
//

import Foundation
import UIKit

extension String {

    public func isBlank() -> Bool {
        trimmingCharacters(in: .whitespaces).isEmpty
    }

    public func ifEmpty(defaultValue: () -> String) -> String {
        isEmpty ? defaultValue() : self
    }

    public func ifBlank(defaultValue: () -> String) -> String {
        isBlank() ? defaultValue() : self
    }
}

extension Optional where Wrapped == String {

    public func orEmpty() -> String {
        self ?? ""
    }

    public func ifNil(defaultValue: () -> String) -> String {
        self ?? defaultValue()
    }

    public func ifNilOrBlank(defaultValue: () -> String) -> String {
        isNilOrBlank() ? defaultValue() : self!
    }

    public func isNilOrEmpty() -> Bool {
        self?.isEmpty ?? true
    }

    public func isNilOrBlank() -> Bool {
        self?.isBlank() ?? true
    }
}
