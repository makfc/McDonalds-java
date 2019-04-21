package com.facebook.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import com.facebook.android.C1926R;

public class LikeButton extends Button {
    private boolean isLiked;

    public LikeButton(Context context, boolean isLiked) {
        super(context);
        this.isLiked = isLiked;
        initialize();
    }

    public void setLikeState(boolean isLiked) {
        if (isLiked != this.isLiked) {
            this.isLiked = isLiked;
            updateForLikeStatus();
        }
    }

    private void initialize() {
        setGravity(16);
        setTextColor(getResources().getColor(C1926R.color.com_facebook_likebutton_text_color));
        setTextSize(0, getResources().getDimension(C1926R.dimen.com_facebook_likebutton_text_size));
        setTypeface(Typeface.DEFAULT_BOLD);
        setCompoundDrawablePadding(getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_likebutton_compound_drawable_padding));
        setPadding(getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_likebutton_padding_left), getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_likebutton_padding_top), getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_likebutton_padding_right), getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_likebutton_padding_bottom));
        updateForLikeStatus();
    }

    private void updateForLikeStatus() {
        if (this.isLiked) {
            setBackgroundResource(C1926R.C1924drawable.com_facebook_button_like_selected);
            setCompoundDrawablesWithIntrinsicBounds(C1926R.C1924drawable.com_facebook_button_like_icon_selected, 0, 0, 0);
            setText(getResources().getString(C1926R.string.com_facebook_like_button_liked));
            return;
        }
        setBackgroundResource(C1926R.C1924drawable.com_facebook_button_like);
        setCompoundDrawablesWithIntrinsicBounds(C1926R.C1924drawable.com_facebook_button_like_icon, 0, 0, 0);
        setText(getResources().getString(C1926R.string.com_facebook_like_button_not_liked));
    }
}
