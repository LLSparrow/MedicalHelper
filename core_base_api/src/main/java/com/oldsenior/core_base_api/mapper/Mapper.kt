package com.oldsenior.core_base_api.mapper

import java.util.ArrayList

abstract class Mapper<T, R> : OneWayMapper<T, R>() {

    abstract fun reverse(item: R): T

    fun reverseList(list: List<R>): List<T> {
        val convertedList = ArrayList<T>(list.size)
        list.forEach { convertedList.add(reverse(it))  }
        return convertedList
    }
}
