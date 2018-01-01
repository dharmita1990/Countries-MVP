package com.dharmita.funwithflagmvp.di.module;

import com.dharmita.funwithflagmvp.countrylist.CountryApi;
import com.dharmita.funwithflagmvp.countrylist.CountryListContract;
import com.dharmita.funwithflagmvp.countrylist.CountryListInteractor;
import com.dharmita.funwithflagmvp.countrylist.CountryListPresenter;
import com.dharmita.funwithflagmvp.countrylist.CountryListRequestsInterface;
import com.dharmita.funwithflagmvp.countrylist.CountryRemoteDatasource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dharmi on 21-12-2017.
 */

@Module
public class CountryListModule {

    CountryListContract.View mView;

    public CountryListModule(CountryListContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public CountryListContract.Interactor providesCountryListInteractor() {
        return new CountryListInteractor(new CountryRemoteDatasource(new CountryApi()));
    }


    @Provides
    public CountryListPresenter providesPresenter(CountryListContract.Interactor remoteDataSource) {
        return new CountryListPresenter(mView, remoteDataSource);
    }

}
