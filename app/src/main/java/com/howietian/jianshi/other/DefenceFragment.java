package com.howietian.jianshi.other;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.Other;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefenceFragment extends Fragment {

  @Bind(R.id.rg_out_connect) RadioGroup rgOutConnect;
  @Bind(R.id.rg_out_defence) RadioGroup rgOutDefence;
  @Bind(R.id.et_out_vpn) EditText etOutVpn;
  @Bind(R.id.et_out_firewall) EditText etOutFirewall;
  @Bind(R.id.et_out_ids) EditText etOutIds;
  @Bind(R.id.et_out_ips) EditText etOutIps;
  @Bind(R.id.et_out_other) EditText etOutOther;
  @Bind(R.id.et_out_communicate) EditText etOutCommunicate;
  @Bind(R.id.rg_out_wireless) RadioGroup rgOutWireless;
  @Bind(R.id.rg_out_Onwirelss) RadioGroup rgOutOnwirelss;
  @Bind(R.id.rg_inner_zuwang) RadioGroup rgInnerZuwang;
  @Bind(R.id.rg_in_defence) RadioGroup rgInDefence;
  @Bind(R.id.et_in_router) EditText etInRouter;
  @Bind(R.id.et_in_switch) EditText etInSwitch;
  @Bind(R.id.et_in_ids) EditText etInIds;
  @Bind(R.id.et_in_other) EditText etInOther;
  String[] YesOrNo = { Constant.YES, Constant.NO };
  String[] Have = { Constant.HAVE, Constant.NO_HAVE };
  String defenceInfo = null;

  public DefenceFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    defenceInfo = ((MainActivity)context).getOtherInfo(MainActivity.O_ZU_NET);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View mainView = inflater.inflate(R.layout.fragment_defence, container, false);
    ButterKnife.bind(this, mainView);
    setData();
    return mainView;
  }

  public String getData() {
    Other other = new Other();
    Other.ZuNet zuNet = other.new ZuNet();
    zuNet.setOutIsBorder(getGroupData(rgOutConnect));
    zuNet.setOutIsDefence(getGroupData(rgOutDefence));
    zuNet.setOutVpn(etOutVpn.getText().toString());
    zuNet.setOutFireWall(etOutFirewall.getText().toString());
    zuNet.setOutIds(etOutIds.getText().toString());
    zuNet.setOutIps(etOutIps.getText().toString());
    zuNet.setOutOtherWay(etOutOther.getText().toString());
    zuNet.setOutProtocol(etOutCommunicate.getText().toString());
    zuNet.setOutWireless(getGroupData(rgOutWireless));
    zuNet.setOutIsWireless(getGroupData(rgOutWireless));
    zuNet.setOutIsInnerZuNet(getGroupData(rgInnerZuwang));

    zuNet.setInIsDefence(getGroupData(rgInDefence));
    zuNet.setInRouter(etInRouter.getText().toString());
    zuNet.setInSwitch(etInSwitch.getText().toString());
    zuNet.setInIds(etInIds.getText().toString());
    zuNet.setInOtherWay(etInOther.getText().toString());

    String zuNetInfo = new Gson().toJson(zuNet,Other.ZuNet.class);
    return zuNetInfo;


  }

  private void setData(){
    if(defenceInfo!=null){
      Other.ZuNet zuNet = new Gson().fromJson(defenceInfo,Other.ZuNet.class);
      setRGChildSelected(rgOutConnect,zuNet.getOutIsBorder());
     setRGChildSelected(rgOutDefence,zuNet.getOutIsDefence());
      etOutVpn.setText(zuNet.getOutVpn());
      etOutFirewall.setText(zuNet.getOutFireWall());
      etOutIds.setText(zuNet.getOutIds());
      etOutIps.setText(zuNet.getOutIps());
      etOutOther.setText(zuNet.getOutOtherWay());
      etOutCommunicate.setText(zuNet.getOutProtocol());
      setRGChildSelected(rgOutWireless,zuNet.getOutWireless());
      setRGChildSelected(rgOutOnwirelss,zuNet.getOutIsWireless());
      setRGChildSelected(rgInnerZuwang,zuNet.getOutIsInnerZuNet());
      setRGChildSelected(rgInDefence,zuNet.getInIsDefence());
      etInRouter.setText(zuNet.getInRouter());
      etInSwitch.setText(zuNet.getInSwitch());
      etInIds.setText(zuNet.getInIds());
      etInOther.setText(zuNet.getInOtherWay());
    }

  }

  private void setRGChildSelected(RadioGroup radioGroup,int i){
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

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
