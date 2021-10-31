/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.resource;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Minh Hoang
 */
public class ResourceDTO implements Serializable{
    private String resourceID;
    private String resourceName;
    private String color;
    private Date usingDate;    
    private String image;
    private int quantity;
    private String cateID;
    private String status;

    public ResourceDTO() {
    }

    public ResourceDTO(String resourceID, String resourceName, String color, Date usingDate, String image, int quantity, String cateID, String status) {
        this.resourceID = resourceID;
        this.resourceName = resourceName;
        this.color = color;
        this.usingDate = usingDate;
        this.image = image;
        this.quantity = quantity;
        this.cateID = cateID;
        this.status = status;
  
    }

    public ResourceDTO(String resourceID, String resourceName, String color, Date usingDate, String image, int quantity, String cateID) {
        this.resourceID = resourceID;
        this.resourceName = resourceName;
        this.color = color;
        this.usingDate = usingDate;
        this.image = image;
        this.quantity = quantity;
        this.cateID = cateID;
    }
    

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(Date usingDate) {
        this.usingDate = usingDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
}
