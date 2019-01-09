package team_3eu.mjclunch;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import static team_3eu.mjclunch.var_Naver_Db.*;
import static team_3eu.mjclunch.ChangeFragment.*;

/**
 * Created by LeeMJ on 2018. 11. 18..
 */

public class Ct_List extends Activity {
    static LayoutInflater inflater;
    static ImageView imageView;
    static TextView names;
    static View rowView;
    static ListView list;
    public int imgID;

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;

        public CustomList(Activity context) {
            super(context, R.layout.listitem, dbSize);//t2 배열의 값의 수로 크기 조잘

            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.listitem, null, true);

            imageView = (ImageView) rowView.findViewById(R.id.image);
            names = (TextView) rowView.findViewById(R.id.name);
            switch (selMenu){
                case "전체":
                    names.setText(dbAll[position][0]);
                    break;
                case "한식":
                    names.setText(dbKor[position][0]);
                    break;
                case "중식":
                    names.setText(dbChi[position][0]);
                    break;
                case "일식":
                    names.setText(dbJap[position][0]);
                    break;
                case "양식":
                    names.setText(dbWest[position][0]);
                    break;
                case "기타":
                    names.setText(dbEtc[position][0]);
                    break;
            }
            imgID = parent.getResources().getIdentifier("i_"+dbSize.get(position),"drawable","team_3eu.mjclunch");
            imageView.setImageResource(imgID);
            return rowView;
        }
    }
}
