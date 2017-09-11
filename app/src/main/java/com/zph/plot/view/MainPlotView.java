package com.zph.plot.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;
import com.zhuhao.hframe.R;

import java.util.ArrayList;
import java.util.List;

public class MainPlotView extends AppCompatActivity implements View.OnClickListener{



    //菜单设计
    LinearLayout line_menu;
    ImageView img_menu;
    TopRightMenu mTopRightMenu;

    //发起活动和活动管理
    Button send_btn;
    Button manager_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.plot_activity_main_admin);

        initView();
        initCreat();

    }


    private void initView() {
        line_menu = (LinearLayout) findViewById(R.id.line_menu);
        img_menu = (ImageView) findViewById(R.id.img_menu);
        send_btn = (Button) findViewById(R.id.send_btn);
        manager_btn = (Button) findViewById(R.id.manager_btn);
    }

    private void initCreat() {
        line_menu.setOnClickListener(this);
        send_btn.setOnClickListener(this);
        manager_btn.setOnClickListener(this);
    }
    private void CreatMenu() {
        mTopRightMenu = new TopRightMenu(MainPlotView.this);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem( "修改用户信息"));
        menuItems.add(new MenuItem( "退出登录"));
        mTopRightMenu
                .setHeight(250)
                .showIcon(false)
                .addMenuList(menuItems)
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        //菜单项操作
                        if(position == 0)
                        {
                            sendMSGActivity();
                        }
                     //   Toast.makeText(MainPlotView.this, "点击菜单:" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .showAsDropDown(img_menu,0,0);
    }

    private void sendMSGActivity() {

        Intent intent = new Intent();
        intent.setClass(MainPlotView.this, MSGActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.line_menu:
                CreatMenu();
                break;
            case R.id.send_btn:
                Intent intent = new Intent();
                intent.setClass(this, SendPlanActivity.class);
                startActivity(intent);
                break;
        }
    }
}
