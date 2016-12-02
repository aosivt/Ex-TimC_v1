package tc.ex_timc_v1.dev_cl.Singleton;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by au.oschepkov on 13.03.2015.
 */
public class Customization_Button extends Button {

    public Customization_Button(Context context) {
        super(context);
    }

    public Customization_Button(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Customization_Button(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, w, oldw, oldh);
    }

}