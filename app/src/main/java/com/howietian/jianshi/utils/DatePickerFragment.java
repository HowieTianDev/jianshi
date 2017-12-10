package com.howietian.jianshi.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import java.util.Calendar;

/**
 * Created by 83624 on 2017/5/13.
 */

public class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {
  private ShowTimeListener showTimeListener;

  public interface ShowTimeListener {
    void showTime(String date);
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
    return datePickerDialog;
  }

  @Override public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
    showTimeListener = (ShowTimeListener) getActivity();
    String date = i + "年" + i1 + "月" + i2 + "日";
    showTimeListener.showTime(date);
  }
}
