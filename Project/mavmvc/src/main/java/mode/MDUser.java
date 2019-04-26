package mode;

import java.util.Date;

/**
 * 创建时间:2019/02/25
 * 创建人:Administrator
 * 描述:
 */
public class MDUser
{
    public String USERID;
    public String LOGINNAME;
    public String USERNAME;
    public Date LOGINTIME;
    public Date CREATETIME;
    public String SSID;
    public String LIKENAME;
    public Integer ISONLINE;

    public Integer getONELINE()
    {
        return ISONLINE;
    }

    public void setONELINE(Integer ONELINE)
    {
        this.ISONLINE = ONELINE;
    }

    public String getLOGINNAME()
    {
        return LOGINNAME;
    }

    public void setLOGINNAME(String LOGINNAME)
    {
        this.LOGINNAME = LOGINNAME;
    }

    public String getUSERNAME()
    {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME)
    {
        this.USERNAME = USERNAME;
    }

    public String getLIKENAME()
    {
        return LIKENAME;
    }

    public void setLIKENAME(String LIKENAME)
    {
        this.LIKENAME = LIKENAME;
    }

    public Date getLOGINTIME()
    {
        return LOGINTIME;
    }

    public void setLOGINTIME(Date LOGINTIME)
    {
        this.LOGINTIME = LOGINTIME;
    }

    public Date getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(Date CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }

    public String getSSID()
    {
        return SSID;
    }

    public void setSSID(String SSID)
    {
        this.SSID = SSID;
    }

    public String getUSERID()
    {
        return USERID;
    }

    public void setUSERID(String USERID)
    {
        this.USERID = USERID;
    }
}
