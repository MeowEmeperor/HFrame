package com.zph.plot.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhuhao.hframe.R;


/**
 * Created by apple on 2017/9/5.
 */
public class SendPlanActivity extends AppCompatActivity implements View.OnClickListener{


    TextView title;
    Spinner nature_spinner;
    ArrayAdapter<CharSequence> spinnerAdapter;

    //分组设置
    RelativeLayout setting_plan;

    //导入文件
    Button select_excel;
    TextView excel_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plot_activity_send_plan);

        initView();
        initCreat();
        spinnerAdapter();
        initContent();

    }

    private void initCreat() {

        setting_plan.setOnClickListener(this);
        select_excel.setOnClickListener(this);
    }

    private void initContent() {

        title.setText("活动信息设定");
    }
    private void spinnerAdapter() {

        spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.nature,R.layout.plot_spinner_item);
        nature_spinner.setAdapter(spinnerAdapter);
    }

    private void initView() {

        title = (TextView) findViewById(R.id.title_log);
        nature_spinner = (Spinner) findViewById(R.id.nature_spinner);
        setting_plan = (RelativeLayout) findViewById(R.id.setting_plan);

        select_excel = (Button) findViewById(R.id.select_excel);
        excel_name = (TextView) findViewById(R.id.excel_name);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.setting_plan:
                Intent intent = new Intent();
                intent.setClass(this,SettingPlan.class);
                startActivity(intent);
                break;
            case R.id.select_excel:
                selectExcel();
        }
    }

    private void selectExcel() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                System.out.println("文件路径："+uri.getPath().toString());
                //Toast.makeText(this, "文件路径："+uri.getPath().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
