package com.howietian.jianshi.entities;

/**
 * Created by HowieTian on 2017/11/12 0012.
 */

public class PLC {
    private Integer uid;
    private Person person;
    private UpMachine upMachine;
    private DownMachine downMachine;
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

    public class UpMachine{
        private Integer upOsType;
        private String upOsBrand;
        private String upOsVersion;
        private Integer upOsIsTrue;
        private String upOsUpTime;
        private Integer upOsUpType;
        private String upOsBrief;

        private Integer upJCompany;
        private String upJBrand;
        private String upJVersion;
        private Integer upJIsBorder;
        private Integer upJIsTrue;
        private String upJUpTime;
        private Integer upJUpType;
        private String upJBrief;

        private Integer upZCompany;
        private String upZBrand;
        private String upZVersion;
        private Integer upZIsBorder;
        private Integer upZIsTrue;
        private String upZUpTime;
        private Integer upZUpType;
        private String upZBrief;

        private Integer upVIsInstall;
        private Integer upVCompany;
        private String upVBrand;
        private String upVVersion;

        private Integer upWIsInstall;
        private Integer upWCompany;
        private String upWBrand;
        private String upWVersion;

        private String upOBrief;

        public Integer getUpOsType() {
            return upOsType;
        }

        public void setUpOsType(Integer upOsType) {
            this.upOsType = upOsType;
        }

        public String getUpOsBrand() {
            return upOsBrand;
        }

        public void setUpOsBrand(String upOsBrand) {
            this.upOsBrand = upOsBrand;
        }

        public String getUpOsVersion() {
            return upOsVersion;
        }

        public void setUpOsVersion(String upOsVersion) {
            this.upOsVersion = upOsVersion;
        }

        public Integer getUpOsIsTrue() {
            return upOsIsTrue;
        }

        public void setUpOsIsTrue(Integer upOsIsTrue) {
            this.upOsIsTrue = upOsIsTrue;
        }

        public String getUpOsUpTime() {
            return upOsUpTime;
        }

        public void setUpOsUpTime(String upOsUpTime) {
            this.upOsUpTime = upOsUpTime;
        }

        public Integer getUpOsUpType() {
            return upOsUpType;
        }

        public void setUpOsUpType(Integer upOsUpType) {
            this.upOsUpType = upOsUpType;
        }

        public String getUpOsBrief() {
            return upOsBrief;
        }

        public void setUpOsBrief(String upOsBrief) {
            this.upOsBrief = upOsBrief;
        }

        public Integer getUpJCompany() {
            return upJCompany;
        }

        public void setUpJCompany(Integer upJCompany) {
            this.upJCompany = upJCompany;
        }

        public String getUpJBrand() {
            return upJBrand;
        }

        public void setUpJBrand(String upJBrand) {
            this.upJBrand = upJBrand;
        }

        public String getUpJVersion() {
            return upJVersion;
        }

        public void setUpJVersion(String upJVersion) {
            this.upJVersion = upJVersion;
        }

        public Integer getUpJIsBorder() {
            return upJIsBorder;
        }

        public void setUpJIsBorder(Integer upJIsBorder) {
            this.upJIsBorder = upJIsBorder;
        }

        public Integer getUpJIsTrue() {
            return upJIsTrue;
        }

        public void setUpJIsTrue(Integer upJIsTrue) {
            this.upJIsTrue = upJIsTrue;
        }

        public String getUpJUpTime() {
            return upJUpTime;
        }

        public void setUpJUpTime(String upJUpTime) {
            this.upJUpTime = upJUpTime;
        }

        public Integer getUpJUpType() {
            return upJUpType;
        }

        public void setUpJUpType(Integer upJUpType) {
            this.upJUpType = upJUpType;
        }

        public String getUpJBrief() {
            return upJBrief;
        }

        public void setUpJBrief(String upJBrief) {
            this.upJBrief = upJBrief;
        }

        public Integer getUpZCompany() {
            return upZCompany;
        }

        public void setUpZCompany(Integer upZCompany) {
            this.upZCompany = upZCompany;
        }

        public String getUpZBrand() {
            return upZBrand;
        }

        public void setUpZBrand(String upZBrand) {
            this.upZBrand = upZBrand;
        }

        public String getUpZVersion() {
            return upZVersion;
        }

        public void setUpZVersion(String upZVersion) {
            this.upZVersion = upZVersion;
        }

        public Integer getUpZIsBorder() {
            return upZIsBorder;
        }

        public void setUpZIsBorder(Integer upZIsBorder) {
            this.upZIsBorder = upZIsBorder;
        }

