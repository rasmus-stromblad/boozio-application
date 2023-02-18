package com.example.boozio;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecorator extends RecyclerView.ItemDecoration {
    private final int verticalSpaceHeight;
    private final int horizontalSpaceWidth;

    public DividerItemDecorator(int verticalSpace, int horizontalSpaceWidth) {
        this.verticalSpaceHeight = verticalSpace;
        this.horizontalSpaceWidth = horizontalSpaceWidth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
        outRect.left = horizontalSpaceWidth;
        outRect.right = horizontalSpaceWidth;
    }

}
