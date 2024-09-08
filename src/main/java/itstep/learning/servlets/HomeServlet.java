package itstep.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import itstep.learning.data.dal.PageVisitDao;
import itstep.learning.data.dto.PageVisit;
import itstep.learning.services.generators.GeneratorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@Singleton
public class HomeServlet extends HttpServlet {
    private final GeneratorService basicPassw;
    private final GeneratorService fasol;
    private final GeneratorService fName;
    private final GeneratorService otpPassw;
    private final PageVisitDao pageVisitDao;

    @Inject
    public HomeServlet(
            @Named("password") GeneratorService basicPassw,
            @Named("fasol") GeneratorService fasol,
            @Named("file") GeneratorService fName,
            @Named("OTP") GeneratorService otpPassw,
            PageVisitDao pageVisitDao
    ) {
        this.basicPassw = basicPassw;
        this.fasol = fasol;
        this.fName = fName;
        this.otpPassw = otpPassw;
        this.pageVisitDao = pageVisitDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String basicPassword = basicPassw.generate();
        String fasolPassword = fasol.generate();
        String fileName = fName.generate();
        String otpPassword = otpPassw.generate();
        req.setAttribute("basicPassword", basicPassword);
        req.setAttribute("fasol", fasolPassword);
        req.setAttribute("fileName", fileName);
        req.setAttribute("otp", otpPassword);
        //ViewData["fromServlet"] = "HomeServlet"
        req.setAttribute("fromServlet", "HomeServlet");
        req.setAttribute("pageBody", "index.jsp");
        req.setAttribute("fromServlet",
                basicPassw.generate() +
                        fasol.generate()

        );

        req.setAttribute("pageBody", "index.jsp");
        String pageUrl = req.getRequestURI();
        PageVisit visit = new PageVisit(0, pageUrl, new Timestamp(System.currentTimeMillis()));
        pageVisitDao.logVisit(visit);

        //return view("index.jsp")
        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }
}

