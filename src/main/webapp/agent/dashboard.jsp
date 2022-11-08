<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2> Agent Dashboard</h2>

    <div class="row mt-5">
        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #6200ea; color: #fff; cursor: pointer;" onclick="location.href='/agent/agents'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Agents</h3>
                        <span class="fa fa-user-shield fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View all the Agents.</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #6200ea; color: #fff; cursor: pointer;" onclick="location.href='/agent/clients'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Clients</h3>
                        <span class="fa fa-user-shield fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View all the clients.</p>
                </div>
            </div>
        </div>



        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #9c27b0; color: #fff; cursor: pointer;" onclick="location.href='/agent/sells/'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Sells</h3>
                        <span class="fa fa-money-check fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View sells.</p>
                </div>
            </div>
        </div>


        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #9c27b0; color: #fff; cursor: pointer;" onclick="location.href='/agent/insurance/'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Policies</h3>
                        <span class="fa fa-money-check fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View Policy.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/template/footer.jsp" %>