/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import hnqm.account.AccountDTO;
import hnqm.booking.RequestDAO;
import hnqm.booking.RequestDTO;
import hnqm.resource.ResourceDAO;
import hnqm.resource.ResourceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.mail.Quota;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "RequestController", urlPatterns = {"/RequestController"})
public class RequestController extends HttpServlet {

    private static final String ERROR = "MainController?action=loginAccount";
    private static final String SUCCESS = "GetAllCategoryController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("LOGIN_USER");
            if (account == null) {
                request.setAttribute("NOT_LOGIN", "Please login to booking resource");
            } else {
                String id = request.getParameter("resourceID");
                ResourceDAO resourceDAO = new ResourceDAO();
                ResourceDTO resource = resourceDAO.getResourceByID(id);
                RequestDTO req = new RequestDTO(id, "New", account.getUserID(), new Date(), resource);
                RequestDAO requestDAO = new RequestDAO();
                requestDAO.insertRequest(req);
                resourceDAO.updateResource(id);
                request.setAttribute("REQUEST_SUCCESS", "Request successfully!");
                url = SUCCESS;

            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