        public Integer getUpZIsTrue() {
            return upZIsTrue;
        }

        public void setUpZIsTrue(Integer upZIsTrue) {
            this.upZIsTrue = upZIsTrue;
        }

        public String getUpZUpTime() {
            return upZUpTime;
        }

        public void setUpZUpTime(String upZUpTime) {
            this.upZUpTime = upZUpTime;
        }

        public Integer getUpZUpType() {
            return upZUpType;
        }

        public void setUpZUpType(Integer upZUpType) {
            this.upZUpType = upZUpType;
        }

        public String getUpZBrief() {
            return upZBrief;
        }

        public void setUpZBrief(String upZBrief) {
            this.upZBrief = upZBrief;
        }

        public Integer getUpVIsInstall() {
            return upVIsInstall;
        }

        public void setUpVIsInstall(Integer upVIsInstall) {
            this.upVIsInstall = upVIsInstall;
        }

        public Integer getUpVCompany() {
            return upVCompany;
        }

        public void setUpVCompany(Integer upVCompany) {
            this.upVCompany = upVCompany;
        }

        public String getUpVBrand() {
            return upVBrand;
        }

        public void setUpVBrand(String upVBrand) {
            this.upVBrand = upVBrand;
        }

        public String getUpVVersion() {
            return upVVersion;
        }

        public void setUpVVersion(String upVVersion) {
            this.upVVersion = upVVersion;
        }

        public Integer getUpWIsInstall() {
            return upWIsInstall;
        }

        public void setUpWIsInstall(Integer upWIsInstall) {
            this.upWIsInstall = upWIsInstall;
        }

        public Integer getUpWCompany() {
            return upWCompany;
        }

        public void setUpWCompany(Integer upWCompany) {
            this.upWCompany = upWCompany;
        }

        public String getUpWBrand() {
            return upWBrand;
        }

        public void setUpWBrand(String upWBrand) {
            this.upWBrand = upWBrand;
        }

        public String getUpWVersion() {
            return upWVersion;
        }

        public void setUpWVersion(String upWVersion) {
            this.upWVersion = upWVersion;
        }

        public String getUpOBrief() {
            return upOBrief;
        }

        public void setUpOBrief(String upOBrief) {
            this.upOBrief = upOBrief;
        }

        @Override
        public String toString() {
            return "UpMachine{" +
                    "upOsType=" + upOsType +
                    ", upOsBrand='" + upOsBrand + '\'' +
                    ", upOsVersion='" + upOsVersion + '\'' +
                    ", upOsIsTrue=" + upOsIsTrue +
                    ", upOsUpTime='" + upOsUpTime + '\'' +
                    ", upOsUpType=" + upOsUpType +
                    ", upOsBrief='" + upOsBrief + '\'' +
                    ", upJCompany=" + upJCompany +
                    ", upJBrand='" + upJBrand + '\'' +
                    ", upJVersion='" + upJVersion + '\'' +
                    ", upJIsBorder=" + upJIsBorder +
                    ", upJIsTrue=" + upJIsTrue +
                    ", upJUpTime='" + upJUpTime + '\'' +
                    ", upJUpType=" + upJUpType +
                    ", upJBrief='" + upJBrief + '\'' +
                    ", upZCompany=" + upZCompany +
                    ", upZBrand='" + upZBrand + '\'' +
                    ", upZVersion='" + upZVersion + '\'' +
                    ", upZIsBorder=" + upZIsBorder +
                    ", upZIsTrue=" + upZIsTrue +
                    ", upZUpTime='" + upZUpTime + '\'' +
                    ", upZUpType=" + upZUpType +
                    ", upZBrief='" + upZBrief + '\'' +
                    ", upVIsInstall=" + upVIsInstall +
                    ", upVCompany=" + upVCompany +
                    ", upVBrand='" + upVBrand + '\'' +
                    ", upVVersion='" + upVVersion + '\'' +
                    ", upWIsInstall=" + upWIsInstall +
                    ", upWCompany=" + upWCompany +
                    ", upWBrand='" + upWBrand + '\'' +
                    ", upWVersion='" + upWVersion + '\'' +
                    ", upOBrief='" + upOBrief + '\'' +
                    '}';
        }
    }

    public class DownMachine{
        private Integer downIOCompany;
        private String downIOName;
        private String downIOModle;

        public Integer getDownIOCompany() {
            return downIOCompany;
        }

        public void setDownIOCompany(Integer downIOCompany) {
            this.downIOCompany = downIOCompany;
        }

        public String getDownIOName() {
            return downIOName;
        }

