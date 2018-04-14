package com.ortho.medicare.medicareortho.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.ortho.medicare.medicareortho.R;


/**
 * {@link CustomButton} : this class is responsible to create custom Button with custom font styles with enum
 *
 * @author Harsh Patel
 * @version : 0.0.1
 * @Date : 21/09/2017
 */
public class CustomButton extends android.support.v7.widget.AppCompatButton {

    public CustomButton(Context context) {
        super(context);
        setTypeface(SansRegular.getInstance(context).getTypeFace());
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomButton, 0, 0);

            CustomEnum.CustomFontType customEnumValue = CustomEnum.CustomFontType.
                    fromId(a.getInt(R.styleable.CustomButton_font_type, 2));
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

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomButton, defStyleAttr, 0);

            CustomEnum.CustomFontType customEnumValue = CustomEnum.CustomFontType.
                    fromId(a.getInt(R.styleable.CustomButton_font_type, 2));
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
}


