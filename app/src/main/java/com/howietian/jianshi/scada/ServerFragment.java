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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.Scada;

import java.util.Calendar;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A simple {@link Fragment} subclass.
 * 服务器模块
 */
public class ServerFragment extends Fragment {
    @Bind(R.id.tv_hard_time)
    TextView tv_hard_time;
    @Bind(R.id.btn_hard_time)
    Button btn_hard_time;
    @Bind(R.id.tv_os_time)
    TextView tv_os_time;
    @Bind(R.id.btn_os_time)
    Button btn_os_time;
    @Bind(R.id.tv_db_time)
    TextView tv_db_time;
    @Bind(R.id.btn_db_time)
    Button btn_db_time;
    @Bind(R.id.sp_hardware)
    AppCompatSpinner spHardware;
    @Bind(R.id.et_hard_brand)
    EditText etHardBrand;
    @Bind(R.id.et_hard_version)
    EditText etHardVersion;
    @Bind(R.id.rg_hard_where)
    RadioGroup rgHardWhere;
    @Bind(R.id.sp_os_company)
    AppCompatSpinner spOsCompany;
    @Bind(R.id.rg_os_type)
    RadioGroup rgOsType;
    @Bind(R.id.et_os_brand)
    EditText etOsBrand;
    @Bind(R.id.et_os_version)
    EditText etOsVersion;
    @Bind(R.id.rg_os_upType)
    RadioGroup rgOsUpType;
    @Bind(R.id.et_up_brief)
    EditText etUpBrief;
    @Bind(R.id.sp_db)
    AppCompatSpinner spDb;
    @Bind(R.id.sp_db_name)
    AppCompatSpinner spDbName;
    @Bind(R.id.et_db_version)
    EditText etDbVersion;
    @Bind(R.id.rg_db_where)
    RadioGroup rgDbWhere;
    @Bind(R.id.rg_db_warrant)
    RadioGroup rgDbWarrant;
    @Bind(R.id.rg_db_upType)
    RadioGroup rgDbUpType;
    @Bind(R.id.et_db_upBrief)
    EditText etDbUpBrief;
    @Bind(R.id.rg_virus_install)
    RadioGroup rgVirusIntall;
    @Bind(R.id.sp_virus_company)
    AppCompatSpinner spVirusCompany;
    @Bind(R.id.et_virus_brand)
    EditText etVirusBrand;
    @Bind(R.id.et_virus_version)
    EditText etVirusVersion;
    @Bind(R.id.et_other_info)
    EditText etOtherInfo;
    @Bind(R.id.rg_os_versionM)
    RadioGroup rgOsVersionM;

    String[] osType = {Constant.WINDOWS, Constant.LINUX, Constant.UNIX, Constant.OTHER};
    String[] isGenuine = {Constant.GENUINE, Constant.UN_GENUINE};
    String[] upType = {
            Constant.UP_WIRELESS, Constant.UP_DIAL, Constant.UP_SITE, Constant.UP_SELF, Constant.UP_OTHER
    };
    String[] isBorder = {Constant.INTERNAL, Constant.BORDER};
    String[] isInstall = {Constant.YES, Constant.NO};

    String[] hardWareNames;
    String[] osNames;
    String[] dbCompnayNames;
    String[] dbNames;
    String[] virusNames;

   private String serverInfo = null;

    int hardWareName, osName, dbCompanyName, dbName, virusName;

