package ru.yandex.yamblz.content_provider.dashboard;

import java.util.List;

import ru.yandex.yamblz.content_provider.dashboard.interfaces.DashboardPresenter;
import ru.yandex.yamblz.content_provider.dashboard.interfaces.DashboardView;
import ru.yandex.yamblz.content_provider.data.entity.Artist;
import ru.yandex.yamblz.content_provider.data.entity.mapper.TypeMapper;
import ru.yandex.yamblz.content_provider.data.exception.DefaultErrorBundle;
import ru.yandex.yamblz.content_provider.data.repository.DataSource;
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

    public DashboardPresenterImpl(DataSource ds, CompositeSubscription subs, TypeMapper<List<Artist>, List<String>> m)
    {
        dataSource = ds;
        subscriptions = subs;
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
        if (forceLoad) dataSource.delete();

        subscriptions.add(dataSource.artists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(mapper::map)
                .subscribe(onNext, onError));
    }

    private Action1<List<String>> onNext = content -> dashboardView.showList(content);
    private Action1<Throwable> onError = e -> dashboardView.showError(new DefaultErrorBundle(e).getMessage());
}
