package com.carte.clouds5spring.service;

import com.carte.clouds5spring.entity.*;
public class Hservice 
{
    public static String getProblemeRoutier()
    {

        return "Service: Route Probleme Data";
    }
    public static String getProblemeDetail(String id)
    {
        return "Service: Route Probleme Data for ID: " + id;
    }
    public static String getProblemeDashboard()
    {
        return "Service: Route Probleme Dashboard Data";
    }
}
