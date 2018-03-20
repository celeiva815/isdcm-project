/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.UserDAO;
import models.Users;
import utils.CookieHelper;

/**
 *
 * @author Tito
 */
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Users user = (Users) request.getSession().getAttribute("user");

        if (user == null) {
            String uuid = CookieHelper.getCookieValue(request, CookieHelper.COOKIE_NAME);

            if (uuid != null) {
                
                user = UserDAO.getInstance().findUserByUUID(uuid);

                if (user != null) {
                    request.login(user.getUsername(), user.getPassword());
                    request.getSession().setAttribute("user", user); // Login.
                    CookieHelper.addCookie(response, CookieHelper.COOKIE_NAME, uuid, CookieHelper.COOKIE_AGE); // Extends age.
                } else {
                    CookieHelper.removeCookie(response, CookieHelper.COOKIE_NAME);
                }
            }
        }

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }
    
}
