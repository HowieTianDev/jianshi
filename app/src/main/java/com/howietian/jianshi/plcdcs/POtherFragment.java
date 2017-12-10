package com.howietian.jianshi.plcdcs;

import android.content.Context;
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

/**
 * A simple {@link Fragment} subclass.
 * 其他模块
 */
public class POtherFragment extends Fragment {

  @Bind(R.id.et_other_brief) EditText etOtherInfo;
  String otherInfo = null;

  public POtherFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    otherInfo = ((MainActivity) context).getPLCInfo(MainActivity.P_OTHER);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_pother, container, false);
    ButterKnife.bind(this, view);
    setData();
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public String getData() {
    PLC plc = new PLC();
    PLC.Other other = plc.new Other();
    other.setOther_brief(etOtherInfo.getText().toString());
    String oInfo = new Gson().toJson(other, PLC.Other.class);
    return oInfo;
  }

  private void setData(){
    if(otherInfo!=null){
      PLC.Other other = new Gson().fromJson(otherInfo,PLC.Other.class);
      etOtherInfo.setText(other.getOther_brief());
    }
  }
}
