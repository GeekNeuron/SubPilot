package com.geekneuron.subpilot.data

data class SubtitleLine(
    val index: Int,
    val startMs: Long,
    val endMs: Long,
    val text: String
)
