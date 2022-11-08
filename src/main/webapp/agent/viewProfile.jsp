<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/agent/dashboard'"> Agent </h2>
        <h3 class="ms-2">${agent.agentId}</h3>



    </div>
    <div class="row justify-content-center">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <button class="btn btn-primary me-md-2" type="button" onclick="location.href = '/user/change-password'">Change Password</button>
      </div>
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Username</th>
                    <td style="width: 50%">${user.username}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Agent Id</th>
                    <td style="width: 50%">${agent.agentId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Agent Name</th>
                    <td style="width: 50%">${agent.firstName} ${agent.middleName} ${agent.lastName}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Address</th>
                    <td style="width: 50%">${agent.houseNo} ${agent.landmark} ${agent.city}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Commision</th>
                    <td style="width: 50%">${agent.commision}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Employee Id</th>
                    <td style="width: 50%">${agent.employeeId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Admin Id</th>
                    <td style="width: 50%">${agent.admin_id}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Gender</th>
                    <td style="width: 50%">${user.gender}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Token</th>
                    <td style="width: 50%">${user.token}</td>
                </tr>

               <tr>
               <td style="width: 15%"></td>
               <th style="width: 35%">Role</th>
               <td style="width: 50%">${user.role}</td>
            </tr>

            </table>


                   <button class="btn btn-dark btn-block" onclick="location.href = '/agent/employees/${agent.employeeId}'" >
                      View Employee
                   </button>


        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>


