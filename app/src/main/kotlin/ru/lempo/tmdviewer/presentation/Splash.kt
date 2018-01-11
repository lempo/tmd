package ru.lempo.tmdviewer.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.lempo.tmdviewer.core.Core
import ru.lempo.tmdviewer.interactor.SplashInteractor
import javax.inject.Inject

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface SplashView : MvpView {
    fun ready()
}

@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {

    @Inject
    lateinit var splashInteractor: SplashInteractor

    init {
        Core.instance.plusSplashComponent().inject(this)
        splashInteractor.getConfiguration().subscribe({ viewState.ready() }, { viewState.ready() })
    }

    override fun onDestroy() {
        Core.instance.clearSplashComponent()
        super.onDestroy()
    }
}