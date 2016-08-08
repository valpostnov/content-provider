package ru.yandex.yamblz.cp.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.yamblz.cp.Injection;
import ru.yandex.yamblz.cp.R;
import ru.yandex.yamblz.cp.dashboard.interfaces.DashboardPresenter;
import ru.yandex.yamblz.cp.dashboard.interfaces.DashboardView;

public class DashboardActivity extends AppCompatActivity implements DashboardView, ContentAdapter.OnItemClickListener
{
    private static final String TAG = "DashboardActivity";

    private DashboardPresenter presenter;
    private ContentAdapter contentAdapter;

    @BindView(R.id.rv_simple_content) RecyclerView rv;
    @BindView(R.id.ev_content) View emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = Injection.providePresenter(
                Injection.provideDataSource(),
                Injection.provideCompositeSubscription(),
                Injection.provideMapper());

        contentAdapter = new ContentAdapter(emptyView);
        contentAdapter.setOnItemClickListener(this);

        rv.setAdapter(contentAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_refresh:
                presenter.fetchData(true);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
    }

    @Override
    public void showError(String error)
    {
        Log.e(TAG, error);
    }

    @Override
    public void onItemClick(View view, int position) {}
}
