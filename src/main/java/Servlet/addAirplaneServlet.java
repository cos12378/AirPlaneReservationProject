package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import Enums.ModuleType;
import Module.ModuleManager;
import Module.EditModule;
public class addAirplaneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EditModule module = getEditModule();
        req.setAttribute("airplaneList", module.getAirPlaneList_by_Database());

        req.getRequestDispatcher("/views/addAirplane.jsp").forward(req, resp);

        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }

    private EditModule getEditModule(){
        EditModule module = null;
        module = ModuleManager.getInstance().getEditModuleByNowMobule();
        if (module == null){
            ModuleManager.getInstance().changeModule(ModuleType.EDIT);
            module = ModuleManager.getInstance().getEditModuleByNowMobule();
        }

        return module;
    }
}
