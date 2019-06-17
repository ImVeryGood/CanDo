package com.baselibrary.service;


import com.android.tu.loadingdialog.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * date:2019/1/2
 */
public abstract class MObserver<T> implements Observer<T> {
    protected LoadingDialog dialog;

    public MObserver(LoadingDialog dialog) {
        this.dialog = dialog;

    }

    @Override
    public void onSubscribe(Disposable d) {
        if (dialog != null) {
            if (!dialog.isShowing()) {
                dialog.show();
            }

        }
    }

    @Override
    public void onNext(T t) {
        dismiss();
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        dismiss();
        String exception = ExceptionHelper.handleException(e);
        error(exception);
    }

    @Override
    public void onComplete() {
        dismiss();
    }

    protected abstract void success(T t);

    protected abstract void error(String error);

    protected void dismiss() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
