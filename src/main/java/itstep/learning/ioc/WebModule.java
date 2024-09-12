package itstep.learning.ioc;


import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.ServletModule;
import itstep.learning.filters.CharsetFilter;
import itstep.learning.filters.CorsFilter;
import itstep.learning.filters.UserFilter;
import itstep.learning.servlets.*;

public class WebModule extends ServletModule {
    @Override
    protected void configureServlets() {
        //reg filters
        filter("/*").through(CharsetFilter.class);
        filter("/*").through(CorsFilter.class);
        //filter("/*").through(UserFilter.class);

        //after servlets
        serve("/").with(HomeServlet.class);
        serve("/db").with(DbServlet.class);
        serve("/logs").with(LogServlet.class);
        serve("/user").with(UserServlet.class);
        serve("/logout").with(LogoutServlet.class);
        serve("/sign").with(SignInServlet.class);
    }
}
