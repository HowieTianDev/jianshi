package com.howietian.jianshi.welcome;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.howietian.jianshi.R;
import com.howietian.jianshi.base.BaseActivity;
import com.howietian.jianshi.db.DatabaseHelper;
import com.howietian.jianshi.entities.Unit;
import com.howietian.jianshi.entities.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddNewUnitActivity extends BaseActivity {

    @Bind(R.id.tb_new_task)
    Toolbar tbNewTask;
    @Bind(R.id.et_unit_name)
    EditText etUnitName;
    @Bind(R.id.et_unit_group)
    EditText etUnitGroup;
    @Bind(R.id.et_unit_brief)
    EditText etUnitBrief;
    @Bind(R.id.et_unitp_name)
    EditText etUnitpName;
    @Bind(R.id.et_unit_position)
    EditText etUnitPosition;
    @Bind(R.id.et_unit_title)
    EditText etUnitTitle;
    @Bind(R.id.et_unit_edu)
    EditText etUnitEdu;
    @Bind(R.id.et_unit_phone)
    EditText etUnitPhone;

    String name,group,brief,pName,position,title,education,phone;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.activity_add_new_unit);
    }

    @Override
    public void init() {
        super.init();
        initListener();
    }

    public void initView() {

    }

    private void initListener() {
        setSupportActionBar(tbNewTask);
        tbNewTask.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_done:
                addNewTask();
                break;
        }
        return true;
    }
    private void addNewTask(){
        if(checkData()){
            final Unit unit = new Unit(name,group,brief,pName,position,title,education,phone);
            ContentValues cv = unit2ContentValues(unit);
            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase database = helper.getWritableDatabase();
            database.insert(DatabaseHelper.TABLE_UNIT,null,cv);
            showToast("添加成功！");
            jumpTo(NewUnitActivity.class,true);

        }
    }

    private boolean checkData(){
        name = etUnitName.getText().toString();
        group = etUnitGroup.getText().toString();
        brief = etUnitBrief.getText().toString();
        pName = etUnitpName.getText().toString();
        position = etUnitPosition.getText().toString();
        title = etUnitTitle.getText().toString();
        phone = etUnitPhone.getText().toString();
        education = etUnitEdu.getText().toString();
        if(TextUtils.isEmpty(name)){
            showToast("本单位名称不能为空");
            return false;
        }

        if(TextUtils.isEmpty(group)){
            showToast("本单位所属集团不能为空");
            return false;
        }

        if(TextUtils.isEmpty(brief)){
            showToast("系统功能简述不能为空");
            return false;
        }
        if(TextUtils.isEmpty(pName)){
            showToast("姓名不能为空");
            return false;
        }
        if(TextUtils.isEmpty(position)){
            showToast("职务不能为空");
            return false;
        }
        if(TextUtils.isEmpty(title)){
            showToast("职称不能为空");
            return false;
        }
        if(TextUtils.isEmpty(phone)){
            showToast("学历不能为空");
            return false;
        }
        if(TextUtils.isEmpty(education)){
            showToast("手机不能为空");
            return false;
        }

        return true;


    }


    // 将Unit对象转为一个contentvaluse对象
    private ContentValues unit2ContentValues(Unit unit) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", unit.getName());
        contentValues.put("_group", unit.getGroup());
        contentValues.put("brief", unit.getBrief());
        contentValues.put("pName", unit.getpName());
        contentValues.put("position", unit.getPosition());
        contentValues.put("title", unit.getTitle());
        contentValues.put("education",unit.getEducation());
        contentValues.put("phoneNum",unit.getPhoneNum());
        return contentValues;
    }


}
