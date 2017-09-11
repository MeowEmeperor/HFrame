package com.zph.plot.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuhao.hframe.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by apple on 2017/9/5.
 */

public class UpdataImg extends AppCompatActivity implements View.OnClickListener{

    private TextView title;
    private ImageView selectImg;
    private ImageView showImg;

    private LinearLayout line_item;
    private LinearLayout select_camera;
    private LinearLayout select_photo;
    private LinearLayout save_img;


    //调取拍照
    private Bitmap myBitmap;
    private byte[] mContent;
    private static final int SELECT_PHOTO = 0;
    private static final int SELECT_CAMERA = 1;
    private static final String IMAGE_CAPTURE = "android.media.action.IMAGE_CAPTURE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plot_updata_user_img);

        initView();
        initCreat();
        initContent();
    }

    private void initContent() {

        title.setText("个人头像");
        selectImg.setImageResource(R.drawable.edit);
    }

    private void initCreat() {
        title.setOnClickListener(this);
        selectImg.setOnClickListener(this);
        select_camera.setOnClickListener(this);
        select_photo.setOnClickListener(this);
        save_img.setOnClickListener(this);
        showImg.setOnClickListener(this);

    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_log);
        selectImg = (ImageView) findViewById(R.id.right_img);
        showImg = (ImageView) findViewById(R.id.showImg);
        line_item = (LinearLayout) findViewById(R.id.line_item);
        select_camera = (LinearLayout) findViewById(R.id.select_camera);
        select_photo = (LinearLayout) findViewById(R.id.select_photo);
        save_img = (LinearLayout) findViewById(R.id.save_img);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.right_img:
                showSelectItem();
                break;
            case R.id.select_camera:
                selectCamera();
                break;
            case R.id.select_photo:
                selectPhoto();
                break;
            case R.id.save_img:
                saveImg();
                break;

        }
    }

    private void saveImg() {

    }

    private void selectPhoto() {

        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
        getImage.addCategory(Intent.CATEGORY_OPENABLE);
        getImage.setType("image/*");
        startActivityForResult(getImage, SELECT_PHOTO);

    }

    private void selectCamera() {

        Intent getImageByCamera  = new Intent(IMAGE_CAPTURE);
        startActivityForResult(getImageByCamera, SELECT_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        ContentResolver contentResolver  = getContentResolver();
        /**
         * 因为两种方式都用到了startActivityForResult方法，这个方法执行完后都会执行onActivityResult方法，
         * 所以为了区别到底选择了那个方式获取图片要进行判断，这里的requestCode跟startActivityForResult里面第二个参数对应
         */

        if(requestCode== 0){


            //方式二
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                showImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }else if(requestCode == 1){
            try {
                Bundle extras = data.getExtras();
                myBitmap = (Bitmap) extras.get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                myBitmap.compress(Bitmap.CompressFormat.JPEG , 100, baos);
                mContent=baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }
            showImg.setImageBitmap(myBitmap);
        }

    }

    private void showSelectItem() {
        if (line_item.getVisibility()==View.GONE)
        {
            line_item.setVisibility(View.VISIBLE);
        }else
            line_item.setVisibility(View.GONE);
    }

}
