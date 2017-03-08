package darkblue.com.skyline.UI.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import darkblue.com.skyline.Adapter.MyFundTabAdapter;
import darkblue.com.skyline.R;
import darkblue.com.skyline.UI.Fragment.HomeMyFundsFragment.ConsumeDayFragment;
import darkblue.com.skyline.UI.Fragment.HomeMyFundsFragment.ConsumeMonthFragment;
import darkblue.com.skyline.UI.Fragment.HomeMyFundsFragment.CousumeTotalFragment;

public class HomeMyFundsActivity extends AppCompatActivity {


    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private ConsumeDayFragment consumeDayFragment;              //每天消费fragment
    private ConsumeMonthFragment consumeMonthFragment;            //月度消费fragment
    private CousumeTotalFragment cousumeTotalFragment;                      //总消费fragment

    @ViewInject(R.id.tablayout_home_my_funds)
    private TabLayout tabLayout;
    @ViewInject(R.id.viewpager_home_my_funds)
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_my_funds);

        x.view().inject(this);
        initControls();

        initView();
    }


    private void initView(){


    }

    private void initControls( ) {


        //初始化各fragment
        consumeDayFragment = new ConsumeDayFragment();
        consumeMonthFragment = new ConsumeMonthFragment();
        cousumeTotalFragment = new CousumeTotalFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(consumeDayFragment);
        list_fragment.add(consumeMonthFragment);
        list_fragment.add(cousumeTotalFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("每日消费");
        list_title.add("月度消费");
        list_title.add("总消费");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(2)));

        fAdapter = new MyFundTabAdapter(getSupportFragmentManager(),list_fragment,list_title);

        //viewpager加载adapter
        viewPager.setAdapter(fAdapter);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
    }


}
