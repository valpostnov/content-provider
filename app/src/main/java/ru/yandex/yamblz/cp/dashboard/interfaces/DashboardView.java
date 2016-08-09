package ru.yandex.yamblz.cp.dashboard.interfaces;

import java.util.List;

/**
 * Created by platon on 05.08.2016.
 */
public interface DashboardView
{
    void showList(List<String> content);
    void showError(String error);
    void showProgressView(boolean show);
}
