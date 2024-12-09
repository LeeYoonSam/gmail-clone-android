package com.ys.log

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class AbsLogService<T: BaseEvent>  {

    fun setObserveEvents(
        events: SharedFlow<T>,
        coroutineScope: CoroutineScope
    ) {
        events.onEach { event ->
            logClick(event)
        }.launchIn(coroutineScope)
    }

    abstract fun logClick(event: T)
}