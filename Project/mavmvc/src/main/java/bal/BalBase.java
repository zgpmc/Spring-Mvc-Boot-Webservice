package bal;

import common.ComEnum.EnumResult;
import common.Common;
import db.DBConnection;
import mode.MDResultMsg;
import mode.MDUser;
import tool.ToolException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * 创建时间:2019/02/25
 * 创建人:pmc
 * 描述:
 */
public class BalBase extends Common
{
    protected String loginMsg = "";
    protected ToolException toolException = new ToolException();
    DBConnection DB = new DBConnection();

    /**
     * 保存用户
     *
     * @param mdUser
     */
    public void setUser(MDUser mdUser)
    {
        HttpSession session = getSession();
        if (null != session)
        {
            session.setAttribute("user", mdUser);
            DB.update("update sys_user set oneline=1 where userid=?", mdUser.USERID);
        }
    }

    /**
     * 用户下线
     *
     * @param mdUser
     */
    public String removeUser(MDUser mdUser)
    {
        HttpSession session = getSession();
        session.invalidate();
        MDResultMsg mdResultMsg = new MDResultMsg(EnumResult.href, "index.html", true);
        return mdResultMsg.toString();
    }

    /**
     * 用户是否登录
     *
     * @param mdUser
     * @return
     */
    public boolean isLoging(MDUser mdUser)
    {
        HttpSession session = getSession();
        if (session == null)
        {
            session = session == null ? Request.getSession() : session;
            session.setMaxInactiveInterval(30);//session 有效时间
            mdUser.SSID = session.getId();
            setUser(mdUser);
            return false;
        }
        loginMsg = "已有用户登录！请注销后重试！";
        return true;
    }

    /**
     * 得到cookie值
     *
     * @param key
     * @return
     */
    public String getCookie(String key)
    {
        Cookie[] cookies = getCookie();
        String res = "";
        for (Cookie cookie : cookies)
        {
            if (cookie.getName().toLowerCase() == key.toLowerCase())
            {
                res = cookie.getValue();
                break;
            }
        }
        return res;
    }

    public MDUser getUser(String userid)
    {
        return DB.selectFirstOrNull(MDUser.class, "select * from sys_user where userid=?", userid);
    }
}
