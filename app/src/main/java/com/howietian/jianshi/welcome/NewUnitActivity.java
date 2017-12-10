package com.howietian.jianshi.welcome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.adapters.UnitAdapter;
import com.howietian.jianshi.base.BaseActivity;
import com.howietian.jianshi.db.DatabaseHelper;
import com.howietian.jianshi.entities.Unit;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class NewUnitActivity extends BaseActivity {
    @Bind(R.id.rv_unit)
    RecyclerView rvUnit;
    @Bind(R.id.tb_unit)
    Toolbar tbUnit;

    public static String FROM_NEWUNIT = "from_new_unit";
    private UnitAdapter adapter;
    private LinearLayoutManager manager;
    private List<Unit> units = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.activity_new_unit);
    }

    @Override
    public void init() {
        super.init();

        initView();
        initListener();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        units.clear();
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query(DatabaseHelper.TABLE_UNIT, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Unit unit = new Unit();
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String group = cursor.getString(cursor.getColumnIndex("_group"));
            String brief = cursor.getString(cursor.getColumnIndex("brief"));
            String pName = cursor.getString(cursor.getColumnIndex("pName"));
            String position = cursor.getString(cursor.getColumnIndex("position"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String education = cursor.getString(cursor.getColumnIndex("education"));
            String phoneNum = cursor.getString(cursor.getColumnIndex("phoneNum"));
            unit.setId(id);
            unit.setName(name);
            unit.setGroup(group);
            unit.setBrief(brief);
            unit.setpName(pName);
            unit.setPosition(position);
            unit.setTitle(title);
            unit.setEducation(education);
            unit.setPhoneNum(phoneNum);
            units.add(unit);

            adapter.notifyDataSetChanged();
        }

    }

    private void initView() {
        setSupportActionBar(tbUnit);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new UnitAdapter(this, units);
        rvUnit.setLayoutManager(manager);
        rvUnit.setAdapter(adapter);

    }

    private void initListener() {
        adapter.setOnClickListener(new UnitAdapter.onItemClickListener() {
            @Override
            public void onClick(int position) {
                ProgressDialog dialog = new ProgressDialog(NewUnitActivity.this);
                dialog.setTitle("请稍后...");
                dialog.show();
                Gson gson = new Gson();
                String unitInfo = gson.toJson(units.get(position), Unit.class);
                Intent intent = new Intent(NewUnitActivity.this, MainActivity.class);
                intent.putExtra(FROM_NEWUNIT, unitInfo);
                jumpTo(intent, false);
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                jumpTo(AddNewUnitActivity.class, false);
                break;
        }
        return true;
    }
}
