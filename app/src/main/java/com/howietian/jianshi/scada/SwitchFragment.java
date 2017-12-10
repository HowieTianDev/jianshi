package com.howietian.jianshi.scada;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * 交换机模块
 */
public class SwitchFragment extends Fragment {

    @Bind(R.id.sp_hardware)
    AppCompatSpinner spHardware;
    @Bind(R.id.et_hard_name)
    EditText etHardName;
    @Bind(R.id.et_hard_model)
    EditText etHardModel;
    @Bind(R.id.rg_hard_where)
    RadioGroup rgHardWhere;
    @Bind(R.id.et_soft_agree)
    EditText etSoftAgree;

    String[] isBorder = {Constant.INTERNAL, Constant.BORDER};
    String[] hardNames;
    int hardName;
    private String switchInfo = null;
    public void setSwitchInfo(String switchInfo){
        this.switchInfo = switchInfo;
    }

    public SwitchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        switchInfo = ((MainActivity) context).getScadaInfo(MainActivity.SWITCH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_switch, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        initListeners();
        setData();
        return view;
    }

    private void initDatas() {
        hardNames = getResources().getStringArray(R.array.hardwareName);
    }

    private void initListeners() {
        spHardware.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hardName = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    public String getData() {
        Scada scada = new Scada();
        Scada.Switch mswitch = scada.new Switch();
        mswitch.setSw_h_company(hardName);
        mswitch.setSw_h_name(etHardName.getText().toString());
        mswitch.setSw_h_type(etHardModel.getText().toString());
        mswitch.setSw_h_isBorder(getGroupData(rgHardWhere));
        mswitch.setSw_s_protocol(etSoftAgree.getText().toString());

        String switchInfo = new Gson().toJson(mswitch, Scada.Switch.class);
        return switchInfo;

    }

    /**
     * 将数据库中的数据设置到表格中
     */
    private void setData() {
        if (switchInfo != null) {
            Scada.Switch mSwitch = new Gson().fromJson(switchInfo, Scada.Switch.class);
            spHardware.setSelection(mSwitch.getSw_h_company());
            etHardName.setText(mSwitch.getSw_h_name());
            etHardModel.setText(mSwitch.getSw_h_type());
            setRGChildChecked(rgHardWhere, mSwitch.getSw_h_isBorder());
            etSoftAgree.setText(mSwitch.getSw_s_protocol());
        }
    }

    /**
     * 设置radiobutton选中
     */
    private void setRGChildChecked(RadioGroup radioGroup, int i) {
        RadioButton radioButton = null;
        switch (radioGroup.getId()) {
            case R.id.rg_hard_where:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
        }
        radioButton.setChecked(true);

    }

    private Integer getGroupData(RadioGroup radioGroup) {
        switch (radioGroup.getId()) {
            case R.id.rg_hard_where:
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
