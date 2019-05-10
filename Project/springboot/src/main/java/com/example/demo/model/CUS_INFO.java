package com.example.demo.model;

import java.util.Date;

public class CUS_INFO {
    private Integer cusId;

    private String cusName;

    private String cusIdno;

    private String cusPhone1;

    private String cusPhone2;

    private String cusPhone3;

    private Date makedate;

    private Date modifydate;

    private String operator;

    public CUS_INFO(Integer cusId, String cusName, String cusIdno, String cusPhone1, String cusPhone2, String cusPhone3, Date makedate, Date modifydate, String operator) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusIdno = cusIdno;
        this.cusPhone1 = cusPhone1;
        this.cusPhone2 = cusPhone2;
        this.cusPhone3 = cusPhone3;
        this.makedate = makedate;
        this.modifydate = modifydate;
        this.operator = operator;
    }

    public CUS_INFO() {
        super();
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName == null ? null : cusName.trim();
    }

    public String getCusIdno() {
        return cusIdno;
    }

    public void setCusIdno(String cusIdno) {
        this.cusIdno = cusIdno == null ? null : cusIdno.trim();
    }

    public String getCusPhone1() {
        return cusPhone1;
    }

    public void setCusPhone1(String cusPhone1) {
        this.cusPhone1 = cusPhone1 == null ? null : cusPhone1.trim();
    }

    public String getCusPhone2() {
        return cusPhone2;
    }

    public void setCusPhone2(String cusPhone2) {
        this.cusPhone2 = cusPhone2 == null ? null : cusPhone2.trim();
    }

    public String getCusPhone3() {
        return cusPhone3;
    }

    public void setCusPhone3(String cusPhone3) {
        this.cusPhone3 = cusPhone3 == null ? null : cusPhone3.trim();
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}