package com.dharmita.funwithflagmvp.di.component;

import com.dharmita.funwithflagmvp.countrylist.CountryApi;
import com.dharmita.funwithflagmvp.countrylist.CountryListActivity;
import com.dharmita.funwithflagmvp.di.module.NetworkModule;
import com.dharmita.funwithflagmvp.di.scopes.PerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dharmi on 26-12-2017.
 */

@Singleton
@Component(
        modules = {NetworkModule.class}
)

public interface NetworkComponent {
    void inject(CountryApi activity);

}



