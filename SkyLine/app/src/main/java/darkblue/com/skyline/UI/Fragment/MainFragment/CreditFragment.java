package darkblue.com.skyline.UI.Fragment.MainFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import darkblue.com.skyline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditFragment extends Fragment {


    public CreditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Credit", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_credit, container, false);
    }

}
