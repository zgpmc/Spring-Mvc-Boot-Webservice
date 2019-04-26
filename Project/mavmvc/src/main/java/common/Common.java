package common;

import mode.MDUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 创建时间:2018/12/27
 * 创建人:Administrator
 * 描述:
 */
public class Common
{
    protected HttpServletRequest Request;
    protected HttpServletResponse Response;

    {
        Request = getRequest();
        Response = getResponse();
    }

    /**
     * 得到request
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //Request = request;
        return request;
    }

    /**
     * 得到response
     *
     * @return HttpServletResponse
     */
    public HttpServletResponse getResponse()
    {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 得到session
     *
     * @return HttpSession
     */
    public HttpSession getSession()
    {
        return getRequest().getSession(false);//getSession() 没有就创建 有就返回 false：没有不创建
    }

    public Cookie[] getCookie()
    {
        return Request.getCookies();
    }

    /**
     * 得到用户
     *
     * @return
     */
    public MDUser getUserFromSession()
    {
        return (MDUser) getSession().getAttribute("user");
    }
}
