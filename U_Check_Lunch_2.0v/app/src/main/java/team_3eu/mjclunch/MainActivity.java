package team_3eu.mjclunch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

//11_15  16 16+
public class MainActivity extends AppCompatActivity {
    Thread w;
    boolean running = true;
    int count = 0;
    Intent intent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout fullscreen = (LinearLayout) findViewById(R.id.fullscreen);
        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                Intent intent = new Intent(MainActivity.this,nextpage.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onStart() {
        final TextView tap = (TextView) findViewById(R.id.tap);
        super.onStart();
        w = new Thread(new Runnable() {
            public void run() {
                while(count < 500) {
                    if(running == false) break;
                    try{
                        Thread.sleep(800);
                    }catch (InterruptedException e){
                    }
                    count++;
                    tap.post(new Runnable() {
                        @Override
                        public void run() {
                            if (count % 2 == 0) tap.setText("Tap to screen");
                            else tap.setText("  ");
                        }
                    });
                }
            }
        });
        running = true;
        w.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        running = false;
    }
}