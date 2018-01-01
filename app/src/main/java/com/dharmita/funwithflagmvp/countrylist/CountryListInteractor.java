package com.dharmita.funwithflagmvp.countrylist;

import com.dharmita.funwithflagmvp.model.CountryDTO;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Dharmi on 18/11/2017.
 */

public class CountryListInteractor implements CountryListContract.Interactor {

    private CountryListContract.RemoteDataSource mRemoteDataSource;

    public CountryListInteractor(CountryListContract.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }


    @Override
    public Observable<List<CountryDTO>> getCountrylist() {
        return mRemoteDataSource.getCountrylist();
    }
}
