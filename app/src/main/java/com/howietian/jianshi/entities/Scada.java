package com.howietian.jianshi.entities;

import com.howietian.jianshi.base.BaseActivity;

/**
 * Created by HowieTian on 2017/11/6 0006.
 */

public class Scada {
    private Integer uid;
    private Person person;
    private Station station;
    private Server server;
    private Switch aSwitch;
    private NetInfo netInfo;
    private Other other;

    public class Person {
        private String sunit;
        private String sname;
        private String sposition;
        private String sphone;

        public String getSunit() {
            return sunit;
        }

        public void setSunit(String sunit) {
            this.sunit = sunit;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getSposition() {
            return sposition;
        }

        public void setSposition(String sposition) {
            this.sposition = sposition;
        }

        public String getSphone() {
            return sphone;
        }

        public void setSphone(String sphone) {
            this.sphone = sphone;
        }

        @Override
        public String toString() {
            return "people{" +
                    "sunit='" + sunit + '\'' +
                    ", sname='" + sname + '\'' +
                    ", sposition='" + sposition + '\'' +
                    ", sphone='" + sphone + '\'' +
                    '}';
        }
    }

    public class Station {
        private Integer st_os_type;
        private String st_os_version;
        private Integer st_os_isTrue;
        private String st_os_upTime;
        private Integer st_os_upType;
        private String st_os_upBrief;
        private Integer st_s_company;
        private String st_s_brand;
        private String st_s_version;
        private Integer st_s_isBorder;
        private Integer st_s_isTrue;
        private String st_s_upTime;
        private Integer st_s_upType;
        private String st_s_upBrief;
        private Integer st_v_isInstall;
        private Integer st_v_company;
        private String st_v_brand;
        private String st_v_version;

        public void setSt_os_type(Integer st_os_type) {
            this.st_os_type = st_os_type;
        }

        public void setSt_os_version(String st_os_version) {
            this.st_os_version = st_os_version;
        }

        public void setSt_os_isTrue(Integer st_os_isTrue) {
            this.st_os_isTrue = st_os_isTrue;
        }

        public void setSt_os_upTime(String st_os_upTime) {
            this.st_os_upTime = st_os_upTime;
        }

        public void setSt_os_upType(Integer st_os_upType) {
            this.st_os_upType = st_os_upType;
        }

        public void setSt_os_upBrief(String st_os_upBrief) {
            this.st_os_upBrief = st_os_upBrief;
        }

        public void setSt_s_company(Integer st_s_company) {
            this.st_s_company = st_s_company;
        }

        public void setSt_s_brand(String st_s_brand) {
            this.st_s_brand = st_s_brand;
        }

        public void setSt_s_version(String st_s_version) {
            this.st_s_version = st_s_version;
        }

        public void setSt_s_isBorder(Integer st_s_isBorder) {
            this.st_s_isBorder = st_s_isBorder;
        }

        public void setSt_s_isTrue(Integer st_s_isTrue) {
            this.st_s_isTrue = st_s_isTrue;
        }

        public void setSt_s_upTime(String st_s_upTime) {
            this.st_s_upTime = st_s_upTime;
        }

        public void setSt_s_upType(Integer st_s_upType) {
            this.st_s_upType = st_s_upType;
        }

        public void setSt_s_upBrief(String st_s_upBrief) {
            this.st_s_upBrief = st_s_upBrief;
        }

        public void setSt_v_isInstall(Integer st_v_isInstall) {
            this.st_v_isInstall = st_v_isInstall;
        }

        public void setSt_v_company(Integer st_v_company) {
            this.st_v_company = st_v_company;
        }

        public void setSt_v_brand(String st_v_brand) {
            this.st_v_brand = st_v_brand;
        }

        public void setSt_v_version(String st_v_version) {
            this.st_v_version = st_v_version;
        }

        public Integer getSt_os_type() {
            return st_os_type;
        }

        public String getSt_os_version() {
            return st_os_version;
        }

        public Integer getSt_os_isTrue() {
            return st_os_isTrue;
        }

        public String getSt_os_upTime() {
            return st_os_upTime;
        }

        public Integer getSt_os_upType() {
            return st_os_upType;
        }

        public String getSt_os_upBrief() {
            return st_os_upBrief;
        }

