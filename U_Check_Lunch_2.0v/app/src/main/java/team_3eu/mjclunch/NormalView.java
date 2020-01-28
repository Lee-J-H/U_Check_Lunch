package team_3eu.mjclunch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhn.android.maps.NMapActivity;

import static team_3eu.mjclunch.Naver_map_api.mapLayout;
import static team_3eu.mjclunch.var_Naver_Db.distance;
import static team_3eu.mjclunch.var_Naver_Db.id;
import static team_3eu.mjclunch.var_Naver_Db.name;
import static team_3eu.mjclunch.var_Naver_Db.newaddr;
import static team_3eu.mjclunch.var_Naver_Db.price;
import static team_3eu.mjclunch.var_Naver_Db.prices;
import static team_3eu.mjclunch.var_Naver_Db.tel;
import static team_3eu.mjclunch.var_Naver_Db.tels;
import static team_3eu.mjclunch.var_Naver_Db.time;
import static team_3eu.mjclunch.var_Naver_Db.times;


public class NormalView extends NMapActivity {

    static TextView nor_price, nor_time, nor_rest, nor_naddr, nor_addr, nor_times, nor_tel;
    static ImageView img_nor_view,nor_view1,nor_view2,line1,line2,line3,line4,line5,line6;
    static Bitmap line_1,line_2,norview1,norview2;
    Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_view);

        Naver_map_api.init.in_inte(mContext);
        GetPointer GPR = new GetPointer(mContext);

        Intent intent = getIntent();
        id = intent.getStringExtra("sel");


        DB_Android DBA = new DB_Android(mContext);
        DB_Android.GetData Data = new DB_Android.GetData();
        Data.execute("http://34.74.154.52/retest.php", id, "Normal");


        img_nor_view = (ImageView) findViewById(R.id.frame_image);

        nor_view1 = (ImageView)findViewById(R.id.normalview1);
        nor_view2 = (ImageView)findViewById(R.id.normalview2);

        line1 = (ImageView)findViewById(R.id.gongimg1);
        line2 =(ImageView)findViewById(R.id.gongimg2);
        line3 =(ImageView)findViewById(R.id.gongimg3);
        line4 =(ImageView)findViewById(R.id.gongimg4);
        line5 =(ImageView)findViewById(R.id.gongimg5);

        line6 = (ImageView)findViewById(R.id.gongimg6);
        nor_price = (TextView) findViewById(R.id.nor_price);
        nor_rest = (TextView) findViewById(R.id.nor_restaurant);
        nor_times = (TextView) findViewById(R.id.nor_times);
        nor_naddr = (TextView) findViewById(R.id.nor_naddr);
        nor_tel = (TextView) findViewById(R.id.nor_tel);


        mapLayout = findViewById(R.id.frame_map);

        line_1 = BitmapFactory.decodeResource(getResources(),R.drawable.line1);
        line_2 = BitmapFactory.decodeResource(getResources(),R.drawable.line2);

        norview1 = BitmapFactory.decodeResource(getResources(),R.drawable.normal_1);
        norview2 = BitmapFactory.decodeResource(getResources(),R.drawable.normal_2);

        nor_view1.setImageBitmap(norview1);
        nor_view2.setImageBitmap(norview2);
        line1.setImageBitmap(line_1);
        line2.setImageBitmap(line_1);
        line3.setImageBitmap(line_1);
        line4.setImageBitmap(line_1);
        line5.setImageBitmap(line_1);
        line6.setImageBitmap(line_2);
    }

    public static void Normal_setText() {
        if (prices.equals("")) nor_price.setText("가격정보없음");
        else nor_price.setText(prices);

        if (time.equals("")) nor_times.setText("정보없음");
        else nor_times.setText(times);

        if (tel.equals("")) nor_tel.setText("전화번호없음");
        else nor_tel.setText(tels);

        nor_rest.setText(name);
        nor_times.setText(distance);
        nor_naddr.setText(newaddr);

    }

}
