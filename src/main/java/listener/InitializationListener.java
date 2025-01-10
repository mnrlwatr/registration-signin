package listener;

import constant.HibernateCfg;
import dao.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.UserService;


@WebListener
public class InitializationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        SessionFactory sessionFactory = new Configuration().configure(HibernateCfg.HIBERNATE_CFG_FILE).buildSessionFactory();
        servletContext.setAttribute("userService", new UserService(new BCryptPasswordEncoder(),new UserDAO(sessionFactory)));
    }
}
