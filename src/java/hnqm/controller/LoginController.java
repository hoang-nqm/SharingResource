/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import hnqm.account.AccountDAO;
import hnqm.account.AccountDTO;
import hnqm.utils.JavaMail;
import hnqm.utils.VerifyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "MainController?action=loginAccount";
    private static final String USER = "GetAllCategoryController";
    private static final String ADMIN = "ManagerController";
    private static final String VERIFY = "MainController?action=verify";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            AccountDAO accountDAO = new AccountDAO();
            boolean checkEmail = accountDAO.checkEmail(email);
            HttpSession session = request.getSession();
            String err = "";
            boolean valid = VerifyUtils.verify(gRecaptchaResponse);
            if (checkEmail) {
                AccountDTO account = accountDAO.checkAccount(email, password);
                if (account != null && valid) {
                    if (account.getRolID().equals("002")) {
                        if (account.getStatus().equalsIgnoreCase("New")) {
                            int randomCode = ThreadLocalRandom.current().nextInt(10000, 99999);
                            String verifyCode = String.valueOf(randomCode);
                            JavaMail.sendMail(email, verifyCode);
                            session.setAttribute("VERIFY_CODE", verifyCode);
                            request.setAttribute("VERIFY_NOTIFY", "Input code get from your mail to verify !");
                            url = VERIFY;
                        } else if (account.getStatus().equalsIgnoreCase("Active")) {
                            url = USER;
                        }
                    } else if (account.getRolID().equals("001")) {
                        url = ADMIN;
                    }
                    Cookie cookie = new Cookie(email, password);
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cookie);
                    session.setAttribute("LOGIN_USER", account);
                } else {
                    if (!valid) {
                        err = "Pls verify captcha";
                        request.setAttribute("ERROR", err);
                    } else {
                        err = "Wrong password";
                        request.setAttribute("EMAIL", email);
                        request.setAttribute("ERROR", err);
                    }
                }

            } else {
                err = "Not found account";
                request.setAttribute("ERROR", err);

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
