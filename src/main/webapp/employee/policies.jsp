<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Policies</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">

        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Policy Term</th>
                        <th>Amount</th>
                        <th>Insurance Id</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Policies}" var="policies">
                    <tr>
                        <td class="align-middle">${policies.PolicyTerm}</td>
                        <td class="align-middle">${policies.TotalAmount}</td>
                        <td class="align-middle">${policies.InsuranceId}</td>
                        <td class="align-middle">${policies.StartDate}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${policies.StartDate}" />
                        </td>
                        <td class="align-middle">${policies.EndDate}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${policies.EndDate}" />
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