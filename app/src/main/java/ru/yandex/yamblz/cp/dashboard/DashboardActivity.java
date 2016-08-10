package ru.yandex.yamblz.cp.dashboard;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.yamblz.cp.App;
import ru.yandex.yamblz.cp.Injection;
import ru.yandex.yamblz.cp.R;
import ru.yandex.yamblz.cp.dashboard.interfaces.DashboardPresenter;
import ru.yandex.yamblz.cp.dashboard.interfaces.DashboardView;

public class DashboardActivity extends AppCompatActivity implements DashboardView, ContentAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener
{
    private DashboardPresenter presenter;
    private ContentAdapter contentAdapter;

    @BindView(R.id.rv_simple_content) RecyclerView rv;
    @BindView(R.id.ev_content) View emptyView;
    @BindView(R.id.main_toolbar) Toolbar toolbar;
    @BindView(R.id.swipe_view) SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        presenter = new DashboardPresenterImpl(App.from(this).getRepository(), Injection.provideMapper());

        contentAdapter = new ContentAdapter();
        contentAdapter.setOnItemClickListener(this);
        refreshLayout.setOnRefreshListener(this);

        rv.setAdapter(contentAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        presenter.bind(this);
        presenter.fetchData(false);
    }

    @Override
    protected void onPause()
    {
        presenter.unsubscribe();
        presenter.unbind();
        super.onPause();
    }

    @Override
    public void showList(List<String> content)
    {
        contentAdapter.changeList(content);
        emptyView.setVisibility(contentAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressView(boolean show)
    {
        refreshLayout.setRefreshing(show);
    }

    @Override
    public void onItemClick(View view, int position) {}

    @Override
    public void onRefresh()
    {
        presenter.fetchData(true);
    }
}
