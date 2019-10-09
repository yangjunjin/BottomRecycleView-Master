package com.ahao.myapplication.banner;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ahao.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设计
 */
public class BannerRecyclerActivity extends Activity {
    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;

    public BannerAdapter adapter2;
    private List<Entity> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_recycler_view);
        ButterKnife.bind(this);
        initData();
        initDataSource();
    }

    private void initData() {
        adapter2 = new BannerAdapter(data, BannerRecyclerActivity.this);
        BannerLayoutManager layoutManager2 = new BannerLayoutManager();
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(layoutManager2);

        LinearSnapHelper bannerPageSnapHelper2 = new LinearSnapHelper();
        bannerPageSnapHelper2.attachToRecyclerView(recyclerView2);

        adapter2.setListener(new BannerAdapter.onClickListener() {
            @Override
            public void onClick() {
                changeHeight();
            }
        });
    }

    boolean isOpen = false;//是否打开了

    private void changeHeight() {
        final ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) recyclerView2.getLayoutParams();
        params.width = params.width;
        params.height = params.height;
        float start = params.height, end = 0f;
        end = isOpen ? dip2px(263) : dip2px(495);

        //执行动画
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                params.height = (int) currentValue;
                recyclerView2.setLayoutParams(params);

                data.get(0).setOpen(isOpen);
                adapter2.notifyDataSetChanged();
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
        data.clear();
        for (int i = 0; i < 10; i++) {
            Entity entity = new Entity("我的==" + i);
            data.add(entity);
        }
        adapter2.notifyDataSetChanged();
    }

//    public void onClick(View view){
//        switch (view.getId()) {
//            case R.id.btn_dialog:
//                BottomDialog dialog = new BottomDialog(BannerRecyclerActivity.this, data);
//                dialog.showDialog();
//                break;
//        }
//    }
}
