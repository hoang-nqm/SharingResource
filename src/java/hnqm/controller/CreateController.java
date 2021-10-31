/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import hnqm.account.AccountDAO;
import hnqm.account.AccountDTO;
import hnqm.account.CreateAccountErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

     private static final String ERROR = "MainController?action=RegisterAccount";
    private static final String SUCCESS = "MainController?action=loginAccount";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            String userId = request.getParameter("txtUserId");
            String fullName = request.getParameter("txtFullName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            boolean check = true;
            CreateAccountErrorDTO err = new CreateAccountErrorDTO();
            AccountDAO accountDAO = new AccountDAO();
            if (accountDAO.checkEmail(userId)) {
                err.setUserIDError("UserID with this mail already existed !");
                check = false;
            } else {
                if (userId.isEmpty()) {
                    err.setUserIDError("UserID not empty");
                    check = false;
                } else {
                    String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(userId);
                    if (!matcher.find()) {
                        err.setUserIDError("UserID as mail");
                        check = false;
                    }
                }
            }
            if (fullName.isEmpty()) {
                err.setFullNameError("Fullname not empty");
                check = false;
            }
            if (password.isEmpty()) {
                err.setPasswordError("Pasword not empty");
                check = false;
            }
            if (password.length() < 6 || password.length() > 11) {
                err.setPasswordError("Password length from 6 to 11");
                check = false;
            }
            if (!confirm.equals(password)) {
                err.setConfirmError("Confirm not match password");
                check = false;
            }
            if (address.isEmpty()) {
                err.setAddressError("Address not empty");
                check = false;
            }
            if (address.length() > 500) {
                err.setAddressError("Address not greater than 500 character");
                check = false;
            }
            String regexPhone = "(03|09[2|6|8|9])+([0-9]{8})\\b";
            Pattern pattern = Pattern.compile(regexPhone);
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.find()) {
                err.setPhoneError("Phease input phone number valid format [0356449764] Length[10] ");
                check = false;
            }
            if (check) {
                AccountDTO account = new AccountDTO(userId, fullName, password, "002", "New", new Date(), address, phone);
                accountDAO.createAccount(account);
                request.setAttribute("SUCCESS", "Register successully");
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR_CREATE", err);
                AccountDTO account = new AccountDTO(userId, fullName, password, address, phone);
                request.setAttribute("confirm", confirm);
                request.setAttribute("ACCOUNT", account);
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
