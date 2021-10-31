/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String LOGIN = "LoginController";
    private static final String VERIFY = "VerifyController";
    private static final String CREATE = "CreateController";
    private static final String LOGOUT = "LogoutController";
    private static final String CHECK_SEARCH = "SearchController";
    private static final String REQUEST = "RequestController";
    private static final String REDIRECT_MANAGER = "manager.jsp";
    private static final String REDIRECT_HOME = "view.jsp";
    private static final String REDIRECT_LOGIN = "login.jsp";
    private static final String REDIRECT_REGISTER = "createaccount.jsp";
    private static final String REDIRECT_VERIFY = "verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("loginAccount".equals(action)) {
                url = REDIRECT_LOGIN;
            } else if ("Verify".equals(action)) {
                url = VERIFY;
            } else if ("Register".equals(action)) {
                url = CREATE;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Search".equals(action)) {
                url = CHECK_SEARCH;
            } else if ("Request".equals(action)) {
                url = REQUEST;

            } else if ("RegisterAccount".equals(action)) {
                url = REDIRECT_REGISTER;
            } else if ("verify".equals(action)) {
                url = REDIRECT_VERIFY;
            } else if ("home".equals(action)) {
                url = REDIRECT_HOME;
            } else if ("manager".equals(action)) {
                url = REDIRECT_MANAGER;
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
