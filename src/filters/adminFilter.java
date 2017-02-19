package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AdminFilter", urlPatterns = { "/admin.index.jsf","/admin.istatistikLS.jsf","/admin.kullaniciDS.jsf","/admin.sinavDS.jsf","/admin.sinavEkle.jsf","/admin.soruEkle.jsf","/admin.soruLS.jsf","/admin.profil.jsf"})
public class adminFilter implements Filter{
	public adminFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
 
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
 
            String reqURI = reqt.getRequestURI();
            if (reqURI.indexOf("/uyeGiris.jsf") >= 0
                    || (ses != null && ses.getAttribute("admin") != null|| reqURI.indexOf("/public/") >= 0
                            || reqURI.contains("javax.faces.resource"))
                   )
                chain.doFilter(request, response);
            else
                resp.sendRedirect(reqt.getContextPath() + "/uyeGiris.jsf");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
    @Override
    public void destroy() {
 
    }
}

