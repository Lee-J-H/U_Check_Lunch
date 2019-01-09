package team_3eu.mjclunch;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class jooatext extends AppCompatTextView {

    public jooatext(Context context) {
        super(context, null);
        applyTypeface(context);
    }


    public jooatext(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.textViewStyle);
        applyTypeface(context);
    }

    public jooatext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyTypeface(context);
    }

    private void applyTypeface(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "textstyle_jooa.ttf");
        setTypeface(typeface);
    }
}
