package com.howietian.jianshi.plcdcs;

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
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.PLC;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PNetInfoFragment extends Fragment {

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
  @Bind(R.id.rg_s_connect) RadioGroup rgSConnect;
  @Bind(R.id.rg_s_defence) RadioGroup rgSDefence;
  @Bind(R.id.et_s_vpn) EditText etSVpn;
  @Bind(R.id.et_s_firewall) EditText etSFirewall;
  @Bind(R.id.et_s_gfirewall) EditText etSGfirewall;
  @Bind(R.id.et_s_ids) EditText etSIds;
  @Bind(R.id.et_s_ips) EditText etSIps;
  @Bind(R.id.et_s_otherway) EditText etSOtherway;
  @Bind(R.id.rg_pdr_connect) RadioGroup rgPdrConnect;
  @Bind(R.id.rg_pdr_connectType) RadioGroup rgPdrConnectType;
  @Bind(R.id.cb_pdr_gfirewall) CheckBox cbPdrGfirewall;
  @Bind(R.id.et_pdr_gfirewall) EditText etPdrGfirewall;
  @Bind(R.id.cb_pdr_net) CheckBox cbPdrNet;
  @Bind(R.id.et_pdr_net) EditText etPdrNet;
  @Bind(R.id.cb_pdr_netGuan) CheckBox cbPdrNetGuan;
  @Bind(R.id.et_pdr_netGuan) EditText etPdrNetGuan;
  @Bind(R.id.cb_pdr_other) CheckBox cbPdrOther;
  @Bind(R.id.et_pdr_other) EditText etPdrOther;
  @Bind(R.id.rg_time_connect) RadioGroup rgTimeConnect;
  @Bind(R.id.rg_time_connectType) RadioGroup rgTimeConnectType;
  @Bind(R.id.et_time_brand) EditText etTimeBrand;
  @Bind(R.id.et_other_brief) EditText etOtherBrief;

  //单选框所需要选择的值
  String[] isConnect = { Constant.YES, Constant.NO };
  String[] isDefence = { Constant.HAVE, Constant.NO_HAVE };
  String[] connectTypes = { Constant.WIRE, Constant.WIRELESS };
  List<CheckBox> cbs1 = new ArrayList();
  List<CheckBox> cbs2 = new ArrayList<>();
  List<EditText> ets = new ArrayList<>();
  String net = null;

  public PNetInfoFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    net = ((MainActivity)context).getPLCInfo(MainActivity.P_NET_INFO);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View mainView = inflater.inflate(R.layout.fragment_pnet_info, container, false);
    initDatas();
    ButterKnife.bind(this, mainView);
    setData();
    return mainView;
  }

  private void initDatas() {
    cbs1.add(cbQq);
    cbs1.add(cbVnc);
    cbs1.add(cbOffice);
    cbs1.add(cbMail);
    cbs1.add(cbOther);
    cbs2.add(cbPdrGfirewall);
    cbs2.add(cbPdrNet);
    cbs2.add(cbPdrNetGuan);
    cbs2.add(cbPdrOther);
    ets.add(etPdrGfirewall);
    ets.add(etPdrNet);
    ets.add(etPdrNetGuan);
    ets.add(etNetOther);
  }

  public String getData() {

    PLC plc = new PLC();
    PLC.NetInfo netInfo = plc.new NetInfo();
    netInfo.setNet_hu_isConnect(getGroupData(rgNetConnect));
    netInfo.setNet_hu_isDefence(getGroupData(rgNetDefence));
    netInfo.setNet_vpn(etNetVpn.getText().toString());
    netInfo.setNet_firewall(etNetFire.getText().toString());
    netInfo.setNet_gFirewall(etNetGfire.getText().toString());
    netInfo.setNet_ids(etNetIds.getText().toString());
    netInfo.setNet_ips(etNetIps.getText().toString());
    netInfo.setNet_other(etNetOther.getText().toString());

    netInfo.setNet_office_isConnect(getGroupData(rgOfficeConnect));
    netInfo.setNet_office_isDefence(getGroupData(rgOfficeDefence));
    netInfo.setNet_office_firewall(etOfficeFirewall.getText().toString());
    netInfo.setNet_office_otherWay(etOfficeOther.getText().toString());

    netInfo.setNet_s_isConnect(getGroupData(rgSConnect));
    netInfo.setNet_s_isDefence(getGroupData(rgSDefence));
    netInfo.setNet_s_vpn(etSVpn.getText().toString());
    netInfo.setNet_s_firewall(etSFirewall.getText().toString());
    netInfo.setNet_s_ids(etSIds.getText().toString());
    netInfo.setNet_s_ips(etSIps.getText().toString());
    netInfo.setNet_s_brief(etSOtherway.getText().toString());

    netInfo.setNet_p_isConnect(getGroupData(rgPdrConnect));
    netInfo.setNet_p_connectType(getGroupData(rgPdrConnectType));

    netInfo.setNet_clo_isConnect(getGroupData(rgTimeConnect));
    netInfo.setNet_clo_connectType(getGroupData(rgTimeConnectType));
    netInfo.setNet_clo_brand(etTimeBrand.getText().toString());

    netInfo.setNet_o_brief(etOtherBrief.getText().toString());

    String pNetInfo = new Gson().toJson(netInfo,PLC.NetInfo.class);
    return pNetInfo;

  }
  private void setData(){
  if(net!=null){

    PLC.NetInfo mnetInfo = new Gson().fromJson(net,PLC.NetInfo.class);
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
    setRGChildChecked(rgSConnect,mnetInfo.getNet_s_isConnect());
    setRGChildChecked(rgSDefence,mnetInfo.getNet_s_isDefence());
    etSVpn.setText(mnetInfo.getNet_s_vpn());
    etSFirewall.setText(mnetInfo.getNet_s_firewall());
    etSIds.setText(mnetInfo.getNet_s_ids());

    etSIps.setText(mnetInfo.getNet_s_ips());
    etSOtherway.setText(mnetInfo.getNet_s_brief());
    setRGChildChecked(rgPdrConnect,mnetInfo.getNet_p_isConnect());
    setRGChildChecked(rgPdrConnectType,mnetInfo.getNet_p_connectType());
    setRGChildChecked(rgTimeConnect,mnetInfo.getNet_clo_isConnect());
    setRGChildChecked(rgTimeConnectType,mnetInfo.getNet_clo_connectType());
    etTimeBrand.setText(mnetInfo.getNet_clo_brand());
    etOtherBrief.setText(mnetInfo.getNet_other());

  }
}

  private void setRGChildChecked(RadioGroup radioGroup,int i){
    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
    radioButton.setChecked(true);

  }

  //单选按钮的选择值
  private int getGroupData(RadioGroup radioGroup) {
    switch (radioGroup.getId()) {
      case R.id.rg_net_connect:
      case R.id.rg_office_connect:
      case R.id.rg_s_connect:
      case R.id.rg_pdr_connect:
      case R.id.rg_time_connect:
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
          RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
          if (radioButton.isChecked()) {
            return i;
          }
        }
        break;
      case R.id.rg_net_defence:
      case R.id.rg_office_defence:
      case R.id.rg_s_defence:
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
          RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
          if (radioButton.isChecked()) {
            return i;
          }
        }
        break;
      case R.id.rg_pdr_connectType:
      case R.id.rg_time_connectType:
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

  //复选框的选择值
  private String getCheckData(List<CheckBox> cbs, boolean flag) {
    StringBuilder sb = new StringBuilder();
    if (flag) {
      for (CheckBox cb : cbs) {
        if (cb.isChecked()) {
          sb.append(cb.getText().toString() + "");
        }
      }
    } else {
      for (int i = 0; i < cbs.size(); i++) {
        if (cbs.get(i).isChecked()) {
          sb.append(cbs.get(i).getText().toString()
              + "  "
              + ets.get(i).getText().toString()
              + Constant.NEW_LINE);
        }
      }
    }
    return String.valueOf(sb);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
