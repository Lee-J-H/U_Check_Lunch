package team_3eu.mjclunch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static team_3eu.mjclunch.Simple_two.mhandler;
import static team_3eu.mjclunch.var_Naver_Db.*;


public class DB_Image {

    public static Bitmap bmImg;

    public static Context mContext;
    public static String mode;
    public static String imgNum;

    public DB_Image(Context context) {
        mContext = context;
    }

    public static class ResImage extends AsyncTask<String, Integer, Bitmap> {
        public static ImageView imgs;

        @Override
        protected Bitmap doInBackground(String... urls) {
            mode = urls[1];
            imgNum = urls[2];
            try {
                URL myFileUrl = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();

                DisplayMetrics dm = mContext.getResources().getDisplayMetrics();

                int width = dm.widthPixels;

                int height = dm.heightPixels/2;

                bmImg = BitmapFactory.decodeStream(is);
                bmImg = Bitmap.createScaledBitmap(bmImg, 400, 300, true);
                imgArray.add(bmImg);
                switch (mode) {
                    case "전체":
                        Img_All.add(bmImg);
                        break;
                    case "한식":
                        Img_Kor.add(bmImg);
                        break;
                    case "중식":
                        Img_Chi.add(bmImg);
                        break;
                    case "일식":
                        Img_Jap.add(bmImg);
                        break;
                    case "양식":
                        Img_West.add(bmImg);
                        break;
                    case "기타":
                        Img_Etc.add(bmImg);
                        break;

                }


            } catch (IOException e) {
                //e.printStackTrace();
            }
            return bmImg;
        }

        protected void onPostExecute(Bitmap img) {
            switch (mode){
                case "simple" :
                    mhandler.sendEmptyMessage(Simple_image);
                    break;
                case "normal":
                    mhandler.sendEmptyMessage(Normal_image);
                    break;
            }
        }
    } //http://www.joshi.co.kr/index.php?mid=board_QBES95&document_srl=948

}
