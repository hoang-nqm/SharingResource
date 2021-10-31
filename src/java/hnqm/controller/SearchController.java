/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import hnqm.category.CategoryDAO;
import hnqm.category.CategoryDTO;
import hnqm.resource.ResourceDAO;
import hnqm.resource.ResourceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private static final String SUCCESS = "view.jsp";
    private static final String ERROR = "MainController?action=home";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            int index = 1;
            int pageSize = 5;
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }
            CategoryDAO cateDAO = new CategoryDAO();
            List<CategoryDTO> listCate = cateDAO.getAllCategory();
            HttpSession session = request.getSession();
            String name = request.getParameter("txtName");
            String category = request.getParameter("txtCategory");
            String from = request.getParameter("from");
            ResourceDAO resourceDAO = new ResourceDAO();
            int numberOfCarAvailable = -1;
            List<ResourceDTO> listResource = null;
            try {
                if (!name.isEmpty()) {
                    numberOfCarAvailable = resourceDAO.searchByName(name).size();
                    listResource = resourceDAO.searchByName(name);
                    int endPage = numberOfCarAvailable / 5;
                    if (numberOfCarAvailable % pageSize != 0) {
                        endPage++;
                        request.setAttribute("RESOURCE", listResource);
                        request.setAttribute("endPage", endPage);

                        session.setAttribute("CATEGORY", listCate);
                        url = SUCCESS;
                    }
                } else if (!from.isEmpty()) {
                    numberOfCarAvailable = resourceDAO.searchByUsingDate(from).size();
                    listResource = resourceDAO.searchByUsingDate(from);
                    int endPage = numberOfCarAvailable / 5;
                    if (numberOfCarAvailable % pageSize != 0) {
                        endPage++;
                        request.setAttribute("RESOURCE", listResource);
                        request.setAttribute("endPage", endPage);
                        session.setAttribute("CATEGORY", listCate);
                        url = SUCCESS;
                    }
                } else if (!category.equals("-1")) {
                    numberOfCarAvailable = resourceDAO.searchByCategoryID(category).size();
                    listResource = resourceDAO.searchByCategoryID(category);
                    int endPage = numberOfCarAvailable / 5;
                    if (numberOfCarAvailable % pageSize != 0) {
                        endPage++;
                        session.setAttribute("RESOURCE", listResource);
                        session.setAttribute("endPage", endPage);
                        session.setAttribute("CATEGORY", listCate);
                        url = SUCCESS;
                    }
                }
            } catch (Exception e) {
                numberOfCarAvailable = 0;
            }

            if (numberOfCarAvailable == 0 || numberOfCarAvailable == -1 && listResource == null) {
                request.setAttribute("NO_RECORD", "No Record to display");
                int count = resourceDAO.getNumberPage();
                int endPage = count / 5;
                if (count / 5 != 0) {
                    endPage++;
                }
                request.setAttribute("endPage", endPage);
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
