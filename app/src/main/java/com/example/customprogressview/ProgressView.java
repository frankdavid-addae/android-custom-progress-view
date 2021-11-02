package com.example.customprogressview;

import android.content.Context;
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

    public ProgressView(@NonNull Context context) {
        super(context);
        init();
    }

    public ProgressView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.progress_view, this);
        progressBar = view.findViewById(R.id.progressBar);
        tvPercentage = view.findViewById(R.id.tvPercentage);
    }
}
