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
import com.howietian.jianshi.entities.Scada;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TerminalFragment extends Fragment {

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
  String terminalInfo = null;

  int hardWareName, osName, softName;

  public TerminalFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    terminalInfo = ((MainActivity) context).getOtherInfo(MainActivity.O_TERMINAL);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View mainView = inflater.inflate(R.layout.fragment_terminal, container, false);
    ButterKnife.bind(this, mainView);
    initDatas();
    initListeners();
    setData();
    return mainView;
  }

  public String getData() {
    Other other = new Other();
    Other.Terminal terminal = other.new Terminal();
    terminal.settHCompany(hardWareName);
    terminal.settHBrand(etHardBrand.getText().toString());
    terminal.settHVersion(etHardVersion.getText().toString());
    terminal.settHBuyTime(tvHardTime.getText().toString());
    terminal.settHIsBorder(getGroupData(rgHardWhere));

    terminal.settOsType(getGroupData(rgOsType));
    terminal.settOsBrand(etOsVersion.getText().toString());
    terminal.settOsIsTrue(getGroupData(rgOsVersionM));
    terminal.settOsUpTime(tvOsTime.getText().toString());
    terminal.settOsUpType(getGroupData(rgOsUpType));
    terminal.settOsUpBrief(etUpBrief.getText().toString());

    terminal.settSCompany(softName);
    terminal.settSBrand(etSoftBrand.getText().toString());
    terminal.settSVersion(etSoftVersion.getText().toString());
    terminal.settSIsBorder(getGroupData(rgSBorder));
    terminal.settSIsTrue(getGroupData(rgSIsTrue));
    terminal.settSUpTime(tvSoftTime.getText().toString());
    terminal.settSUpType(getGroupData(rgSoftUpType));
    terminal.settSUpBrief(etSoftUpBrief.getText().toString());

    String terminalInfo = new Gson().toJson(terminal, Other.Terminal.class);
    return terminalInfo;
  }

  private void setData(){
    if(terminalInfo!=null){
      Other.Terminal terminal = new Gson().fromJson(terminalInfo,Other.Terminal.class);
      spHardware.setSelection(terminal.gettHCompany());
      etHardBrand.setText(terminal.gettHBrand());
      etHardVersion.setText(terminal.gettHVersion());
      setGroupData(rgHardWhere, terminal.gettHIsBorder());
      tvHardTime.setText(terminal.gettHBuyTime());

      setGroupData(rgOsType, terminal.gettOsType());
      etOsVersion.setText(terminal.gettOsBrand());
      setGroupData(rgOsVersionM, terminal.gettOsIsTrue());
      tvOsTime.setText(terminal.gettOsUpTime());
      setGroupData(rgOsUpType, terminal.gettOsUpType());
      etUpBrief.setText(terminal.gettOsUpBrief());

      spSoftCompany.setSelection(terminal.gettSCompany());
      etSoftBrand.setText(terminal.gettSBrand());
      etSoftVersion.setText(terminal.gettSVersion());
      setGroupData(rgSBorder,terminal.gettSIsBorder());
      setGroupData(rgSIsTrue,terminal.gettSIsTrue());
      tvSoftTime.setText(terminal.gettSUpTime());
      setGroupData(rgSoftUpType,terminal.gettSUpType());
      etSoftUpBrief.setText(terminal.gettSUpBrief());
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
