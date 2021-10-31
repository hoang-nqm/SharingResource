/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.booking;

import hnqm.resource.ResourceDTO;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Minh Hoang
 */
public class RequestDTO implements Serializable {

    private String requestID;
    private String status;
    private String userID;
    private Date createDate;

    private ResourceDTO dto;

    public RequestDTO() {
    }

    public RequestDTO(String requestID, String status, String userID, Date createDate, ResourceDTO dto) {
        this.requestID = requestID;
        this.status = status;
        this.userID = userID;
        this.createDate = createDate;
        this.dto = dto;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResourceDTO getDto() {
        return dto;
    }

    public void setDto(ResourceDTO dto) {
        this.dto = dto;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
