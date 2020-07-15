package com.hlt.hao;

import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * create by zhangzhuo
 * time: 2020/7/9
 * desc
 */
class MainAdapter extends BaseQuickAdapter<MainData, BaseViewHolder> {
    public MainAdapter(int layoutResId, @Nullable List<MainData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull final BaseViewHolder baseViewHolder, MainData mainData) {
        baseViewHolder.setImageResource(R.id.item_image,R.drawable.aa);

        TextView textView = baseViewHolder.getView(R.id.item_text);
        Rect bounds = new Rect();
        String text = mainData.name;
        textView.setText(text);
//        TextPaint paint;
//        paint = textView.getPaint();
//        paint.getTextBounds(text, 0, text.length(), bounds);
//        int height = bounds.height();
//        Log.e("asker","文字高度"+ baseViewHolder.itemView.getHeight());


        baseViewHolder.itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("asker","recyclerView高度变化"+baseViewHolder.itemView.getHeight());

                baseViewHolder.itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });




//        if (mainData.name.length()>15){
//            ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams)    baseViewHolder.itemView.getLayoutParams();
//            layoutParams.height  = 1400;
//            baseViewHolder.itemView.setLayoutParams(layoutParams);
//
//        }

//        baseViewHolder.setText(R.id.item_text,mainData.name);
    }


}
