package darkblue.com.skyline.UI.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import darkblue.com.skyline.Adapter.SearchHistoryGridViewAdapter;
import darkblue.com.skyline.Bean.SearchHistorysBean;
import darkblue.com.skyline.R;
import darkblue.com.skyline.Utils.DBUtil.SearchHistorysDao;
import darkblue.com.skyline.Utils.other.ToastUtils;


public class HomeSearchActivity extends AppCompatActivity implements View.OnClickListener {


    @ViewInject(R.id.et_home_fragment_search_word)  //搜索内容输入框
    private EditText et_search_keyword;

    @ViewInject(R.id.tv_home_search_ensure) //搜索按钮
    private TextView tv_search;

    @ViewInject(R.id.tv_search_history_clear) //清除历史
    private TextView tv_clear;

    @ViewInject(R.id.grid_view_search_history) // 显示历史记录
    private GridView grid_View_history_word;

    @ViewInject(R.id.list_View_search_history) // 搜索时动态显示历史
    private ListView list_View_history;

    @ViewInject(R.id.tv_search_history_dynamic_txt)
    private TextView  tvSearchDynamic;      //列表动态显示时文本提示

    @ViewInject(R.id.linear_layout_search)
    private LinearLayout linearLayout;


    private SearchHistorysDao dao;
    private ArrayList<SearchHistorysBean> historywordsList = new ArrayList<SearchHistorysBean>();
    private ArrayList<SearchHistorysBean> historywordsDynamicList = new ArrayList<SearchHistorysBean>();
    private SearchHistoryGridViewAdapter mAdapter;
    private SearchHistoryGridViewAdapter listAdapter;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);


        x.view().inject(this);
        dao = new SearchHistorysDao(this);
        historywordsList = dao.findAll();
        initView();
        addListenser();
    }



    private void initView() {


        mAdapter = new SearchHistoryGridViewAdapter(historywordsList, HomeSearchActivity.this);
        count = mAdapter.getCount();

        if (count > 0) {
            tv_clear.setVisibility(View.VISIBLE);
        } else {
            tv_clear.setVisibility(View.GONE);
        }
        grid_View_history_word.setAdapter(mAdapter);

    }



    private void addListenser(){


        tv_search.setOnClickListener(this);
        tv_clear.setOnClickListener(this);


        //搜索框内容动态的监听
        et_search_keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    tvSearchDynamic.setText("搜索历史");
                } else {
                    tvSearchDynamic.setText("搜索结果");

                }
                linearLayout.setVisibility(View.VISIBLE);
                String tempName = et_search_keyword.getText().toString().trim();
                // 根据tempName去模糊查询数据库中有没有数据
//                queryData(tempName);

            }
        });


        list_View_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ToastUtils.show(HomeSearchActivity.this,"111");

            }
        });


        // 搜索历史的显示
        grid_View_history_word.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SearchHistorysBean bean = (SearchHistorysBean) mAdapter.getItem(position);

                et_search_keyword.setText(bean.historyword);

                mAdapter.notifyDataSetChanged();
                tv_search.performClick();
            }
        });

    }



    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {

        historywordsDynamicList = dao.queryData(HomeSearchActivity.this,tempName);
        listAdapter = new SearchHistoryGridViewAdapter(historywordsDynamicList,HomeSearchActivity.this);

        list_View_history.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_home_search_ensure:// 点击搜索，先拿关键词
                String searchWord = et_search_keyword.getText().toString().trim();
                if (TextUtils.isEmpty(searchWord)) {
                    ToastUtils.show(this, "关键字不能为空！");
                } else {
                    // 存储数据
                    dao.addOrUpdate(searchWord);
                    historywordsList.clear();
                    historywordsList.addAll(dao.findAll());
                    mAdapter.notifyDataSetChanged();

                    //跳转页面：

                }

                break;
            case R.id.tv_search_history_clear:
                // 点击清除历史的时候：清除数据库中所有的数据
                dao.deleteAll();
                historywordsList.clear();
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;

        }

    }


}
