package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Job;
import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Daos.EmployeeDao;
import com.example.webapphr1_2023.Daos.LocationDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "listar" : req.getParameter("action");

        RequestDispatcher view;

        switch(action){
            case "listar":

                LocationDao locationdao = new LocationDao();
                ArrayList<Location> listaLocation=locationdao.listarLocation();
                req.setAttribute("listaLocation", listaLocation);
                view = req.getRequestDispatcher("location/list.jsp");
                view.forward(req, resp);
                break;
            case "crear":
                view = req.getRequestDispatcher("location/nuevaLocation.jsp");
                view.forward(req, resp);
                break;

            case "editar":
                if (req.getParameter("id") != null) {
                    int locationId = Integer.parseInt(req.getParameter("id"));
                    Location location = LocationDao.obtenerLocation(locationId);

                    if (location != null) {

                        req.setAttribute("location", location);
                        view = req.getRequestDispatcher("location/editarLocation.jsp");
                        view.forward(req, resp);
                    } else {
                        resp.sendRedirect("LocationServlet?action=listar");
                    }
                } else {
                    resp.sendRedirect("LocationServlet?action=listar");
                }
                break;

        }
        //req.setAttribute("locationList", new ArrayList<>());
        view = req.getRequestDispatcher("location/list.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");


        LocationDao locationDao = new LocationDao();

        Location location = new Location();
        location.setStreetAddress(req.getParameter("first_name"));
        location.setPostalCode(req.getParameter("last_name"));
        location.setCity(req.getParameter("email"));
        location.setStateProvince(req.getParameter("phone"));
        location.setCountry(req.getParameter("hire_date"));

        Job job = new Job();
        job.setJobId(req.getParameter("job_id"));
        employee.setJob(job);

        employee.setSalary(new BigDecimal(req.getParameter("salary")));
        employee.setCommissionPct(req.getParameter("commission").equals("") ? null : new BigDecimal(req.getParameter("commission")));

        Employee manager = new Employee();
        manager.setEmployeeId(Integer.parseInt(req.getParameter("manager_id")));
        employee.setManager(manager);

        Department department = new Department();
        department.setDepartmentId(Integer.parseInt(req.getParameter("department_id")));
        employee.setDepartment(department);

        switch (action) {
            case "guardar":
                employeeDao.guardarEmpleado(employee);

                resp.sendRedirect("EmployeeServlet");
                break;
            case "actualizar":
                employee.setEmployeeId(Integer.parseInt(req.getParameter("employee_id"))); //no olvidar que para actualizar se debe enviar el ID

                employeeDao.actualizarEmpleado(employee);

                resp.sendRedirect("EmployeeServlet");

                break;
        }
        super.doPost(req, resp);
    }
}