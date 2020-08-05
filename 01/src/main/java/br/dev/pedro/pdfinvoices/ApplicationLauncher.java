package br.dev.pedro.pdfinvoices;

import br.dev.pedro.pdfinvoices.web.PDFInvoiceServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); // bootstraps Tomcat’s HTTP engine

        // on context we can specify differents apps on the same tomcat
        // docBase can be a reference for a directory for static files
        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "myFirstServlet", new PDFInvoiceServlet());

        // load our servlet (otherwise it will load on the first request)
        servlet.setLoadOnStartup(1);

        // our servlet handle all the requests
        servlet.addMapping("/*");

        tomcat.start();
    }
}
