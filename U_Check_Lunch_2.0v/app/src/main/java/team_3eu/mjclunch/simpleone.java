package team_3eu.mjclunch;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import static team_3eu.mjclunch.Choice.writeChoice;
import static team_3eu.mjclunch.LoadingImg.progressDialog;
import static team_3eu.mjclunch.LoadingImg.progressOFF;
import static team_3eu.mjclunch.LoadingImg.progressON;
import static team_3eu.mjclunch.var_Naver_Db.dbSize;
import static team_3eu.mjclunch.var_Naver_Db.id;
import static team_3eu.mjclunch.var_Naver_Db.selMenu;

/**
 * Created by LeeMJ on 2018. 10. 4..
 */

public class simpleone extends AppCompatActivity {
    Context mContext = this;
    private FloatingActionButton fab;
    private LinearLayout simplechange;
    private LinearLayout normalchange;
    private ImageView btn_start;
    private EditText edit_time;
    private FrameLayout fra;
    private TextView milText,distext;
    int x = 30, y = 0;

    private Integer i;
    int state = 0;

    Mydialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (progressDialog == null || !progressDialog.isShowing()) progressON(this, null);
        setContentView(R.layout.simple_one_page);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        distext = findViewById(R.id.distance_select);
        btn_start = findViewById(R.id.one_page_start);

        edit_time = findViewById(R.id.edit_time);
        milText = (TextView) findViewById(R.id.mil);
        fra = findViewById(R.id.fra);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        btn_lis();
        btn_start();
        fra.bringToFront();
        if (progressDialog.isShowing()) progressOFF();
    }

    public void btn_lis() {

        distext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.dis_item, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.m1:
                                distext.setText("5");
                                y=5;
                                break;
                            case R.id.m2:
                                distext.setText("10");
                                y=10;
                                break;
                            case R.id.m3:
                                distext.setText("15");
                                y=15;
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });

                popup.show();//Popup Menu 보이기
            }
        });

        edit_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edit_time.length()==0) {
                    milText.setVisibility(View.INVISIBLE);
                    edit_time.setHint("공강시간 입력");
                }
                else {
                    milText.setVisibility(View.VISIBLE);
                    edit_time.setHint("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private View.OnClickListener LeftDialogListener = new View.OnClickListener() {
        public void onClick(View v) {
            String TimeNum = Integer.toString(y);
            Intent intent = new Intent(simpleone.this, Simple_two.class);
            intent.putExtra("거리", TimeNum); // 준혁's
            startActivity(intent);
            //finish();

            final Notice notice = new Notice();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    notice.Noti(mContext);
                }
            }, 30000);//60000 * (i-10));
            dialog.dismiss();
        }
    };
    private View.OnClickListener RightDialogListener = new View.OnClickListener() {
        public void onClick(View v) {
            dialog.dismiss();
        }
    };

    public void btn_start() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(y==0){
                    dialog = new Mydialog(mContext,
                            "거리를 선택해주세요.", // 내용
                            RightDialogListener); // 왼쪽 버튼 이벤트
                    // 오른쪽 버튼 이벤트


                    //요청 이 다이어로그를 종료할 수 있게 지정함
                    dialog.setCancelable(true);
                    dialog.getWindow().setGravity(Gravity.CENTER);
                    dialog.show();
                }
                else if (edit_time.getText().length() == 0) {
                    dialog = new Mydialog(mContext,
                            "공강시간을 입력해주세요.", // 내용
                            RightDialogListener); // 왼쪽 버튼 이벤트
                    // 오른쪽 버튼 이벤트


                    //요청 이 다이어로그를 종료할 수 있게 지정함
                    dialog.setCancelable(true);
                    dialog.getWindow().setGravity(Gravity.CENTER);
                    dialog.show();


                } else if (Integer.parseInt(edit_time.getText().toString()) < 30) {
                    edit_time.setText(null);
                    dialog = new Mydialog(mContext, "30분 이상 입력해주세요.", RightDialogListener);
                    dialog.setCancelable(true);
                    dialog.getWindow().setGravity(Gravity.CENTER);
                    dialog.show();
                } else if (Integer.parseInt(edit_time.getText().toString()) > 180) {
                    edit_time.setText(null);
                    dialog = new Mydialog(mContext, "180분 이하 입력해주세요.", RightDialogListener);
                    dialog.setCancelable(true);
                    dialog.getWindow().setGravity(Gravity.CENTER);
                    dialog.show();
                } else {
                    i = Integer.parseInt(edit_time.getText().toString());
                    dialog = new Mydialog(mContext,
                            "거리 :" + y + "분" + "\n공강 :" + i + "분" + "\n" + "알람 :" +"30초 뒤" /*(i - 10) + "분 뒤"*/, // 내용
                            LeftDialogListener, RightDialogListener); // 왼쪽 버튼 이벤트
                    // 오른쪽 버튼 이벤트

                    //요청 이 다이어로그를 종료할 수 있게 지정함
                    dialog.setCancelable(true);
                    dialog.getWindow().setGravity(Gravity.CENTER);
                    dialog.show();
                }
            }
        });
    }

    private void openDialog() {
        View view = getLayoutInflater().inflate(R.layout.activity_bottom_sheet, null);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        simplechange = (LinearLayout) view.findViewById(R.id.simplemodechange);
        normalchange = (LinearLayout) view.findViewById(R.id.normalmodechange);


        simplechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, simpleone.class);
                startActivity(intent);
                finish();
            }
        });
        normalchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeChoice(mContext, "No");
                id="0";
                selMenu="Normal";
                Intent intent = new Intent(mContext, normal.class);
                startActivity(intent);
                finish();

            }
        });
        dialog.show();
    }


    @Override
    public void onBackPressed() {
        Exit exit = new Exit();
        exit.exitDialog(mContext);
    }
}
