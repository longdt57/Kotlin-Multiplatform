//
//  AppHorizontalDivider.swift
//  Git Users
//
//  Created by Long Do on 31/12/2025.
//

import SwiftUI

struct AppHorizontalDivider: View {
    var thickness: CGFloat = 1
    var color: Color = Color.gray.opacity(0.2)

    var body: some View {
        Divider()
            .frame(height: thickness)
            .background(color)
    }
}

struct AppHorizontalDivider_Previews: PreviewProvider {
    static var previews: some View {
        AppHorizontalDivider()
            .previewLayout(.sizeThatFits)
            .padding()
    }
}
