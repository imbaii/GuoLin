package mopgoo.huang.guolin.index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * GuoLin[V 1.0.0]
 * classes :mopgoo.huang.guolin.index.MainActivityAdapter
 * 黄威 Create at 2017/3/27 10:30
 */

public class MainActivityAdapter extends SwipeMenuAdapter<MainViewHolder>{
    private Context mcontext;
    private int layoutId;
    private List<String> data;
    private OnItemClickListener onItemClickListener;

    public MainActivityAdapter(Context mcontext, int layoutId, List<String> data, MainActivityAdapter.OnItemClickListener onItemClickListener) {
        this.mcontext = mcontext;
        this.layoutId = layoutId;
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public View onCreateContentView(ViewGroup viewGroup, final int i) {
        View view=LayoutInflater.from(mcontext).inflate(layoutId,viewGroup,false);
        return view;
    }

    @Override
    public MainViewHolder onCompatCreateViewHolder(View view, final int i) {
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onclick(v,position);
            }
        });
        holder.textView.setText((position+1)+". "+data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface  OnItemClickListener{
        public void onclick(View view,int position);
    }
}
