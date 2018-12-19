package com.techyourchance.journeytodependencyinjection.screens.common.mvcviews;

public interface ObservableViewMvc<ListenerType> extends ViewMvc{

    void registerListener(ListenerType listener);
    void unregisterListener(ListenerType listener);
}
