package com.example.customprogressview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProgressView extends FrameLayout {
    ProgressBar progressBar;
    TextView tvPercentage;
    LayerDrawable layerDrawable;

    public ProgressView(@NonNull Context context) {
        super(context);
        init();
    }

    public ProgressView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);

        int progress = typedArray.getInteger(R.styleable.ProgressView_progressAndText, 0);
        setProgressAndPercentage(String.valueOf(progress));
        int color = typedArray.getColor(R.styleable.ProgressView_progressBaseColor, getResources().getColor(R.color.black));
        setProgressBaseColor(color);
        int insideColor = typedArray.getColor(R.styleable.ProgressView_insideColor, getResources().getColor(R.color.black));
        setInsideColor(insideColor);
        int progressColor = typedArray.getColor(R.styleable.ProgressView_progressColor, getResources().getColor(R.color.black));
        setProgressColor(progressColor);
    }

    public ProgressView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.progress_view, this);
        progressBar = view.findViewById(R.id.progressBar);
        tvPercentage = view.findViewById(R.id.tvPercentage);
        layerDrawable = (LayerDrawable) progressBar.getProgressDrawable();
    }

    public void setPercentage(String percentage) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, Integer.parseInt(percentage));
        valueAnimator.setDuration(1700);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tvPercentage.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();
    }

    public void setProgress(int progress) {
        ObjectAnimator.ofInt(progressBar, "progress", progress)
        .setDuration(1700)
        .start();
    }

    public void setProgressAndPercentage(String progress) {
        setPercentage(progress);
        setProgress(Integer.parseInt(progress));
    }

    public void setProgressBaseColor(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.progress_base_color);
        gradientDrawable.setColor(color);
    }

    public void setInsideColor(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.inside_color);
        gradientDrawable.setColor(color);
    }

    public void setProgressColor(int color) {
        RotateDrawable rotateDrawable = (RotateDrawable) layerDrawable.findDrawableByLayerId(R.id.progress_color);
        rotateDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
