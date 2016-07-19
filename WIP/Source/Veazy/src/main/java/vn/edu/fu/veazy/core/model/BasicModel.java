/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Hoang Linh
 */
@MappedSuperclass
public abstract class BasicModel implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "createDate",columnDefinition = "LONG" ,nullable = true)
    private Long createDate;
    @Column(name = "updateDate",columnDefinition = "LONG" ,nullable = true)
    private Long updateDate;
    @Column(name = "deleteDate",columnDefinition = "LONG" ,nullable = true)
    private Long deleteDate;
    @Column(name = "deleteFlag",columnDefinition = "LONG" ,nullable = true)
    private boolean deleteFlag;

    public BasicModel() {
    }

    public BasicModel(Integer id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag) {
        super();
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
        this.deleteFlag = deleteFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
