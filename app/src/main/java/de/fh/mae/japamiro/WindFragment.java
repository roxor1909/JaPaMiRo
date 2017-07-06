package de.fh.mae.japamiro;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class WindFragment extends Fragment {
    private TextView textView;
    private long startID;
    private AppDbHelper dbHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.dbHelper = new AppDbHelper(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {

        View view = inflater.inflate(R.layout.fragment_wind, group, false);
        textView = (TextView) view.findViewById(R.id.textName);
        Bundle args = getArguments();
        this.startID = args.getLong(MainActivity.EXTRA_ID, -1);
        if (startID != -1) {
            String name = dbHelper.getNameFromId(startID);
            textView.setText(name);
        }
        return view;
    }
}