    public ServerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        serverInfo = ((MainActivity) context).getScadaInfo(MainActivity.SERVER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_server, container, false);
        ButterKnife.bind(this, view);
        initDatas();
        initListeners();
        setData(serverInfo);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData(serverInfo);
    }

    private void initDatas() {
        hardWareNames = getResources().getStringArray(R.array.hardwareName);
        osNames = getResources().getStringArray(R.array.companyName);
        dbCompnayNames = getResources().getStringArray(R.array.dbName);
        dbNames = getResources().getStringArray(R.array.dbNameAll);
        virusNames = getResources().getStringArray(R.array.virusName);
    }

    public void setServerInfo(String serverInfo){
        this.serverInfo = serverInfo;
    }

    /**
     * 如果数据库有数据，直接设置上去
     */
    public void setData(String s) {
        if (s != null) {
            Scada.Server server = new Gson().fromJson(serverInfo, Scada.Server.class);
            spHardware.setSelection(server.getSe_h_company());
            etHardBrand.setText(server.getSe_h_brand());
            etHardVersion.setText(server.getSe_h_version());
            setGroupData(rgHardWhere, server.getSe_h_isBorder());
            tv_hard_time.setText(server.getSe_h_buyTime());

            setGroupData(rgOsType, server.getSe_os_type());
            etOsVersion.setText(server.getSe_os_brand());
            setGroupData(rgOsVersionM, server.getSe_os_isTrue());
            tv_os_time.setText(server.getSe_os_upTime());
            setGroupData(rgOsUpType, server.getSe_os_upType());
            etUpBrief.setText(server.getSe_os_upBrief());

            spDb.setSelection(server.getSe_db_company());
            spDbName.setSelection(server.getSe_db_name());
            etDbVersion.setText(server.getSe_db_version());
            setGroupData(rgDbWhere, server.getSe_db_isBorder());
            setGroupData(rgDbWarrant, server.getSe_db_isTrue());
            tv_db_time.setText(server.getSe_db_upTime());
            setGroupData(rgDbUpType, server.getSe_db_upType());
            etDbUpBrief.setText(server.getSe_db_brief());

            setGroupData(rgVirusIntall, server.getSe_v_isInstall());
            spVirusCompany.setSelection(server.getSe_v_company());
            etVirusBrand.setText(server.getSe_v_brand());
            etVirusVersion.setText(server.getSe_v_version());

            etOtherInfo.setText(server.getSe_o_brief());

        }
    }

    private void initListeners() {
        spHardware.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hardWareName = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spOsCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                osName = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spDb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dbCompanyName = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spDbName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dbName = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spVirusCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                virusName = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.btn_hard_time)
    public void setHardTime() {
        showDateDialog(tv_hard_time);
    }

    @OnClick(R.id.btn_os_time)
    public void setOsTime() {
        showDateDialog(tv_os_time);
    }

    @OnClick(R.id.btn_db_time)
    public void setDbTime() {
        showDateDialog(tv_db_time);
    }

    public String getData() {
        Scada scada = new Scada();
        Scada.Server server = scada.new Server();
        server.setSe_h_company(hardWareName);
        server.setSe_h_brand(etHardBrand.getText().toString());
        server.setSe_h_version(etHardVersion.getText().toString());
        server.setSe_h_buyTime(tv_hard_time.getText().toString());
        server.setSe_h_isBorder(getGroupData(rgHardWhere));

        server.setSe_os_type(getGroupData(rgOsType));
        server.setSe_os_brand(etOsVersion.getText().toString());
        server.setSe_os_isTrue(getGroupData(rgOsVersionM));
        server.setSe_os_upTime(tv_os_time.getText().toString());
        server.setSe_os_upType(getGroupData(rgOsUpType));
        server.setSe_os_upBrief(etUpBrief.getText().toString());

        server.setSe_db_company(dbCompanyName);
        server.setSe_db_name(dbName);
        server.setSe_db_version(etDbVersion.getText().toString());
        server.setSe_db_isBorder(getGroupData(rgDbWhere));
        server.setSe_db_isTrue(getGroupData(rgDbWarrant));
        server.setSe_db_upTime(tv_db_time.getText().toString());
        server.setSe_db_upType(getGroupData(rgDbUpType));
        server.setSe_db_brief(etDbUpBrief.getText().toString());

        server.setSe_v_isInstall(getGroupData(rgVirusIntall));
        server.setSe_v_company(virusName);
        server.setSe_v_brand(etVirusBrand.getText().toString());
        server.setSe_v_version(etVirusVersion.getText().toString());

        server.setSe_o_brief(etOtherInfo.getText().toString());

        String serverInfo = new Gson().toJson(server, Scada.Server.class);
        return serverInfo;
    }

    /**
     * 设置radiogroup的选择
     */
    private void setGroupData(RadioGroup radioGroup, int i) {
        RadioButton radioButton = null;
        switch (radioGroup.getId()) {
            case R.id.rg_hard_where:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
            case R.id.rg_os_type:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
            case R.id.rg_os_version:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
            case R.id.rg_os_upType:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
            case R.id.rg_db_where:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
            case R.id.rg_db_warrant:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
            case R.id.rg_db_upType:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
            case R.id.rg_virus_install:
                radioButton = (RadioButton) radioGroup.getChildAt(i);
                break;
            case R.id.rg_os_versionM:
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
            case R.id.rg_db_where:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_db_warrant:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_db_upType:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_virus_install:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        return i;
                    }
                }
                break;
            case R.id.rg_os_versionM:
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
