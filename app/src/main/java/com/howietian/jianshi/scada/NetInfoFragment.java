package com.howietian.jianshi.scada;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.Scada;

/**
 * A simple {@link Fragment} subclass.
 * 联网信息模块
 */
public class NetInfoFragment extends Fragment {

  @Bind(R.id.rg_net_connect) RadioGroup rgNetConnect;
  @Bind(R.id.rg_net_defence) RadioGroup rgNetDefence;
  @Bind(R.id.et_net_vpn) EditText etNetVpn;
  @Bind(R.id.et_net_fire) EditText etNetFire;
  @Bind(R.id.et_net_Gfire) EditText etNetGfire;
  @Bind(R.id.et_net_ids) EditText etNetIds;
  @Bind(R.id.et_net_ips) EditText etNetIps;
  @Bind(R.id.et_net_other) EditText etNetOther;
  @Bind(R.id.rg_office_connect) RadioGroup rgOfficeConnect;
  @Bind(R.id.rg_office_defence) RadioGroup rgOfficeDefence;
  @Bind(R.id.et_office_firewall) EditText etOfficeFirewall;
  @Bind(R.id.et_office_other) EditText etOfficeOther;
  @Bind(R.id.cb_qq) CheckBox cbQq;
  @Bind(R.id.cb_vnc) CheckBox cbVnc;
  @Bind(R.id.cb_office) CheckBox cbOffice;
  @Bind(R.id.cb_mail) CheckBox cbMail;
  @Bind(R.id.cb_other) CheckBox cbOther;
  @Bind(R.id.rg_plc_connect) RadioGroup rgPlcConnect;
  @Bind(R.id.rg_plc_connectType) RadioGroup rgPlcConnectType;
  @Bind(R.id.et_plc_type) EditText etPlcType;
  @Bind(R.id.rg_rtu_connect) RadioGroup rgRtuConnect;
  @Bind(R.id.rg_rtu_connectType) RadioGroup rgRtuConnectType;
  @Bind(R.id.et_rtu_type) EditText etRtuType;
  @Bind(R.id.et_other_info) EditText etOtherInfo;
  String[] isConnect = { Constant.YES, Constant.NO };
  String[] isDefence = { Constant.HAVE, Constant.NO_HAVE };
  String[] connectTypes = { Constant.WIRE, Constant.WIRELESS };
  String netInfo = null;




