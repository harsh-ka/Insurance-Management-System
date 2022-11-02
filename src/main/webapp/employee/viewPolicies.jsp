<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/employee/agent'">Policies  >  </h2>
        <h3 class="ms-2">${Policies.InsuranceId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Policies Term</th>
                    <td style="width: 50%">${policies.PolicyTerm}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Total Amount</th>
                    <td style="width: 50%">${policies.TotalAmount}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Insurance Id</th>
                    <td style="width: 50%">${policies.InsuranceId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Start Date</th>
                    <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${policies.StartDate}" /></td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">End Date</th>
                    <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${policies.EndDate}" /></td>
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