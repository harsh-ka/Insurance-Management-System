<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Insurance</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">

        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Insurance Id</th>
                        <th>Insurance Type</th>
                        <th>Insurance Name</th>
                        <th>Get Insurances</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Insurance}" var="insurance">
                    <tr>
                        <td class="align-middle">${insurance.insuranceId}</td>
                        <td class="align-middle">${insurance.insuranceType}</td>
                        <td class="align-middle">${insurance.insuranceName}</td>
                        <td class="align-middle">
                         <button class="btn btn-dark btn-block" onclick="location.href = '/admin/policies/${insurance.insuranceId}'" >
                             View Insurance
                         </button>
                        </td>

                        <td class="align-middle">
                         <button class="btn btn-dark btn-block" onclick="location.href = '/admin/policies/${insurance.insuranceId}'" >
                             Edit
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