package com.howietian.jianshi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.gson.Gson;
import com.howietian.jianshi.base.BaseActivity;
import com.howietian.jianshi.db.DatabaseHelper;
import com.howietian.jianshi.entities.Other;
import com.howietian.jianshi.entities.PLC;
import com.howietian.jianshi.entities.Scada;
import com.howietian.jianshi.entities.Unit;
import com.howietian.jianshi.other.OtherFragment;
import com.howietian.jianshi.plcdcs.PLCFragment;
import com.howietian.jianshi.scada.SCADAFragment;
import com.howietian.jianshi.welcome.NewUnitActivity;

public class MainActivity extends BaseActivity {
    private SCADAFragment scadaFrag;
    private PLCFragment plcFrag;
    private OtherFragment otherFrag;

    BottomNavigationBar bnv_bar;
    public static final String BASE_INFO = "base_info";
    public static final String STATION = "station";
    public static final String SERVER = "server";
    public static final String SWITCH = "switch";
    public static final String NET_INFO = "net_info";
    public static final String OTHER = "other";

    public static final String P_BASE_INFO = "p_base_info";
    public static final String P_HOST_COMPUTER = "p_host_computer";
    public static final String P_SLAVE_COMPUTER = "p_slave_computer";
    public static final String P_SERVER = "p_server";
    public static final String P_SWITCH = "p_switch";
    public static final String P_NET_INFO = "p_net_info";
    public static final String P_OTHER = "p_other";

    public static final String O_BASE_INFO = "o_base_info";
    public static final String O_TERMINAL = "o_terminal";
    public static final String O_SERVER = "o_server";
    public static final String O_ZU_NET = "o_zu_net";

    private static final String QUERY_SCADA =
            "select * from " + DatabaseHelper.TABLE_SCADA + " where uid = ?";
    private static final String QUERY_PLC =
            "select * from "+DatabaseHelper.TABLE_PLC+" where uid = ?";
    private static final String QUERY_OTHER =
            "select * from "+DatabaseHelper.TABLE_OTHER+" where uid = ?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {
        super.init();
        initViews();
        setBnvListener();

