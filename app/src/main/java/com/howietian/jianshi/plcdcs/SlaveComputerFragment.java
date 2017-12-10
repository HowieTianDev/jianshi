package com.howietian.jianshi.plcdcs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.PLC;
import com.howietian.jianshi.entities.Scada;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlaveComputerFragment extends Fragment {

  @Bind(R.id.sp_hardware) AppCompatSpinner spHardware;
  @Bind(R.id.et_slave_name) EditText etSlaveName;
  @Bind(R.id.et_slave_model) EditText etSlaveModel;
  int hardwareName;
  String[] hardwareNames;
  String slaveInfo = null;

  public SlaveComputerFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    slaveInfo = ((MainActivity) context).getPLCInfo(MainActivity.P_SLAVE_COMPUTER);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View mainView = inflater.inflate(R.layout.fragment_slave_computer, container, false);
    ButterKnife.bind(this, mainView);
    initDatas();
    initListeners();
    setSlaveData();
    return mainView;
  }
  private void initDatas(){
    hardwareNames = getResources().getStringArray(R.array.hardwareName);
  }
  private void initListeners(){
    spHardware.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        hardwareName = i;
      }

      @Override public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
  }
public String getData(){
  PLC plc = new PLC();
  PLC.DownMachine downMachine = plc.new DownMachine();
  downMachine.setDownIOCompany(hardwareName);
  downMachine.setDownIOName(etSlaveName.getText().toString());
  downMachine.setDownIOModle(etSlaveModel.getText().toString());

  String downIfo = new Gson().toJson(downMachine, PLC.DownMachine.class);
  return downIfo;
}

public void setSlaveData(){
  if(slaveInfo!=null){
    PLC.DownMachine downMachine = new Gson().fromJson(slaveInfo,PLC.DownMachine.class);
    spHardware.setSelection(downMachine.getDownIOCompany());
    etSlaveName.setText(downMachine.getDownIOName());
    etSlaveModel.setText(downMachine.getDownIOModle());
  }
}
  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
