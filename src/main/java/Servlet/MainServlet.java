package Servlet;
import Module.ModuleManager;
import Module.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class MainServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {
        //main 진입 시 모듈 메니저 초기화
        ModuleManager.getInstance().initModuleManager();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainModule module = ModuleManager.getInstance().getMainModuleByNowMobule();

        super.doPost(req, resp);
    }
}
