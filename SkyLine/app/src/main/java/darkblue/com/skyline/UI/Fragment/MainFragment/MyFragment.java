package darkblue.com.skyline.UI.Fragment.MainFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import darkblue.com.skyline.R;


public class MyFragment extends Fragment {

    public MyFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "My", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_my, container, false);
    }


}
