package common;

import common.ComEnum.EnumResult;
import mode.MDResultMsg;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 创建时间:2019/03/08
 * 创建人:pmc
 * 描述:
 */
public class ComFilter implements Filter
{
    private String loginpath = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        loginpath = filterConfig.getInitParameter("loginpath");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().toLowerCase();
        HttpSession session = request.getSession();
        if (!loginpath.contains(url))
        {
            Object user = session.getAttribute("user");
            if (user != null)
            {
                filterChain.doFilter(servletRequest, servletResponse);
            } else
            {
                String requestType = request.getHeader("X-Requested-With");
//判断是否是ajax请求
                if (requestType != null && "XMLHttpRequest".equals(requestType))
                {
                    MDResultMsg mdResultMsg = new MDResultMsg(EnumResult.href, "index.html", true);
                    response.getWriter().write(mdResultMsg.toString());
                } else
                {
                    response.sendRedirect(request.getContextPath() + "/" + loginpath);
                }
                return;
            }
        } else
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy()
    {

    }
}
