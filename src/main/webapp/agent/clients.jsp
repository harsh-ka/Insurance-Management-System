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
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Client Contact</th>
                        <th>Client Email </th>
                        <th>View Profile</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Clients}" var="client">
                    <tr>
                        <td class="align-middle">${client.clientNo}</td>
                        <td class="align-middle">${client.firstName}</td>
                        <td class="align-middle">${client.middleName}</td>
                        <td class="align-middle">${client.lastName}</td>
                        <td class="align-middle">${client.clientContact}</td>
                        <td class="align-middle">${client.clientEmail}</td>


                        <td class="align-middle">
                           <button class="btn btn-dark btn-block" onclick="location.href = '/agent/clients/${client.clientNo}'" >
                              View Client
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