package com.sj7.searchablespinner.common

data class Spinner(
    var id: String = "0",
    var name: String = "",
    var isSelected: Boolean = false,
    var parentId: Int = 0
)