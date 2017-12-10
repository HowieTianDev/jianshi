package com.howietian.jianshi.entities;

/**
 * Created by HowieTian on 2017/11/13 0013.
 */

public class Other {
    private Integer uid;
    private Person person;
    private Terminal terminal;
    private Server server;
    private ZuNet zuNet;

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

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public ZuNet getZuNet() {
        return zuNet;
    }

    public void setZuNet(ZuNet zuNet) {
        this.zuNet = zuNet;
    }

    @Override
    public String toString() {
        return "Other{" +
                "uid=" + uid +
                ", person=" + person +
                ", terminal=" + terminal +
                ", server=" + server +
                ", zuNet=" + zuNet +
                '}';
    }

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

    public class Terminal{
        private Integer tHCompany;
        private String tHBrand;
        private String tHVersion;
        private Integer tHIsBorder;
        private String tHBuyTime;
        private Integer tOsType;
        private String tOsBrand;
        private Integer tOsIsTrue;
        private String tOsUpTime;
        private Integer tOsUpType;
        private String tOsUpBrief;
        private Integer tSCompany;
        private String tSBrand;
        private String tSVersion;
        private Integer tSIsBorder;
        private Integer tSIsTrue;
        private String tSUpTime;
        private Integer tSUpType;
        private String tSUpBrief;

        public Integer gettHCompany() {
            return tHCompany;
        }

        public void settHCompany(Integer tHCompany) {
            this.tHCompany = tHCompany;
        }

        public String gettHBrand() {
            return tHBrand;
        }

        public void settHBrand(String tHBrand) {
            this.tHBrand = tHBrand;
        }

        public String gettHVersion() {
            return tHVersion;
        }

        public void settHVersion(String tHVersion) {
            this.tHVersion = tHVersion;
        }

        public Integer gettHIsBorder() {
            return tHIsBorder;
        }

        public void settHIsBorder(Integer tHIsBorder) {
            this.tHIsBorder = tHIsBorder;
        }

        public String gettHBuyTime() {
            return tHBuyTime;
        }

        public void settHBuyTime(String tHBuyTime) {
            this.tHBuyTime = tHBuyTime;
        }

        public Integer gettOsType() {
            return tOsType;
        }

        public void settOsType(Integer tOsType) {
            this.tOsType = tOsType;
        }

        public String gettOsBrand() {
            return tOsBrand;
        }

        public void settOsBrand(String tOsBrand) {
            this.tOsBrand = tOsBrand;
        }

        public Integer gettOsIsTrue() {
            return tOsIsTrue;
        }

        public void settOsIsTrue(Integer tOsIsTrue) {
            this.tOsIsTrue = tOsIsTrue;
        }

        public String gettOsUpTime() {
            return tOsUpTime;
        }

        public void settOsUpTime(String tOsUpTime) {
            this.tOsUpTime = tOsUpTime;
        }

        public Integer gettOsUpType() {
            return tOsUpType;
        }

        public void settOsUpType(Integer tOsUpType) {
            this.tOsUpType = tOsUpType;
        }

        public String gettOsUpBrief() {
            return tOsUpBrief;
        }

        public void settOsUpBrief(String tOsUpBrief) {
            this.tOsUpBrief = tOsUpBrief;
        }

        public Integer gettSCompany() {
            return tSCompany;
        }

        public void settSCompany(Integer tSCompany) {
            this.tSCompany = tSCompany;
        }

        public String gettSBrand() {
            return tSBrand;
        }

        public void settSBrand(String tSBrand) {
            this.tSBrand = tSBrand;
        }

        public String gettSVersion() {
            return tSVersion;
        }

        public void settSVersion(String tSVersion) {
            this.tSVersion = tSVersion;
        }

        public Integer gettSIsBorder() {
            return tSIsBorder;
        }

        public void settSIsBorder(Integer tSIsBorder) {
            this.tSIsBorder = tSIsBorder;
        }

        public Integer gettSIsTrue() {
            return tSIsTrue;
        }

        public void settSIsTrue(Integer tSIsTrue) {
            this.tSIsTrue = tSIsTrue;
        }

        public String gettSUpTime() {
            return tSUpTime;
        }

        public void settSUpTime(String tSUpTime) {
            this.tSUpTime = tSUpTime;
        }

        public Integer gettSUpType() {
            return tSUpType;
        }