        public Integer getSt_s_company() {
            return st_s_company;
        }

        public String getSt_s_brand() {
            return st_s_brand;
        }

        public String getSt_s_version() {
            return st_s_version;
        }

        public Integer getSt_s_isBorder() {
            return st_s_isBorder;
        }

        public Integer getSt_s_isTrue() {
            return st_s_isTrue;
        }

        public String getSt_s_upTime() {
            return st_s_upTime;
        }

        public Integer getSt_s_upType() {
            return st_s_upType;
        }

        public String getSt_s_upBrief() {
            return st_s_upBrief;
        }

        public Integer getSt_v_isInstall() {
            return st_v_isInstall;
        }

        public Integer getSt_v_company() {
            return st_v_company;
        }

        public String getSt_v_brand() {
            return st_v_brand;
        }

        public String getSt_v_version() {
            return st_v_version;
        }

        @Override
        public String toString() {
            return "Station{" +
                    "st_os_type=" + st_os_type +
                    ", st_os_version='" + st_os_version + '\'' +
                    ", st_os_isTrue=" + st_os_isTrue +
                    ", st_os_upTime='" + st_os_upTime + '\'' +
                    ", st_os_upType=" + st_os_upType +
                    ", st_os_upBrief='" + st_os_upBrief + '\'' +
                    ", st_s_company='" + st_s_company + '\'' +
                    ", st_s_brand='" + st_s_brand + '\'' +
                    ", st_s_version='" + st_s_version + '\'' +
                    ", st_s_isBorder=" + st_s_isBorder +
                    ", st_s_isTrue=" + st_s_isTrue +
                    ", st_s_upTime='" + st_s_upTime + '\'' +
                    ", st_s_upType=" + st_s_upType +
                    ", st_s_upBrief='" + st_s_upBrief + '\'' +
                    ", st_v_isInstall=" + st_v_isInstall +
                    ", st_v_company='" + st_v_company + '\'' +
                    ", st_v_brand='" + st_v_brand + '\'' +
                    ", st_v_version='" + st_v_version + '\'' +
                    '}';
        }
    }

    public class Server {
        private Integer se_h_company;
        private String se_h_brand;
        private String se_h_version;
        private Integer se_h_isBorder;
        private String se_h_buyTime;
        private Integer se_os_type;
        private String se_os_brand;
        private Integer se_os_isTrue;
        private String se_os_upTime;
        private Integer se_os_upType;
        private String se_os_upBrief;
        private Integer se_db_company;
        private Integer se_db_name;
        private String se_db_version;
        private Integer se_db_isBorder;
        private Integer se_db_isTrue;
        private String se_db_upTime;
        private Integer se_db_upType;
        private String se_db_brief;
        private Integer se_v_isInstall;
        private Integer se_v_company;
        private String se_v_brand;
        private String se_v_version;
        private String se_o_brief;

        public Integer getSe_h_company() {
            return se_h_company;
        }

        public void setSe_h_company(Integer se_h_company) {
            this.se_h_company = se_h_company;
        }

        public String getSe_h_brand() {
            return se_h_brand;
        }

        public void setSe_h_brand(String se_h_brand) {
            this.se_h_brand = se_h_brand;
        }

        public String getSe_h_version() {
            return se_h_version;
        }

        public void setSe_h_version(String se_h_version) {
            this.se_h_version = se_h_version;
        }

        public Integer getSe_h_isBorder() {
            return se_h_isBorder;
        }

        public void setSe_h_isBorder(Integer se_h_isBorder) {
            this.se_h_isBorder = se_h_isBorder;
        }

        public String getSe_h_buyTime() {
            return se_h_buyTime;
        }

        public void setSe_h_buyTime(String se_h_buyTime) {
            this.se_h_buyTime = se_h_buyTime;
        }

        public Integer getSe_os_type() {
            return se_os_type;
        }

        public void setSe_os_type(Integer se_os_type) {
            this.se_os_type = se_os_type;
        }

        public String getSe_os_brand() {
            return se_os_brand;
        }

        public void setSe_os_brand(String se_os_brand) {
            this.se_os_brand = se_os_brand;
        }

        public Integer getSe_os_isTrue() {
            return se_os_isTrue;
        }

        public void setSe_os_isTrue(Integer se_os_isTrue) {
            this.se_os_isTrue = se_os_isTrue;
        }

