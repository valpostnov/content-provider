package ru.yandex.yamblz.content_provider.dashboard.interfaces;

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
