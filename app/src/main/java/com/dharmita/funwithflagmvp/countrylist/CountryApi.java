package com.dharmita.funwithflagmvp.countrylist;

import com.dharmita.funwithflagmvp.di.component.DaggerCountryListComponent;
import com.dharmita.funwithflagmvp.di.component.DaggerNetworkComponent;
import com.dharmita.funwithflagmvp.di.module.CountryListModule;
import com.dharmita.funwithflagmvp.di.module.NetworkModule;
import com.dharmita.funwithflagmvp.model.CountryDTO;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Dharmi on 26-12-2017.
 */

public class CountryApi {
    @Inject
    CountryListRequestsInterface mRequestsService;

    public CountryApi() {
        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build()
                .inject(this);
    }

    public Observable<List<CountryDTO>> getCountrylist() {

        return mRequestsService.downloadCountries();
    }

}
