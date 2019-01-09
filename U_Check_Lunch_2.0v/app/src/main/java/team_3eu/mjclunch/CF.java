package team_3eu.mjclunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import static team_3eu.mjclunch.ChangeFragment.dbChi;
import static team_3eu.mjclunch.Ct_List.list;
import static team_3eu.mjclunch.var_Naver_Db.adapter;

/**
 * Created by LeeMJ on 2018. 11. 15..
 */

public class CF  extends Fragment {
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cf, null);

        list = (ListView) view.findViewById(R.id.list_cf);
        adapter = new Ct_List().new CustomList(getActivity());
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(view.getContext(), NormalView.class);

                String selID = dbChi[position][1];
                intent.putExtra("sel", selID);

                startActivity(intent);
            }
        });

        return view;

    }
}