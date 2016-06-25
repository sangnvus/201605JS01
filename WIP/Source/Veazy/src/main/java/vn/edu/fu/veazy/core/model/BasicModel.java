/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import java.io.Serializable;

/**
 *
 * @author Hoang Linh
 */
public abstract class BasicModel implements Serializable {

    private Long id;
    private Long createDate;
    private Long updateDate;
    private Long deleteDate;
    private Double deleteFlag;

    public BasicModel() {
    }

    public BasicModel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public Long getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Long deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Double getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Double deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
