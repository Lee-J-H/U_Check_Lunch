package team_3eu.mjclunch;

import android.content.Context;
import android.util.Log;

import static team_3eu.mjclunch.Simple_two.mhandler;
import static team_3eu.mjclunch.var_Naver_Db.mapx;
import static team_3eu.mjclunch.var_Naver_Db.mapy;
import static team_3eu.mjclunch.var_Naver_Db.on_start;

/**
 * Created by LeeMJ on 2018. 11. 15..
 */

public class GetPointer {
    static Context mContext ;
    public GetPointer(Context context) {
        mContext = context;
    }
    public static void getPoint(String... addr) {
        GeoPointer geoPointer = new GeoPointer(mContext, listener);
        geoPointer.execute(addr);
    }

    private static GeoPointer.OnGeoPointListener listener = new GeoPointer.OnGeoPointListener() {
        @Override
        public void onPoint(GeoPointer.Point[] p) {
            for (GeoPointer.Point point : p) {
                mapx = point.x;
                mapy = point.y;
                mhandler.sendEmptyMessage(on_start);
            }
        }

        @Override
        public void onProgress(int progress, int max) {
        }
    };
}