        public String getSe_os_upTime() {
            return se_os_upTime;
        }

        public void setSe_os_upTime(String se_os_upTime) {
            this.se_os_upTime = se_os_upTime;
        }

        public Integer getSe_os_upType() {
            return se_os_upType;
        }

        public void setSe_os_upType(Integer se_os_upType) {
            this.se_os_upType = se_os_upType;
        }

        public String getSe_os_upBrief() {
            return se_os_upBrief;
        }

        public void setSe_os_upBrief(String se_os_upBrief) {
            this.se_os_upBrief = se_os_upBrief;
        }

        public Integer getSe_db_company() {
            return se_db_company;
        }

        public void setSe_db_company(Integer se_db_company) {
            this.se_db_company = se_db_company;
        }

        public Integer getSe_db_name() {
            return se_db_name;
        }

        public void setSe_db_name(Integer se_db_name) {
            this.se_db_name = se_db_name;
        }

        public String getSe_db_version() {
            return se_db_version;
        }

        public void setSe_db_version(String se_db_version) {
            this.se_db_version = se_db_version;
        }

        public Integer getSe_db_isBorder() {
            return se_db_isBorder;
        }

        public void setSe_db_isBorder(Integer se_db_isBorder) {
            this.se_db_isBorder = se_db_isBorder;
        }

        public Integer getSe_db_isTrue() {
            return se_db_isTrue;
        }

        public void setSe_db_isTrue(Integer se_db_isTrue) {
            this.se_db_isTrue = se_db_isTrue;
        }

        public String getSe_db_upTime() {
            return se_db_upTime;
        }

        public void setSe_db_upTime(String se_db_upTime) {
            this.se_db_upTime = se_db_upTime;
        }

        public Integer getSe_db_upType() {
            return se_db_upType;
        }

        public void setSe_db_upType(Integer se_db_upType) {
            this.se_db_upType = se_db_upType;
        }

        public String getSe_db_brief() {
            return se_db_brief;
        }

        public void setSe_db_brief(String se_db_brief) {
            this.se_db_brief = se_db_brief;
        }

        public Integer getSe_v_isInstall() {
            return se_v_isInstall;
        }

        public void setSe_v_isInstall(Integer se_v_isInstall) {
            this.se_v_isInstall = se_v_isInstall;
        }

        public Integer getSe_v_company() {
            return se_v_company;
        }

        public void setSe_v_company(Integer se_v_company) {
            this.se_v_company = se_v_company;
        }

        public String getSe_v_brand() {
            return se_v_brand;
        }

        public void setSe_v_brand(String se_v_brand) {
            this.se_v_brand = se_v_brand;
        }

        public String getSe_v_version() {
            return se_v_version;
        }

        public void setSe_v_version(String se_v_version) {
            this.se_v_version = se_v_version;
        }

        public String getSe_o_brief() {
            return se_o_brief;
        }

        public void setSe_o_brief(String se_o_brief) {
            this.se_o_brief = se_o_brief;
        }

        @Override
        public String toString() {
            return "Server{" +
                    "se_h_company='" + se_h_company + '\'' +
                    ", se_h_brand='" + se_h_brand + '\'' +
                    ", se_h_version='" + se_h_version + '\'' +
                    ", se_h_isBorder='" + se_h_isBorder + '\'' +
                    ", se_h_buyTime='" + se_h_buyTime + '\'' +
                    ", se_os_type=" + se_os_type +
                    ", se_os_brand='" + se_os_brand + '\'' +
                    ", se_os_isTrue=" + se_os_isTrue +
                    ", se_os_upTime='" + se_os_upTime + '\'' +
                    ", se_os_upType=" + se_os_upType +
                    ", se_os_upBrief='" + se_os_upBrief + '\'' +
                    ", se_db_company='" + se_db_company + '\'' +
                    ", se_db_name='" + se_db_name + '\'' +
                    ", se_db_version='" + se_db_version + '\'' +
                    ", se_db_isBorder=" + se_db_isBorder +
                    ", se_db_isTrue=" + se_db_isTrue +
                    ", se_db_upTime='" + se_db_upTime + '\'' +
                    ", se_db_upType=" + se_db_upType +
                    ", se_db_brief='" + se_db_brief + '\'' +
                    ", se_v_isInstall='" + se_v_isInstall + '\'' +
                    ", se_v_company='" + se_v_company + '\'' +
                    ", se_v_brand='" + se_v_brand + '\'' +
                    ", se_v_version='" + se_v_version + '\'' +
                    ", se_o_brief='" + se_o_brief + '\'' +
                    '}';
        }
    }

