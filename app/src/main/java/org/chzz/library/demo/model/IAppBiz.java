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

package org.chzz.library.demo.model;

import android.content.Context;

import java.util.List;

/**
 * Create by h4de5ing 2016/5/24 024
 */
public interface IAppBiz {
    void getData(Context context, OnAppLoadListener onAppLoadListener);

    interface OnAppLoadListener {
        void loading();

        void loadSuccess(List<AppBean> list);

        void loadFaile();
    }
}
