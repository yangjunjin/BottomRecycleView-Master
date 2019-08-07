// Generated code from Butter Knife. Do not modify!
package com.ahao.myapplication.banner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ahao.myapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BannerRecyclerActivity_ViewBinding implements Unbinder {
  private BannerRecyclerActivity target;

  @UiThread
  public BannerRecyclerActivity_ViewBinding(BannerRecyclerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BannerRecyclerActivity_ViewBinding(BannerRecyclerActivity target, View source) {
    this.target = target;

    target.recyclerView2 = Utils.findRequiredViewAsType(source, R.id.recycler_view2, "field 'recyclerView2'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BannerRecyclerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView2 = null;
  }
}
