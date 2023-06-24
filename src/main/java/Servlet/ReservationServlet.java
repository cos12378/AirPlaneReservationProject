package Servlet;

import Data.Airplane;
import Data.Seat;
import Data.Ticket;
import Enums.ModuleType;
import Module.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Module.*;

public class ReservationServlet extends HttpServlet {
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

        // getAirPlaneList_by_Database() 메서드를 실행하여 데이터베이스로부터 비행기 목록을 가져와 데이터를 반환
        req.setAttribute("airplaneList", module.getAirPlaneList_by_Database());

//        resp.addCookie(new Cookie("LoginID", "test"));

        // 현재 요청을 airplaneList.jsp로 전달
        req.getRequestDispatcher("views/airplaneList.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ModuleManager 의 인스턴스를 얻어온 후, changeModule 메서드를 호출하여 RESERVATION 모듈로 변경
        ModuleManager.getInstance().changeModule(ModuleType.RESERVATION);

        // ModuleManager 의 인스턴스를 얻어온 후, getReservationModuleByNowModule 메서드를 호출
        // 현재 모듈의 ReservationModule 인스턴스를 가져온다
        // module 변수에 현재 실행 중인 예약 모듈의 인스턴스가 저장된다.
        ReservationModule module = ModuleManager.getInstance().getReservationModuleByNowMobule();

        // 요청에 포함된 쿠키들 중에서 이름이 "LoginID"인 쿠키의 값을 가져와 userID 변수에 저장
        Cookie[] cookies = req.getCookies();
        String userID = "";

        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("LoginID"))
                userID = cookies[i].getValue();
        }

        // 요청한 정보를 가져와 변수에 저장
        String airplaneName = req.getParameter("airplaneName");
        String seatName = req.getParameter("seatName");

        // reservationAirPlane_Step2() 메서드의 비행기 이름, 좌석 이름, 유저 아이디를 인자로 전달하여 비행기 예약 작업 실행
        module.reservationAirPlane_Step2(airplaneName, seatName, userID);

        // getSeats_by_Database() 메서드의 비행기 이름을 인자로 전달하여 해당 비행기의 좌석 목록 데이터 반환
        List<Seat> seatList = module.getSeats_by_Database(airplaneName);

        // seatList를 jsp로 전달
        req.setAttribute("seatList2", seatList);

        // message를 jsp로 전달
        req.setAttribute("message", "Your reservation has been confirmed!");

        // 현재 요청을 confirmation.jsp로 전달
        req.getRequestDispatcher("views/confirmation.jsp").forward(req, resp);
    }
}
