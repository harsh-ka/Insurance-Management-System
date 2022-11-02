<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/employee/client'">Client  >  </h2>
        <h3 class="ms-2">${client.clientNo}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Client Number</th>
                    <td style="width: 50%">${client.clientNo}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Agent Name</th>
                    <td style="width: 50%">${client.FirstName} ${client.MiddleName} ${client.LastName}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Address</th>
                    <td style="width: 50%">${client.HouseNo} ${client.landMark} ${client.city}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Email</th>
                    <td style="width: 50%">${agent.clientEmail}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Employee Id</th>
                    <td style="width: 50%">${client.employeeId}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Contact</th>
                    <td style="width: 50%">${client.clientContact}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Username</th>
                    <td style="width: 50%">${client.username}</td>
                </tr>

            </table>

            <h4 class="text-center pt-2">Employee</h4>

            <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Employee Id</th>
                        <th>Employee Name</th>
                        <th>Email Address</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${employee}" var="employee">
                    <tr>
                        <td class="align-middle">${employee.employeeId}</td>
                        <td class="align-middle">${employee.firstName} ${employee.middleName} ${employee.lastName}</td>
                        <td class="align-middle">${employee.email}</td>
                        <td class="align-middle">${employee.joinDate}</td>
                        <td class="align-middle">${employee.endDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>