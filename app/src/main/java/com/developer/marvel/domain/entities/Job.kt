package com.developer.marvel.domain.entities

enum class JobType(val label: String) {
    COMICS("comics"),
    SERIES("series"),
    STORIES("stories"),
    EVENTS("events");
}

class Job (
    val type: JobType,
    val available: Int,
    val items: List<JobItem>
)