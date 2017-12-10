package com.howietian.jianshi.plcdcs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.PLC;
import com.howietian.jianshi.entities.Unit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PInfoFragment extends Fragment {

  @Bind(R.id.et_work_name) EditText et_work_name;
  @Bind(R.id.et_group) EditText et_workSpace;
  @Bind(R.id.et_brief) EditText et_brief;
  @Bind(R.id.et_peo_name) EditText et_peo_name;
  @Bind(R.id.et_peo_duty) EditText et_peo_duty;
  @Bind(R.id.et_peo_title) EditText et_peo_title;
  @Bind(R.id.et_peo_phone) EditText et_peo_phone;
  @Bind(R.id.et_peo_edu) EditText et_peo_edu;
  @Bind(R.id.et_s_name) EditText et_s_name;
  @Bind(R.id.et_s_duty) EditText et_s_duty;
  @Bind(R.id.et_s_phone) EditText et_s_phone;
  @Bind(R.id.et_s_workspace) EditText et_s_workspace;

  private String info = null;
  private String personInfo = null;

  PLC plc = new PLC();
  PLC.Person person = plc.new Person();

  public PInfoFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    info = ((MainActivity) activity).getData();
    personInfo = ((MainActivity)activity).getPLCInfo(MainActivity.P_BASE_INFO);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View mainView = inflater.inflate(R.layout.fragment_pinfo, container, false);
    ButterKnife.bind(this, mainView);
    setInfo();
    setPersonInfo();
    return mainView;
  }

  private void setInfo(){
    if(info!=null){
      Unit unit = new Gson().fromJson(info, Unit.class);
      et_work_name.setText(unit.getName());
      et_workSpace.setText(unit.getGroup());
      et_brief.setText(unit.getBrief());
      et_peo_name.setText(unit.getpName());
      et_peo_duty.setText(unit.getPosition());
      et_peo_edu.setText(unit.getEducation());
      et_peo_phone.setText(unit.getPhoneNum());
      et_peo_title.setText(unit.getTitle());
    }
  }

  /**
   * 如果数据库中有数据，直接填写联系人信息
   * @return
   */

  public void setPersonInfo(){
    if(personInfo!=null){
      PLC.Person person = new Gson().fromJson(personInfo,PLC.Person.class);
      et_s_workspace.setText(person.getSunit());
      et_s_name.setText(person.getSname());
      et_s_duty.setText(person.getSposition());
      et_s_phone.setText(person.getSphone());
    }
  }

  /**
   * 返回给IPSFragment的Json信息
   * @return
   */
  public String getData() {
    person.setSunit(et_s_workspace.getText().toString());
    person.setSname(et_s_name.getText().toString());
    person.setSposition(et_s_duty.getText().toString());
    person.setSphone(et_s_phone.getText().toString());
    String personInfo = new Gson().toJson(person, PLC.Person.class);
    return personInfo;
  }

}
