package com.dharmita.funwithflagmvp.countrylist;

import com.dharmita.funwithflagmvp.model.CountryDTO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dharmi on 20-12-2017.
 */

public interface CountryListContract {

    interface View {
        void initAdapter();

        boolean isConnectedToInternet();

        void showProgress(boolean isShow);

        void showNoInternet(boolean isShow);

        void showErrorMessage(String errMsg);

        void showNoRecord(boolean isShow);

        void updateCountryList(List<CountryDTO> lstCountry);

    }

    interface Interactor {

        Observable<List<CountryDTO>> getCountrylist();
    }


    interface RemoteDataSource {
        Observable<List<CountryDTO>> getCountrylist();
    }
}
