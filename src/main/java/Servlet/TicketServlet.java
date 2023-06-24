package Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import Enums.ModuleType;
import Module.*;


public class TicketServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // main 진입 시 모듈 메니저 초기화
        ModuleManager.getInstance().initModuleManager();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ModuleManager 의 인스턴스를 얻어온 후, changeModule 메서드를 호출하여 RESERVATION 모듈로 변경
        ModuleManager.getInstance().changeModule(ModuleType.RESERVATION);

        // ModuleManager 의 인스턴스를 얻어온 후, getReservationModuleByNowModule 메서드를 호출
        // 현재 모듈의 ReservationModule 인스턴스를 가져온다
        // module 변수에 현재 실행 중인 예약 모듈의 인스턴스가 저장된다.
        ReservationModule module = ModuleManager.getInstance().getReservationModuleByNowMobule();


//        resp.addCookie(new Cookie("LoginID", "test"));

        // 요청에 포함된 쿠키들 중에서 이름이 "LoginID"인 쿠키의 값을 가져와 userID 변수에 저장
        Cookie[] cookies = req.getCookies();
        String userID = "";

        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("LoginID"))
                userID = cookies[i].getValue();
        }

        // getMyTickets_by_Database() 메서드를 실행하여 데이터베이스로부터 비행기 티켓 목록을 가져와 데이터를 반환
        req.setAttribute("ticketList", module.getMyTickets_by_Database(userID));

        // 현재 요청을 ticketList.jsp로 전달
        req.getRequestDispatcher("views/ticketList.jsp").forward(req, resp);
    }
}