        public void settSUpType(Integer tSUpType) {
            this.tSUpType = tSUpType;
        }

        public String gettSUpBrief() {
            return tSUpBrief;
        }

        public void settSUpBrief(String tSUpBrief) {
            this.tSUpBrief = tSUpBrief;
        }

        @Override
        public String toString() {
            return "Terminal{" +
                    "tHCompany=" + tHCompany +
                    ", tHBrand='" + tHBrand + '\'' +
                    ", tHVersion='" + tHVersion + '\'' +
                    ", tHIsBorder=" + tHIsBorder +
                    ", tHBuyTime='" + tHBuyTime + '\'' +
                    ", tOsType=" + tOsType +
                    ", tOsBrand='" + tOsBrand + '\'' +
                    ", tOsIsTrue=" + tOsIsTrue +
                    ", tOsUpTime='" + tOsUpTime + '\'' +
                    ", tOsUpType=" + tOsUpType +
                    ", tOsUpBrief='" + tOsUpBrief + '\'' +
                    ", tSCompany=" + tSCompany +
                    ", tSBrand='" + tSBrand + '\'' +
                    ", tSVersion='" + tSVersion + '\'' +
                    ", tSIsBorder=" + tSIsBorder +
                    ", tSIsTrue=" + tSIsTrue +
                    ", tSUpTime='" + tSUpTime + '\'' +
                    ", tSUpType=" + tSUpType +
                    ", tSUpBrief='" + tSUpBrief + '\'' +
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
        private Integer se_s_company;
        private String se_s_brand;
        private String se_s_version;
        private Integer se_s_isBorder;
        private Integer se_s_isTrue;
        private String se_s_upTime;
        private Integer se_s_upType;
        private String se_s_upBrief;

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

        public Integer getSe_s_company() {
            return se_s_company;
        }

        public void setSe_s_company(Integer se_s_company) {
            this.se_s_company = se_s_company;
        }

        public String getSe_s_brand() {
            return se_s_brand;
        }

        public void setSe_s_brand(String se_s_brand) {
            this.se_s_brand = se_s_brand;
        }

        public String getSe_s_version() {
            return se_s_version;
        }

        public void setSe_s_version(String se_s_version) {
            this.se_s_version = se_s_version;
        }

        public Integer getSe_s_isBorder() {
            return se_s_isBorder;
        }

        public void setSe_s_isBorder(Integer se_s_isBorder) {
            this.se_s_isBorder = se_s_isBorder;
        }

        public Integer getSe_s_isTrue() {
            return se_s_isTrue;
        }

        public void setSe_s_isTrue(Integer se_s_isTrue) {
            this.se_s_isTrue = se_s_isTrue;
        }

        public String getSe_s_upTime() {
            return se_s_upTime;
        }

        public void setSe_s_upTime(String se_s_upTime) {
            this.se_s_upTime = se_s_upTime;
        }

        public Integer getSe_s_upType() {
            return se_s_upType;
        }

        public void setSe_s_upType(Integer se_s_upType) {
            this.se_s_upType = se_s_upType;
        }

        public String getSe_s_upBrief() {
            return se_s_upBrief;
        }

        public void setSe_s_upBrief(String se_s_upBrief) {
            this.se_s_upBrief = se_s_upBrief;
        }

        @Override
        public String toString() {
            return "Server{" +
                    "se_h_company=" + se_h_company +
                    ", se_h_brand='" + se_h_brand + '\'' +
                    ", se_h_version='" + se_h_version + '\'' +
                    ", se_h_isBorder=" + se_h_isBorder +
                    ", se_h_buyTime='" + se_h_buyTime + '\'' +
                    ", se_os_type=" + se_os_type +
                    ", se_os_brand='" + se_os_brand + '\'' +
                    ", se_os_isTrue=" + se_os_isTrue +
                    ", se_os_upTime='" + se_os_upTime + '\'' +
                    ", se_os_upType=" + se_os_upType +
                    ", se_os_upBrief='" + se_os_upBrief + '\'' +
                    ", se_s_company=" + se_s_company +
                    ", se_s_brand='" + se_s_brand + '\'' +
                    ", se_s_version='" + se_s_version + '\'' +
                    ", se_s_isBorder=" + se_s_isBorder +
                    ", se_s_isTrue=" + se_s_isTrue +
                    ", se_s_upTime='" + se_s_upTime + '\'' +
                    ", se_s_upType=" + se_s_upType +
                    ", se_s_upBrief='" + se_s_upBrief + '\'' +
                    '}';
        }
    }

