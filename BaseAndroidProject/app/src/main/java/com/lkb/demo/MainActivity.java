package com.lkb.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity implements ViewDialog.OnCustomDialogClickListener {
    private Button mUploadButton;
    private WeakReference<MainActivity> context;
    private Bitmap mSelectedImage;
    private ImageView mImageView;
    CardView mImageContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = new WeakReference<>(this);
        mUploadButton = findViewById(R.id.btnPicUpload);
        mImageView = findViewById(R.id.imageView);
        mImageContainer = findViewById(R.id.imageContainer);

        mUploadButton.setOnClickListener(v -> {
            mUploadButton.setVisibility(View.GONE);
            ViewDialog alert = new ViewDialog();
            alert.showDialog(context);
        });

    }

    private void launchCamera() {
        // check for camera permission
        checkPermission();
    }

    private void launchFileManager() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 34);
    }

    @Override
    public void onTakePhotoClicked() {
        launchCamera();
    }

    @Override
    public void onChoosePhotoClicked() {
        launchFileManager();
    }

    @Override
    public void onCancelDialog() {
        mUploadButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 33:
                if (resultCode == RESULT_OK) {
                    mImageContainer.setVisibility(View.VISIBLE);
                    mSelectedImage = (Bitmap) data.getExtras().get("data");
                    Glide.with(context.get())
                            .load(mSelectedImage) // Uri of the picture
                            //.apply(new RequestOptions().override(80, 80))
                            .into(mImageView);
                    mImageView.setImageBitmap(mSelectedImage);
                }
                break;
            case 34:
                if (resultCode == RESULT_OK) {
                    mImageContainer.setVisibility(View.VISIBLE);
                   Uri uri = data.getData();
                    try {
                        String path = FileUtil.getPath(this,uri);
                        Glide.with(context.get())
                                .load(uri) // Uri of the picture
                                //.apply(new RequestOptions().override(80, 80))
                                .into(mImageView);
                        //mImageView.setImageBitmap(mSelectedImage);
                        Log.d("path", path);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    void checkPermission() {
        if (ContextCompat.checkSelfPermission(context.get(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

            ActivityCompat.requestPermissions(context.get(),
                    new String[]{Manifest.permission.CAMERA},
                    36);
        } else {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 33);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 36: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, 33);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
