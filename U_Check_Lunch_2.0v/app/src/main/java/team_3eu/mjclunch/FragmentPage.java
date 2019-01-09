package team_3eu.mjclunch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static team_3eu.mjclunch.ChangeFragment.*;
import static team_3eu.mjclunch.Choice.writeChoice;
import static team_3eu.mjclunch.var_Naver_Db.adapter;
import static team_3eu.mjclunch.var_Naver_Db.selMenu;


/**
 * Created by LeeMJ on 2018. 11. 15..
 */

public class FragmentPage extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout simplechange;
    private LinearLayout normalchange;
    private FloatingActionButton fab;
    String menu_list[] = {"전체", "한식", "중식", "일식", "양식", "기타"};
    Context context;
    int i =1, j =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_page);

        context = this;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_layout);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back_button);
        TextView textView = (TextView) findViewById(R.id.mytext);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.rightMargin = 100;
        textView.setLayoutParams(layoutParams);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.all));//.setText("전체"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.kor));//.setText("한식"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.cha));//.setText("중식"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.jpn));//.setText("일식"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.fry));//.setText("양식"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.etc));//.setText("기타"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        final TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        final Intent intent = new Intent(getIntent());

        viewPager.setCurrentItem(intent.getIntExtra("value", 0));


        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selMenu = menu_list[tab.getPosition()];
                onDB(menu_list[tab.getPosition()]);
                viewPager.setCurrentItem(tab.getPosition());
                pagerAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                adapter.notifyDataSetChanged();
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
                writeChoice(context, "Yes");
                selMenu="Simple";
                Intent intent = new Intent(context, simpleone.class);
                startActivity(intent);
                finish();
            }
        });
        normalchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, normal.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
    }
}
//출처: http://swalloow.tistory.com/80 [MyCloud]
