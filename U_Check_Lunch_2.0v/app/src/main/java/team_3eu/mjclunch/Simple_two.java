package team_3eu.mjclunch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhn.android.maps.NMapActivity;

import java.util.Random;

import static team_3eu.mjclunch.ChangeFragment.getDB;
import static team_3eu.mjclunch.ChangeFragment.setDB;
import static team_3eu.mjclunch.LoadingImg.progressDialog;
import static team_3eu.mjclunch.LoadingImg.progressOFF;
import static team_3eu.mjclunch.Naver_map_api.mapLayout;
import static team_3eu.mjclunch.NormalView.Normal_setText;
import static team_3eu.mjclunch.NormalView.img_nor_view;
import static team_3eu.mjclunch.var_Naver_Db.*;
import static team_3eu.mjclunch.DB_Android.*;
import static team_3eu.mjclunch.DB_Image.*;
import static team_3eu.mjclunch.Result.*;

/**
 * Created by LeeMJ on 2018. 10. 4..
 */

public class Simple_two extends NMapActivity {
    public Context mContext = this;
    public static Mydialog dialog1;

    public static TextView sim_menu,resname,sim_tel,sim_clk,won,sim_addr;
    public static ImageView imgs,resback;
    public static LinearLayout lin1;
    int resnum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpletwo_page);
        GetPointer GPR = new GetPointer(mContext);
        Naver_map_api.init.in_inte(mContext);
        Intent intent = getIntent();

        resname = findViewById(R.id.resname);
        resback = findViewById(R.id.backgroundimg);
        lin1 = findViewById(R.id.lin1);
        mapLayout = findViewById(R.id.mapLayout);
        sim_menu = (TextView) findViewById(R.id.sim_menu);
        sim_tel = findViewById(R.id.sim_tel);
        sim_clk = findViewById(R.id.sim_clk);
        won = findViewById(R.id.won);
        sim_addr = findViewById(R.id.sim_addr);
        String distance = intent.getStringExtra("거리"); //준혁's
        resnum = Integer.parseInt(distance);
        imgs = (ImageView) findViewById(R.id.imageView);
        if (resnum != 0) id = Integer.toString(selectTime(resnum));
        DB_Android DBA = new DB_Android(mContext);
        GetData Data = new GetData();
        Data.execute("http://18.222.11.106/retest.php", id, selMenu);  // 에뮬-"10.0.2.2
        resback.bringToFront();
        lin1.bringToFront();
    }



    final static Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case on_start:
                    Naver_map_api.init.in_inite1(true);
                    break;
                case GetPoint:
                    GetPointer.getPoint(addrs);
                    break;
                case Simple_start:
                    onResult("simple");
                    if (price.equals("")) won.setText("정보없음");
                    else won.setText(price);
                    if (time.equals("")) sim_clk.setText("정보없음");
                    else sim_clk.setText(times);
                    if (tel.equals("")) sim_tel.setText("정보없음");
                    else sim_tel.setText(tels);
                    resname.setText(names);
                    sim_menu.setText(menu);
                    sim_addr.setText(newaddr);
                    break;
                case Normal_view_start:
                    onResult("normal");
                    Normal_setText();
                    break;
                case Simple_image:
                    imgs.setImageBitmap(bmImg);
                    imgs.setVisibility(View.VISIBLE);
                    imgArray.clear();
                    break;
                case Normal_image:
                    img_nor_view.setImageBitmap(bmImg);
                    img_nor_view.setVisibility(View.VISIBLE);
                    imgArray.clear();
                    break;
                case All_Menu:
                    ResultList("전체");
                    getDB("한식", DB_Android.mContext);
                    break;
                case Korea_Menu:
                    ResultList("한식");
                    getDB("중식", DB_Android.mContext);
                    break;
                case China_Menu:
                    ResultList("중식");
                    getDB("일식", DB_Android.mContext);
                    break;
                case Japan_Menu:
                    ResultList("일식");
                    getDB("양식", DB_Android.mContext);
                    break;
                case West_Menu:
                    ResultList("양식");
                    getDB("기타", DB_Android.mContext);
                    break;
                case Etc_Menu:
                    ResultList("기타");
                    selMenu = "Normal";
                    setDB();
                    break;
                case Faild_internet:
                    View.OnClickListener DialogListener = new View.OnClickListener() {
                    public void onClick(View v) {
                        if(progressDialog.isShowing())progressOFF();
                        dialog1.dismiss();
                        if (selMenu=="Simple") {
                            GetPointer GPR = new GetPointer(DB_Android.mContext);
                            Naver_map_api.init.in_inte(DB_Android.mContext);
                            DB_Android DBA = new DB_Android(DB_Android.mContext);
                            DB_Android.GetData Data = new DB_Android.GetData();
                            Data.execute("http://18.222.11.106/retest.php", id, selMenu);
                        } else if (dbSize.size()==0) {
                            selMenu="Normal";
                            getDB("전체", DB_Android.mContext);
                        } else if (!(id=="0") && !(dbSize.size()==0)) {
                            GetPointer GPR = new GetPointer(DB_Android.mContext);
                            Naver_map_api.init.in_inte(DB_Android.mContext);
                            DB_Android DBA = new DB_Android(DB_Android.mContext);
                            DB_Android.GetData Data = new DB_Android.GetData();
                            Data.execute("http://18.222.11.106/retest.php", id, "Normal");
                        }
                    }
                };
                dialog1 = new Mydialog(DB_Android.mContext,
                        "인터넷 연결을 확인해주세요", // 내용
                        DialogListener); // 왼쪽 버튼 이벤트
                dialog1.setCancelable(false);
                dialog1.getWindow().setGravity(Gravity.CENTER);
                dialog1.show();
                break;
            }
        }
    };

    private int selectTime(int a) {
        Random rnd = new Random();
        switch (a) {
            case 5:
                resnum = rnd.nextInt(100) + 1;
                while (resnum > 40)
                    resnum = rnd.nextInt(100) + 1;
                break;
            case 10:
                resnum = rnd.nextInt(200) + 1;
                while ((40 < resnum && resnum < 100) || 137 < resnum)
                    resnum = rnd.nextInt(200) + 1;
                break;
            case 15:
                resnum = rnd.nextInt(300) + 1;
                while ((40 < resnum && resnum < 100) || (137 < resnum && resnum < 200) || 221 < resnum)
                    resnum = rnd.nextInt(300) + 1;
                break;
        }
        return resnum;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}