package team_3eu.mjclunch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Vector;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import static team_3eu.mjclunch.ChangeFragment.dbAll;
import static team_3eu.mjclunch.ChangeFragment.onDB;
import static team_3eu.mjclunch.LoadingImg.progressDialog;
import static team_3eu.mjclunch.LoadingImg.progressON;
import static team_3eu.mjclunch.var_Naver_Db.dbSize;
import static team_3eu.mjclunch.var_Naver_Db.selMenu;
import static team_3eu.mjclunch.ChangeFragment.getDB;

public class normal extends AppCompatActivity {
    public static Context norContext;
    public static int banner;
    float touchX, touchY;
    static ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6;

    AutoScrollViewPager autoViewPager;
    LinearLayout viewLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal);
        norContext = normal.this;

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        imageButton6 = (ImageButton) findViewById(R.id.imageButton6);

        ArrayList<String> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        for (banner = 1; banner < 3; banner++) {
            data.add("http://18.222.11.106/images/banners/banner_" + banner + ".png");
        }
        autoViewPager = (AutoScrollViewPager) findViewById(R.id.autoViewPager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(this, data);

        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착


        autoViewPager.startAutoScroll(); //Auto Scroll 시작
        autoViewPager.setInterval(2500); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override

            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                Intent intent;
                String selID;
                if (action == MotionEvent.ACTION_DOWN) {
                    touchX=event.getX();
                    touchY=event.getY();
                    banner =autoViewPager.getCurrentItem();
                }
                if (action == MotionEvent.ACTION_MOVE) {
                }
                if (action == MotionEvent.ACTION_UP) {
                    if (Math.sqrt(Math.pow(Math.abs(touchX-event.getX()),2)+Math.pow(Math.abs(touchY-event.getY()),2))<=50) {
                        switch (banner) {
                            case 0:
                                intent = new Intent(norContext, NormalView.class);
                                selID = dbAll[68][1]; //우리식당
                                intent.putExtra("sel", selID);
                                startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(norContext, NormalView.class);
                                selID = dbAll[9][1]; //인라면
                                intent.putExtra("sel", selID);
                                startActivity(intent);
                                break;
                        }
                    }
                }
                return false;
            }
        });

        if (dbSize.size() == 0 && (progressDialog == null || !progressDialog.isShowing())) {
            getDB("전체", norContext);
            progressON(this, null);
        }

    }



    @Override
    public void onBackPressed() {
        Exit exit = new Exit();
        exit.exitDialog(norContext);
    }

    public void onClick(View view) {
        Intent intent = new Intent(norContext, FragmentPage.class);

        switch (view.getId()) {
            case R.id.imageButton1:
                selMenu = "전체";
                intent.putExtra("value", 0);
                break;
            case R.id.imageButton2:
                selMenu = "한식";
                intent.putExtra("value", 1);
                break;
            case R.id.imageButton3:
                selMenu = "중식";
                intent.putExtra("value", 2);
                break;
            case R.id.imageButton4:
                selMenu = "일식";
                intent.putExtra("value", 3);
                break;
            case R.id.imageButton5:
                selMenu = "양식";
                intent.putExtra("value", 4);
                break;
            case R.id.imageButton6:
                selMenu = "기타";
                intent.putExtra("value", 5);
                break;
        }
        onDB(selMenu);
        norContext.startActivity(intent);
    }
}