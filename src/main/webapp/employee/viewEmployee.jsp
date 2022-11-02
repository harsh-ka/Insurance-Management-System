<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/employee/employee'"> Employee </h2>
        <h3 class="ms-2">${employee.employeeId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Employee Id</th>
                    <td style="width: 50%">${employee.employeeId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Employee Name</th>
                    <td style="width: 50%">${employee.firstName} ${employee.middleName} ${employee.lastName}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Address</th>
                    <td style="width: 50%">${employee.employeeAddress}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Email</th>
                    <td style="width: 50%">${employee.email}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">AdminId</th>
                    <td style="width: 50%">${employee.admin_id}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Username</th>
                    <td style="width: 50%">${employee.username}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Join Date</th>
                    <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${employee.joinDate}" /></td>
                </tr>

               <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">End Date</th>
                    <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${employee.endDate}" /></td>
               </tr>



            </table>

        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>