package team_3eu.mjclunch;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Mydialog extends Dialog {
    private TextView mContentView;
    private TextView mBlankView1, mBlankView2, mBlankView3;
    private ImageButton mRightImageButton;
    private ImageButton mLeftImageButton;
    private ImageView mDialogImg;
    private String mContent;
    private LinearLayout.LayoutParams params;

    private View.OnClickListener mRightClickListener;
    private View.OnClickListener mLeftClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.mydialog);

        mContentView = (TextView) findViewById(R.id.dialog_text);
        mRightImageButton = (ImageButton) findViewById(R.id.dialog_right_btn);
        mLeftImageButton = (ImageButton) findViewById(R.id.dialog_left_btn);
        mDialogImg = (ImageView) findViewById(R.id.dialog_img);
        mBlankView1 = (TextView) findViewById(R.id.blank2);
        mBlankView2 = (TextView) findViewById(R.id.blank3);
        mBlankView3 = (TextView) findViewById(R.id.blank4);
        LinearLayout blank = (LinearLayout) findViewById(R.id.blank_layout);
        LinearLayout btn_layout = (LinearLayout) findViewById(R.id.btn_layout);
        // 제목과 내용을 생성자에서 셋팅한다.
        mContentView.setText(mContent);

        // 클릭 이벤트 셋팅

        if (mLeftClickListener != null && mRightClickListener != null) {
            mLeftImageButton.setOnClickListener(mLeftClickListener);
            mRightImageButton.setOnClickListener(mRightClickListener);
            mRightImageButton.setImageResource(R.drawable.dialog_no);
            mLeftImageButton.setVisibility(View.VISIBLE);
            mDialogImg.setImageResource(R.drawable.dialog_2);
            params
                    = (LinearLayout.LayoutParams) mBlankView1.getLayoutParams();
            params.weight = 0.25f;
            mBlankView1.setLayoutParams(params); //다이얼로그 왼쪽 여백
            params
                    = (LinearLayout.LayoutParams) mBlankView2.getLayoutParams();
            params.weight = 0.12f;
            mBlankView2.setLayoutParams(params); //다이얼로그 Yes버튼 왼쪽 여백
            mBlankView3.setVisibility(View.GONE);

            params
                    = (LinearLayout.LayoutParams) blank.getLayoutParams();
            params.weight = 0.52f;
            blank.setLayoutParams(params);
            mContentView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 27.5f);

            params
                    = (LinearLayout.LayoutParams) btn_layout.getLayoutParams();
            params.weight = 0.2f;
            btn_layout.setLayoutParams(params);


        } else if (mLeftClickListener == null
                && mRightClickListener != null) {
            mRightImageButton.setImageResource(R.drawable.yes_1);
            mRightImageButton.setOnClickListener(mRightClickListener);
            mLeftImageButton.setVisibility(View.GONE);
            mBlankView3.setVisibility(View.VISIBLE);
            mDialogImg.setImageResource(R.drawable.dialog_1);
             params
                    = (LinearLayout.LayoutParams) mBlankView1.getLayoutParams();
            params.weight = 0.17f;
            mBlankView1.setLayoutParams(params); //다이얼로그 왼쪽 여백
            params
                    = (LinearLayout.LayoutParams) mBlankView2.getLayoutParams();
            params.weight = 0.6f;
            mBlankView2.setLayoutParams(params); //다이얼로그 Yes버튼 왼쪽 여백
            params
                    = (LinearLayout.LayoutParams) mBlankView3.getLayoutParams();
            params.weight = 0.2f;
            mBlankView3.setLayoutParams(params); //다이얼로그 Yes버튼 아래 여백

            params
                    = (LinearLayout.LayoutParams) blank.getLayoutParams();
            params.weight = 0.45f;
            blank.setLayoutParams(params);
            mContentView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 26.5f);

            params
                    = (LinearLayout.LayoutParams) btn_layout.getLayoutParams();
            params.weight = 0.15f;
            btn_layout.setLayoutParams(params);//버튼크기
//[출처] [안드로이드] 뷰의 가중치(layout_weight) 코드에서 동적으로 변경|작성자 자바킹
        } else {

        }
    }


    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public Mydialog(Context context, String content,
                    View.OnClickListener Listener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContent = content;
        this.mRightClickListener = Listener;
    }


    public Mydialog(Context context,
                    String content, View.OnClickListener leftListener,
                    View.OnClickListener rightListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContent = content;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;
    }

}