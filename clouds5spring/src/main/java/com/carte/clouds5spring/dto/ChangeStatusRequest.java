package com.carte.clouds5spring.dto;

public class ChangeStatusRequest 
{
    private Integer routeProblemeId;
    private Integer routeStatusId;

    public Integer getRouteProblemeId() {
        return routeProblemeId;
    }
    public void setRouteProblemeId(Integer routeProblemeId) {
        this.routeProblemeId = routeProblemeId;
    }
    public Integer getRouteStatusId() {
        return routeStatusId;
    }
    public void setRouteStatusId(Integer routeStatusId) {
        this.routeStatusId = routeStatusId;
    }
}
