package ru.yandex.yamblz.cp.dashboard;

import java.util.List;

import ru.yandex.yamblz.cp.dashboard.interfaces.DashboardPresenter;
import ru.yandex.yamblz.cp.dashboard.interfaces.DashboardView;
import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.entity.mapper.TypeMapper;
import ru.yandex.yamblz.cp.data.exception.DefaultErrorBundle;
import ru.yandex.yamblz.cp.data.exception.NetworkConnectionException;
import ru.yandex.yamblz.cp.data.source.DataSource;
import ru.yandex.yamblz.cp.util.INetworkManager;
import ru.yandex.yamblz.cp.util.NetworkManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by platon on 05.08.2016.
 */
public class DashboardPresenterImpl implements DashboardPresenter
{
    private DashboardView dashboardView;
    private DataSource dataSource;
    private CompositeSubscription subscriptions;
    private TypeMapper<List<Artist>, List<String>> mapper;

    public DashboardPresenterImpl(DataSource ds, TypeMapper<List<Artist>, List<String>> m)
    {
        subscriptions = new CompositeSubscription();
        dataSource = ds;
        mapper = m;
    }

    @Override
    public void bind(DashboardView view)
    {
        dashboardView = view;
    }

    @Override
    public void unbind()
    {
        dashboardView = null;
    }

    @Override
    public void unsubscribe()
    {
        subscriptions.clear();
    }

    @Override
    public void fetchData(boolean forceLoad)
    {
        INetworkManager networkManager = NetworkManager.getManager();
        if (networkManager != null && networkManager.networkIsAvailable())
        {
            dashboardView.showProgressView(true);
            if (forceLoad) dataSource.delete();

            subscriptions.add(dataSource.artists()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(mapper::map)
                    .subscribe(onNext, onError));
        }
        else
        {
            dashboardView.showProgressView(false);
            dashboardView.showError(new NetworkConnectionException().getMessage());
        }
    }

    private Action1<List<String>> onNext = content ->
    {
        dashboardView.showList(content);
        dashboardView.showProgressView(false);
    };

    private Action1<Throwable> onError = e ->
    {
        dashboardView.showError(new DefaultErrorBundle(e).getMessage());
        dashboardView.showProgressView(false);
    };
}
