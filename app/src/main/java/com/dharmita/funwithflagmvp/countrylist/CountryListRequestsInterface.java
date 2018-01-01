package com.dharmita.funwithflagmvp.countrylist;

import com.dharmita.funwithflagmvp.model.CountryDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Dharmi on 26-12-2017.
 */

public interface CountryListRequestsInterface {
    String URL_COUNTRIES = "all";

    @GET(URL_COUNTRIES)
    Observable<List<CountryDTO>> downloadCountries();
}
