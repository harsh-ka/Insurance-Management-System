<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Employee</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">

        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Employee Id</th>
                        <th>Name</th>
                        <th>Admin Id</th>
                        <th>username</th>
                        <th>Join Date</th>
                        <th>End Date</th>
                        <th>Email</th>
                        <th>Address</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Employee}" var="employee">
                    <tr>
                        <td class="align-middle">${employee.employeeId}</td>
                        <td class="align-middle">${employee.FirstName} ${Employee.MiddleName} ${Employee.LastName}</td>
                        <td class="align-middle">${employee.admin_id}</td>
                        <td class="align-middle">${employee.username}</td>
                        <td class="align-middle">${employee.joinDate}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${Employee.joinDate}" />
                        </td>
                        <td class="align-middle">${Employee.endDate}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${Employee.endDate}" />
                        </td>

                        <td class="align-middle">${Employee.email}</td>
                        <td class="align-middle">${Employee.employeeAddress}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>