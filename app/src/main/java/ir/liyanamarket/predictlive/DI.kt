package ir.liyanamarket.predictlive


import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.adapter.*
import ir.liyanamarket.predictlive.adapter.topscore.ShowLeagueAdapter
import ir.liyanamarket.predictlive.adapter.topscore.TopScoreAdapter
import ir.liyanamarket.predictlive.db.CreateDataBase
import ir.liyanamarket.predictlive.db.FunctionDataBase
import ir.liyanamarket.predictlive.fragment.*
import ir.liyanamarket.predictlive.model.award.ApiconnectAwardservices
import ir.liyanamarket.predictlive.model.changeprofile.ApiConnectToChangeProfile
import ir.liyanamarket.predictlive.model.footballapi.league.ApiConnectSelectLeague
import ir.liyanamarket.predictlive.model.footballapi.topscores.ApiConnectSelectTopScores
import ir.liyanamarket.predictlive.model.item.ApiConnectItem
import ir.liyanamarket.predictlive.model.match.ApiConnectToSelectMatch
import ir.liyanamarket.predictlive.model.news.ApiConnectSelectNews
import ir.liyanamarket.predictlive.model.predict.ApiConnectSelectPredict
import ir.liyanamarket.predictlive.model.predict.ApiConnectinsertPredict
import ir.liyanamarket.predictlive.model.register.ApiConnectRegister
import ir.liyanamarket.predictlive.model.sendcode.ApiConnectSendCode
import ir.liyanamarket.predictlive.model.shop.basket.ApiConnectDeleteBasket
import ir.liyanamarket.predictlive.model.shop.basket.ApiConnectEditTedadKala
import ir.liyanamarket.predictlive.model.shop.basket.ApiConnectInsertBasket
import ir.liyanamarket.predictlive.model.shop.basket.ApiConnectShowBasket
import ir.liyanamarket.predictlive.model.shop.buy.ApiConnectInsertBuy
import ir.liyanamarket.predictlive.model.shop.buy.ApiConnectLastCodesefaresh
import ir.liyanamarket.predictlive.model.shop.buy.ApiConnectSelectBuy
import ir.liyanamarket.predictlive.model.shop.buy.ApiConnectSelectsuccessBuy
import ir.liyanamarket.predictlive.model.shop.group.ApiConnectGroup
import ir.liyanamarket.predictlive.model.shop.kala.ApiConnectToSelectKala
import ir.liyanamarket.predictlive.model.user.ApiConnectChangeprofileDetails
import ir.liyanamarket.predictlive.model.user.ApiConnectRankingUser
import ir.liyanamarket.predictlive.model.user.ApiConnectUsers
import ir.liyanamarket.predictlive.model.validatephonenumber.ApiConnectValidatePhoneNumber
import ir.liyanamarket.predictlive.presenter.award.PresenterApiConnectAward
import ir.liyanamarket.predictlive.presenter.changeprofile.PresenterApiconnectChangeProfile
import ir.liyanamarket.predictlive.presenter.footballapi.league.PresenterApiConnectSelectLeague
import ir.liyanamarket.predictlive.presenter.footballapi.topscores.PresenterApiConnectSelectTopScores
import ir.liyanamarket.predictlive.presenter.item.PresenterApiConnectSelectItem
import ir.liyanamarket.predictlive.presenter.match.PresenterApiConnectSelectMatch
import ir.liyanamarket.predictlive.presenter.news.PresenterApiConnectSelectNews
import ir.liyanamarket.predictlive.presenter.predict.PresenterApiConnectSelectPredict
import ir.liyanamarket.predictlive.presenter.predict.PresenterApiConnectinsertPredict
import ir.liyanamarket.predictlive.presenter.register.PresenterApiConnectRegister
import ir.liyanamarket.predictlive.presenter.sendcode.PresenterApiConnectSendCode
import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectDeleteBasket
import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectEDitTedadKala
import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectInsertBasket
import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectShowBasket
import ir.liyanamarket.predictlive.presenter.shop.buy.PresenterApiConnectLastCodeSefaresh
import ir.liyanamarket.predictlive.presenter.shop.buy.PresenterApiConnectSelectBuy
import ir.liyanamarket.predictlive.presenter.shop.buy.PresenterApiConnectinsertBuy
import ir.liyanamarket.predictlive.presenter.shop.buy.PresenterSelectSuccessBuy
import ir.liyanamarket.predictlive.presenter.shop.group.PresenterApiConnectGroup
import ir.liyanamarket.predictlive.presenter.shop.kala.PresenterApiConnectSelectKala
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectChangeProfileDetails
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectRankingUser
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectUser
import ir.liyanamarket.predictlive.presenter.validatephonenumber.PresenterApiConnectValidatePhoneNumber
import ir.liyanamarket.predictlive.utils.*
import org.koin.dsl.module

