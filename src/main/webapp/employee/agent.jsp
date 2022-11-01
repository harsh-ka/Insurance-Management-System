<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Agent</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">

        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Agent Id</th>
                        <th>License Number</th>
                        <th>Name</th>
                        <th>Employee Id </th>
                        <th>username</th>
                        <th>Admin Id</th>
                        <th>Commission</th>
                        <th>Address</th>

                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Agent}" var="agent">
                    <tr>
                        <td class="align-middle">${agent.agentId}</td>
                        <td class="align-middle">${agent.licenceNo}</td>
                        <td class="align-middle">${agent.firstName} ${agent.middleName} ${agent.lastName}</td>
                        <td class="align-middle">${agent.employeeId}</td>
                        <td class="align-middle">${agent.username}</td>
                        <td class="align-middle">${agent.admin_id}</td>
                        <td class="align-middle">${agent.Commision}</td>
                        <td class="align-middle">${agent.houseNo} ${agent.landmark} ${agent.city}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>