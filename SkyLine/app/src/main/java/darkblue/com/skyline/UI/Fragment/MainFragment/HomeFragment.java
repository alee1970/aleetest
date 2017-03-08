package darkblue.com.skyline.UI.Fragment.MainFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

import org.xutils.x;

import darkblue.com.skyline.R;
import darkblue.com.skyline.UI.Activity.HomeMyFundsActivity;
import darkblue.com.skyline.UI.Activity.HomeSearchActivity;
import darkblue.com.skyline.UI.Activity.HomeSkyBillActivity;
import darkblue.com.skyline.UI.Activity.HomeSupplyActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{




    private PopupWindow mPopWindow;//更多的弹框
    private View view;//布局的View
    private TextView tvMore;//更多
    private TextView tvSearch;
    private RelativeLayout relativeLayoutMyFunds;
    private TextView tvSupply;//我的供货

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        x.view().inject(getActivity());
        initView();
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimaryDark), false); //设置状态栏的颜色

        return view;
    }




    private void initView(){
        tvSearch = (TextView) view.findViewById(R.id.tv_home_fragment_search);
        tvMore = (TextView) view.findViewById(R.id.tv_home_more);
        tvSupply = (TextView) view.findViewById(R.id.tv_home_supply);
        relativeLayoutMyFunds = (RelativeLayout) view.findViewById(R.id.relative_home_my_funds);
        relativeLayoutMyFunds.setOnClickListener(this);
        tvSupply.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
    }


    /**
     * 右上角更多的弹框
     */
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popwindow_home_sky_bill_service, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = (TextView)contentView.findViewById(R.id.tv_home_more_bill);
        TextView tv2 = (TextView)contentView.findViewById(R.id.tv_home_more_service);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);

        mPopWindow.showAsDropDown(tvMore,-10,0);

    }




    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.tv_home_more:   //右上角更多
                if (mPopWindow != null && mPopWindow.isShowing()){
                    mPopWindow.dismiss();
                    mPopWindow = null;
                }else {
                    showPopupWindow();
                }

                break;

            case R.id.tv_home_fragment_search: //顶部搜索框

                startActivity(new Intent(getActivity(), HomeSearchActivity.class));
                break;

            case R.id.tv_home_more_bill: //天际账单
                startActivity(new Intent(getActivity(), HomeSkyBillActivity.class));

                break;
            case R.id.tv_home_more_service: //我的客服

                break;
            case R.id.tv_home_scan: //扫一扫

                break;
            case R.id.tv_home_pay: //付款单

                break;
            case R.id.tv_home_bank_card: //银行卡

                break;
            case R.id.relative_home_my_funds: //总资产
                startActivity(new Intent(getActivity(), HomeMyFundsActivity.class));

                break;
            case R.id.tv_home_supply: //总资产
                startActivity(new Intent(getActivity(), HomeSupplyActivity.class));

                break;

        }
    }
}
