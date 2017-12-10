package com.howietian.jianshi.scada;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.constants.Constant;
import com.howietian.jianshi.entities.Scada;
import com.howietian.jianshi.entities.Unit;

/**
 * A simple {@link Fragment} subclass.
 * 基本信息模块
 */
public class InfoFragment extends Fragment {

    @Bind(R.id.et_work_name)
    EditText et_work_name;
    @Bind(R.id.et_group)
    EditText et_workSpace;
    @Bind(R.id.et_brief)
    EditText et_brief;
    @Bind(R.id.et_peo_name)
    EditText et_peo_name;
    @Bind(R.id.et_peo_duty)
    EditText et_peo_duty;
    @Bind(R.id.et_peo_title)
    EditText et_peo_title;
    @Bind(R.id.et_peo_phone)
    EditText et_peo_phone;
    @Bind(R.id.et_peo_edu)
    EditText et_peo_edu;
    @Bind(R.id.et_s_name)
    EditText et_s_name;
    @Bind(R.id.et_s_duty)
    EditText et_s_duty;
    @Bind(R.id.et_s_phone)
    EditText et_s_phone;
    @Bind(R.id.et_s_workspace)
    EditText et_s_workspace;
    private String info = null;
    private String personInfo = null;

    Scada scada = new Scada();
    Scada.Person person = scada.new Person();

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);

        info = ((MainActivity) activity).getData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                personInfo = ((MainActivity) activity).getScadaInfo(MainActivity.BASE_INFO);

            }
        }).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, mainView);
        setInfo();
        setPersonInfo();

        return mainView;
    }

    // 直接填写单位和系统的基本信息
    public void setInfo() {

        if (info != null) {
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
            Scada.Person person = new Gson().fromJson(personInfo,Scada.Person.class);
            et_s_workspace.setText(person.getSunit());
            et_s_name.setText(person.getSname());
            et_s_duty.setText(person.getSposition());
            et_s_phone.setText(person.getSphone());
        }
    }

    // 返回给ScadaFragment的Json信息
    public String getData() {
        person.setSunit(et_s_workspace.getText().toString());
        person.setSname(et_s_name.getText().toString());
        person.setSposition(et_s_duty.getText().toString());
        person.setSphone(et_s_phone.getText().toString());
        String personInfo = new Gson().toJson(person, Scada.Person.class);
        return personInfo;
    }
}

       /* StringBuilder sb = new StringBuilder();
        String info = Constant.INFO + Constant.NEW_LINE;
        String workname = Constant.WORK_NAME + et_work_name.getText().toString() + Constant.NEW_LINE;
        String workgroup = Constant.WORK_GROUP + et_workSpace.getText().toString() + Constant.NEW_LINE;
        String systembrief = Constant.SYSTEM_BRIEF + et_brief.getText().toString() + Constant.NEW_LINE;
        String people = Constant.PEOPLE + Constant.NEW_LINE;
        String peo_name = Constant.NAME + et_peo_name.getText().toString() + Constant.NEW_LINE;
        String peo_duty = Constant.DUTY + et_peo_duty.getText().toString() + Constant.NEW_LINE;
        String peo_title = Constant.TITLE + et_peo_title.getText().toString() + Constant.NEW_LINE;
        String peo_education = Constant.EDUCATION + et_peo_edu.getText().toString() + Constant.NEW_LINE;
        String peo_phone = Constant.PHONE + et_peo_phone.getText().toString() + Constant.NEW_LINE;
        String scada = Constant.SACDA_SOFTWARE + Constant.NEW_LINE;
        String s_unit = Constant.UNIT + et_s_workspace.getText().toString() + Constant.NEW_LINE;
        String s_name = Constant.NAME + et_s_name.getText().toString() + Constant.NEW_LINE;
        String s_duty = Constant.DUTY + et_s_duty.getText().toString() + Constant.NEW_LINE;
        String s_phone = Constant.PHONE + et_s_phone.getText().toString() + Constant.NEW_LINE;
        sb.append(info)
                .append(workname)
                .append(workgroup)
                .append(systembrief)
                .append(people)
                .append(peo_name)
                .append(peo_duty)
                .append(peo_title)
                .append(peo_education)
                .append(peo_phone)
                .append(scada)
                .append(s_unit)
                .append(s_name)
                .append(s_duty)
                .append(s_phone)
                .append(Constant.NEW_LINE)
                .append(Constant.NEW_LINE);
        return sb.toString();
    }*/
