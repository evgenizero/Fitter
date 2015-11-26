package com.fitter.views.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fitter.R;

/**
 * Created by evgeniy.yanev on 11/23/15.
 */
public class RegistrationEditText extends LinearLayout {
    public RegistrationEditText(Context context) {
        super(context);
        initView(null);
    }

    public RegistrationEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public RegistrationEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.registration_edittext, this);

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.RegistrationEditText,
                    0, 0);

            try {
                EditText et = (EditText) v.findViewById(R.id.registration_edittext_field);
                et.setText(a.getString(R.styleable.RegistrationEditText_text));
                et.setHint(a.getString(R.styleable.RegistrationEditText_hint));
            } finally {
                a.recycle();
            }
        }
    }


}
