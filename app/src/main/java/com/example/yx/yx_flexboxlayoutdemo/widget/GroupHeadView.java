package com.example.yx.yx_flexboxlayoutdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yx.yx_flexboxlayoutdemo.R;
import com.example.yx.yx_flexboxlayoutdemo.utils.ScreenUtil;
import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;

/**
 * Created by pc on 2018/10/14.
 */

public class GroupHeadView extends FlexboxLayout {
    /**
     * 子view的个数
     */
    private int itemCount =5;
    /**
     * flexbox 布局的宽
     */
    private int layoutWidth ;
    /**
     * flexbox 布局的高
     */
    private int layoutHeight ;
    private Context mContext;

    public GroupHeadView(Context context) {
        this(context,null);
    }

    public GroupHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }


    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void initView(){

        layoutWidth = layoutHeight = ScreenUtil.dp2px(mContext,100);
        int itemWidth = layoutWidth;
        switch (itemCount){
            case 1:
                itemWidth = layoutWidth;
                break;
            case 2:
            case 3:
            case 4:
                itemWidth = layoutWidth/2;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                itemWidth =layoutWidth/3;
                break;
            default:
                break;
        }

        this.setFlexDirection(FlexDirection.ROW);
        this.setJustifyContent(JustifyContent.CENTER);
        this.setAlignItems(AlignItems.CENTER);
        this.setFlexWrap(FlexWrap.WRAP);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(layoutWidth,layoutHeight);
        this.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        this.setLayoutParams(params);


//        for (int i = 0; i <itemCount; i++) {
//            TextView textView = new TextView(mContext);
//            textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//            textView.setText("1");
//            textView.setGravity(Gravity.CENTER);
//            LayoutParams layoutParams = new LayoutParams(itemWidth,itemWidth);
//            textView.setLayoutParams(layoutParams);
//            this.addView(textView);
//        }



    }
}
