package team_3eu.mjclunch;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static team_3eu.mjclunch.DB_Android.loadImage;
import static team_3eu.mjclunch.DB_Android.mJsonString;
import static team_3eu.mjclunch.Simple_two.mhandler;
import static team_3eu.mjclunch.LoadingImg.*;
import static team_3eu.mjclunch.var_Naver_Db.*;
import static team_3eu.mjclunch.var_Naver_Db.tels;
import static team_3eu.mjclunch.var_Naver_Db.times;

public class Result {
    public static StringBuffer stringb;
    public static Context mContext;


    public Result(Context context) {
        mContext = context;
    }

    public static void ResultList(String mode) {
        stringb = new StringBuffer();

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);

                id = item.getString("id");
                name = item.getString("name");
                time = item.getString("time");
                newaddr = item.getString("newaddr");
                menu = item.getString("menu");
                tel =", " + item.getString("tel");

                if (tel.equals(", " +"NULL")) {
                    tel = "";
                }

                stringb.append(
                        name +
                                "\n" + newaddr +
                                "\n" + menu + tel
                ); //http://apphappy.tistory.com/81
                switch (mode) {
                    case "전체":
                        db_All.add(new String[]{stringb.toString(), id});
                        break;
                    case "한식":
                        db_Kor.add(new String[]{stringb.toString(), id});
                        break;
                    case "중식":
                        db_Chi.add(new String[]{stringb.toString(), id});
                        break;
                    case "일식":
                        db_Jap.add(new String[]{stringb.toString(), id});
                        break;
                    case "양식":
                        db_West.add(new String[]{stringb.toString(), id});
                        break;
                    case "기타":
                        db_Etc.add(new String[]{stringb.toString(), id});
                        break;

                }

                if (mode.equals("simple")) {
                    mhandler.sendEmptyMessage(GetPoint);
                    progressOFF();
                }
                if(mode.equals("기타") && i == jsonArray.length()-1) progressOFF();
                stringb.delete(0, stringb.length());
            }

        } catch (JSONException e) {

        }
    }

    public static void onResult(String mode) {

        stringb = new StringBuffer();

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            JSONObject item = jsonArray.getJSONObject(0);


            id = item.getString("id");
            name = item.getString("name");
            distance = item.getString("distance");
            newaddr = item.getString("newaddr");
            oldaddr = item.getString("oldaddr");
            menu = item.getString("menu");
            time ="\n" + item.getString("time");
            tel ="\n" + item.getString("tel");
            price = item.getString("price");
            prices = price;

            if (time.equals("\n" + "NULL"))
                time = "";
            if (tel.equals("\n" + "NULL"))
                tel = "";
            if (price.equals("NULL")) {
                price = "";
                prices = "";
            }
            if(time.equals("") && tel.equals("") && !price.equals("")){
                price = "\n" + item.getString("price");

            }

            addrs = oldaddr;
            names = name;
            tels = item.getString("tel");
            times = item.getString("time");

            stringb.append(
                    name + "           " + menu + ", " +distance +
                            "\n" + newaddr +
                            "\n" + oldaddr +
                            time +
                            tel + "           "+ price
            );
            loadImage(id, mode, String.valueOf(jsonArray.length()));
            mhandler.sendEmptyMessage(GetPoint);
        } catch (JSONException e) {

        }
    }
}