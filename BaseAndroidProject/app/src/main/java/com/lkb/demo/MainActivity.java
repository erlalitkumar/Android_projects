package com.lkb.demo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        constraintLayout.setId(R.id.root_container);
        setContentView(constraintLayout);
        TextView contactUs = new TextView(this);
        contactUs.setText(R.string.contactus);
        contactUs.setTextSize(25);
        contactUs.setId(R.id.contactUs);

        ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        contactUs.setLayoutParams(clpcontactUs);

        constraintLayout.addView(contactUs);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(contactUs.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 18);
        constraintSet.connect(contactUs.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 18);

        constraintSet.connect(contactUs.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 18);
        constraintSet.connect(contactUs.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 18);

        constraintSet.applyTo(constraintLayout);
    }
}
