package controller;

import bal.BalLogin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建时间:2018/12/14
 * 创建人:Administrator
 * 描述:
 */
@Controller
@Scope("prototype")
@RequestMapping("/home")
@ResponseBody
public class CtrolLogin
{
    @RequestMapping("/login")
    public String login(String name, String psd)
    {
        BalLogin balLogin = new BalLogin();
        return balLogin.checkLogin(name, psd);
    }
}