        getData();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        bnv_bar = (BottomNavigationBar) findViewById(R.id.bnv_bar);
        bnv_bar.setBarBackgroundColor(R.color.white);
        bnv_bar.setBackgroundColor(getResources().getColor(R.color.divideLine));
        bnv_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bnv_bar.setMode(BottomNavigationBar.MODE_DEFAULT);
        bnv_bar.addItem(new BottomNavigationItem(R.drawable.ic_perm_data_setting_black_24dp,
                R.string.scada).setActiveColorResource(R.color.colorPrimaryDark))
                .addItem(new BottomNavigationItem(R.drawable.ic_storage_black_24dp,
                        R.string.plcdcs).setActiveColorResource(R.color.colorPrimaryDark))
                .addItem(new BottomNavigationItem(R.drawable.ic_widgets_black_24dp,
                        R.string.other).setActiveColorResource(R.color.colorPrimaryDark))
                .initialise();
        choosePage(0);
    }

    public String getData() {
        Intent intent = getIntent();
        if (intent != null) {
            String unitInfo = intent.getStringExtra(NewUnitActivity.FROM_NEWUNIT);
            return unitInfo;
        }
        return null;
    }

    /**
     * 查询Scada数据库
     * @param str
     * @return
     */
    public String getScadaInfo(String str) {

        Unit unit = new Gson().fromJson(getData(), Unit.class);
        Scada scada = new Scada();


        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_SCADA, new String[]{unit.getId().toString()});

        if (cursor.moveToFirst()) {
            String info = null;
            switch (str) {
                case BASE_INFO:
                    Scada.Person person = scada.new Person();
                    String sunit = cursor.getString(cursor.getColumnIndex("sunit"));
                    String sname = cursor.getString(cursor.getColumnIndex("sname"));
                    String sposition = cursor.getString(cursor.getColumnIndex("sposition"));
                    String sphone = cursor.getString(cursor.getColumnIndex("sphone"));
                    person.setSunit(sunit);
                    person.setSname(sname);
                    person.setSposition(sposition);
                    person.setSphone(sphone);
                    info = new Gson().toJson(person, Scada.Person.class);
                    break;
                case STATION:
                    Scada.Station station = scada.new Station();
                    Integer stOsType = cursor.getInt(cursor.getColumnIndex("st_os_type"));
                    String stOsVersion = cursor.getString(cursor.getColumnIndex("st_os_version"));
                    Integer stOsIsTrue = cursor.getInt(cursor.getColumnIndex("st_os_isTrue"));
                    String stOsUpTime = cursor.getString(cursor.getColumnIndex("st_os_upTime"));
                    Integer stOsUpType = cursor.getInt(cursor.getColumnIndex("st_os_upType"));
                    String stOsUpBrief = cursor.getString(cursor.getColumnIndex("st_os_upBrief"));

                    Integer stSCompany = cursor.getInt(cursor.getColumnIndex("st_s_company"));
                    String stSBrand = cursor.getString(cursor.getColumnIndex("st_s_brand"));
                    String stSVersion = cursor.getString(cursor.getColumnIndex("st_s_version"));
                    Integer stSIsBorder = cursor.getInt(cursor.getColumnIndex("st_s_isBorder"));
                    Integer stSIsTrue = cursor.getInt(cursor.getColumnIndex("st_s_isTrue"));
                    String stSUpTime = cursor.getString(cursor.getColumnIndex("st_s_upTime"));
                    Integer stSUpType = cursor.getInt(cursor.getColumnIndex("st_s_upType"));
                    String stSUpBrief = cursor.getString(cursor.getColumnIndex("st_s_upBrief"));

                    Integer stVIsInstall = cursor.getInt(cursor.getColumnIndex("st_v_isInstall"));
                    Integer stVCompany = cursor.getInt(cursor.getColumnIndex("st_v_company"));
                    String stVBrand = cursor.getString(cursor.getColumnIndex("st_v_brand"));
                    String stVVersion = cursor.getString(cursor.getColumnIndex("st_v_version"));
                    station.setSt_os_type(stOsType);
                    station.setSt_os_version(stOsVersion);
                    station.setSt_os_isTrue(stOsIsTrue);
                    station.setSt_os_upTime(stOsUpTime);
                    station.setSt_os_upBrief(stOsUpBrief);
                    station.setSt_os_upType(stOsUpType);
                    station.setSt_s_company(stSCompany);
                    station.setSt_s_brand(stSBrand);
                    station.setSt_s_version(stSVersion);
                    station.setSt_s_isBorder(stSIsBorder);
                    station.setSt_s_isTrue(stSIsTrue);
                    station.setSt_s_upTime(stSUpTime);
                    station.setSt_s_upType(stSUpType);
                    station.setSt_s_upBrief(stSUpBrief);
                    station.setSt_v_isInstall(stVIsInstall);
                    station.setSt_v_company(stVCompany);
                    station.setSt_v_brand(stVBrand);
                    station.setSt_v_version(stVVersion);
                    info = new Gson().toJson(station, Scada.Station.class);

                    break;
                case SERVER:
                    Scada.Server server = scada.new Server();
                    Integer seHCompany = cursor.getInt(cursor.getColumnIndex("se_h_company"));
                    String seHBrand = cursor.getString(cursor.getColumnIndex("se_h_brand"));
                    String seHVersion = cursor.getString(cursor.getColumnIndex("se_h_version"));
                    Integer seHIsBorder = cursor.getInt(cursor.getColumnIndex("se_h_isBorder"));
                    String seHBuyTime = cursor.getString(cursor.getColumnIndex("se_h_buyTime"));
                    Integer seOsType = cursor.getInt(cursor.getColumnIndex("se_os_type"));
                    String seOsBrand = cursor.getString(cursor.getColumnIndex("se_os_brand"));
                    Integer seOsIsTrue = cursor.getInt(cursor.getColumnIndex("se_os_isTrue"));
                    Integer seOsUpType = cursor.getInt(cursor.getColumnIndex("se_os_upType"));
                    String seOsUpTime = cursor.getString(cursor.getColumnIndex("se_os_upTime"));
                    String seOsUpBrief = cursor.getString(cursor.getColumnIndex("se_os_upBrief"));
                    Integer seDbCompany = cursor.getInt(cursor.getColumnIndex("se_db_company"));
                    Integer seDbName = cursor.getInt(cursor.getColumnIndex("se_db_name"));
                    String seDbVersion = cursor.getString(cursor.getColumnIndex("se_db_version"));
                    Integer seDbIsBorder = cursor.getInt(cursor.getColumnIndex("se_db_isBorder"));
                    Integer seDbIsTrue = cursor.getInt(cursor.getColumnIndex("se_db_isTrue"));
                    String seDbUpTime = cursor.getString(cursor.getColumnIndex("se_db_upTime"));
                    Integer seDbUpType = cursor.getInt(cursor.getColumnIndex("se_db_upType"));
                    String seDbBrief = cursor.getString(cursor.getColumnIndex("se_db_brief"));
                    Integer seVIsInstall = cursor.getInt(cursor.getColumnIndex("se_v_isInstall"));
                    Integer seVCompany = cursor.getInt(cursor.getColumnIndex("se_v_company"));
                    String seVBrand = cursor.getString(cursor.getColumnIndex("se_v_brand"));
                    String seVVersion = cursor.getString(cursor.getColumnIndex("se_v_version"));
                    String seOBrief = cursor.getString(cursor.getColumnIndex("se_o_brief"));
                    server.setSe_h_company(seHCompany);
                    server.setSe_h_brand(seHBrand);
                    server.setSe_h_version(seHVersion);
                    server.setSe_h_isBorder(seHIsBorder);
                    server.setSe_h_buyTime(seHBuyTime);
                    server.setSe_os_type(seOsType);
                    server.setSe_os_brand(seOsBrand);
                    server.setSe_os_isTrue(seOsIsTrue);
                    server.setSe_os_upTime(seOsUpTime);
                    server.setSe_os_upType(seOsUpType);
                    server.setSe_os_upBrief(seOsUpBrief);
                    server.setSe_db_company(seDbCompany);
                    server.setSe_db_name(seDbName);
                    server.setSe_db_version(seDbVersion);
                    server.setSe_db_isBorder(seDbIsBorder);
                    server.setSe_db_isTrue(seDbIsTrue);
                    server.setSe_db_upTime(seDbUpTime);
                    server.setSe_db_upType(seDbUpType);
                    server.setSe_db_brief(seDbBrief);
                    server.setSe_v_isInstall(seVIsInstall);
                    server.setSe_v_company(seVCompany);
                    server.setSe_v_brand(seVBrand);
                    server.setSe_v_version(seVVersion);
                    server.setSe_o_brief(seOBrief);

                    info = new Gson().toJson(server, Scada.Server.class);


                    break;
                case SWITCH:
                    Scada.Switch mswitch = scada.new Switch();
                    Integer swHCompany = cursor.getInt(cursor.getColumnIndex("sw_h_company"));
                    String swHName = cursor.getString(cursor.getColumnIndex("sw_h_name"));
                    String swHType = cursor.getString(cursor.getColumnIndex("sw_h_type"));
                    Integer swHIsBorder = cursor.getInt(cursor.getColumnIndex("sw_h_isBorder"));
                    String swSProtocol = cursor.getString(cursor.getColumnIndex("sw_s_protocol"));
                    mswitch.setSw_h_company(swHCompany);
                    mswitch.setSw_h_name(swHName);
                    mswitch.setSw_h_type(swHType);
                    mswitch.setSw_h_isBorder(swHIsBorder);
                    mswitch.setSw_s_protocol(swSProtocol);

                    info = new Gson().toJson(mswitch, Scada.Switch.class);
                    break;
                case NET_INFO:
                    Scada.NetInfo netInfo = scada.new NetInfo();
                    Integer netHuIsConnect = cursor.getInt(cursor.getColumnIndex("net_hu_isConnect"));
                    Integer netHuIsDefence = cursor.getInt(cursor.getColumnIndex("net_hu_isDefence"));
                    String vpn = cursor.getString(cursor.getColumnIndex("net_vpn"));
                    String firewall = cursor.getString(cursor.getColumnIndex("net_firewall"));
                    String gFirewall = cursor.getString(cursor.getColumnIndex("net_gFirewall"));
                    String ids = cursor.getString(cursor.getColumnIndex("net_ids"));
                    String ips = cursor.getString(cursor.getColumnIndex("net_ips"));
                    String other = cursor.getString(cursor.getColumnIndex("net_other"));
                    Integer netOfficeIsConnect = cursor.getInt(cursor.getColumnIndex("net_office_isConnect"));
                    Integer netOfficeIsDefence = cursor.getInt(cursor.getColumnIndex("net_office_isDefence"));
                    String netOfficeFirewall = cursor.getString(cursor.getColumnIndex("net_office_firewall"));
                    String netOfficeOtherway = cursor.getString(cursor.getColumnIndex("net_office_otherway"));
                    String netOfficeSoftware = cursor.getString(cursor.getColumnIndex("net_office_software"));
                    Integer netPIsConnect = cursor.getInt(cursor.getColumnIndex("net_p_isConnect"));
                    Integer netPConnectType = cursor.getInt(cursor.getColumnIndex("net_p_connectType"));
                    String netPType = cursor.getString(cursor.getColumnIndex("net_p_type"));
                    Integer netRConnectType = cursor.getInt(cursor.getColumnIndex("net_r_connectType"));
                    Integer netRIsConnect = cursor.getInt(cursor.getColumnIndex("net_r_isConnect"));
                    String netRType = cursor.getString(cursor.getColumnIndex("net_r_type"));
                    String netOBrief = cursor.getString(cursor.getColumnIndex("net_o_brief"));

                    netInfo.setNet_hu_isConnect(netHuIsConnect);
                    netInfo.setNet_hu_isDefence(netHuIsDefence);
                    netInfo.setNet_vpn(vpn);
                    netInfo.setNet_firewall(firewall);
                    netInfo.setNet_gFirewall(gFirewall);
                    netInfo.setNet_ids(ids);
                    netInfo.setNet_ips(ips);
                    netInfo.setNet_other(other);
                    netInfo.setNet_office_isConnect(netOfficeIsConnect);
                    netInfo.setNet_office_isDefence(netOfficeIsDefence);
                    netInfo.setNet_office_otherWay(netOfficeOtherway);
                    netInfo.setNet_office_software(netOfficeSoftware);
                    netInfo.setNet_office_firewall(netOfficeFirewall);
                    netInfo.setNet_p_isConnect(netPIsConnect);
                    netInfo.setNet_p_connectType(netPConnectType);
                    netInfo.setNet_p_type(netPType);
                    netInfo.setNet_r_ConnectType(netRConnectType);
                    netInfo.setNet_r_isConnect(netRIsConnect);
                    netInfo.setNet_r_type(netRType);
                    netInfo.setNet_o_brief(netOBrief);

                    info = new Gson().toJson(netInfo, Scada.NetInfo.class);


                    break;
                case OTHER:
                    Scada.Other mother = scada.new Other();
                    String otherInfo = cursor.getString(cursor.getColumnIndex("other_brief"));
                    mother.setOther_brief(otherInfo);
                    info = new Gson().toJson(mother, Scada.Other.class);
                    break;
            }
            return info;
        } else {
            return null;
        }

    }
    /**
     * 数据库中查询PLC表格内容
     */
    public String getPLCInfo(String str) {

        Unit unit = new Gson().fromJson(getData(), Unit.class);
        PLC plc = new PLC();


        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_PLC, new String[]{unit.getId().toString()});

        if (cursor.moveToFirst()) {
            String info = null;
            switch (str) {
                case P_BASE_INFO:
                    PLC.Person person = plc.new Person();
                    String sunit = cursor.getString(cursor.getColumnIndex("sunit"));
                    String sname = cursor.getString(cursor.getColumnIndex("sname"));
                    String sposition = cursor.getString(cursor.getColumnIndex("sposition"));
                    String sphone = cursor.getString(cursor.getColumnIndex("sphone"));
                    person.setSunit(sunit);
                    person.setSname(sname);
                    person.setSposition(sposition);
                    person.setSphone(sphone);
                    info = new Gson().toJson(person, PLC.Person.class);
                    break;
                case P_HOST_COMPUTER:
                    PLC.UpMachine upMachine = plc.new UpMachine();
                    Integer upOsType = cursor.getInt(cursor.getColumnIndex("upOsType"));
                    String upOsBrand = cursor.getString(cursor.getColumnIndex("upOsBrand"));
                    String upOsVersion = cursor.getString(cursor.getColumnIndex("upOsVersion"));
                    Integer upOsIsTrue = cursor.getInt(cursor.getColumnIndex("upOsIsTrue"));
                    String upOsUpTime = cursor.getString(cursor.getColumnIndex("upOsUpTime"));
                    Integer upOsUpType = cursor.getInt(cursor.getColumnIndex("upOsUpType"));
                    String upOsBrief = cursor.getString(cursor.getColumnIndex("upOsBrief"));
                    Integer upJCompany = cursor.getInt(cursor.getColumnIndex("upJCompany"));
                    String upJBrand = cursor.getString(cursor.getColumnIndex("upJBrand"));
                    String upJVersion = cursor.getString(cursor.getColumnIndex("upJVersion"));
                    Integer upJIsBorder = cursor.getInt(cursor.getColumnIndex("upJIsBorder"));
                    Integer upJIsTrue = cursor.getInt(cursor.getColumnIndex("upJIsTrue"));
                    String upJUpTime = cursor.getString(cursor.getColumnIndex("upJUpTime"));
                    Integer upJUpType = cursor.getInt(cursor.getColumnIndex("upJUpType"));
                    String upJBrief = cursor.getString(cursor.getColumnIndex("upJBrief"));
                    Integer upZCompany = cursor.getInt(cursor.getColumnIndex("upZCompany"));
                    String upZBrand = cursor.getString(cursor.getColumnIndex("upZBrand"));
                    String upZVersion = cursor.getString(cursor.getColumnIndex("upZVersion"));
                    Integer upZIsBorder = cursor.getInt(cursor.getColumnIndex("upZIsBorder"));
                    Integer upZIsTrue = cursor.getInt(cursor.getColumnIndex("upZIsTrue"));
                    String upZUpTime = cursor.getString(cursor.getColumnIndex("upZUpTime"));
                    Integer upZUpType = cursor.getInt(cursor.getColumnIndex("upZUpType"));
                    String upZUpBrief = cursor.getString(cursor.getColumnIndex("upZBrief"));
                    Integer upVIsInstall = cursor.getInt(cursor.getColumnIndex("upVIsInstall"));
                    Integer upVCompany = cursor.getInt(cursor.getColumnIndex("upVCompany"));
                    String upVBrand = cursor.getString(cursor.getColumnIndex("upVBrand"));
                    String upVVersion = cursor.getString(cursor.getColumnIndex("upVVersion"));
                    Integer upWIsInstall = cursor.getInt(cursor.getColumnIndex("upWIsInstall"));
                    Integer upWCompany = cursor.getInt(cursor.getColumnIndex("upWCompany"));
                    String upWBrand = cursor.getString(cursor.getColumnIndex("upWBrand"));
                    String upWVersion = cursor.getString(cursor.getColumnIndex("upWVersion"));
                    String upOBrief = cursor.getString(cursor.getColumnIndex("upOBrief"));

                    upMachine.setUpOsType(upOsType);
                    upMachine.setUpOsBrand(upOsBrand);
                    upMachine.setUpOsVersion(upOsVersion);
                    upMachine.setUpOsIsTrue(upOsIsTrue);
                    upMachine.setUpOsUpTime(upOsUpTime);
                    upMachine.setUpOsUpType(upOsUpType);
                    upMachine.setUpOsBrief(upOsBrief);
                    upMachine.setUpJCompany(upJCompany);
                    upMachine.setUpJBrand(upJBrand);
                    upMachine.setUpJVersion(upJVersion);
                    upMachine.setUpJIsBorder(upJIsBorder);
                    upMachine.setUpJIsTrue(upJIsTrue);
                    upMachine.setUpJUpTime(upJUpTime);
                    upMachine.setUpJUpType(upJUpType);
                    upMachine.setUpJBrief(upJBrief);
                    upMachine.setUpZCompany(upZCompany);
                    upMachine.setUpZBrand(upZBrand);
                    upMachine.setUpZVersion(upZVersion);
                    upMachine.setUpZIsBorder(upZIsBorder);
                    upMachine.setUpZIsTrue(upZIsTrue);
                    upMachine.setUpZUpTime(upZUpTime);
                    upMachine.setUpZUpType(upZUpType);
                    upMachine.setUpZBrief(upZUpBrief);
                    upMachine.setUpVIsInstall(upVIsInstall);
                    upMachine.setUpVCompany(upVCompany);
                    upMachine.setUpVBrand(upVBrand);
                    upMachine.setUpVVersion(upVVersion);
                    upMachine.setUpWIsInstall(upWIsInstall);
                    upMachine.setUpWCompany(upWCompany);
                    upMachine.setUpWVersion(upWVersion);
                    upMachine.setUpWBrand(upWBrand);
                    upMachine.setUpOBrief(upOBrief);

                    info = new Gson().toJson(upMachine,PLC.UpMachine.class);

                    break;
                case P_SLAVE_COMPUTER:
                    PLC.DownMachine downMachine = plc.new DownMachine();
                    Integer downCompany = cursor.getInt(cursor.getColumnIndex("downIOCompany"));
                    String downName = cursor.getString(cursor.getColumnIndex("downIOName"));
                    String downModle = cursor.getString(cursor.getColumnIndex("downIOModle"));
                    downMachine.setDownIOCompany(downCompany);
                    downMachine.setDownIOName(downName);
                    downMachine.setDownIOModle(downModle);
                    info = new Gson().toJson(downMachine,PLC.DownMachine.class);

                    break;
                case P_SERVER:
                    PLC.Server server = plc.new Server();
                    Integer seHCompany = cursor.getInt(cursor.getColumnIndex("seHCompany"));
                    String seHBrand = cursor.getString(cursor.getColumnIndex("seHBrand"));
                    String seHVersion = cursor.getString(cursor.getColumnIndex("seHVersion"));
                    Integer seHIsBorder = cursor.getInt(cursor.getColumnIndex("seHIsBorder"));
                    String seHBuyTime = cursor.getString(cursor.getColumnIndex("seHBuyTime"));
                    Integer seOsType = cursor.getInt(cursor.getColumnIndex("seOsType"));
                    String seOsBrand = cursor.getString(cursor.getColumnIndex("seOsBrand"));
                    Integer seOsIsTrue = cursor.getInt(cursor.getColumnIndex("seOsIsTrue"));
                    Integer seOsUpType = cursor.getInt(cursor.getColumnIndex("seOsUpType"));
                    String seOsUpTime = cursor.getString(cursor.getColumnIndex("seOsUpTime"));
                    String seOsUpBrief = cursor.getString(cursor.getColumnIndex("seOsUpBrief"));
                    Integer seDbCompany = cursor.getInt(cursor.getColumnIndex("seDbCompany"));
                    Integer seDbName = cursor.getInt(cursor.getColumnIndex("seDbName"));
                    String seDbVersion = cursor.getString(cursor.getColumnIndex("seDbVersion"));
                    Integer seDbIsBorder = cursor.getInt(cursor.getColumnIndex("seDbIsBorder"));
                    Integer seDbIsTrue = cursor.getInt(cursor.getColumnIndex("seDbIsTrue"));
                    String seDbUpTime = cursor.getString(cursor.getColumnIndex("seDbUpTime"));
                    Integer seDbUpType = cursor.getInt(cursor.getColumnIndex("seDbUpType"));
                    String seDbBrief = cursor.getString(cursor.getColumnIndex("seDbBrief"));
                    Integer seVIsInstall = cursor.getInt(cursor.getColumnIndex("seVIsInstall"));
                    Integer seVCompany = cursor.getInt(cursor.getColumnIndex("seVCompany"));
                    String seVBrand = cursor.getString(cursor.getColumnIndex("seVBrand"));
                    String seVVersion = cursor.getString(cursor.getColumnIndex("seVVersion"));
                    String seOBrief = cursor.getString(cursor.getColumnIndex("seOBrief"));
                    server.setSe_h_company(seHCompany);
                    server.setSe_h_brand(seHBrand);
                    server.setSe_h_version(seHVersion);
                    server.setSe_h_isBorder(seHIsBorder);
                    server.setSe_h_buyTime(seHBuyTime);
                    server.setSe_os_type(seOsType);
                    server.setSe_os_brand(seOsBrand);
                    server.setSe_os_isTrue(seOsIsTrue);
                    server.setSe_os_upTime(seOsUpTime);
                    server.setSe_os_upType(seOsUpType);
                    server.setSe_os_upBrief(seOsUpBrief);
                    server.setSe_db_company(seDbCompany);
                    server.setSe_db_name(seDbName);
                    server.setSe_db_version(seDbVersion);
                    server.setSe_db_isBorder(seDbIsBorder);
                    server.setSe_db_isTrue(seDbIsTrue);
                    server.setSe_db_upTime(seDbUpTime);
                    server.setSe_db_upType(seDbUpType);
                    server.setSe_db_brief(seDbBrief);
                    server.setSe_v_isInstall(seVIsInstall);
                    server.setSe_v_company(seVCompany);
                    server.setSe_v_brand(seVBrand);
                    server.setSe_v_version(seVVersion);
                    server.setSe_o_brief(seOBrief);

                    info = new Gson().toJson(server, PLC.Server.class);
                    break;
                case P_SWITCH:

                    PLC.Switch mswitch = plc.new Switch();
                    Integer swHCompany = cursor.getInt(cursor.getColumnIndex("swHCompany"));
                    String swHName = cursor.getString(cursor.getColumnIndex("swHName"));
                    String swHType = cursor.getString(cursor.getColumnIndex("swHType"));
                    Integer swHIsBorder = cursor.getInt(cursor.getColumnIndex("swHIsBorder"));
                    String swSProtocol = cursor.getString(cursor.getColumnIndex("swSProtocol"));
                    mswitch.setSw_h_company(swHCompany);
                    mswitch.setSw_h_name(swHName);
                    mswitch.setSw_h_type(swHType);
                    mswitch.setSw_h_isBorder(swHIsBorder);
                    mswitch.setSw_s_protocol(swSProtocol);

                    info = new Gson().toJson(mswitch, PLC.Switch.class);


                    break;
                case P_NET_INFO:
                    PLC.NetInfo netInfo = plc.new NetInfo();
                    Integer netHuIsConnect = cursor.getInt(cursor.getColumnIndex("netHuIsConnect"));
                    Integer netHuIsDefence = cursor.getInt(cursor.getColumnIndex("netHuIsDefence"));
                    String vpn = cursor.getString(cursor.getColumnIndex("netVpn"));
                    String firewall = cursor.getString(cursor.getColumnIndex("netFireWall"));
                    String gFirewall = cursor.getString(cursor.getColumnIndex("netGFireWall"));
                    String ids = cursor.getString(cursor.getColumnIndex("netIds"));
                    String ips = cursor.getString(cursor.getColumnIndex("netIps"));
                    String other = cursor.getString(cursor.getColumnIndex("netOther"));
                    Integer netOfficeIsConnect = cursor.getInt(cursor.getColumnIndex("netOfficeIsConnect"));
                    Integer netOfficeIsDefence = cursor.getInt(cursor.getColumnIndex("netOfficeIsDefence"));
                    String netOfficeFirewall = cursor.getString(cursor.getColumnIndex("netOfficeFirewall"));
                    String netOfficeOtherway = cursor.getString(cursor.getColumnIndex("netOfficeOtherWay"));
                    Integer netSIsConnect = cursor.getInt(cursor.getColumnIndex("netSIsConnect"));
                    Integer netSIsDefence = cursor.getInt(cursor.getColumnIndex("netSIsDefence"));
                    String netSVpn = cursor.getString(cursor.getColumnIndex("netSVpn"));
                    String netSFireWall = cursor.getString(cursor.getColumnIndex("netSFireWall"));
                    String netSIds = cursor.getString(cursor.getColumnIndex("netSIds"));
                    String netSIps = cursor.getString(cursor.getColumnIndex("netSIps"));
                    String netSBrief = cursor.getString(cursor.getColumnIndex("netSBrief"));
                    Integer netPIsConnect = cursor.getInt(cursor.getColumnIndex("netPIsConnect"));
                    Integer netPConnectType = cursor.getInt(cursor.getColumnIndex("netPConnectType"));
                    Integer netCloIsConnect = cursor.getInt(cursor.getColumnIndex("netCloIsConnect"));
                    Integer netCloConnectType = cursor.getInt(cursor.getColumnIndex("netCloConnectType"));
                    String netCloBrand = cursor.getString(cursor.getColumnIndex("netCloBrand"));
                    String netOBrief = cursor.getString(cursor.getColumnIndex("netOBrief"));


                    netInfo.setNet_hu_isConnect(netHuIsConnect);
                    netInfo.setNet_hu_isDefence(netHuIsDefence);
                    netInfo.setNet_vpn(vpn);
                    netInfo.setNet_firewall(firewall);
                    netInfo.setNet_gFirewall(gFirewall);
                    netInfo.setNet_ids(ids);
                    netInfo.setNet_ips(ips);
                    netInfo.setNet_other(other);
                    netInfo.setNet_office_isConnect(netOfficeIsConnect);
                    netInfo.setNet_office_isDefence(netOfficeIsDefence);
                    netInfo.setNet_office_otherWay(netOfficeOtherway);
                    netInfo.setNet_office_firewall(netOfficeFirewall);
                    netInfo.setNet_s_isConnect(netSIsConnect);
                    netInfo.setNet_s_isDefence(netSIsDefence);
                    netInfo.setNet_s_vpn(netSVpn);
                    netInfo.setNet_s_firewall(netSFireWall);
                    netInfo.setNet_s_ids(netSIds);
                    netInfo.setNet_ips(netSIps);
                    netInfo.setNet_s_brief(netSBrief);
                    netInfo.setNet_p_isConnect(netPIsConnect);
                    netInfo.setNet_p_connectType(netPConnectType);
                    netInfo.setNet_clo_isConnect(netCloIsConnect);
                    netInfo.setNet_clo_connectType(netCloConnectType);
                    netInfo.setNet_clo_connectType(netCloConnectType);
                    netInfo.setNet_clo_brand(netCloBrand);
                    netInfo.setNet_o_brief(netOBrief);




                    info = new Gson().toJson(netInfo, PLC.NetInfo.class);

                    break;
                case P_OTHER:
                    PLC.Other other1 = plc.new Other();
                    String otherInfo = cursor.getString(cursor.getColumnIndex("otherBrief"));
                    other1.setOther_brief(otherInfo);
                    info = new Gson().toJson(other1, PLC.Other.class);
                    break;
            }
            return info;
        } else {
            return null;
        }

    }

    public String getOtherInfo(String str) {

        Unit unit = new Gson().fromJson(getData(), Unit.class);
        Other other = new Other();


        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_OTHER, new String[]{unit.getId().toString()});

        if (cursor.moveToFirst()) {
            String info = null;
            switch (str) {
                case O_BASE_INFO:
                    Other.Person person = other.new Person();
                    String sunit = cursor.getString(cursor.getColumnIndex("sunit"));
                    String sname = cursor.getString(cursor.getColumnIndex("sname"));
                    String sposition = cursor.getString(cursor.getColumnIndex("sposition"));
                    String sphone = cursor.getString(cursor.getColumnIndex("sphone"));
                    person.setSunit(sunit);
                    person.setSname(sname);
                    person.setSposition(sposition);
                    person.setSphone(sphone);
                    info = new Gson().toJson(person, Other.Person.class);
                    break;
                case O_TERMINAL:
                    Other.Terminal terminal = other.new Terminal();

                    Integer tHCompany = cursor.getInt(cursor.getColumnIndex("tHCompany"));
                    String tHBrand = cursor.getString(cursor.getColumnIndex("tHBrand"));
                    String tHVersion = cursor.getString(cursor.getColumnIndex("tHVersion"));
                    Integer tHIsBorder = cursor.getInt(cursor.getColumnIndex("tHIsBorder"));
                    String tHBuyTime = cursor.getString(cursor.getColumnIndex("tHBuyTime"));
                    Integer tOsType = cursor.getInt(cursor.getColumnIndex("tOsType"));
                    String tOsBrand = cursor.getString(cursor.getColumnIndex("tOsBrand"));
                    Integer tOsIsTrue = cursor.getInt(cursor.getColumnIndex("tOsIsTrue"));
                    Integer tOsUpType = cursor.getInt(cursor.getColumnIndex("tOsUpType"));
                    String tOsUpTime = cursor.getString(cursor.getColumnIndex("tOsUpTime"));
                    String tOsUpBrief = cursor.getString(cursor.getColumnIndex("tOsUpBrief"));
                    Integer tSCompany = cursor.getInt(cursor.getColumnIndex("tSCompany"));
                    String tSBrand = cursor.getString(cursor.getColumnIndex("tSBrand"));
                    String tSVersion = cursor.getString(cursor.getColumnIndex("tSVersion"));
                    Integer tSIsBorder = cursor.getInt(cursor.getColumnIndex("tSIsBorder"));
                    Integer tSIsTrue = cursor.getInt(cursor.getColumnIndex("tSIsTrue"));
                    String tSUpTime = cursor.getString(cursor.getColumnIndex("tSUpTime"));
                    Integer tSUpType = cursor.getInt(cursor.getColumnIndex("tSUpType"));
                    String tSUpBrief = cursor.getString(cursor.getColumnIndex("tSupBrief"));

                    terminal.settHCompany(tHCompany);
                    terminal.settHBrand(tHBrand);
                    terminal.settHVersion(tHVersion);
                    terminal.settHIsBorder(tHIsBorder);
                    terminal.settHBuyTime(tHBuyTime);
                    terminal.settOsType(tOsType);
                    terminal.settOsBrand(tOsBrand);
                    terminal.settOsIsTrue(tOsIsTrue);
                    terminal.settOsUpTime(tOsUpTime);
                    terminal.settOsUpType(tOsUpType);
                    terminal.settOsUpBrief(tOsUpBrief);
                    terminal.settSCompany(tSCompany);
                    terminal.settSBrand(tSBrand);
                    terminal.settSVersion(tSVersion);
                    terminal.settSIsBorder(tSIsBorder);
                    terminal.settSIsTrue(tSIsTrue);
                    terminal.settSUpTime(tSUpTime);
                    terminal.settSUpType(tSUpType);
                    terminal.settSUpBrief(tSUpBrief);

                    info = new Gson().toJson(terminal,Other.Terminal.class);

                    break;
                case O_SERVER:
                    Other.Server server = other.new Server();

                    Integer seHCompany = cursor.getInt(cursor.getColumnIndex("seHCompany"));
                    String seHBrand = cursor.getString(cursor.getColumnIndex("seHBrand"));
                    String seHVersion = cursor.getString(cursor.getColumnIndex("seHVersion"));
                    Integer seHIsBorder = cursor.getInt(cursor.getColumnIndex("seHIsBorder"));
                    String seHBuyTime = cursor.getString(cursor.getColumnIndex("seHBuyTime"));
                    Integer seOsType = cursor.getInt(cursor.getColumnIndex("seOsType"));
                    String seOsBrand = cursor.getString(cursor.getColumnIndex("seOsBrand"));
                    Integer seOsIsTrue = cursor.getInt(cursor.getColumnIndex("seOsIsTrue"));
                    Integer seOsUpType = cursor.getInt(cursor.getColumnIndex("seOsUpType"));
                    String seOsUpTime = cursor.getString(cursor.getColumnIndex("seOsUpTime"));
                    String seOsUpBrief = cursor.getString(cursor.getColumnIndex("seOsUpBrief"));
                    Integer seSCompany = cursor.getInt(cursor.getColumnIndex("seSCompany"));
                    String seSBrand = cursor.getString(cursor.getColumnIndex("seSBrand"));
                    String seSVersion = cursor.getString(cursor.getColumnIndex("seSVersion"));
                    Integer seSIsBorder = cursor.getInt(cursor.getColumnIndex("seSIsBorder"));
                    Integer seSIsTrue = cursor.getInt(cursor.getColumnIndex("seSIsTrue"));
                    String seSUpTime = cursor.getString(cursor.getColumnIndex("seSUpTime"));
                    Integer seSUpType = cursor.getInt(cursor.getColumnIndex("seSUpType"));
                    String seSUpBrief = cursor.getString(cursor.getColumnIndex("seSUpBrief"));

                    server.setSe_h_company(seHCompany);
                    server.setSe_h_brand(seHBrand);
                    server.setSe_h_version(seHVersion);
                    server.setSe_h_isBorder(seHIsBorder);
                    server.setSe_h_buyTime(seHBuyTime);
                    server.setSe_os_type(seOsType);
                    server.setSe_os_brand(seOsBrand);
                    server.setSe_os_isTrue(seOsIsTrue);
                    server.setSe_os_upTime(seOsUpTime);
                    server.setSe_os_upType(seOsUpType);
                    server.setSe_os_upBrief(seOsUpBrief);
                    server.setSe_s_company(seSCompany);
                    server.setSe_s_brand(seSBrand);
                    server.setSe_s_version(seSVersion);
                    server.setSe_s_isBorder(seSIsBorder);
                    server.setSe_s_isTrue(seSIsTrue);
                    server.setSe_s_upTime(seSUpTime);
                    server.setSe_s_upType(seSUpType);
                    server.setSe_s_upBrief(seSUpBrief);

                    info = new Gson().toJson(server,Other.Server.class);

                    break;
                case O_ZU_NET:
                    Other.ZuNet zuNet = other.new ZuNet();
                    Integer outIsBorder = cursor.getInt(cursor.getColumnIndex("outIsBorder"));
                    Integer outIsDefence = cursor.getInt(cursor.getColumnIndex("outIsDefence"));
                    String outVpn = cursor.getString(cursor.getColumnIndex("outVpn"));
                    String outFireWall = cursor.getString(cursor.getColumnIndex("outFireWall"));
                    String outIds = cursor.getString(cursor.getColumnIndex("outIds"));
                    String outIps = cursor.getString(cursor.getColumnIndex("outIps"));
                    String outOtherWay = cursor.getString(cursor.getColumnIndex("outOtherWay"));
                    String outProtocol = cursor.getString(cursor.getColumnIndex("outProtocol"));
                    Integer outWireless = cursor.getInt(cursor.getColumnIndex("outWireless"));
                    Integer outIsWireless = cursor.getInt(cursor.getColumnIndex("outIsWireless"));
                    Integer outIsInnerZuNet = cursor.getInt(cursor.getColumnIndex("outIsInnerZuNet"));
                    Integer inIsDefence = cursor.getInt(cursor.getColumnIndex("inIsDefence"));
                    String inRouter = cursor.getString(cursor.getColumnIndex("inRouter"));
                    String inSwitch = cursor.getString(cursor.getColumnIndex("inSwitch"));
                    String inIds = cursor.getString(cursor.getColumnIndex("inIds"));
                    String inOtherWay = cursor.getString(cursor.getColumnIndex("inOtherWay"));

                    zuNet.setOutIsBorder(outIsBorder);
                    zuNet.setOutIsDefence(outIsDefence);
                    zuNet.setOutVpn(outVpn);
                    zuNet.setOutFireWall(outFireWall);
                    zuNet.setOutIds(outIds);
                    zuNet.setOutIps(outIps);
                    zuNet.setOutOtherWay(outOtherWay);
                    zuNet.setOutProtocol(outProtocol);
                    zuNet.setOutWireless(outWireless);
                    zuNet.setOutIsWireless(outIsWireless);
                    zuNet.setOutIsInnerZuNet(outIsInnerZuNet);
                    zuNet.setInIsDefence(inIsDefence);
                    zuNet.setInRouter(inRouter);
                    zuNet.setInSwitch(inSwitch);
                    zuNet.setInIds(inIds);
                    zuNet.setInOtherWay(inOtherWay);

                    info = new Gson().toJson(zuNet,Other.ZuNet.class);
                    break;
            }
            return info;
        } else {
            return null;
        }

    }

    /**
     * 设置底部导航栏的点击事件
     */
    private void setBnvListener() {
        bnv_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                choosePage(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    //选择页面
    private void choosePage(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hidePages(ft);
        switch (position) {
            case 0:
                if (scadaFrag == null) {
                    scadaFrag = new SCADAFragment();
                    ft.add(R.id.fl_main, scadaFrag);
                }
                ft.show(scadaFrag);
                break;
            case 1:
                if (plcFrag == null) {
                    plcFrag = new PLCFragment();
                    ft.add(R.id.fl_main, plcFrag);
                }
                ft.show(plcFrag);
                break;
            case 2:
                if (otherFrag == null) {
                    otherFrag = new OtherFragment();
                    ft.add(R.id.fl_main, otherFrag);
                }
                ft.show(otherFrag);
                break;
        }
        ft.commit();
    }

    //隐藏所有的 Fragment
    private void hidePages(FragmentTransaction ft) {
        if (scadaFrag != null) {
            ft.hide(scadaFrag);
        }
        if (plcFrag != null) {
            ft.hide(plcFrag);
        }
        if (otherFrag != null) {
            ft.hide(otherFrag);
        }
    }
}
