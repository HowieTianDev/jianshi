package com.howietian.jianshi.other;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.Other;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class OServerFragment extends Fragment {

  @Bind(R.id.sp_hardware) AppCompatSpinner spHardware;
  @Bind(R.id.et_hard_brand) EditText etHardBrand;
  @Bind(R.id.tv_hard_time) TextView tvHardTime;
  @Bind(R.id.et_hard_version) EditText etHardVersion;
  @Bind(R.id.rg_hard_where) RadioGroup rgHardWhere;
  @Bind(R.id.btn_hard_time) Button btnHardTime;
  @Bind(R.id.sp_os_company) AppCompatSpinner spOsCompany;
  @Bind(R.id.rg_os_type) RadioGroup rgOsType;
  @Bind(R.id.et_os_brand) EditText etOsBrand;
  @Bind(R.id.et_os_version) EditText etOsVersion;
  @Bind(R.id.rg_os_versionM) RadioGroup rgOsVersionM;
  @Bind(R.id.tv_os_time) TextView tvOsTime;
  @Bind(R.id.btn_os_time) Button btnOsTime;
  @Bind(R.id.rg_os_upType) RadioGroup rgOsUpType;
  @Bind(R.id.et_up_brief) EditText etUpBrief;

  @Bind(R.id.sp_soft_company) AppCompatSpinner spSoftCompany;
  @Bind(R.id.et_soft_brand) EditText etSoftBrand;
  @Bind(R.id.et_soft_version) EditText etSoftVersion;
  @Bind(R.id.rg_s_border) RadioGroup rgSBorder;
  @Bind(R.id.rg_s_isTrue) RadioGroup rgSIsTrue;
  @Bind(R.id.tv_soft_time) TextView tvSoftTime;
  @Bind(R.id.btn_soft_time) Button btnSoftTime;
  @Bind(R.id.rg_s_upType) RadioGroup rgSoftUpType;
  @Bind(R.id.et_soft_upBrief) EditText etSoftUpBrief;

  String[] osType = { Constant.WINDOWS, Constant.LINUX, Constant.UNIX, Constant.OTHER };
  String[] isGenuine = { Constant.GENUINE, Constant.UN_GENUINE };
  String[] upType = {
      Constant.UP_WIRELESS, Constant.UP_DIAL, Constant.UP_SITE, Constant.UP_SELF, Constant.UP_OTHER
  };
  String[] isBorder = { Constant.INTERNAL, Constant.BORDER };

  String[] hardWareNames;
  String[] osNames;
  String[] softNames;
  private String serverInfo = null;

  public void setServerInfo(String serverInfo){
    this.serverInfo = serverInfo;
  }

  int hardWareName, osName, softName;

  public OServerFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    serverInfo = ((MainActivity)context).getOtherInfo(MainActivity.O_SERVER);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View mainView = inflater.inflate(R.layout.fragment_oserver, container, false);
    ButterKnife.bind(this, mainView);
    initDatas();
    initListeners();
    setData();
    return mainView;
  }

  @Override
  public void onResume() {
    super.onResume();
    setData();
  }

  public String getData() {
    Other other = new Other();
    Other.Server server = other.new Server();
    server.setSe_h_company(hardWareName);
    server.setSe_h_brand(etHardBrand.getText().toString());
    server.setSe_h_version(etHardVersion.getText().toString());
    server.setSe_h_buyTime(tvHardTime.getText().toString());
    server.setSe_h_isBorder(getGroupData(rgHardWhere));

    server.setSe_os_type(getGroupData(rgOsType));
    server.setSe_os_brand(etOsVersion.getText().toString());
    server.setSe_os_isTrue(getGroupData(rgOsVersionM));
    server.setSe_os_upTime(tvOsTime.getText().toString());
    server.setSe_os_upType(getGroupData(rgOsUpType));
    server.setSe_os_upBrief(etUpBrief.getText().toString());

    server.setSe_s_company(softName);
    server.setSe_s_brand(etSoftBrand.getText().toString());
    server.setSe_s_version(etSoftVersion.getText().toString());
    server.setSe_s_isBorder(getGroupData(rgSBorder));
    server.setSe_s_isTrue(getGroupData(rgSIsTrue));
    server.setSe_s_upTime(tvSoftTime.getText().toString());
    server.setSe_s_upType(getGroupData(rgSoftUpType));
    server.setSe_s_upBrief(etSoftUpBrief.getText().toString());

    String serverInfo = new Gson().toJson(server, Other.Server.class);
    return serverInfo;
  }

  private void setData(){
    if(serverInfo!=null){
      Other.Server server = new Gson().fromJson(serverInfo,Other.Server.class);
      spHardware.setSelection(server.getSe_h_company());
      etHardBrand.setText(server.getSe_h_brand());
      etHardVersion.setText(server.getSe_h_version());
      setGroupData(rgHardWhere, server.getSe_h_isBorder());
      tvHardTime.setText(server.getSe_h_buyTime());

      setGroupData(rgOsType, server.getSe_os_type());
      etOsVersion.setText(server.getSe_os_brand());
      setGroupData(rgOsVersionM, server.getSe_os_isTrue());
      tvOsTime.setText(server.getSe_os_upTime());
      setGroupData(rgOsUpType, server.getSe_os_upType());
      etUpBrief.setText(server.getSe_os_upBrief());

      spSoftCompany.setSelection(server.getSe_s_company());
      etSoftBrand.setText(server.getSe_s_brand());
      etSoftVersion.setText(server.getSe_s_version());
      setGroupData(rgSBorder,server.getSe_s_isBorder());
      setGroupData(rgSIsTrue,server.getSe_s_isTrue());
      tvSoftTime.setText(server.getSe_s_upTime());
      setGroupData(rgSoftUpType,server.getSe_s_upType());
      etSoftUpBrief.setText(server.getSe_s_upBrief());
    }
  }


  private void initDatas() {
    hardWareNames = getResources().getStringArray(R.array.hardwareName);
    osNames = getResources().getStringArray(R.array.companyName);
    softNames = getResources().getStringArray(R.array.hardwareName);
  }

  private void initListeners() {
    spHardware.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        hardWareName = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    spOsCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        osName = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    spSoftCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        softName = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
  }

  private void setGroupData(RadioGroup radioGroup,int i){
    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
    radioButton.setChecked(true);

  }

  private int getGroupData(RadioGroup radioGroup) {

    for (int i = 0; i < radioGroup.getChildCount(); i++) {
      RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
      if (radioButton.isChecked()) {
        return i;
      }

    }
    return -1;
  }

  @OnClick(R.id.btn_hard_time) public void setHardTime() {
    showDateDialog(tvHardTime);
  }

  @OnClick(R.id.btn_os_time) public void setOsTime() {
    showDateDialog(tvOsTime);
  }

  @OnClick(R.id.btn_soft_time) public void setSoftTime() {
    showDateDialog(tvSoftTime);
  }

  private void showDateDialog(final TextView textView) {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    DatePickerDialog dialog =
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
          @Override public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            int month = i1 + 1;
            textView.setText(i + "年" + month + "月" + i2 + "日");
          }
        }, year, month, day);
    dialog.show();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
