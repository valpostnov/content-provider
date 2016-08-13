package ru.yandex.yamblz.cp.dashboard.interfaces;

/**
 * Created by platon on 05.08.2016.
 */
public interface DashboardPresenter
{
    void bind(DashboardView view);
    void unbind();
    void unsubscribe();
    void fetchData(boolean forceLoad);
}
