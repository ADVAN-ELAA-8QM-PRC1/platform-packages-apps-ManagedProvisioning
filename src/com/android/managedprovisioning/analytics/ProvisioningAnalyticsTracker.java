/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.managedprovisioning.analytics;

import static com.android.internal.logging.MetricsProto.MetricsEvent.PROVISIONING_DPC_INSTALLED_BY_PACKAGE;
import static com.android.internal.logging.MetricsProto.MetricsEvent.PROVISIONING_DPC_PACKAGE_NAME;

import android.content.Context;
import com.android.internal.logging.MetricsLogger;
import com.android.managedprovisioning.ProvisionLogger;

/**
 * Utility class to log metrics using TRON.
 */
public class ProvisioningAnalyticsTracker {

    public ProvisioningAnalyticsTracker() {}

    /**
     * Logs package name of the dpc.
     * @param context Context passed to MetricsLogger.
     * @param dpcPackageName Package name of the dpc.
     */
    public void logDPCPackageName(Context context, String dpcPackageName) {
        logActionWithString(context, PROVISIONING_DPC_PACKAGE_NAME, dpcPackageName);
    }

    /**
     * Logs package name of the package which installed dpc.
     * @param context Context passed to MetricsLogger.
     * @param dpcPackageName Package name of the dpc.
     */
    public void logDpcInstalledByPackage(Context context, String dpcPackageName) {
        final String dpcInstallerPackage =
                AnalyticsUtils.getInstallerPackageName(context, dpcPackageName);
        logActionWithString(context, PROVISIONING_DPC_INSTALLED_BY_PACKAGE, dpcInstallerPackage);
    }

    /**
     * Wrapper to log action with string values.
     * @param context Context passed to MetricsLogger.
     * @param category Metrics category to be logged.
     * @param value String value to be logged
     */
    private static void logActionWithString(Context context, int category, String value) {
        ProvisionLogger
                .logd("ProvisioningAnalyticsTracker, category:" + category + ", value: " + value);
        MetricsLogger.action(context, category, value);
    }

    /**
     * Wrapper to log action with integer values.
     * @param context Context passed to MetricsLogger.
     * @param category Metrics category to be logged.
     * @param value Int value to be logged.
     */
    public void logActionWithInt(Context context, int category, int value) {
        ProvisionLogger
                .logd("ProvisioningAnalyticsTracker, category:" + category + ", value: " + value);
        MetricsLogger.action(context, category, value);
    }
}