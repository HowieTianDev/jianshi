package com.howietian.jianshi.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.db.DatabaseHelper;
import com.howietian.jianshi.entities.User;
import com.howietian.jianshi.welcome.NewUnitActivity;

public class RegisterActivity extends AppCompatActivity {
  Button btn_register;
  EditText et_name;
  EditText et_id_num;
  EditText et_phone;
  EditText et_pwd;
  EditText et_pwd_again;
  EditText et_email;
  EditText et_workspace;
  String name, id_num, phone, pwd, pwd_again, email, workspace;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    initViews();
    initListeners();
  }

  private void initViews() {
    btn_register = (Button) findViewById(R.id.btn_register);
    et_name = (EditText) findViewById(R.id.et_name);
    et_id_num = (EditText) findViewById(R.id.et_id_num);
    et_phone = (EditText) findViewById(R.id.et_phone_num);
    et_pwd = (EditText) findViewById(R.id.et_pwd);
    et_pwd_again = (EditText) findViewById(R.id.et_pwd_again);
    et_email = (EditText) findViewById(R.id.et_email);
    et_workspace = (EditText) findViewById(R.id.et_workspace);
  }

  private void initListeners(){
    btn_register.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        register();
      }
    });
  }

  // 检查数据的合法性
  private boolean checkData() {
    name = et_name.getText().toString();
    id_num = et_id_num.getText().toString();
    phone = et_phone.getText().toString();
    pwd = et_pwd.getText().toString();
    pwd_again = et_pwd_again.getText().toString();
    email = et_email.getText().toString();
    workspace = et_workspace.getText().toString();
    if (TextUtils.isEmpty(name)) {
      showToast("姓名不能为空");
      return false;
    } else if (TextUtils.isEmpty(id_num)) {
      showToast("身份证号码不能为空");
      return false;
    } else if (TextUtils.isEmpty(phone)) {
      showToast("手机号码不能为空");
      return false;
    } else if (phone.length() != 11) {
      showToast("手机号码长度不合法");
      return false;
    } else if (TextUtils.isEmpty(pwd) | TextUtils.isEmpty(pwd_again)) {
      showToast("密码不能为空");
      return false;
    } else if(pwd.length()<6){
      showToast("密码长度至少为6位");
      return false;
    }else if (!pwd.equals(pwd_again)) {
      showToast("两次输入密码不一致，请重新输入");
      return false;
    } else if (TextUtils.isEmpty(email)) {
      showToast("电子邮箱不能为空");
      return false;
    } else if (TextUtils.isEmpty(workspace)) {
      showToast("工作单位不能为空");
      return false;
    }
    return true;
  }

  // 将user对象转为一个contentvaluse对象
  private ContentValues user2ContentValues(User user) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("name", user.getName());
    contentValues.put("id_num", user.getIdNum());
    contentValues.put("phone", user.getPhoneNum());
    contentValues.put("pwd", user.getPwd());
    contentValues.put("email", user.getEmail());
    contentValues.put("workspace", user.getWordplace());
    return contentValues;
  }

  private void register(){

    if(checkData()&&isExist(id_num,phone,email)){
      final User  user = new User(name,id_num,phone,pwd,email,workspace);
      Log.e("注册","HHH");
      DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
      SQLiteDatabase database = databaseHelper.getWritableDatabase();
      ContentValues contentValues = user2ContentValues(user);
      database.insert(DatabaseHelper.TABLE_USER,null,contentValues);
      Intent intent = new Intent(this, NewUnitActivity.class);
      startActivity(intent);
      finish();
      showToast("注册成功");
    }

  }
// 检查用户是否已经存在
  private boolean isExist(String id,String phone,String email){
    DatabaseHelper helper = new DatabaseHelper(this);
    SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
    String queryID = "select * from "+DatabaseHelper.TABLE_USER+" where id_num = ?";
    String queryPhone = "select * from "+DatabaseHelper.TABLE_USER+" where phone = ?";
    String queryEmail = "select * from "+DatabaseHelper.TABLE_USER+" where email = ?";
    Cursor cursor1 = sqLiteDatabase.rawQuery(queryID,new String[]{id});
    if(cursor1.getCount()>0){
      cursor1.close();
      showToast("身份证号码已存在");
      return false;
    }
    Cursor cursor2 = sqLiteDatabase.rawQuery(queryPhone,new String[]{phone});
    if(cursor2.getCount()>0){
      cursor2.close();
      showToast("手机号码已存在");
      return false;
    }
    Cursor cursor3 = sqLiteDatabase.rawQuery(queryEmail,new String[]{email});
    if(cursor3.getCount()>0){
      cursor3.close();
      showToast("email已存在");
      return false;
    }

    return true;


  }
  // 封装一个 Toast
  private void showToast(String s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }
}
