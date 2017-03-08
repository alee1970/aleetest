package darkblue.com.skyline.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import darkblue.com.skyline.R;

public class HomeSkyBillActivity extends AppCompatActivity implements View.OnClickListener {


    @ViewInject(R.id.relative_home_sk_bill_money)
    private RelativeLayout rlMoney;                 //钱来钱往
    @ViewInject(R.id.relative_home_sk_bill_trade)
    private RelativeLayout rlTrade;                 //与他人交易
    @ViewInject(R.id.relative_home_sky_bill_refund)
    private RelativeLayout rlRefund;                 //授信还款
    @ViewInject(R.id.relative_home_sky_bill_withdraw)
    private RelativeLayout rlWithdraw ;                 //提现到银行卡
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sky_bill);

        x.view().inject(this);
        addListener();
    }

    private void addListener(){
        rlMoney.setOnClickListener(this);
        rlTrade.setOnClickListener(this);
        rlRefund.setOnClickListener(this);
        rlWithdraw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeSkyBillActivity.this, HomeSkyBillDetailActivity.class);
        switch (v.getId()){

            case R.id.relative_home_sk_bill_money:  //钱来钱往


                break;
            case R.id.relative_home_sk_bill_trade:   //与他人交易

                break;
            case R.id.relative_home_sky_bill_refund:    //授信还款

                break;
            case R.id.relative_home_sky_bill_withdraw:  //提现到银行卡

                break;




        }
        startActivity(intent);
    }
}
