package com.joshuabutton.documentscanner;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.LoaderCallbackInterface;

import timber.log.Timber;

/**
 * This class is used to display custom messaging when using OpenCV via the initAsync method.
 *
 * Created by jbttn on 5/24/16.
 */
public abstract class OpenCVCallback implements LoaderCallbackInterface {
    Activity mContext;

    public OpenCVCallback(Activity context) {
        mContext = context;
    }

    public void onManagerConnected(int status) {
        switch (status) {
            case LoaderCallbackInterface.SUCCESS: {
                break;
            }

            case LoaderCallbackInterface.MARKET_ERROR: {
                Timber.e("Package installation failed, there is no market app.");
                showMessage("Unable to open Google Market", "The document scanner depends on the OpenCV Manager application available on Google Play.  We were unable to launch the Google Play Market on your device.");
                break;
            }

            case LoaderCallbackInterface.INSTALL_CANCELED: {
                Timber.d("OpenCV library installation was canceled by the user");
                finish();
                break;
            }

            case LoaderCallbackInterface.INCOMPATIBLE_MANAGER_VERSION: {
                Timber.d("OpenCV Manager Service is incompatible with this app!");
                showMessage("OpenCV Manager Outdated", "The OpenCV Manager installed on this device is incompatible with this app.  Try to update it via Google Play.");
                break;
            }

            default: {
                Timber.e("OpenCV loading failed!");
                showMessage("OpenCV Error", "OpenCV is unable initialize!  Document scanner is unavailable.");
                break;
            }
        }
    }

    public void onPackageInstall(final int operation, final InstallCallbackInterface callback) {
        switch (operation) {
            case InstallCallbackInterface.NEW_INSTALLATION: {
                new MaterialDialog.Builder(mContext)
                        .title("OpenCV Not Found")
                        .content("The document scanner feature relies on OpenCV.  In order to continue, you must download OpenCV from Google Play Market.  Would you like to download it now?")
                        .cancelable(false)
                        .positiveText("Yes")
                        .negativeText("No")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                callback.install();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                callback.cancel();
                            }
                        })
                        .show();
                break;
            }

            case InstallCallbackInterface.INSTALLATION_PROGRESS: {
                new MaterialDialog.Builder(mContext)
                        .title("OpenCV is not ready")
                        .content("Installation is in progress. Wait or exit?")
                        .cancelable(false)
                        .positiveText("Wait")
                        .negativeText("Exit")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                callback.wait_install();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                callback.cancel();
                            }
                        })
                        .show();
                break;
            }
        }
    }

    void showMessage(String title, String content) {
        new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .cancelable(false)
                .positiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .show();
    }

    void finish() {
        mContext.finish();
    }
}