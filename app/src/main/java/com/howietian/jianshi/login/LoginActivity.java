package com.howietian.jianshi.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.db.DatabaseHelper;
import com.howietian.jianshi.welcome.NewUnitActivity;

public class LoginActivity extends AppCompatActivity {
  Button btn_login;
  Button btn_register;
  EditText et_account;
  EditText et_pwd;
  CheckBox cb_remember;

  String account, pwd;
  Boolean isRemember = false;

  private static final String USERNAME = "user";
  private static final String PWD = "pwd";
  private static final String IS_REMEMBER = "remember";
  private static final String SPRE_NAME = "userInfo";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    initViews();
    initListeners();
  }

  private void initViews() {
    btn_login = (Button) findViewById(R.id.btn_login);
    btn_register = (Button) findViewById(R.id.btn_register);
    et_account = (EditText) findViewById(R.id.et_account);
    et_pwd = (EditText) findViewById(R.id.et_pwd);
    cb_remember = (CheckBox) findViewById(R.id.cb_remember);
    setData();
  }

  private void initListeners() {
    // 登录跳转
    btn_login.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        login();
      }
    });

    // 注册跳转
    btn_register.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
      }
    });
  }

  //利用sharedpreference保存数据
  private void saveData() {
    SharedPreferences spre = getSharedPreferences(SPRE_NAME, MODE_PRIVATE);
    SharedPreferences.Editor editor = spre.edit();
    editor.putString(USERNAME, account);
    editor.putString(PWD, pwd);
    isRemember = cb_remember.isChecked();
    editor.putBoolean(IS_REMEMBER, isRemember);
    editor.commit();
  }

  private void setData() {
    SharedPreferences spre = getSharedPreferences(SPRE_NAME, MODE_PRIVATE);
    String userName = spre.getString(USERNAME, null);
    String pwd = spre.getString(PWD, null);
    Boolean remember = spre.getBoolean(IS_REMEMBER, false);
    if (remember) {
      cb_remember.setChecked(true);
      et_pwd.setText(pwd);
      et_account.setText(userName);
    } else {
      et_account.setText(userName);
      cb_remember.setChecked(false);
    }
    //设置光标位置
    et_account.setSelection(et_account.getText().length());
  }

  private void login() {
    if (checkData()) {
      saveData();
      Intent intent = new Intent(this, NewUnitActivity.class);
      startActivity(intent);
      finish();
    }
  }

  //检查输入的合法性
  private boolean checkData() {
    account = et_account.getText().toString();
    pwd = et_pwd.getText().toString();
    if (TextUtils.isEmpty(account)) {
      showToast("账号不能为空");
      return false;
    } else if (TextUtils.isEmpty(pwd)) {
      showToast("密码不能为空");
      return false;
    } else if (!isTrue(account, pwd)) {
      showToast("用户名或密码错误");
      return false;
    }

    return true;
  }

  //判断用户名和密码的正确性
  private boolean isTrue(String account, String pwd) {
    boolean flag1;//判断身份证号码+密码
    boolean flag2;//判断手机号码+密码
    boolean result;
    DatabaseHelper helper = new DatabaseHelper(this);
    SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
    String queryIDandPwd =
        "select * from " + DatabaseHelper.TABLE_USER + " where id_num = ? and pwd = ?";
    Cursor cursor1 = sqLiteDatabase.rawQuery(queryIDandPwd, new String[] { account, pwd });
    if (cursor1.getCount() > 0) {
      flag1 = true;
    } else {
      flag1 = false;
    }
    String queryPhoneandPwd =
        "select * from " + DatabaseHelper.TABLE_USER + " where phone = ? and pwd = ?";
    Cursor cursor2 = sqLiteDatabase.rawQuery(queryPhoneandPwd, new String[] { account, pwd });
    if (cursor2.getCount() > 0) {
      flag2 = true;
    } else {
      flag2 = false;
    }

    result = flag1 | flag2;
    return result;
  }

  private void showToast(String s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }
}
