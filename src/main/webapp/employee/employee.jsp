<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="header.jsp" %>

<div class="container my-4 px-3">
    <h2>Employee</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">

        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>EmployeeId</th>
                        <th>First Name</th>

                        <th>Last Name</th>
                        <th>JoinDate</th>
                        <th>Email</th>
                        <th>View Profile</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Employee}" var="employee">
                    <tr>
                         <td class="align-middle">${employee.employeeId}</td>
                         <td class="align-middle">${employee.firstName}</td>

                         <td class="align-middle">${employee.lastName}</td>
                         <td class="align-middle">${employee.email}</td>
                         <td class="align-middle">${employee.joinDate}</td>
                       <td class="align-middle">
                       <button class="btn btn-dark btn-block" onclick="location.href = 'employee/${employee.employeeId}'" >
                          ViewProfile
                        </button>
                        </td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>