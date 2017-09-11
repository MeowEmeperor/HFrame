package com.zph.plot.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.zhuhao.hframe.R;


/**
 * Created by apple on 2017/9/5.
 */

public class MSGActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plot_activity_admin_msg);

        initView();
        initCreat();
    }

    private void initCreat() {
        relativeLayout.setOnClickListener(this);
    }

    private void initView() {
        relativeLayout = (RelativeLayout) findViewById(R.id.relative1);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.relative1:
                Intent intent = new Intent();
                intent.setClass(this,UpdataImg.class);
                startActivity(intent);
        }

    }
}
