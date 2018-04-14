package com.ortho.medicare.medicareortho.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;

import com.ortho.medicare.medicareortho.R;

/**
 * {@link CustomEditText} : this class is responsible to create custom EditText with custom font styles with enum
 *
 * @author Harsh Patel
 * @version : 0.0.1
 * @Date : 21/09/2017
 */
public class CustomEditText extends TextInputEditText {

    private static final String TAG = "EditText";

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText, defStyle, 0);

            CustomEnum.CustomFontType customEnumValue = CustomEnum.CustomFontType.
                    fromId(a.getInt(R.styleable.CustomEditText_font_type, 2));
            a.recycle();
            switch (customEnumValue) {
                case BOLD:
                    setTypeface(SansBold.getInstance(context).getTypeFace());
                    break;

                case REGULAR:
                    setTypeface(SansRegular.getInstance(context).getTypeFace());
                    break;

                default:
                    setTypeface(SansRegular.getInstance(context).getTypeFace());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText, 0, 0);

            CustomEnum.CustomFontType customEnumValue = CustomEnum.CustomFontType.
                    fromId(a.getInt(R.styleable.CustomEditText_font_type, 2));
            a.recycle();
            switch (customEnumValue) {
                case BOLD:
                    setTypeface(SansBold.getInstance(context).getTypeFace());
                    break;

                case REGULAR:
                    setTypeface(SansRegular.getInstance(context).getTypeFace());
                    break;

                default:
                    setTypeface(SansRegular.getInstance(context).getTypeFace());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomEditText(Context context) {
        super(context);
        setTypeface(SansRegular.getInstance(context).getTypeFace());
    }

    public boolean setCustomFont(Context context, String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(context.getAssets(), asset);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        setTypeface(tf);
        return true;
    }
}
