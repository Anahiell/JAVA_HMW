package itstep.learning.servlets;

import com.google.gson.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.data.dal.UserDao;
import itstep.learning.data.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Singleton
public class UserServlet extends HttpServlet {
    private final UserDao userDao;

    @Inject
    public UserServlet(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("userId");
        if (userIdParam != null) {
            try {
                UUID userId = UUID.fromString(userIdParam);
                userDao.deleteUser(userId); // Удаление пользователя

                resp.sendRedirect(req.getContextPath() + "/");
            } catch (IllegalArgumentException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат ID пользователя");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID пользователя не указан");
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
        JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);
        String username = body.get("name").getAsString();
        String password = body.get("password").getAsString();
        String email = body.get("email").getAsString();
        String name = body.get("name").getAsString();
        JsonObject respBody;
        if(name == null || email == null || password == null)
        {
            respBody = getErrorBody(req,"Bad Request: 'name' or 'email' or 'password' missed or empty");
        }
        else {
            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setPasswordHash(password);
            if (userDao.signupUser(user)) {

                respBody = getSuccessBody(req, gson.toJsonTree(user));
            } else {
                respBody = getErrorBody(req, "Email already taken or other error. Check logs for more details.");
            }
        }
        resp.setContentType("application/json");
        resp.getWriter().print(gson.toJson(respBody));
    }

    private JsonObject getSuccessBody(HttpServletRequest req, JsonElement body) {
        JsonObject respBody = new JsonObject();
        JsonObject status = new JsonObject();
        status.addProperty("isOk", true);
        status.addProperty("code", 0);
        status.addProperty("httpCode", 200);
        status.addProperty("phrase", "OK");
        respBody.add("status", status);
        JsonObject meta = new JsonObject();
        meta.addProperty("service", "User");
        meta.addProperty("action", "SignUp");
        meta.addProperty("location", req.getContextPath() + "/user");
        meta.addProperty("serverTime", System.currentTimeMillis());
        meta.addProperty("locale", "uk-UA");
        meta.addProperty("count", 0);
        respBody.add("meta", meta);
        respBody.add("body", body);
        return respBody;
    }

    private JsonObject getErrorBody(HttpServletRequest req, String text) {
        JsonObject respBody = new JsonObject();
        JsonObject status = new JsonObject();
        status.addProperty("isOk", false);
        status.addProperty("code", -1);
        status.addProperty("httpCode", 400);
        status.addProperty("phrase", text);
        respBody.add("status", status);
        JsonObject meta = new JsonObject();
        meta.addProperty("service", "User");
        meta.addProperty("action", "SignUp");
        meta.addProperty("location", req.getContextPath() + "/user");
        meta.addProperty("serverTime", System.currentTimeMillis());
        meta.addProperty("locale", "uk-UA");
        meta.addProperty("count", 0);
        respBody.add("meta", meta);
        respBody.add("body", new JsonObject()); // Empty body for errors
        return respBody;
    }
}
