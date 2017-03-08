package darkblue.com.skyline.UI.Fragment.MainFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import darkblue.com.skyline.R;
import darkblue.com.skyline.UI.CustomView.LineGraphicView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditFragment extends Fragment {


    private ArrayList<Double> yList;
    private LineGraphicView tu;
    View view;
    public CreditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Credit", Toast.LENGTH_SHORT).show();
        view = inflater.inflate(R.layout.fragment_credit, container, false);
        tu = (LineGraphicView) view.findViewById(R.id.line_graphic);
        initData();
        return view;
    }
    void initData(){
        yList = new ArrayList<Double>();
        yList.add((double) 20000.00);
        yList.add(5500.00);
        yList.add(5000.00);
        yList.add(5000.00);
        yList.add(0.0);

        ArrayList<String> xRawDatas = new ArrayList<String>();
        xRawDatas.add("10月");
        xRawDatas.add("11月");
        xRawDatas.add("12月");
        xRawDatas.add("1月");
        xRawDatas.add("2月");



        tu.setData(yList, xRawDatas, 10, 2);
    }

}
