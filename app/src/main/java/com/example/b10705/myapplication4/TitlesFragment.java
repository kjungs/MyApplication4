package com.example.b10705.myapplication4;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TitlesFragment extends Fragment {

    public TitlesFragment() {
        // Required empty public constructor
    }

    public interface OnTitleSelectedListener {
        public void onTitleSelected(int i);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (View)inflater.inflate(R.layout.fragment_titles, container, false);

        ArrayList<MyItem> data = new ArrayList<MyItem>();
        data.add(new MyItem(R.drawable.sample_0, "냉면", "6000"));
        data.add(new MyItem(R.drawable.sample_1, "라면", "4000"));
        data.add(new MyItem(R.drawable.sample_2, "김밥", "2000"));
        data.add(new MyItem(R.drawable.sample_3, "돈가스", "5000"));

        adapter = new MyAdapter(this, R.layout.item, data);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MenuDetail.class);

                startActivity(intent);
            }
        });

        String sample1 = getText(R.string.sample1);

        TextView Address = (TextView)findViewById(R.id.Address);
        Address.setText(sample1);

        String sample2 = getText(R.string.sample2);

        TextView Call = (TextView)findViewById(R.id.Call);
        Call.setText(sample1);

        String sample3 = getText(R.string.sample3);

        TextView Time = (TextView)findViewById(R.id.Time);
        Call.setText(sample3);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Activity activity = getActivity();
                ((OnTitleSelectedListener)activity).onTitleSelected(i);
            }
        });

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        return rootView;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onGotoCallClicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-0000-0000"));
        startActivity(myIntent);
    }
    public void onBtnClicked(View view){

    }
}
