/*
 * Copyright (C) 2022 Project Kaleidoscope
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.settings.fuelgauge.batterysaver;

import android.app.ActivityManager;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.android.settings.core.BasePreferenceController;
import com.android.settings.R;

public class ForceBackgroundFreezePreferenceController extends BasePreferenceController {
    private static final String TAG = "ForceBackgroundFreezePreferenceController";

    public ForceBackgroundFreezePreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    @AvailabilityStatus
    public int getAvailabilityStatus() {
        int ret = UNSUPPORTED_ON_DEVICE;

        try {
            if (ActivityManager.getService().isAppFreezerEnabled())
                ret = AVAILABLE;
        } catch (RemoteException e) {
            Log.w(TAG, "Unable to obtain freezer enabling status from ActivityManager");
        }

        return ret;
    }
}
