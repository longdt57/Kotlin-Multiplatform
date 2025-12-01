package leegroup.module.core.util

object LoremIpsum {

    private val words = listOf(
        "lorem", "ipsum", "dolor", "sit", "amet", "consectetur",
        "adipiscing", "elit", "sed", "do", "eiusmod", "tempor",
        "incididunt", "ut", "labore", "et", "dolore", "magna", "aliqua"
    )

    data class Result(val values: List<String>)

    operator fun invoke(wordCount: Int): Result {
        val text = List(wordCount) { words.random() }
            .joinToString(" ")

        return Result(values = listOf(text))
    }

    fun sentence(wordCount: Int = 12): String =
        List(wordCount) { words.random() }
            .joinToString(" ")
            .replaceFirstChar { it.uppercase() } + "."

    fun paragraph(
        sentenceCount: Int = 4,
        sentenceWordCount: Int = 12
    ): String =
        List(sentenceCount) { sentence(sentenceWordCount) }
            .joinToString(" ")
}