    public class ZuNet{
        private Integer outIsBorder;
        private Integer outIsDefence;
        private String outVpn;
        private String outFireWall;
        private String outIds;
        private String outIps;
        private String outOtherWay;
        private String outProtocol;
        private Integer outWireless;
        private Integer outIsWireless;
        private Integer outIsInnerZuNet;

        private Integer inIsDefence;
        private String inRouter;
        private String inSwitch;
        private String inIds;
        private String inOtherWay;

        public Integer getOutIsBorder() {
            return outIsBorder;
        }

        public void setOutIsBorder(Integer outIsBorder) {
            this.outIsBorder = outIsBorder;
        }

        public Integer getOutIsDefence() {
            return outIsDefence;
        }

        public void setOutIsDefence(Integer outIsDefence) {
            this.outIsDefence = outIsDefence;
        }

        public String getOutVpn() {
            return outVpn;
        }

        public void setOutVpn(String outVpn) {
            this.outVpn = outVpn;
        }

        public String getOutFireWall() {
            return outFireWall;
        }

        public void setOutFireWall(String outFireWall) {
            this.outFireWall = outFireWall;
        }

        public String getOutIds() {
            return outIds;
        }

        public void setOutIds(String outIds) {
            this.outIds = outIds;
        }

        public String getOutIps() {
            return outIps;
        }

        public void setOutIps(String outIps) {
            this.outIps = outIps;
        }

        public String getOutOtherWay() {
            return outOtherWay;
        }

        public void setOutOtherWay(String outOtherWay) {
            this.outOtherWay = outOtherWay;
        }

        public String getOutProtocol() {
            return outProtocol;
        }

        public void setOutProtocol(String outProtocol) {
            this.outProtocol = outProtocol;
        }

        public Integer getOutWireless() {
            return outWireless;
        }

        public void setOutWireless(Integer outWireless) {
            this.outWireless = outWireless;
        }

        public Integer getOutIsWireless() {
            return outIsWireless;
        }

        public void setOutIsWireless(Integer outIsWireless) {
            this.outIsWireless = outIsWireless;
        }

        public Integer getOutIsInnerZuNet() {
            return outIsInnerZuNet;
        }

        public void setOutIsInnerZuNet(Integer outIsInnerZuNet) {
            this.outIsInnerZuNet = outIsInnerZuNet;
        }

        public Integer getInIsDefence() {
            return inIsDefence;
        }

        public void setInIsDefence(Integer inIsDefence) {
            this.inIsDefence = inIsDefence;
        }

        public String getInRouter() {
            return inRouter;
        }

        public void setInRouter(String inRouter) {
            this.inRouter = inRouter;
        }

        public String getInSwitch() {
            return inSwitch;
        }

        public void setInSwitch(String inSwitch) {
            this.inSwitch = inSwitch;
        }

        public String getInIds() {
            return inIds;
        }

        public void setInIds(String inIds) {
            this.inIds = inIds;
        }

        public String getInOtherWay() {
            return inOtherWay;
        }

        public void setInOtherWay(String inOtherWay) {
            this.inOtherWay = inOtherWay;
        }

        @Override
        public String toString() {
            return "ZuNet{" +
                    "outIsBorder=" + outIsBorder +
                    ", outIsDefence=" + outIsDefence +
                    ", outVpn='" + outVpn + '\'' +
                    ", outFireWall='" + outFireWall + '\'' +
                    ", outIds='" + outIds + '\'' +
                    ", outIps='" + outIps + '\'' +
                    ", outOtherWay='" + outOtherWay + '\'' +
                    ", outProtocol='" + outProtocol + '\'' +
                    ", outWireless=" + outWireless +
                    ", outIsWireless=" + outIsWireless +
                    ", outIsInnerZuNet=" + outIsInnerZuNet +
                    ", inIsDefence=" + inIsDefence +
                    ", inRouter='" + inRouter + '\'' +
                    ", inswitch='" + inSwitch + '\'' +
                    ", inIds='" + inIds + '\'' +
                    ", inOtherWay='" + inOtherWay + '\'' +
                    '}';
        }
    }

}
