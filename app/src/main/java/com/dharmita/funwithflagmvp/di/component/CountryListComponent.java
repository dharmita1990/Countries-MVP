package com.dharmita.funwithflagmvp.di.component;

import com.dharmita.funwithflagmvp.countrylist.CountryListActivity;
import com.dharmita.funwithflagmvp.di.module.CountryListModule;
import com.dharmita.funwithflagmvp.di.scopes.PerActivity;

import dagger.Component;

/**
 * Created by Dharmi on 18/11/2017.
 */
@PerActivity
@Component(
        modules = {CountryListModule.class}
)

public interface CountryListComponent {
    void inject(CountryListActivity activity);
}
