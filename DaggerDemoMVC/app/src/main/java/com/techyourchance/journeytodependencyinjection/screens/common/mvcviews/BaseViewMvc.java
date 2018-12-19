package com.techyourchance.journeytodependencyinjection.screens.common.mvcviews;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.techyourchance.journeytodependencyinjection.common.BaseObservable;

public abstract class BaseViewMvc<ListenerType> extends BaseObservable<ListenerType>
implements  ObservableViewMvc<ListenerType>{
    private View mRootView;

    @Override
    public View getRootView() {
        return mRootView;
    }

    protected void setRootView(View rootView){
        mRootView = rootView;
    }

    protected <T extends View> T findViewById(@IdRes int id){
        return (T) mRootView.findViewById(id);
    }
    protected Context getContext(){
        return getRootView().getContext();
    }
    protected String getString(@StringRes int id){
        return getContext().getString(id);
    }
    protected String getString(@StringRes int id, Object... formatArgs){
        return getContext().getString(id, formatArgs);
    }

}
