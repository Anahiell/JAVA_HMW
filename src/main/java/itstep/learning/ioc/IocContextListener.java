package itstep.learning.ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class IocContextListener extends GuiceServletContextListener {
//делаем сборку инжекций
    @Override
    protected Injector getInjector() {
    return Guice.createInjector(
            new ServiceModule(),
            new WebModule()
    );
}


}
