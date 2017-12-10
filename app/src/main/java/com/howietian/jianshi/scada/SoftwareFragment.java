package com.howietian.jianshi.scada;

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
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.Scada;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * scada软件模块
 */
public class SoftwareFragment extends Fragment {
    @Bind(R.id.tv_os_time)
    TextView tv_os_time;
    @Bind(R.id.btn_os_time)
    Button btn_os_time;
    @Bind(R.id.tv_scada_time)
    TextView tv_scada_time;
    @Bind(R.id.btn_scada_time)
    Button btn_scada_time;
    @Bind(R.id.rg_os_type)
    RadioGroup rgOsType;
    @Bind(R.id.et_os_version)
    EditText etOsVersion;
    @Bind(R.id.rg_os_version)
    RadioGroup rgOsVersion;
    @Bind(R.id.rg_os_upType)
    RadioGroup rgOsUpType;
    @Bind(R.id.et_os_brief)
    EditText etOsBrief;
    @Bind(R.id.sp_scada)
    AppCompatSpinner spScada;
    @Bind(R.id.et_scada_brand)
    EditText etScadaBrand;
    @Bind(R.id.et_scada_version)
    EditText etScadaVersion;
    @Bind(R.id.rg_scada_where)
    RadioGroup rgScadaWhere;
    @Bind(R.id.rg_scada_version)
    RadioGroup rgScadaVersion;
    @Bind(R.id.rg_scada_upType)
    RadioGroup rgScadaUpType;
    @Bind(R.id.et_scada_brief)
    EditText etScadaBrief;
    @Bind(R.id.rg_virus_download)
    RadioGroup rgVirusDownload;
    @Bind(R.id.sp_virus)
    AppCompatSpinner spVirus;
    @Bind(R.id.et_virus_brand)
    EditText etVirusBrand;
    @Bind(R.id.et_virus_version)
    EditText etVirusVersion;

    int osCompany, scadaCompany, virusCompany;
    String[] osType = {Constant.WINDOWS, Constant.LINUX, Constant.UNIX, Constant.OTHER};
    String[] isGenuine = {Constant.GENUINE, Constant.UN_GENUINE};
    String[] upType = {
            Constant.UP_WIRELESS, Constant.UP_DIAL, Constant.UP_SITE, Constant.UP_SELF, Constant.UP_OTHER
    };
    String[] isBorder = {Constant.INTERNAL, Constant.BORDER};
    String[] isInstall = {Constant.YES, Constant.NO};
    String[] companyNames;
    String[] scadaNames;
    String[] virusNames;
    String station = null;

