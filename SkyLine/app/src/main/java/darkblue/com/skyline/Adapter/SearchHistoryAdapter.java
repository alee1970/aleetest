package darkblue.com.skyline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import darkblue.com.skyline.Bean.SearchHistorysBean;
import darkblue.com.skyline.R;

/**
 * 搜索页面-历史记录显示的Adapter
 */

public class SearchHistoryAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<SearchHistorysBean> historywordsList;

    public SearchHistoryAdapter(ArrayList<SearchHistorysBean> historywordsList, Context context) {
        this.historywordsList = historywordsList;
        this.context = context;
    }

    @Override
    public int getCount() {

        return historywordsList == null ? 0 : historywordsList.size();
    }

    @Override
    public Object getItem(int position) {

        return historywordsList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_history_word, null);
            holder.tv_word = (TextView) convertView.findViewById(R.id.tv_search_record);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_word.setText(historywordsList.get(position).toString());

        return convertView;
    }


    class ViewHolder {
        TextView tv_word;
    }
}
