package com.hlt.hao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MainData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainRecycler);

        initData();

        RecyclerView.LayoutManager   layoutManager  = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
//        layoutManager.setAutoMeasureEnabled(true);
//        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);

//        recyclerView.setAdapter(new MainAdapter(R.layout.item_main,dataList));
        recyclerView.setAdapter(new MainAdapter2(this,dataList,recyclerView));

    }

    private void initData() {

        for (int i = 1; i <= 50; i++) {
            MainData mainData = new MainData();
            StringBuilder name = new StringBuilder();
            for (int j = 0; j <i; j++) {
                name.append("我");
            }
            mainData.name = name.toString();
            dataList.add(mainData);
        }
    }

    class MyLayoutManager extends LinearLayoutManager{

        public MyLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
            Log.e("asker","执行测量onMeasure");

            super.onMeasure(recycler,state,widthSpec,heightSpec);
        }
    }


}