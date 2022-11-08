<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>
        <c:choose>
            <c:when test="${not empty edit}">
                Edit Client
            </c:when>
            <c:otherwise>
                Add Policy
            </c:otherwise>
        </c:choose>
    </h2>

    <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">
          <div class="d-inline-flex p-2">
            ${success}
            </div>
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">

             ${error}

        </div>
    </c:if>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <form:form action="${submiturl}" method="post" modelAttribute="policies">
            <div class="row mt-3">
                 <div class="col-lg-6">
                <table class="table table-borderless mb-0">
                    <tr>
                        <th style="width: 40%">Policy Terms</th>
                        <td style="width: 60%">
                        <form:input type="number" path="policyTerm" class="form-control" required="true" placeholder="policyTerm"></form:input>
                        <form:errors path="policyTerm" style="color: red;"></form:errors>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">Insurance Id</th>
                        <td style="width: 60%">
                            <input type="text" name="insuranceId" value="${insurance.insuranceId}"class="form-control" required="true" placeholder="InsuranceId" disabled="true"></input>

                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Insurance Type</th>
                        <td style="width: 60%">
                            <input type="text" name="insuranceType" value="${insurance.insuranceType}" class="form-control" required="true" placeholder="InsuranceType" disabled="true"></input>

                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">Insurance Name</th>
                        <td style="width: 60%">
                            <input type="text" name="insuranceName" value="${insurance.insuranceName}" class="form-control" required="true" placeholder="InsuranceName" disabled="true"></input>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">Amount</th>
                        <td style="width: 60%">
                        <form:input type="number" path="totalAmount" class="form-control" required="true"></form:input>
                        <form:errors path="totalAmount" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Start Date</th>
                        <td style="width: 60%">
                        <form:input type="date" path="startDate" class="form-control" required="true"></form:input>
                        <form:errors path="startDate" style="color: red;"></form:errors>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">End Date</th>
                        <td style="width: 60%">
                        <form:input type="date" path="endDate" class="form-control" required="true"></form:input>
                        <form:errors path="endDate" style="color: red;"></form:errors>
                        </td>
                    </tr>

                </table>
            </div>
            </div>

            <div class="d-flex justify-content-center">
                <button class="btn btn-success" type="submit">Submit</button>
            </div>

            </form:form>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>