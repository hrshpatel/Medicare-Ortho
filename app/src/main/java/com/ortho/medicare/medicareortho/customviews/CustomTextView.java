package com.ortho.medicare.medicareortho.customviews;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.ortho.medicare.medicareortho.R;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "CustomTextView";
    private Typeface tf = null;

    /**
     * @param context:This is an abstract class whose implementation is provided by Android Operating System.
     * @param attrs:A      collection of attributes, as found associated with a tag in an XML document.
     * @param defStyle:
     */
    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        try {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CustomTextView, defStyle, 0);

            CustomEnum.CustomFontType customEnumValue = CustomEnum.CustomFontType.
                    fromId(a.getInt(R.styleable.CustomTextView_font_type, 2));
            a.recycle();
            switch (customEnumValue) {
                case BOLD:
                    setTypeface(SansBold.getInstance(context).getTypeFace());
                    break;

                case REGULAR:
                    setTypeface(SansRegular.getInstance(context).getTypeFace());
                    break;

                case BLACK:
                    setTypeface(SansRegular.getInstance(context).getTypeFace());
                    break;

                case SEMI_BOLD:
                    setTypeface(SansSemiBold.getInstance(context).getTypeFace());
                    break;

                default:
                    setTypeface(SansRegular.getInstance(context).getTypeFace());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

}