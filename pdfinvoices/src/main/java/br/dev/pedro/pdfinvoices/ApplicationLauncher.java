package br.dev.pedro.pdfinvoices;

import br.dev.pedro.pdfinvoices.context.ApplicationConfiguration;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class ApplicationLauncher {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); // bootstraps Tomcat’s HTTP engine

        // on context we can specify differents apps on the same tomcat
        // docBase can be a reference for a directory for static files
        Context tomcatCtx = tomcat.addContext("", null);

        // create an ApplicationContext
        WebApplicationContext appCtx = createApplicationContext(tomcatCtx.getServletContext());

        // create a dispatcher that will handle all requests and send to controllers
        // it need an WebApplicationContext to know where to send the requests
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appCtx);
        Wrapper servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet);

        // load our servlet (otherwise it will load on the first request)
        servlet.setLoadOnStartup(1);

        // our servlet handle all the requests
        servlet.addMapping("/*");

        tomcat.start();
    }

    public static WebApplicationContext createApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfiguration.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();
        return ctx;
    }
}
