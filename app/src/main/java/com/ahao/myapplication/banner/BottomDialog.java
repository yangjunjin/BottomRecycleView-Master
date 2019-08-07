package com.ahao.myapplication.banner;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ahao.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家状态提示框
 * Created by yjj on 2019/4/9.
 */

public class BottomDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private RecyclerView recyclerView;
    private LinearLayout root;

    private List<Entity> mlist = new ArrayList<>();

    public BottomDialog(Context context, List<Entity> list) {
        super(context, R.style.bottom_dialog);
        init(context);
        mlist.addAll(list);
    }

    private void init(Context context) {
        mContext = context;
        //获取当前Activity所在的窗体
        Window dialogWindow = this.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0); //消除边距
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        //设置动画
        dialogWindow.setWindowAnimations(R.style.bottom_dialog);

        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_bottom, null);
        initUI(inflate);

        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
        this.setContentView(inflate);
    }

    /**
     * 初始化ui
     */
    private void initUI(View inflate) {
        recyclerView = inflate.findViewById(R.id.recyclerView);
        root = inflate.findViewById(R.id.root);

        BannerAdapter1 adapter = new BannerAdapter1(mlist,mContext);
        BannerLayoutManager layoutManager2 = new BannerLayoutManager();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager2);

        BannerPageSnapHelper bannerPageSnapHelper2 = new BannerPageSnapHelper();
        bannerPageSnapHelper2.setInfinite(true);
        bannerPageSnapHelper2.attachToRecyclerView(recyclerView);

        adapter.setListener(new BannerAdapter1.onClickListener() {
            @Override
            public void onClick() {
                changeHeight();
            }
        });
    }
    boolean isOpen = false;//是否打开了

    private void changeHeight() {
        final ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) root.getLayoutParams();
        params.width = params.width;
        params.height = params.height;
        float start = params.height, end = 0f;
        end = isOpen ? dip2px(250) : dip2px(440);

        //执行动画
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                params.height = (int) currentValue;
                root.setLayoutParams(params);
            }
        });
        anim.start();
        isOpen = !isOpen;
    }
    public int dip2px(int dip) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_ok:
//                this.dismiss();
//                break;
        }
    }

    /**
     * 显示弹框
     */
    public void showDialog() {
        if (!this.isShowing()) {
            this.show();
        }
    }
}