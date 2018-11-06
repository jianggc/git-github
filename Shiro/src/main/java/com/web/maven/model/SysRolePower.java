package com.web.maven.model;

import java.io.Serializable;

public class SysRolePower implements Serializable {
    private Long rId;

    private Long pId;

    private static final long serialVersionUID = 1L;

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRolePower other = (SysRolePower) that;
        return (this.getrId() == null ? other.getrId() == null : this.getrId().equals(other.getrId()))
            && (this.getpId() == null ? other.getpId() == null : this.getpId().equals(other.getpId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getrId() == null) ? 0 : getrId().hashCode());
        result = prime * result + ((getpId() == null) ? 0 : getpId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rId=").append(rId);
        sb.append(", pId=").append(pId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}