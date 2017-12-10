package com.howietian.jianshi.scada;

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
import com.howietian.jianshi.entities.Scada;

/**
 * A simple {@link Fragment} subclass.
 * 其他模块
 */
public class SOtherFragment extends Fragment {

    @Bind(R.id.et_other_info)
    EditText etOtherInfo;
    String otherInfo = null;

    public SOtherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        otherInfo = ((MainActivity) context).getScadaInfo(MainActivity.OTHER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sother, container, false);
        ButterKnife.bind(this, view);
        setData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setData() {
        if (otherInfo != null) {
            Scada.Other other = new Gson().fromJson(otherInfo, Scada.Other.class);
            etOtherInfo.setText(other.getOther_brief());
        }
    }

    public String getData() {
        Scada scada = new Scada();
        Scada.Other other = scada.new Other();
        other.setOther_brief(etOtherInfo.getText().toString());
        String oInfo = new Gson().toJson(other, Scada.Other.class);
        return oInfo;
    }
}
