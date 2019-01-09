package team_3eu.mjclunch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import static team_3eu.mjclunch.Choice.*;
import static team_3eu.mjclunch.LoadingImg.progressON;
import static team_3eu.mjclunch.var_Naver_Db.selMenu;

/**
 * Created by LeeMJ on 2018. 10. 4..
 */

public class nextpage extends AppCompatActivity {
    String choice;
    Intent intent;
    Context context = this;
    ImageButton no_btn, yes_btn;
    TextView textView;
    Mydialog dialog;
    Activity activity = this;
    public  static float dm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setMode(readChoice(context));
        setContentView(R.layout.nextpage);
        no_btn = (ImageButton)findViewById(R.id.no_nc);
        yes_btn = (ImageButton)findViewById(R.id.yes_nc);
        textView = (TextView)findViewById(R.id.IsSelected);
        choice = "NULL";
        onLister();

    }

    public void onBackPressed() {
        finish();
    }

    public void Dialog() {
        dialog = new Mydialog(context,
                "거리와 시간을" + "\n입력해주세요!", // 내용
                DialogListener); // 왼쪽 버튼 이벤트
        // 오른쪽 버튼 이벤트

        //요청 이 다이어로그를 종료할 수 있게 지정함
        dialog.setCancelable(true);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    //다이얼로그 클릭이벤트
    private View.OnClickListener DialogListener = new View.OnClickListener() {
        public void onClick(View v) {
            progressON(activity, null);
            writeChoice(context, choice);
            setMode(readChoice(context));
            dialog.dismiss();
        }
    };
//출처: http://yoo-hyeok.tistory.com/51 [유혁의 엉터리 개발]

    public void onLister() {
        no_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = "No";
                writeChoice(context,choice);
                setMode(readChoice(context));
            }
        });

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "Yes";
                Dialog();
            }
        });
    }


    public void setMode(String a) {
        switch (a) {
            case "simple":
                selMenu="Simple";
                intent = new Intent(nextpage.this, simpleone.class);
                startActivity(intent);
                finish();
                break;
            case "normal":
                selMenu="Normal";
                intent = new Intent(nextpage.this, normal.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
