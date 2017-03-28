package mopgoo.huang.guolin.index.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import mopgoo.huang.guolin.R;
import mopgoo.huang.guolin.index.MainActivityAdapter;
import mopgoo.huang.guolin.widget.RecyclerViewDecoration;

public class MainActivity extends Activity implements MainActivityAdapter.OnItemClickListener {
    private SwipeMenuRecyclerView recyclerView;
    private MainActivityAdapter mAdapter;
    private List<String> data=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView= (SwipeMenuRecyclerView) findViewById(R.id.index);
        recyclerView.addItemDecoration(new RecyclerViewDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter=new MainActivityAdapter(MainActivity.this,R.layout.item_main,data,this);
        recyclerView.setAdapter(mAdapter);

    }

    private void initData() {
        data.add("Rotate3dActivity");
        data.add("ImageSwitchActivity");
        data.add("SlideMenuActivity");
    }

    private void startActivity(Class clazz){
        startActivity(new Intent(MainActivity.this,clazz));
    }

    @Override
    public void onclick(View view, int position) {
        startActivity(getclass(data.get(position)));
    }

    private Class getclass(String s) {
        try {
            return  Class.forName("mopgoo.huang.guolin.index.activity."+s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return MainActivity.class;
    }
}
