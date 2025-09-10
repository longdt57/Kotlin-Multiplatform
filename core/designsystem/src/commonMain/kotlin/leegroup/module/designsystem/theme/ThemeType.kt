package leegroup.module.designsystem.theme

enum class ThemeType(val value: String) {
    DARK("Dark"), LIGHT("Light"), SYSTEM("System");

    companion object {
        fun fromCode(value: String?): ThemeType {
            return entries.firstOrNull { it.value.equals(value, ignoreCase = true) } ?: SYSTEM
        }
    }
}