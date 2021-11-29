package hama.alsaygh.kw.delivery.view.order.orderDetails;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.delivery.adapters.pagerAdapter.OrderTrackingPagerAdapter;
import hama.alsaygh.kw.delivery.databinding.ActivityOrderDetailsBinding;
import hama.alsaygh.kw.delivery.dialog.LoginDialog;
import hama.alsaygh.kw.delivery.model.order.Order;
import hama.alsaygh.kw.delivery.utils.AppConstants;
import hama.alsaygh.kw.delivery.view.base.BaseActivity;
import hama.alsaygh.kw.delivery.view.order.OrderViewModel;

public class OrderDetailsActivity extends BaseActivity {

    ActivityOrderDetailsBinding binding;
    OrderViewModel model;
    private int order_id = -1;
    Order order;
    String status;
    Skeleton skeleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new OrderViewModel(this);
        binding.setModel(model);
        if (getIntent() != null)
            order_id = getIntent().getIntExtra(AppConstants.ORDER_ID, -1);
        status = getIntent().getStringExtra(AppConstants.ORDER_STATUS);

        binding.imgBack.setOnClickListener(view->{
            onBackPressed();
        });
        skeleton= SkeletonLayoutUtils.createSkeleton(binding.getRoot());


        model.getOrderObservable().observe(this, orderResponse -> {
            skeleton.showOriginal();
            if (orderResponse.isStatus()) {
                order = orderResponse.getData();
                OrderTrackingPagerAdapter orderTrackingPagerAdapter = new OrderTrackingPagerAdapter(OrderDetailsActivity.this,getSupportFragmentManager(), binding.tabOrderTracking.getTabCount(), order,  status);
                binding.viewPagerOrderTracking.setAdapter(orderTrackingPagerAdapter);

                binding.tabOrderTracking.setupWithViewPager(binding.viewPagerOrderTracking);
                binding.tabOrderTracking.setTabIndicatorFullWidth(false);
            } else {
                if (orderResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog loginDialog = LoginDialog.newInstance();
                    loginDialog.show(getSupportFragmentManager(), "login");
                } else
                       Snackbar.make(binding.viewPagerOrderTracking, orderResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
        skeleton.showSkeleton();
        model.getOrder(order_id,status);
    }
}