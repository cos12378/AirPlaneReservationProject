package Servlet;
import Module.ModuleManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Module.*;

@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // main 진입 시 모듈 메니저 초기화
        ModuleManager.getInstance().initModuleManager();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/reservation.html").forward(req, resp);
        super.doGet(req, resp);
        ReservationModule module = ModuleManager.getInstance().getReservationModuleByNowMobule();
        String text = req.getParameter("text");
        System.out.println(text);
        module.getAirPlaneList_by_Database();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
