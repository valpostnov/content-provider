package ru.yandex.yamblz.cp.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.yamblz.cp.R;

/**
 * Created by postnov on 14.04.2016.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ArtistsViewHolder>
{
    private View emptyView;
    private List<String> contents;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public ContentAdapter(View emptyView)
    {
        this.emptyView = emptyView;
    }

    @Override
    public ArtistsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
        return new ArtistsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArtistsViewHolder holder, int position)
    {
        String name = getList().get(position);
        holder.bind(name);
    }

    @Override
    public int getItemCount()
    {
        if (null == contents) return 0;
        return contents.size();
    }

    public void changeList(List<String> newList)
    {
        contents = newList;
        notifyDataSetChanged();
        emptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public List<String> getList()
    {
        return contents;
    }

    public class ArtistsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.tv_artist_name) TextView artistName;

        public ArtistsViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            int adapterPosition = getAdapterPosition();
            onItemClickListener.onItemClick(v, adapterPosition);
        }

        public void bind(String name)
        {
            artistName.setText(name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        onItemClickListener = listener;
    }
}
