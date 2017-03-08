package darkblue.com.skyline.UI.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import darkblue.com.skyline.R;

public class HomePaymentOrderActivity extends AppCompatActivity implements View.OnClickListener {


    @ViewInject(R.id.tv_home_payment_order_cart)
    private TextView tvCart;


//    private BadgeView badgeViewDaiFuKuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_payment_order);
        x.view().inject(this);
        addListener();
    }


    private void addListener(){
        tvCart.setOnClickListener(this);
    }

    private void initView(){

//        tvCart.
//                badgeViewDaiFuKuan = new BadgeView(getActivity(), mDaifukuan);  //实例化BadgeView
//        badgeViewDaiFuKuan.setTextSize(12f);  //设置文字的大小
//        badgeViewDaiFuKuan.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);//设置在右上角
//        badgeViewDaiFuKuan.setTextColor(Color.WHITE);  //字体的设置颜色
//        badgeViewDaiFuKuan.setBadgeBackgroundColor(getResources().getColor());
//        badgeViewDaiFuKuan.show(); //显示





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_payment_order_cart:


                break;
        }
    }
}
