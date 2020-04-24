//package com.llsparrow.core_util.concurrency
//
//import android.widget.SearchView
//import com.llsparrow.core_util.concurrency.RxSearchObservable.fromView
//import io.reactivex.Observable
//import io.reactivex.ObservableSource
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.functions.Consumer
//import io.reactivex.functions.Predicate
//import io.reactivex.schedulers.Schedulers
//import io.reactivex.subjects.PublishSubject
//import java.util.concurrent.TimeUnit
//
//
//object RxSearchObservable {
//    fun fromView(searchView: SearchView): Observable<String> {
//        val subject: PublishSubject<String> = PublishSubject.create()
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(s: String): Boolean {
//                subject.onComplete()
//                return true
//            }
//
//            override fun onQueryTextChange(text: String): Boolean {
//                subject.onNext(text)
//                return true
//            }
//        })
//        return subject
//    }
//}
//
//private fun test() {
//    fromView(searchView)
//        .debounce(300, TimeUnit.MILLISECONDS)
//        .filter { text ->
//            if (text.isEmpty()) {
//                textViewResult.setText("")
//                false
//            } else {
//                true
//            }
//        }
//        .distinctUntilChanged()
//        .switchMap{ query ->
//                return dataFromNetwork(query)
//                    .doOnError({ throwable -> }) // continue emission in case of error also
//                    .onErrorReturn({ throwable -> "" })
//            }
//
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(obxject : Consumer<String> {
//            override fun accept(result: String) {
//                textViewResult.setText(result)
//            }
//        })
//}