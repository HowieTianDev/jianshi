package com.howietian.jianshi.plcdcs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
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
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.PLC;
import com.howietian.jianshi.entities.Scada;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class HostComputerFragment extends Fragment {

  @Bind(R.id.sp_companyName) AppCompatSpinner spCompanyName;
  @Bind(R.id.rg_os_type) RadioGroup rgOsType;
  @Bind(R.id.et_os_brand) EditText etOsBrand;
  @Bind(R.id.et_os_version) EditText etOsVersion;
  @Bind(R.id.rg_os_version) RadioGroup rgOsVersion;
  @Bind(R.id.tv_os_time) TextView tvOsTime;
  @Bind(R.id.btn_os_time) Button btnOsTime;
  @Bind(R.id.rg_os_upType) RadioGroup rgOsUpType;
  @Bind(R.id.et_os_brief) EditText etOsBrief;
  @Bind(R.id.sp_Monitor) AppCompatSpinner spMonitor;
  @Bind(R.id.et_monitor_brand) EditText etMonitorBrand;
  @Bind(R.id.et_monitor_version) EditText etMonitorVersion;
  @Bind(R.id.rg_monitor_border) RadioGroup rgMonitorBorder;
  @Bind(R.id.rg_monitor_versionM) RadioGroup rgMonitorVersionM;
  @Bind(R.id.tv_monitor_time) TextView tvMonitorTime;
  @Bind(R.id.btn_monitor_time) Button btnMonitorTime;
  @Bind(R.id.rg_monitor_upType) RadioGroup rgMonitorUpType;
  @Bind(R.id.et_monitor_upBrief) EditText etMonitorUpBrief;
  @Bind(R.id.sp_zutai) AppCompatSpinner spZutai;
  @Bind(R.id.et_zutai_brand) EditText etZutaiBrand;
  @Bind(R.id.et_zutai_version) EditText etZutaiVersion;
  @Bind(R.id.rg_zutai_border) RadioGroup rgZutaiBorder;
  @Bind(R.id.rg_zutai_versionM) RadioGroup rgZutaiVersionM;
  @Bind(R.id.rg_zutai_upType) RadioGroup rgZutaiUpType;
  @Bind(R.id.rg_virus_download) RadioGroup rgVirusDownload;
  @Bind(R.id.sp_virus) AppCompatSpinner spVirus;
  @Bind(R.id.et_virus_brand) EditText etVirusBrand;
  @Bind(R.id.et_virus_version) EditText etVirusVersion;
  @Bind(R.id.rg_white_download) RadioGroup rgWhiteDownload;
  @Bind(R.id.sp_white) AppCompatSpinner spWhite;
  @Bind(R.id.et_white_brand) EditText etWhiteBrand;
  @Bind(R.id.et_white_version) EditText etWhiteVersion;
  @Bind(R.id.et_other_brief) EditText etOtherBrief;
  @Bind(R.id.et_zutai_upBrief) EditText etZutaiUpBrief;
  @Bind(R.id.btn_zutai_time) Button btnZutaiTime;
  @Bind(R.id.tv_zutai_upBrief) TextView tvZutaiUpBrief;

  int osCompany, monitorCompany, zutaiCompany, virusCompany, whiteCompany;
  String[] osCompanys, monitorCompanys, zutaiCompanys, virusCompanys, whiteCompanys;
  String[] osType = { Constant.WINDOWS, Constant.LINUX, Constant.UNIX, Constant.OTHER };
  String[] isGenuine = { Constant.GENUINE, Constant.UN_GENUINE };
  String[] upType = {
      Constant.UP_WIRELESS, Constant.UP_DIAL, Constant.UP_SITE, Constant.UP_SELF, Constant.UP_OTHER
  };
  String[] isBorder = { Constant.INTERNAL, Constant.BORDER };
  String[] isInstall = { Constant.YES, Constant.NO };

  String hostInfo = null;

  public HostComputerFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    hostInfo = ((MainActivity) context).getPLCInfo(MainActivity.P_HOST_COMPUTER);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View mainView = inflater.inflate(R.layout.fragment_host_computer, container, false);
    ButterKnife.bind(this, mainView);
    initDatas();
    initListeners();
    setHostData();
    return mainView;
  }

  private void initDatas() {
    osCompanys = getResources().getStringArray(R.array.companyName);
    monitorCompanys = getResources().getStringArray(R.array.monitorName);
    zutaiCompanys = getResources().getStringArray(R.array.zutaiName);
    virusCompanys = getResources().getStringArray(R.array.virusName);
    whiteCompanys = getResources().getStringArray(R.array.virusName);
  }

  private void initListeners() {
    spCompanyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        osCompany = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    spMonitor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        monitorCompany = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    spVirus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        virusCompany = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    spZutai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        zutaiCompany = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    spWhite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        whiteCompany = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
  }

  public String getData() {

    PLC plc = new PLC();
    PLC.UpMachine upMachine = plc.new UpMachine();
    upMachine.setUpOsType(getGroupName(rgOsType));
    upMachine.setUpOsBrand(etOsBrand.getText().toString());
    upMachine.setUpOsVersion(etOsVersion.getText().toString());
    upMachine.setUpOsIsTrue(getGroupName(rgOsVersion));
    upMachine.setUpOsUpTime(tvOsTime.getText().toString());
    upMachine.setUpOsUpType(getGroupName(rgOsUpType));
    upMachine.setUpOsBrief(etOsBrief.getText().toString());
    upMachine.setUpJCompany(monitorCompany);
    upMachine.setUpJBrand(etMonitorBrand.getText().toString());
    upMachine.setUpJVersion(etMonitorVersion.getText().toString());
    upMachine.setUpJIsBorder(getGroupName(rgMonitorBorder));
    upMachine.setUpJIsTrue(getGroupName(rgMonitorVersionM));
    upMachine.setUpJUpTime(tvMonitorTime.getText().toString());
    upMachine.setUpJUpType(getGroupName(rgMonitorUpType));
    upMachine.setUpJBrief(etMonitorUpBrief.getText().toString());
    upMachine.setUpZCompany(zutaiCompany);
    upMachine.setUpZBrand(etZutaiBrand.getText().toString());
    upMachine.setUpZVersion(etZutaiVersion.getText().toString());
    upMachine.setUpZIsBorder(getGroupName(rgZutaiBorder));
    upMachine.setUpZIsTrue(getGroupName(rgZutaiVersionM));
    upMachine.setUpZUpTime(tvZutaiUpBrief.getText().toString());
    upMachine.setUpZUpType(getGroupName(rgZutaiUpType));
    upMachine.setUpZBrief(etZutaiUpBrief.getText().toString());
    upMachine.setUpVCompany(virusCompany);
    upMachine.setUpVIsInstall(getGroupName(rgVirusDownload));
    upMachine.setUpVBrand(etVirusBrand.getText().toString());
    upMachine.setUpVVersion(etVirusVersion.getText().toString());
    upMachine.setUpWIsInstall(getGroupName(rgWhiteDownload));
    upMachine.setUpWCompany(whiteCompany);
    upMachine.setUpWBrand(etWhiteBrand.getText().toString());
    upMachine.setUpWVersion(etWhiteVersion.getText().toString());
    upMachine.setUpOBrief(etOtherBrief.getText().toString());

    String upInfo = new Gson().toJson(upMachine,PLC.UpMachine.class);

    return upInfo;
  }

  /**
   * 设置数据
   */
  private void setHostData(){
    if(hostInfo!=null){
      PLC.UpMachine upMachine = new Gson().fromJson(hostInfo, PLC.UpMachine.class);
      setRGChildSelected(rgOsType,upMachine.getUpOsType());
      etOsVersion.setText(upMachine.getUpOsVersion());
      setRGChildSelected(rgOsVersion,upMachine.getUpOsIsTrue());
      tvOsTime.setText(upMachine.getUpOsUpTime());
      setRGChildSelected(rgOsUpType,upMachine.getUpOsUpType());
      etOsBrief.setText(upMachine.getUpOsBrief());
      spMonitor.setSelection(upMachine.getUpJCompany());
      etMonitorBrand.setText(upMachine.getUpJBrand());
      etMonitorVersion.setText(upMachine.getUpJVersion());
      setRGChildSelected(rgMonitorBorder,upMachine.getUpJIsBorder());
      setRGChildSelected(rgMonitorVersionM,upMachine.getUpJIsTrue());
      tvMonitorTime.setText(upMachine.getUpJUpTime());
      setRGChildSelected(rgMonitorUpType,upMachine.getUpJUpType());
      etMonitorUpBrief.setText(upMachine.getUpJBrief());
      spZutai.setSelection(upMachine.getUpZCompany());
      etZutaiBrand.setText(upMachine.getUpZBrand());
      etZutaiVersion.setText(upMachine.getUpZVersion());
      setRGChildSelected(rgZutaiBorder,upMachine.getUpZIsBorder());
      setRGChildSelected(rgZutaiVersionM,upMachine.getUpZIsTrue());
      tvZutaiUpBrief.setText(upMachine.getUpZUpTime());
      setRGChildSelected(rgZutaiUpType,upMachine.getUpZUpType());
      etZutaiUpBrief.setText(upMachine.getUpZBrief());
      setRGChildSelected(rgVirusDownload,upMachine.getUpVIsInstall());
      spVirus.setSelection(upMachine.getUpVCompany());
      etVirusBrand.setText(upMachine.getUpVBrand());
      etVirusVersion.setText(upMachine.getUpVVersion());
      setRGChildSelected(rgWhiteDownload,upMachine.getUpWIsInstall());
      spWhite.setSelection(upMachine.getUpWCompany());
      etWhiteBrand.setText(upMachine.getUpWBrand());
      etWhiteVersion.setText(upMachine.getUpWVersion());
      etOtherBrief.setText(upMachine.getUpOBrief());


    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  private void setRGChildSelected(RadioGroup radioGroup,int i){
    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);

    radioButton.setChecked(true);

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

  //获取 radiogroup 的信息
  private int getGroupName(RadioGroup radioGroup) {
    for(int i = 0;i<radioGroup.getChildCount();i++){
      RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
      if(radioButton.isChecked()){
        return i;
      }
    }
    return -1;
  }

  @OnClick({ R.id.btn_os_time, R.id.btn_monitor_time ,R.id.btn_zutai_time}) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_os_time:
        showDateDialog(tvOsTime);
        break;
      case R.id.btn_monitor_time:
        showDateDialog(tvMonitorTime);
        break;
      case R.id.btn_zutai_time:
        showDateDialog(tvZutaiUpBrief);
        break;
    }
  }
}
