package com.howietian.jianshi.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 83624 on 2017/5/8.
 */

public class MyPageFragmentAdapter extends FragmentPagerAdapter {


  private List<Fragment> fragments = new ArrayList<>();
  private List<String> titles = new ArrayList<>();

  public MyPageFragmentAdapter(FragmentManager fm,List<Fragment> fragments,List<String> titles) {
    super(fm);
    this.fragments = fragments;
    this.titles = titles;
  }

  @Override public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override public int getCount() {
    return fragments.size();
  }

  @Override public CharSequence getPageTitle(int position) {
    return titles.get(position);
  }
}
