package darkblue.com.skyline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import darkblue.com.skyline.UI.Fragment.MainFragment.CreditFragment;
import darkblue.com.skyline.UI.Fragment.MainFragment.HomeFragment;
import darkblue.com.skyline.UI.Fragment.MainFragment.MessageFragment;
import darkblue.com.skyline.UI.Fragment.MainFragment.MyFragment;

public class MainActivity extends FragmentActivity {


    @ViewInject(R.id.main_radio_group)
    private RadioGroup radioGroup;
    @ViewInject(R.id.main_radio_buton_home)
    private RadioButton rbHome;                     //首页
    @ViewInject(R.id.main_radio_buton_message)
    private RadioButton rbMessage;                  //消息
    @ViewInject(R.id.main_radio_buton_credit)
    private RadioButton rbCredit;                   //授信
    @ViewInject(R.id.main_radio_buton_my)
    private RadioButton rbMy;                       //我的
    @ViewInject(R.id.fragment_container)
    private LinearLayout fragment_container;

    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";//当前显示的fragment

    private HomeFragment homeFragment;
    private List<Fragment> fragments = new ArrayList<>();
//    private Fragment[] fragmentArray = new Fragment[4];
    private int currentFragmentIndex = 0; //当前正在显示的Fragment
//    private int clickRBIndex = 0; //当前点击的是哪个radiobutton

    private Fragment currentFragment = new Fragment(); //当内在重启后用于记录当前正在显示的Fragment
    private FragmentManager fragmentManager;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        x.view().inject(this);//xutils框架绑定UI




        if (savedInstanceState != null) { // “内存重启”时调用

            //获取“内存重启”时保存的索引下标
            currentFragmentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);

            //注意，添加顺序要跟下面添加的顺序一样！！！！
            fragments.removeAll(fragments);
            fragments.add(fragmentManager.findFragmentByTag(0 + ""));
            fragments.add(fragmentManager.findFragmentByTag(1 + ""));
            fragments.add(fragmentManager.findFragmentByTag(2 + ""));
            fragments.add(fragmentManager.findFragmentByTag(3 + ""));

            //恢复fragment页面
            restoreFragment();

        } else {      //正常启动时调用

            initFragment(); //初始化主页的四个Fragment
            showFirstFragment(); //进入应用显示HomeFragment
            addListenser(); //给四个RadioBuuton设置监听
        }

    }



    /**
     * 恢复fragment
     */
    private void restoreFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();

        for (int i = 0; i < fragments.size(); i++) {

            if(i == currentFragmentIndex){
                mBeginTreansaction.show(fragments.get(i));
            }else{
                mBeginTreansaction.hide(fragments.get(i));
            }
        }
        mBeginTreansaction.commit();
        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentFragmentIndex);

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {

        //“内存重启”时保存当前的fragment名字
        outState.putInt(CURRENT_FRAGMENT, currentFragmentIndex);
        super.onSaveInstanceState(outState);
    }




    private void addListenser() {
        rbHome.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_radio_buton_home:
                        changeFragment(0);
                        break;
                    case R.id.main_radio_buton_message:
                        changeFragment(1);
                        break;
                    case R.id.main_radio_buton_credit:
                        changeFragment(2);
                        break;
                    case R.id.main_radio_buton_my:
                        changeFragment(3);
                        break;
                }
            }
        });


    }


    /**
     * @param clickRBIndex 当前点击RadioButton
     */

    private void changeFragment(int clickRBIndex) {
        // 判断单击的是不是当前的btn
        if (clickRBIndex != currentFragmentIndex) {
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            // 动作1:隐藏以前的fragment
            transaction.hide(fragments.get(currentFragmentIndex));

            // 动作2:添加新的fragment
            Fragment showFragment = fragments.get(clickRBIndex);
            if (showFragment.isAdded() == false) {
                transaction.add(R.id.fragment_container, showFragment);
            }
            // 动作3:显示新的fragment
            transaction.show(showFragment);
            // 如果3个动作都成功，提交
            transaction.commit();

            currentFragmentIndex = clickRBIndex;
        }
    }



    private void initFragment() {

        homeFragment = new HomeFragment();

        MessageFragment messageFragment = new MessageFragment();
        CreditFragment creditFragment = new CreditFragment();
        MyFragment myFragment = new MyFragment();

        fragments.add(homeFragment);
        fragments.add(messageFragment);
        fragments.add(creditFragment);
        fragments.add(myFragment);


    }

    /**
     * 进入应用时显示HomeFragment(首页)
     */
    private void showFirstFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        // transaction事务：包含多个动作
        // 全部成功，才能提交
        // 动作1：添加
        transaction.add(R.id.fragment_container, homeFragment);
        // 动作2:显示
        transaction.show(homeFragment);
        // 两个动作都成功，才提交
        transaction.commit();


    }

}
