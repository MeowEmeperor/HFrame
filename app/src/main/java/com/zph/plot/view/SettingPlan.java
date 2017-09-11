package com.zph.plot.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhuhao.hframe.R;
import com.zph.plot.base.CommonAdapter;
import com.zph.plot.base.ViewHolder;
import com.zph.plot.model.PlanMSG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/9/6.
 */

public class SettingPlan extends AppCompatActivity implements View.OnClickListener{

    //线路选择listview
    ListView plan_list;
    TextView plan_name;
    ImageView plan_next;
    List<PlanMSG> list;
    PlanMSG planMSG;
    CommonAdapter<PlanMSG> planMSGCommonAdapter;

    //添加方案
    ImageView add_plan_img;
    LinearLayout plan_item_line;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plot_activity_setting_plan);

        initView();
        initCreat();
        initAdapter();
    }

    private void initCreat() {

        add_plan_img.setOnClickListener(this);
    }


    //线路选择的listview
    private void initAdapter() {

        planMSG = new PlanMSG();
        list = new ArrayList<>();
        for (int i = 0;i < 3;i++)
        {
            planMSG.setImg(R.drawable.ic_btn_next);
            planMSG.setName("路线"+(char)('A'+i));
        }
        list.add(planMSG);

        planMSGCommonAdapter = new CommonAdapter<PlanMSG>(this,list,R.layout.plot_plan_listview_item) {
            @Override
            public void convert(ViewHolder viewhelper, PlanMSG item) {

                plan_name = viewhelper.getView(R.id.plan_name);
                plan_next = viewhelper.getView(R.id.plan_next);

                plan_name.setText(item.getName());

                Glide.with(getApplicationContext())
                        .load(item.getImg())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(plan_next);

            }
        };
        plan_list.setAdapter(planMSGCommonAdapter);
    }

    private void initView() {

        plan_list = (ListView) findViewById(R.id.plan_list);
        add_plan_img = (ImageView) findViewById(R.id.add_plan_img);
        plan_item_line = (LinearLayout) findViewById(R.id.plan_item_line);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.add_plan_img:
                showPlanItem();
        }
    }

    private void showPlanItem() {

        if(plan_item_line.getVisibility() == View.VISIBLE)
        {
            plan_item_line.setVisibility(View.GONE);
        }else
            plan_item_line.setVisibility(View.VISIBLE);
    }
}
