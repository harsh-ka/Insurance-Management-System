<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">

    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/client/insurance/'">Insurance</h2>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Insurance Id</th>
                    <td style="width: 50%">${insurance.insuranceId}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Insurance Type</th>
                    <td style="width: 50%">${insurance.insuranceType}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Insurance Name</th>
                    <td style="width: 50%">${insurance.insuranceName}</td>
                </tr>
                </table>

                <table class="table table table-striped">
                <thead>
                    <tr>
                        <th>Insurance Id</th>
                        <th>Policy Term</th>
                        <th>startDate</th>
                        <th>endDate</th>
                        <th>Total Amount</th>
                    </tr>
                 </thead>

                 <tbody>
                 <c:forEach items="${Policies}" var="policy">
                     <tr>
                          <td class="align-middle">${policy.insuranceId}</td>
                          <td class="align-middle">${policy.policyTerm}</td>

                          <td class="align-middle">
                              <fmt:formatDate pattern="dd-MM-yyyy" value="${policy.startDate}" />
                          </td>


                          <td class="align-middle">
                             <fmt:formatDate pattern="dd-MM-yyyy" value="${policy.endDate}" />
                          </td>
                          <td class="align-middle">${policy.totalAmount}</td>
                     </tr>

                 </c:forEach>
                 </tbody>
            </table>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>