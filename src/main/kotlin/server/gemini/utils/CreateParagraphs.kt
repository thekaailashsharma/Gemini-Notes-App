package server.gemini.utils

data class Paragraph(val text: String)

fun String.splitIntoParagraphs(): List<Paragraph> {
    val lines = this.lines()
    val paragraphs = mutableListOf<Paragraph>()
    val paragraphBuilder = StringBuilder()

    for (line in lines) {
        if (line.isNotBlank()) {
            if (paragraphBuilder.isNotEmpty()) {
                paragraphBuilder.append("\n")
            }
            paragraphBuilder.append(line.trim())
        } else if (paragraphBuilder.isNotEmpty()) {
            paragraphs.add(Paragraph(paragraphBuilder.toString()))
            paragraphBuilder.clear()
        }
    }
    if (paragraphBuilder.isNotEmpty()) {
        paragraphs.add(Paragraph(paragraphBuilder.toString()))
    }
    return paragraphs
}
