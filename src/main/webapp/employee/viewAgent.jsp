<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/employee/agent'">Agent  >  </h2>
        <h3 class="ms-2">${agent.agentId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">AgentId</th>
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
                    <th style="width: 35%;">Licence Number</th>
                    <td style="width: 50%">${agent.licenceNo}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Employee Id</th>
                    <td style="width: 50%">${agent.employeeId}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">AdminId</th>
                    <td style="width: 50%">${agent.admin_id}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Commision</th>
                    <td style="width: 50%">${agent.Commision}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Username</th>
                    <td style="width: 50%">${agent.username}</td>
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