<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Sells</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">

        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Agent Id</th>
                        <th>Client Number</th>
                        <th>Policy Term</th>
                        <th>InsuranceId</th>
                        <th>Buy Date</th>
                        <th>Amount</th>

                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Sells}" var="sell">
                    <tr>
                         <td class="align-middle" onclick="location.href='agent/${sell.agentId}'">${sell.agentId}</td>

                         <td class="align-middle">${sell.clientNo}</td>
                         <td class="align-middle">${sell.policyTerm}</td>
                         <td class="align-middle">${sell.insuranceId}</td>
                         <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${sell.buyDate}" />
                         </td>
                         <td class="align-middle">${sell.amount}</td>

                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>