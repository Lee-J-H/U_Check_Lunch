package team_3eu.mjclunch;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static team_3eu.mjclunch.Simple_two.mhandler;
import static team_3eu.mjclunch.var_Naver_Db.*;
/**
 * Created by LeeMJ on 2018. 11. 9..
 */

public class DB_Android {
    public static Context mContext;
    public static String mJsonString;
    public static String getId;
    public static String getMenu;


    public DB_Android(Context context) {
        mContext = context;
    }

    public static void loadImage(String id, String mode, String imgNum) {
        DB_Image DBI = new DB_Image(mContext);
        DB_Image.ResImage rImage = new DB_Image.ResImage();
        rImage.execute("http://3eutest.cf/images/i_" + id + ".jpg", mode, imgNum);
    }

    public static class GetData extends AsyncTask<String, Void, String> {
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null) {
                mhandler.sendEmptyMessage(Faild_internet);
            } else {
                mJsonString = result;

                if (!getId.equals("0")) {
                    switch (getMenu){
                        case "Simple" :
                            mhandler.sendEmptyMessage(Simple_start);
                            break;
                        case "Normal":
                            mhandler.sendEmptyMessage(Normal_view_start);
                            break;
                    }

                } else {
                    switch (getMenu) {
                        case "전체":
                            mhandler.sendEmptyMessage(All_Menu);
                            break;
                        case "한식":
                            mhandler.sendEmptyMessage(Korea_Menu);
                            break;
                        case "중식":
                            mhandler.sendEmptyMessage(China_Menu);
                            break;
                        case "일식":
                            mhandler.sendEmptyMessage(Japan_Menu);
                            break;
                        case "양식":
                            mhandler.sendEmptyMessage(West_Menu);
                            break;
                        case "기타":
                            mhandler.sendEmptyMessage(Etc_Menu);
                            break;
                    }
                }
            }
        }


        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            getId = params[1];
            getMenu = params[2];

            String postParameters = "id=" + params[1] + "&menu=" + params[2];

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();


                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();


            } catch (Exception e) {
                errorString = e.toString();

                return null;
            }

        }
    }

}