var appmadules = module {
    single {
        ApiConnectUsers()
    }
    single { PresenterApiConnectUser() }
    single { FragmentProgressBar() }
    single { FragmentHome() }
    //  single { Picasso.with(get()) }
    single { Picasso.get() }
    single { FragmentRanking(get()) }
    single { RankingAdapter(get()) }
    single { ApiConnectSelectPredict() }
    single { PresenterApiConnectSelectPredict() }
    single { ApiConnectToSelectMatch() }
    single { PresenterApiConnectSelectMatch() }
    single { PredictAdapter(get()) }
    single { ApiConnectinsertPredict() }
    single { PresenterApiConnectinsertPredict() }
    single { ApiConnectToChangeProfile() }
    single { PresenterApiconnectChangeProfile() }
    single { EncodeAndDecodeImage() }
    single { FragmentSetting() }
    single { SaveLoginInfo(get()) }
    single { CheckValidateInputPhoneNumber() }
    single { ApiConnectValidatePhoneNumber() }
    single { PresenterApiConnectValidatePhoneNumber() }
    single { ApiConnectSendCode() }
    single { PresenterApiConnectSendCode() }
    single { ApiConnectRegister() }
    single { PresenterApiConnectRegister() }
    single { ApiConnectToSelectKala() }
    single { PresenterApiConnectSelectKala(get()) }
    single { GroupKalaAdapter() }
    single { KalaAdapter(get()) }
    single { ApiConnectGroup() }
    single { PresenterApiConnectGroup() }
    single { SpinerAdapter() }
    single { FilterRecyclerViewKala() }
    single { SearchkalaAdapter(get()) }
    single { FragmentMessage() }
    single { MyMessage() }
    single { ApiConnectRankingUser() }
    single { PresenterApiConnectRankingUser() }
    single { SpinerSizeAdapter() }
    single { ApiConnectInsertBasket() }
    single { PresenterApiConnectInsertBasket() }
    single { ApiConnectShowBasket() }
    single { PresenterApiConnectShowBasket() }
    single { ShowBasketAdapter() }
    single { ApiConnectEditTedadKala() }
    single { PresenterApiConnectEDitTedadKala() }
    single { ApiConnectDeleteBasket() }
    single { PresenterApiConnectDeleteBasket() }
    single { ApiConnectLastCodesefaresh() }
    single { PresenterApiConnectLastCodeSefaresh() }
    single { ApiConnectInsertBuy() }
    single { PresenterApiConnectinsertBuy() }
    single { CheckNetworkConnection(get()) }
    single { ApiConnectSelectBuy() }
    single { PresenterApiConnectSelectBuy() }
    single { ApiConnectSelectNews() }
    single { PresenterApiConnectSelectNews() }
    single { ApiConnectSelectTopScores() }
    single { PresenterApiConnectSelectTopScores() }
    single { TopScoreAdapter() }
    single { ApiConnectSelectLeague() }
    single { PresenterApiConnectSelectLeague() }
    single { ShowLeagueAdapter(get()) }
    single { ApiConnectItem() }
    single { PresenterApiConnectSelectItem() }
    single { ItemAdapter() }
    single { ApiConnectChangeprofileDetails() }
    single { PresenterApiConnectChangeProfileDetails() }
    single { ApiConnectSelectsuccessBuy() }
    single { PresenterSelectSuccessBuy() }
    single { SuccessBuyActivityAdapter() }
    single { CreateDataBase(get()) }
    single { FunctionDataBase() }
    single { ApiconnectAwardservices() }
    single { PresenterApiConnectAward() }

}