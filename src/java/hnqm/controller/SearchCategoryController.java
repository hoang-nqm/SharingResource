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
import java.util.List;
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
@WebServlet(name = "SearchCategoryController", urlPatterns = {"/SearchCategoryController"})
public class SearchCategoryController extends HttpServlet {

    private static final String SUCCESS = "view.jsp";
    private static final String ERROR = "MainController?action=home";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int index = 1;
            int pageSize = 20;
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }
            CategoryDAO cateDAO = new CategoryDAO();
            List<CategoryDTO> listCate = cateDAO.getAllCategory();
            HttpSession session = request.getSession();
            ResourceDAO resourceDAO = new ResourceDAO();
            String cateID = request.getParameter("cateID");
            int numberOfResourceAvailable = -1;
            List<ResourceDTO> listResource = null;
            try {

                if (!cateID.isEmpty()) {
                    numberOfResourceAvailable = resourceDAO.searchByCategoryID(cateID).size();
                    listResource = resourceDAO.searchByCategoryID(cateID);
                    int endPage = numberOfResourceAvailable / 20;
                    if (numberOfResourceAvailable % pageSize != 0) {
                        endPage++;
                        session.setAttribute("RESOURCE", listResource);
                        session.setAttribute("endPage", endPage);
                        request.setAttribute("CATEGORY", listCate);
                        url = SUCCESS;
                    }
                }
            } catch (Exception e) {
                numberOfResourceAvailable = 0;
            }
            if (numberOfResourceAvailable == 0 || numberOfResourceAvailable == -1 && listResource == null) {
                request.setAttribute("NO_RECORD", "No Record to display");
                int count = resourceDAO.getNumberPage();
                int endPage = count / 20;
                if (count / 20 != 0) {
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