    public class Switch {
        private Integer sw_h_company;
        private String sw_h_name;
        private String sw_h_type;
        private Integer sw_h_isBorder;
        private String sw_s_protocol;

        public Integer getSw_h_company() {
            return sw_h_company;
        }

        public void setSw_h_company(Integer sw_h_company) {
            this.sw_h_company = sw_h_company;
        }

        public String getSw_h_name() {
            return sw_h_name;
        }

        public void setSw_h_name(String sw_h_name) {
            this.sw_h_name = sw_h_name;
        }

        public String getSw_h_type() {
            return sw_h_type;
        }

        public void setSw_h_type(String sw_h_type) {
            this.sw_h_type = sw_h_type;
        }

        public Integer getSw_h_isBorder() {
            return sw_h_isBorder;
        }

        public void setSw_h_isBorder(Integer sw_h_isBorder) {
            this.sw_h_isBorder = sw_h_isBorder;
        }

        public String getSw_s_protocol() {
            return sw_s_protocol;
        }

        public void setSw_s_protocol(String sw_s_protocol) {
            this.sw_s_protocol = sw_s_protocol;
        }

        @Override
        public String toString() {
            return "Switch{" +
                    "sw_h_company='" + sw_h_company + '\'' +
                    ", sw_h_name='" + sw_h_name + '\'' +
                    ", sw_h_type=" + sw_h_type +
                    ", sw_h_isBorder=" + sw_h_isBorder +
                    ", sw_s_protocol='" + sw_s_protocol + '\'' +
                    '}';
        }
    }

    public class NetInfo {
        private Integer net_hu_isConnect;
        private Integer net_hu_isDefence;
        private String net_vpn;
        private String net_firewall;
        private String net_gFirewall;
        private String net_ids;
        private String net_ips;
        private String net_other;
        private Integer net_office_isConnect;
        private Integer net_office_isDefence;
        private String net_office_firewall;
        private String net_office_otherWay;
        private String net_office_software;
        private Integer net_p_isConnect;
        private Integer net_p_connectType;
        private String net_p_type;
        private Integer net_r_isConnect;
        private Integer net_r_ConnectType;
        private String net_r_type;
        private String net_o_brief;

        public Integer getNet_hu_isConnect() {
            return net_hu_isConnect;
        }

        public void setNet_hu_isConnect(Integer net_hu_isConnect) {
            this.net_hu_isConnect = net_hu_isConnect;
        }

        public Integer getNet_hu_isDefence() {
            return net_hu_isDefence;
        }

        public void setNet_hu_isDefence(Integer net_hu_isDefence) {
            this.net_hu_isDefence = net_hu_isDefence;
        }

        public String getNet_vpn() {
            return net_vpn;
        }

        public void setNet_vpn(String net_vpn) {
            this.net_vpn = net_vpn;
        }

        public String getNet_firewall() {
            return net_firewall;
        }

        public void setNet_firewall(String net_firewall) {
            this.net_firewall = net_firewall;
        }

        public String getNet_gFirewall() {
            return net_gFirewall;
        }

        public void setNet_gFirewall(String net_gFirewall) {
            this.net_gFirewall = net_gFirewall;
        }

        public String getNet_ids() {
            return net_ids;
        }

        public void setNet_ids(String net_ids) {
            this.net_ids = net_ids;
        }

        public String getNet_ips() {
            return net_ips;
        }

        public void setNet_ips(String net_ips) {
            this.net_ips = net_ips;
        }

        public String getNet_other() {
            return net_other;
        }

        public void setNet_other(String net_other) {
            this.net_other = net_other;
        }

        public Integer getNet_office_isConnect() {
            return net_office_isConnect;
        }

        public void setNet_office_isConnect(Integer net_office_isConnect) {
            this.net_office_isConnect = net_office_isConnect;
        }

        public Integer getNet_office_isDefence() {
            return net_office_isDefence;
        }

