package com.dharmita.funwithflagmvp.di.module;

import com.dharmita.funwithflagmvp.Common.Constants;
import com.dharmita.funwithflagmvp.countrylist.CountryListRequestsInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static final String NAME_BASE_URL = "NAME_BASE_URL";


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
    }

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    CountryListRequestsInterface provideUsdaApi(Retrofit retrofit) {
        return retrofit.create(CountryListRequestsInterface.class);
    }
}
