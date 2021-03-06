package com.llsparrow.core_base_api.mapper

import java.util.*


abstract class OneWayMapper<T, R> {

    abstract suspend fun map(item: T): R

    suspend fun mapList(list: List<T>): List<R> {
        val convertedList = ArrayList<R>(list.size)
        list.forEach { convertedList.add(map(it)) }
        return convertedList
    }
}
