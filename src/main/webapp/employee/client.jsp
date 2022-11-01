<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Client</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">

        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Client Number</th>
                        <th>Name</th>
                        <th>Employee Id</th>
                        <th>username</th>
                        <th>Email</th>
                        <th>Contact</th>
                        <th>Address</th>

                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Client}" var="client">
                    <tr>
                        <td class="align-middle">${client.clientNo}</td>
                        <td class="align-middle">${client.Name}</td>
                        <td class="align-middle">${client.employeeId}</td>
                        <td class="align-middle">${client.username}</td>
                        <td class="align-middle">${client.clientEmail}</td>
                        <td class="align-middle">${client.clientContact}</td>
                        <td class="align-middle">${client.HouseNo} ${client.landMark} ${client.city}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>