        public void setNet_office_isDefence(Integer net_office_isDefence) {
            this.net_office_isDefence = net_office_isDefence;
        }

        public String getNet_office_firewall() {
            return net_office_firewall;
        }

        public void setNet_office_firewall(String net_office_firewall) {
            this.net_office_firewall = net_office_firewall;
        }

        public String getNet_office_otherWay() {
            return net_office_otherWay;
        }

        public void setNet_office_otherWay(String net_office_otherWay) {
            this.net_office_otherWay = net_office_otherWay;
        }

        public String getNet_office_software() {
            return net_office_software;
        }

        public void setNet_office_software(String net_office_software) {
            this.net_office_software = net_office_software;
        }

        public Integer getNet_p_isConnect() {
            return net_p_isConnect;
        }

        public void setNet_p_isConnect(Integer net_p_isConnect) {
            this.net_p_isConnect = net_p_isConnect;
        }

        public Integer getNet_p_connectType() {
            return net_p_connectType;
        }

        public void setNet_p_connectType(Integer net_p_connectType) {
            this.net_p_connectType = net_p_connectType;
        }

        public String getNet_p_type() {
            return net_p_type;
        }

        public void setNet_p_type(String net_p_type) {
            this.net_p_type = net_p_type;
        }

        public Integer getNet_r_isConnect() {
            return net_r_isConnect;
        }

        public void setNet_r_isConnect(Integer net_r_isConnect) {
            this.net_r_isConnect = net_r_isConnect;
        }

        public Integer getNet_r_ConnectType() {
            return net_r_ConnectType;
        }

        public void setNet_r_ConnectType(Integer net_r_ConnectType) {
            this.net_r_ConnectType = net_r_ConnectType;
        }

        public String getNet_r_type() {
            return net_r_type;
        }

        public void setNet_r_type(String net_r_type) {
            this.net_r_type = net_r_type;
        }

        public String getNet_o_brief() {
            return net_o_brief;
        }

        public void setNet_o_brief(String net_o_brief) {
            this.net_o_brief = net_o_brief;
        }

        @Override
        public String toString() {
            return "NetInfo{" +
                    "net_hu_isConnect=" + net_hu_isConnect +
                    ", net_hu_isDefence=" + net_hu_isDefence +
                    ", net_vpn='" + net_vpn + '\'' +
                    ", net_firewall='" + net_firewall + '\'' +
                    ", net_gFirewall='" + net_gFirewall + '\'' +
                    ", net_ids='" + net_ids + '\'' +
                    ", net_ips='" + net_ips + '\'' +
                    ", net_other='" + net_other + '\'' +
                    ", net_office_isConnect='" + net_office_isConnect + '\'' +
                    ", net_office_isDefence='" + net_office_isDefence + '\'' +
                    ", net_office_firewall='" + net_office_firewall + '\'' +
                    ", net_office_otherWay='" + net_office_otherWay + '\'' +
                    ", net_office_software='" + net_office_software + '\'' +
                    ", net_p_isConnect=" + net_p_isConnect +
                    ", net_p_connectType='" + net_p_connectType + '\'' +
                    ", net_p_type=" + net_p_type +
                    ", net_r_isConnect=" + net_r_isConnect +
                    ", net_r_ConnectType='" + net_r_ConnectType + '\'' +
                    ", net_r_type=" + net_r_type +
                    ", net_o_brief='" + net_o_brief + '\'' +
                    '}';
        }
    }

    public class Other {
        private String other_brief;

        public String getOther_brief() {
            return other_brief;
        }

        public void setOther_brief(String other_brief) {
            this.other_brief = other_brief;
        }

        @Override
        public String toString() {
            return "Other{" +
                    "other_brief='" + other_brief + '\'' +
                    '}';
        }
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Switch getaSwitch() {
        return aSwitch;
    }

    public void setaSwitch(Switch aSwitch) {
        this.aSwitch = aSwitch;
    }

    public NetInfo getNetInfo() {
        return netInfo;
    }

    public void setNetInfo(NetInfo netInfo) {
        this.netInfo = netInfo;
    }

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Scada{" +
                "uid=" + uid +
                ", person=" + person +
                ", station=" + station +
                ", server=" + server +
                ", aSwitch=" + aSwitch +
                ", netInfo=" + netInfo +
                ", other=" + other +
                '}';
    }
}
