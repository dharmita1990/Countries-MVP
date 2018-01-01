package com.dharmita.funwithflagmvp.countrylist;

import com.dharmita.funwithflagmvp.model.CountryDTO;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dharmi on 20-12-2017.
 */

public class CountryListPresenter {

    CountryListContract.View mView;
    CountryListContract.Interactor mInteractor;

    public CountryListPresenter(CountryListContract.View mView, CountryListContract.Interactor mInteractor) {
        this.mView = mView;
        this.mInteractor = mInteractor;
    }

    public void init() {
        mView.initAdapter();
        FetchCountryDetails();

    }

    private void FetchCountryDetails() {
        if (mView.isConnectedToInternet()) {
            mView.showProgress(true);
            mInteractor.getCountrylist().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<List<CountryDTO>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull List<CountryDTO> countryDTO) {
                    mView.showProgress(false);
                    if (countryDTO != null) {
                        mView.updateCountryList(countryDTO);
                    } else {
                        mView.showNoRecord(
                                true);
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mView.showProgress(false);
                    if (e instanceof SocketTimeoutException || e instanceof IOException)
                        mView.showNoInternet(false);
                    else {
                        if (e != null && e.getMessage() != null && !e.getMessage().equalsIgnoreCase(""))
                            mView.showErrorMessage(e.getMessage());
                        else
                            mView.showErrorMessage("Something Went Wrong");
                    }
                }

                @Override
                public void onComplete() {

                }
            });
        } else {
            noInternet();
        }
    }

    public void onRetry() {
        mView.showNoInternet(false);
        FetchCountryDetails();
    }

    private void noInternet() {
        mView.showProgress(false);
        mView.showNoInternet(true);
    }
}