    public SoftwareFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        station = ((MainActivity) context).getScadaInfo(MainActivity.STATION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_software, container, false);
        ButterKnife.bind(this, view);
        initData();
        initListeners();
        setData();
        return view;
    }

    /**
     * 设置数据
     */
    private void setData() {
        if (station != null) {
            Scada.Station mStation = new Gson().fromJson(station, Scada.Station.class);
            setRGChildSelected(rgOsType,mStation.getSt_os_type());
            etOsVersion.setText(mStation.getSt_os_version());
            setRGChildSelected(rgOsVersion,mStation.getSt_os_isTrue());
            tv_os_time.setText(mStation.getSt_os_upTime());
            setRGChildSelected(rgOsUpType,mStation.getSt_os_upType());
            etOsBrief.setText(mStation.getSt_os_upBrief());
            spScada.setSelection(mStation.getSt_s_company());
            etScadaBrand.setText(mStation.getSt_s_brand());
            etScadaVersion.setText(mStation.getSt_s_version());
            setRGChildSelected(rgScadaWhere,mStation.getSt_s_isBorder());
            setRGChildSelected(rgScadaVersion,mStation.getSt_s_isTrue());
            tv_scada_time.setText(mStation.getSt_s_upTime());
            setRGChildSelected(rgScadaUpType,mStation.getSt_s_upType());
            etScadaBrief.setText(mStation.getSt_s_upBrief());
            setRGChildSelected(rgVirusDownload,mStation.getSt_v_isInstall());
            spVirus.setSelection(mStation.getSt_v_company());
            etVirusBrand.setText(mStation.getSt_v_brand());
            etVirusVersion.setText(mStation.getSt_v_version());
        }
    }

    private void initData() {
        companyNames = getResources().getStringArray(R.array.companyName);
        scadaNames = getResources().getStringArray(R.array.scadaName);
        virusNames = getResources().getStringArray(R.array.virusName);
    }

    private void initListeners() {


        spVirus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                virusCompany = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spScada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scadaCompany = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btn_os_time)
    public void setOSTime() {
        showDateDialog(tv_os_time);
    }

    @OnClick(R.id.btn_scada_time)
    public void setScadaTime() {
        showDateDialog(tv_scada_time);
    }



    public String getData() {
        Scada scada = new Scada();
        Scada.Station station = scada.new Station();
        /**
         * 工作站-操作系统部分
         */
        station.setSt_os_type(getGroupName(rgOsType));
        station.setSt_os_version(etOsVersion.getText().toString());
        station.setSt_os_isTrue(getGroupName(rgOsVersion));
        station.setSt_os_upTime(tv_os_time.getText().toString());
        station.setSt_os_upType(getGroupName(rgOsUpType));
        station.setSt_os_upBrief(etOsBrief.getText().toString());
        /**
         * 工作站-SCADA软件部分
         */
        station.setSt_s_company(scadaCompany);
        station.setSt_s_brand(etScadaBrand.getText().toString());
        station.setSt_s_version(etScadaVersion.getText().toString());
        station.setSt_s_isBorder(getGroupName(rgScadaWhere));
        station.setSt_s_isTrue(getGroupName(rgScadaVersion));
        station.setSt_s_upTime(tv_scada_time.getText().toString());
        station.setSt_s_upType(getGroupName(rgScadaUpType));
        station.setSt_s_upBrief(etScadaBrief.getText().toString());
        /**
         * 工作站-杀毒软件部分
         */

        station.setSt_v_isInstall(getGroupName(rgVirusDownload));
        station.setSt_v_company(virusCompany);
        station.setSt_v_brand(etVirusBrand.getText().toString());
        station.setSt_v_version(etVirusVersion.getText().toString());


        String stationInfo = new Gson().toJson(station, Scada.Station.class);

        return stationInfo;
    }

    private void setRGChildSelected(RadioGroup radioGroup, int child) {
        RadioButton radioButton = null;
        switch (radioGroup.getId()) {
            case R.id.rg_os_type:
                radioButton = (RadioButton) radioGroup.getChildAt(child);
                break;
            case R.id.rg_os_version:
                radioButton = (RadioButton) radioGroup.getChildAt(child);
                break;
            case R.id.rg_os_upType:
                radioButton = (RadioButton) radioGroup.getChildAt(child);
                break;
            case R.id.rg_scada_upType:
                radioButton = (RadioButton) radioGroup.getChildAt(child);
                break;
            case R.id.rg_scada_version:
                radioButton = (RadioButton) radioGroup.getChildAt(child);
                break;
            case R.id.rg_scada_where:
                radioButton = (RadioButton) radioGroup.getChildAt(child);
                break;
            case R.id.rg_virus_download:
                radioButton = (RadioButton) radioGroup.getChildAt(child);
                break;
        }
        radioButton.setChecked(true);

    }

    //获取radiogroup的信息
    private Integer getGroupName(RadioGroup radioGroup) {

        switch (radioGroup.getId()) {
            case R.id.rg_os_type:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_os_version:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_os_upType:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_scada_upType:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_scada_version:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_scada_where:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_virus_download:
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

    private void showDateDialog(final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog =
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int month = i1 + 1;
                        textView.setText(i + "年" + month + "月" + i2 + "日");
                    }
                }, year, month, day);
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
