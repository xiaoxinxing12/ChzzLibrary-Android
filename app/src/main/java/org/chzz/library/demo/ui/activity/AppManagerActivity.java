/*
 *   Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.chzz.library.demo.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.chzz.library.demo.R;
import org.chzz.library.demo.model.AppBean;
import org.chzz.library.demo.presenter.AppPresenter;
import org.chzz.library.demo.ui.adapter.AppRecyAdapter;
import org.chzz.library.demo.view.IAppView;

import java.util.List;

public class AppManagerActivity extends AppCompatActivity implements IAppView {
    private static final String TAG = "ghost";

    private AppPresenter mPresenter;
    private ProgressDialog mDialog;
    private RecyclerView mRecyapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        mRecyapp = (RecyclerView) findViewById(R.id.recy_app);
        mRecyapp.setHasFixedSize(true);
        mRecyapp.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyapp.setItemAnimator(new DefaultItemAnimator());
        initProgress();
        mPresenter = new AppPresenter(this, this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPresenter.getData();
            }
        }).start();
    }

    private void initProgress() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage(getString(R.string.loading));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.cancel();
    }

    @Override
    public void referData(List<AppBean> list) {
        for (AppBean bean : list) {
            Log.i(TAG, "app: " + bean.toString());
        }
        mRecyapp.setAdapter(new AppRecyAdapter(this, list));
    }
}
