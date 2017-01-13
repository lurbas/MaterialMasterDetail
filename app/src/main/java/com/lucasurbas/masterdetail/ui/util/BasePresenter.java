package com.lucasurbas.masterdetail.ui.util;

/**
 * Created by Lucas on 12/06/16.
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
