package com.dharmita.funwithflagmvp.countrylist;

import com.dharmita.funwithflagmvp.model.CountryDTO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dharmi on 26-12-2017.
 */

public class CountryRemoteDatasource implements CountryListContract.RemoteDataSource {


    public CountryApi mCountryApi;

    public CountryRemoteDatasource(CountryApi countryApi) {
        mCountryApi = countryApi;
    }

    @Override
    public Observable<List<CountryDTO>> getCountrylist() {
        return mCountryApi.getCountrylist();
    }

}
