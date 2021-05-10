package com.omaks.sample.kotlin150

import arrow.core.Option
import arrow.core.toOption
import arrow.optics.Optional
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Store<StateType : State> {
    var state: StateType? = null
        private set

    private val subject: BehaviorSubject<StateType> by lazy {
        BehaviorSubject.createDefault(state)
    }

    fun observe(): Observable<StateType> {
        val observable = if (state == null) {
            Observable.create<Unit> {
                CoroutineScope(Dispatchers.IO).launch {
                    // some non-relevant code
                    it.onNext(Unit)
                    it.onComplete()
                }
            }.concatMap { subject }
        } else {
            subject
        }
        return observable
            .distinctUntilChanged()
    }

    fun <A> observe(optional: Optional<StateType, A>): Observable<Option<A>> {
        return observe()
            .map { optional.getOrNull(it).toOption() }
                as Observable<Option<A>>
    }
}

