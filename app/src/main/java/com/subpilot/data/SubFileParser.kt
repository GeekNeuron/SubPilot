package com.geekneuron.subpilot.subtitle

import okio.BufferedSource
import okio.FileSystem
import okio.Path
import okio.buffer
import okio.use

sealed class SubtitleEntry(open val startMs: Long, open val endMs: Long, open val text: String)

data class SRTEntry(
    override val startMs: Long,
    override val endMs: Long,
    override val text: String
) : SubtitleEntry(startMs, endMs, text)

data class ASSEntry(
    override val startMs: Long,
    override val endMs: Long,
    override val text: String,
    val style: String = "Default"
) : SubtitleEntry(startMs, endMs, text)

object SubFileParser {
    fun parse(fileSystem: FileSystem, path: Path): List<SubtitleEntry> {
        val extension = path.name.substringAfterLast(".").lowercase()
        return when (extension) {
            "srt" -> parseSRT(fileSystem, path)
            "ass" -> parseASS(fileSystem, path)
            else -> emptyList()
        }
    }

    private fun parseSRT(fs: FileSystem, path: Path): List<SRTEntry> {
        val result = mutableListOf<SRTEntry>()
        fs.source(path).buffer().use { source ->
            val lines = source.readUtf8().split("\r?\n")
            var i = 0
            while (i < lines.size) {
                val index = lines[i].trim()
                i++
                if (i >= lines.size) break
                val timeLine = lines[i++].trim()
                val timeParts = timeLine.split(" --> ")
                if (timeParts.size != 2) continue
                val startMs = parseSrtTime(timeParts[0])
                val endMs = parseSrtTime(timeParts[1])
                val textLines = mutableListOf<String>()
                while (i < lines.size && lines[i].isNotBlank()) {
                    textLines.add(lines[i++])
                }
                result.add(SRTEntry(startMs, endMs, textLines.joinToString("\n")))
                while (i < lines.size && lines[i].isBlank()) i++
            }
        }
        return result
    }

    private fun parseSrtTime(timeStr: String): Long {
        val parts = timeStr.split(":", ",")
        val h = parts[0].toInt()
        val m = parts[1].toInt()
        val s = parts[2].toInt()
        val ms = parts[3].toInt()
        return ((h * 3600 + m * 60 + s) * 1000L + ms)
    }

    private fun parseASS(fs: FileSystem, path: Path): List<ASSEntry> {
        val result = mutableListOf<ASSEntry>()
        var dialogueFound = false
        fs.source(path).buffer().use { source ->
            source.readUtf8LineSequence().forEach { line ->
                if (line.startsWith("[Events]")) {
                    dialogueFound = true
                } else if (dialogueFound && line.startsWith("Dialogue:")) {
                    val parts = line.split(",", limit = 10)
                    if (parts.size >= 10) {
                        val startMs = parseAssTime(parts[1])
                        val endMs = parseAssTime(parts[2])
                        val text = parts[9].trim()
                        result.add(ASSEntry(startMs, endMs, text))
                    }
                }
            }
        }
        return result
    }

    private fun parseAssTime(timeStr: String): Long {
        val parts = timeStr.split(":", ".")
        val h = parts[0].toInt()
        val m = parts[1].toInt()
        val s = parts[2].toInt()
        val cs = parts[3].toInt()
        return ((h * 3600 + m * 60 + s) * 1000L + cs * 10)
    }
}
