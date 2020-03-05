package com.luckylittlesparrow.healthassistant.core_base_impl.core

open class SingletonHolder<out T, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstanceOrInit(arg: A): T {
        val localInstance = instance
        if (localInstance != null) {
            return localInstance
        }

        return synchronized(this) {
            val i = instance
            if (i != null) {
                i
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }

    fun getInstance(): T? {
        val localInstance = instance
        return if (localInstance != null) {
            localInstance
        } else null

    }
}