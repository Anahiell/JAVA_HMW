package itstep.learning.ioc;


import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.ServletModule;
import itstep.learning.filters.CharsetFilter;
import itstep.learning.filters.CorsFilter;
import itstep.learning.servlets.DbServlet;
import itstep.learning.servlets.HomeServlet;
import itstep.learning.servlets.LogServlet;

public class WebModule extends ServletModule {
    @Override
    protected void configureServlets() {
        //reg filters
        filter("/*").through(CharsetFilter.class);
        filter("/*").through(CorsFilter.class);

        //after servlets
        serve("/").with(HomeServlet.class);
        serve("/db").with(DbServlet.class);
        serve("/logs").with(LogServlet.class);
    }
}