        public void setDownIOName(String downIOName) {
            this.downIOName = downIOName;
        }

        public String getDownIOModle() {
            return downIOModle;
        }

        public void setDownIOModle(String downIOModle) {
            this.downIOModle = downIOModle;
        }

        @Override
        public String toString() {
            return "DownMachine{" +
                    "downIOCompany=" + downIOCompany +
                    ", downIOName='" + downIOName + '\'' +
                    ", downIOModle='" + downIOModle + '\'' +
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
        private Integer net_s_isConnect;
        private Integer net_s_isDefence;
        private String net_s_vpn;
        private String net_s_firewall;
        private String net_s_ids;
        private String net_s_ips;
        private String net_s_brief;

        private Integer net_p_isConnect;
        private Integer net_p_connectType;
        private Integer net_clo_isConnect;
        private Integer net_clo_connectType;
        private String net_clo_brand;
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

        public Integer getNet_s_isConnect() {
            return net_s_isConnect;
        }

        public void setNet_s_isConnect(Integer net_s_isConnect) {
            this.net_s_isConnect = net_s_isConnect;
        }

        public Integer getNet_s_isDefence() {
            return net_s_isDefence;
        }

        public void setNet_s_isDefence(Integer net_s_isDefence) {
            this.net_s_isDefence = net_s_isDefence;
        }

        public String getNet_s_vpn() {
            return net_s_vpn;
        }

        public void setNet_s_vpn(String net_s_vpn) {
            this.net_s_vpn = net_s_vpn;
        }

        public String getNet_s_firewall() {
            return net_s_firewall;
        }

        public void setNet_s_firewall(String net_s_firewall) {
            this.net_s_firewall = net_s_firewall;
        }

        public String getNet_s_ids() {
            return net_s_ids;
        }

        public void setNet_s_ids(String net_s_ids) {
            this.net_s_ids = net_s_ids;
        }

        public String getNet_s_ips() {
            return net_s_ips;
        }

        public void setNet_s_ips(String net_s_ips) {
            this.net_s_ips = net_s_ips;
        }

        public String getNet_s_brief() {
            return net_s_brief;
        }

        public void setNet_s_brief(String net_s_brief) {
            this.net_s_brief = net_s_brief;
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

        public Integer getNet_clo_isConnect() {
            return net_clo_isConnect;
        }

        public void setNet_clo_isConnect(Integer net_clo_isConnect) {
            this.net_clo_isConnect = net_clo_isConnect;
        }

        public Integer getNet_clo_connectType() {
            return net_clo_connectType;
        }

        public void setNet_clo_connectType(Integer net_clo_connectType) {
            this.net_clo_connectType = net_clo_connectType;
        }

        public String getNet_clo_brand() {
            return net_clo_brand;
        }

        public void setNet_clo_brand(String net_clo_brand) {
            this.net_clo_brand = net_clo_brand;
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
                    ", net_office_isConnect=" + net_office_isConnect +
                    ", net_office_isDefence=" + net_office_isDefence +
                    ", net_office_firewall='" + net_office_firewall + '\'' +
                    ", net_office_otherWay='" + net_office_otherWay + '\'' +
                    ", net_s_isConnect=" + net_s_isConnect +
                    ", net_s_isDefence=" + net_s_isDefence +
                    ", net_s_vpn='" + net_s_vpn + '\'' +
                    ", net_s_firewall='" + net_s_firewall + '\'' +
                    ", net_s_ids='" + net_s_ids + '\'' +
                    ", net_s_ips='" + net_s_ips + '\'' +
                    ", net_s_brief='" + net_s_brief + '\'' +
                    ", net_p_isConnect=" + net_p_isConnect +
                    ", net_p_connectType=" + net_p_connectType +
                    ", net_clo_isConnect=" + net_clo_isConnect +
                    ", net_clo_connectType=" + net_clo_connectType +
                    ", net_clo_brand='" + net_clo_brand + '\'' +
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

    public UpMachine getUpMachine() {
        return upMachine;
    }

    public void setUpMachine(UpMachine upMachine) {
        this.upMachine = upMachine;
    }

    public DownMachine getDownMachine() {
        return downMachine;
    }

    public void setDownMachine(DownMachine downMachine) {
        this.downMachine = downMachine;
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
        return "PLC{" +
                "uid=" + uid +
                ", person=" + person +
                ", upMachine=" + upMachine +
                ", downMachine=" + downMachine +
                ", server=" + server +
                ", aSwitch=" + aSwitch +
                ", netInfo=" + netInfo +
                ", other=" + other +
                '}';
    }
}