  public NetInfoFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    netInfo = ((MainActivity) context).getScadaInfo(MainActivity.NET_INFO);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_net_info, container, false);
    ButterKnife.bind(this, view);
    setData();
    return view;
  }

  public String getData() {

    Scada scada = new Scada();
    Scada.NetInfo netInfo = scada.new NetInfo();
    netInfo.setNet_hu_isConnect(getGroupData(rgNetConnect));
    netInfo.setNet_hu_isDefence(getGroupData(rgNetDefence));
    netInfo.setNet_vpn(etNetVpn.getText().toString());
    netInfo.setNet_firewall(etNetFire.getText().toString());
    netInfo.setNet_gFirewall(etNetGfire.getText().toString());
    netInfo.setNet_ids(etNetIds.getText().toString());
    netInfo.setNet_ips(etNetIps.getText().toString());
    netInfo.setNet_o_brief(etNetOther.getText().toString());

    netInfo.setNet_office_isConnect(getGroupData(rgOfficeConnect));
    netInfo.setNet_office_isDefence(getGroupData(rgOfficeDefence));
    netInfo.setNet_office_firewall(etOfficeFirewall.getText().toString());
    netInfo.setNet_office_otherWay(etOfficeOther.getText().toString());

    netInfo.setNet_p_isConnect(getGroupData(rgPlcConnect));
    netInfo.setNet_p_connectType(getGroupData(rgPlcConnectType));
    netInfo.setNet_p_type(etPlcType.getText().toString());

    netInfo.setNet_r_isConnect(getGroupData(rgRtuConnect));
    netInfo.setNet_r_ConnectType(getGroupData(rgRtuConnectType));
    netInfo.setNet_r_type(etRtuType.getText().toString());

    netInfo.setNet_other(etOtherInfo.getText().toString());

    String nInfo = new Gson().toJson(netInfo,Scada.NetInfo.class);
    return nInfo;
  }

  /**
   * 如果数据库存在数据，直接显示数据
   */

  private void setData(){
    if(netInfo!=null){
      Scada.NetInfo mnetInfo = new Gson().fromJson(netInfo,Scada.NetInfo.class);
      setRGChildChecked(rgNetConnect,mnetInfo.getNet_hu_isConnect());
      setRGChildChecked(rgNetDefence,mnetInfo.getNet_hu_isDefence());
      etNetVpn.setText(mnetInfo.getNet_vpn());
      etNetFire.setText(mnetInfo.getNet_firewall());
      etNetGfire.setText(mnetInfo.getNet_gFirewall());
      etNetIds.setText(mnetInfo.getNet_ids());
      etNetIps.setText(mnetInfo.getNet_ips());
      etNetOther.setText(mnetInfo.getNet_o_brief());

      setRGChildChecked(rgOfficeConnect,mnetInfo.getNet_office_isConnect());
      setRGChildChecked(rgOfficeDefence,mnetInfo.getNet_office_isConnect());
      etOfficeFirewall.setText(mnetInfo.getNet_office_firewall());
      etOfficeOther.setText(mnetInfo.getNet_office_otherWay());
      etOfficeOther.setText(etOfficeOther.getText());

      setRGChildChecked(rgPlcConnect,mnetInfo.getNet_p_isConnect());
      setRGChildChecked(rgPlcConnectType,mnetInfo.getNet_p_connectType());
      etPlcType.setText(mnetInfo.getNet_p_type());

      setRGChildChecked(rgRtuConnect,mnetInfo.getNet_r_isConnect());
      setRGChildChecked(rgRtuConnectType,mnetInfo.getNet_r_ConnectType());
      etRtuType.setText(mnetInfo.getNet_r_type());

      etOtherInfo.setText(mnetInfo.getNet_o_brief());

    }
  }



  private String getCheckData(CheckBox cb) {
    if (cb.isChecked()) {
      switch (cb.getId()) {
        case R.id.cb_qq:
          return Constant.QQ;
        case R.id.cb_mail:
          return Constant.MAIL;
        case R.id.cb_office:
          return Constant.OFFICE;
        case R.id.cb_other:
          return Constant.OTHER;
      }
    }
    return "";
  }

  private void setRGChildChecked(RadioGroup radioGroup,int i){
    RadioButton radioButton = null;
    switch (radioGroup.getId()) {
      case R.id.rg_net_connect:
      case R.id.rg_office_connect:
      case R.id.rg_plc_connect:
      case R.id.rg_rtu_connect:
           radioButton = (RadioButton) radioGroup.getChildAt(i);
        break;
      case R.id.rg_net_defence:
      case R.id.rg_office_defence:
           radioButton = (RadioButton) radioGroup.getChildAt(i);
        break;
      case R.id.rg_plc_connectType:
      case R.id.rg_rtu_connectType:
           radioButton = (RadioButton) radioGroup.getChildAt(i);
        break;
    }
    radioButton.setChecked(true);

  }
  private Integer getGroupData(RadioGroup radioGroup) {
    switch (radioGroup.getId()) {
      case R.id.rg_net_connect:
      case R.id.rg_office_connect:
      case R.id.rg_plc_connect:
      case R.id.rg_rtu_connect:
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
          RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
          if (radioButton.isChecked()) {
            return i;
          }
        }
        break;
      case R.id.rg_net_defence:
      case R.id.rg_office_defence:
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
          RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
          if (radioButton.isChecked()) {
            return i;
          }
        }
        break;
      case R.id.rg_plc_connectType:
      case R.id.rg_rtu_connectType:
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
          RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
          if (radioButton.isChecked()) {
            return i;
          }
        }
        break;
    }
    return -1;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
