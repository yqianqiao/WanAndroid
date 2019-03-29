package youxuntianxiaapp.huimee.com.wanandroid.rx.scheduler

import io.reactivex.*
import org.reactivestreams.Publisher

abstract class BaseScheduler<T> protected constructor(private val subscribeOnScheduler: Scheduler,
                                                      private val observeOnScheduler: Scheduler) :
        ObservableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T>,
        CompletableTransformer,
        FlowableTransformer<T, T> {
    override fun apply(upstream: Maybe<T>): MaybeSource<T> =
            upstream.subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)

    override fun apply(upstream: Observable<T>): ObservableSource<T> =
            upstream.subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)

    override fun apply(upstream: Single<T>): SingleSource<T> =
            upstream.subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)

    override fun apply(upstream: Completable): CompletableSource =
            upstream.subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)

    override fun apply(upstream: Flowable<T>): Publisher<T> =
            upstream.subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)
}