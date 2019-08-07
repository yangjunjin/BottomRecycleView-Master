package com.ahao.myapplication.banner;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.ahao.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerRecyclerActivity extends Activity {////
    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;
    BannerAdapter1 adapter2;

    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_recycler_view);
        ButterKnife.bind(this);
        initData();
        initDataSource();
    }

    private void initData() {
        adapter2 = new BannerAdapter1(data, this);
        BannerLayoutManager layoutManager2 = new BannerLayoutManager();
        adapter2.setItemWidth(0.8f);//item的宽
        adapter2.setRatio(0.6f);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(layoutManager2);

        BannerPageSnapHelper bannerPageSnapHelper2 = new BannerPageSnapHelper();
        bannerPageSnapHelper2.setInfinite(true);
        bannerPageSnapHelper2.attachToRecyclerView(recyclerView2);

        adapter2.setListener(new BannerAdapter1.onClickListener() {
            @Override
            public void onClick() {
                changeHeight();
            }
        });
    }

    boolean isOpen = false;//是否打开了

    private void changeHeight() {
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) recyclerView2.getLayoutParams();
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
                recyclerView2.setLayoutParams(params);
            }
        });
        anim.start();
        isOpen = !isOpen;
    }

    public int dip2px(int dip) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    private void initDataSource() {
        for (int i = 0; i < 10; i++) {
            data.add(i + "");
        }
        adapter2.notifyDataSetChanged();
    }
}
