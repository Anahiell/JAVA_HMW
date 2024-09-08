package itstep.learning.ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import javax.servlet.ServletContextEvent;

public class IocContextListener extends GuiceServletContextListener {
    private DbModule dbModule;

    //делаем сборку инжекций
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new ServiceModule(),
                new WebModule(),
                dbModule = new DbModule()
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (dbModule != null) {
            dbModule.close();
        }
        super.contextDestroyed(servletContextEvent);
    }


}
