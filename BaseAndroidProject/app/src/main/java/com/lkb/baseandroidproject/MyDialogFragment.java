package com.lkb.baseandroidproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class MyDialogFragment extends DialogFragment {
    DialogClickListener listener = null;

    interface DialogClickListener {
        void onSuccess();

        void onFailure();
    }

    public void setOnDialogClickListener(@NotNull DialogClickListener listener) {
        this.listener = listener;
    }
    public void removeListener(){
        this.listener = null;
    }


    private final String LOG_TAG = MyDialogFragment.class.getSimpleName();

    // onCreate --> (onCreateDialog) --> onCreateView --> onActivityCreated
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(LOG_TAG, "onCreateView");

        View dialogView = inflater.inflate(R.layout.dialog_content, container, false);

        // "Got it" button
        Button buttonPos = (Button) dialogView.findViewById(R.id.pos_button);
        buttonPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showToast(getString(R.string.pos_button));
                listener.onSuccess();
                // Dismiss the DialogFragment (remove it from view)
                dismiss();
            }
        });

        // "Cancel" button
        Button buttonNeg = (Button) dialogView.findViewById(R.id.neg_button);
        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showToast(getString(R.string.neg_button));
                // If shown as dialog, cancel the dialog (cancel --> dismiss)
                if (getShowsDialog()) {
                    getDialog().cancel();
                    listener.onFailure();
                }
                // If shown as Fragment, dismiss the DialogFragment (remove it from view)
                else
                    dismiss();
            }
        });

        return dialogView;
    }

    // If shown as dialog, set the width of the dialog window
    // onCreateView --> onActivityCreated -->  onViewStateRestored --> onStart --> onResume
    @Override
    public void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "onResume");
        if (getShowsDialog()) {
            // Set the width of the dialog to the width of the screen in portrait mode
            DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
            int dialogWidth = Math.min(metrics.widthPixels, metrics.heightPixels);
            getDialog().getWindow().setLayout(dialogWidth, WRAP_CONTENT);
        }
    }

    private void showToast(String buttonName) {
        Toast.makeText(getActivity(), "Clicked on \"" + buttonName + "\"", Toast.LENGTH_SHORT).show();
    }

    // If dialog is cancelled: onCancel --> onDismiss
    @Override
    public void onCancel(DialogInterface dialog) {
    }

    // If dialog is cancelled: onCancel --> onDismiss
    // If dialog is dismissed: onDismiss
    @Override
    public void onDismiss(DialogInterface dialog) {
    }
}
