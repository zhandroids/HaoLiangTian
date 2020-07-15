package com.hlt.hao;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create by zhangzhuo
 * time: 2020/7/11
 * desc
 */
public class MainAdapter2 extends RecyclerView.Adapter<MainAdapter2.Viewh> {

    private Context context;
    private List<MainData> dataList;
    private RecyclerView recyclerView;
    private int preHeight = 0;



    public MainAdapter2(Context context, List<MainData> dataList, RecyclerView recyclerView){
        this.context  =context;
        this.dataList = dataList;
        this.recyclerView = recyclerView;
    }


    @NonNull
    @Override
    public Viewh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewh(LayoutInflater.from(context).inflate(R.layout.item_main,parent,false));
    }

    private Handler handler = new Handler();

    private class MyRunable implements Runnable {
        @Override
        public void run() {
            ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
            layoutParams.height = preHeight;
            recyclerView.setLayoutParams(layoutParams);
        }
    }

    private MyRunable runable = new MyRunable();

    boolean isDraw = false;
    @Override
    public void onBindViewHolder(@NonNull final Viewh holder, int position) {
        holder.imageView.setImageResource(R.drawable.aa);
        holder.textView.setText(dataList.get(position).name);

        final ViewTreeObserver.OnDrawListener onDrawListener = new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                int with =View.MeasureSpec.makeMeasureSpec(  holder.itemView.getMeasuredWidth(),View.MeasureSpec.EXACTLY);
                int heightMeasureSpec =View.MeasureSpec.makeMeasureSpec(  100000,View.MeasureSpec.UNSPECIFIED);
                holder.itemView.measure(with,heightMeasureSpec);
                int height =   holder.itemView.getMeasuredHeight();
                if (height > preHeight){
                    preHeight = height;
                    handler.removeCallbacks(runable);
                    handler.postDelayed(runable,1000);
                }
                isDraw = true;
            }
        };
        holder.itemView.getViewTreeObserver().addOnDrawListener(onDrawListener);
        holder.itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isDraw){
                    holder.itemView.getViewTreeObserver().removeOnDrawListener(onDrawListener);
                }
                holder.itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class Viewh extends RecyclerView.ViewHolder {
        View itemView;
         TextView textView;
        ImageView imageView;

        public Viewh(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            textView = itemView.findViewById(R.id.item_text);
            imageView = itemView.findViewById(R.id.item_image);
        }

    }
}


