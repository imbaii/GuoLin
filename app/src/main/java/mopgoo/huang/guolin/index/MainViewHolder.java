package mopgoo.huang.guolin.index;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mopgoo.huang.guolin.R;

/**
 * GuoLin[V 1.0.0]
 * classes :mopgoo.huang.guolin.index.MainViewHolder
 * 黄威 Create at 2017/3/27 10:33
 */

public class MainViewHolder extends RecyclerView.ViewHolder{
    public TextView textView;
    public View view;
    public MainViewHolder(View itemView) {
        super(itemView);
        view=itemView;
        textView= (TextView) itemView.findViewById(R.id.item_name);
    }
}
