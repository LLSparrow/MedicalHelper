package com.llsparrow.healthassistant.core_base_impl.rx.scheduler

import io.reactivex.Scheduler

interface RxSchedulers {

    fun main(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun newThread(): Scheduler

}
