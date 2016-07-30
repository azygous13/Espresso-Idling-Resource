package com.bananacoding.espressoidlingresource;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button toggleButton;
    private TextView elapsedTimeTextView;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = (Button) findViewById(R.id.toggle_button);
        elapsedTimeTextView = (TextView) findViewById(R.id.elapsed_Time);

        valueAnimator = ValueAnimator.ofFloat(0f, 1000f);
        valueAnimator.setDuration(DateUtils.SECOND_IN_MILLIS * 1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                elapsedTimeTextView.setText(getString(R.string.elapsed_time, (float) valueAnimator.getAnimatedValue()));
            }
        });
    }

    public void toggle(View v) {
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
            toggleButton.setText(R.string.start);
        } else {
            valueAnimator.start();
            toggleButton.setText(R.string.stop);
        }
    }
}
