package com.dharmita.funwithflagmvp.countrylist;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.dharmita.funwithflagmvp.Common.Connectivity;
import com.dharmita.funwithflagmvp.R;
import com.dharmita.funwithflagmvp.di.component.DaggerCountryListComponent;
import com.dharmita.funwithflagmvp.di.module.CountryListModule;
import com.dharmita.funwithflagmvp.model.CountryDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountryListActivity extends AppCompatActivity implements CountryListContract.View, CountryListAdapter.CountryInteraction {

    @BindView(R.id.list_songs)
    public RecyclerView mRecyclerView;

    @BindView(R.id.layout_no_internet)
    public LinearLayout mNoInternet;

    @BindView(R.id.layout_loading)
    public LinearLayout mLoading;
    @BindView(R.id.coordinator)
    public CoordinatorLayout coordinator;

    @Inject
    public CountryListPresenter mPresenter;

    private CountryListAdapter mCountryadapter;
    private List<CountryDTO> mCountryDTOs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        ButterKnife.bind(this);
        initToolBar();
        injectDependencies();
    }

    private void injectDependencies() {
        DaggerCountryListComponent.builder()
                .countryListModule(new CountryListModule(this))
                .build()
                .inject(this);

        mPresenter.init();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    @Override
    public void initAdapter() {
        mCountryDTOs = new ArrayList<>();
        mCountryadapter = new CountryListAdapter(mCountryDTOs, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mCountryadapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public boolean isConnectedToInternet() {
        return Connectivity.getInstance().isConnected();
    }

    @Override
    public void showProgress(boolean isShow) {
        mLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNoInternet(boolean isShow) {
        mNoInternet.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Snackbar.make(coordinator, errMsg, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showNoRecord(boolean isShow) {

    }

    @Override
    public void updateCountryList(List<CountryDTO> lstCountry) {
        int oldSize = mCountryDTOs.size();
        mCountryDTOs.addAll(lstCountry);
        mCountryadapter.notifyItemRangeInserted(oldSize, mCountryDTOs.size());
    }
    @OnClick(R.id.btn_retry)
    public void buttonRetry() {
        mPresenter.onRetry();
    }
    @Override
    public void OnListInteractionListener(View v, CountryDTO object, int pos) {

    }
}
