package com.example.testeapp.common

    fun <T> List<T>.areListsEqual(otherList: List<T>): Boolean {
        if (this.size != otherList.size) {
            return false
        }

        for (i in this.indices) {
            if (this[i] != otherList[i]) {
                return false
            }
        }

        return true
    }