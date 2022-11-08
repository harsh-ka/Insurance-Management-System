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
                Add Client
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

            <form:form action="${submiturl}" method="post" modelAttribute="insurance">
            <div class="row mt-3">
                 <div class="col-lg-6">
                <table class="table table-borderless mb-0">
                    <tr>
                        <th style="width: 40%">Policy Terms</th>
                        <td style="width: 60%">
                        <input type="number" name="policyTerm" class="form-control" required="true" placeholder="policyTerm"></input>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">Insurance Id</th>
                        <td style="width: 60%">
                            <form:input type="text" path="insuranceId" class="form-control" required="true" placeholder="InsuranceId" ></form:input>
                            <form:errors path="insuranceId" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Insurance Type</th>
                        <td style="width: 60%">
                            <form:input type="text" path="insuranceType" class="form-control" required="true" placeholder="InsuranceType" ></form:input>
                            <form:errors path="insuranceType" style="color: red;"></form:errors>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">Insurance Name</th>
                        <td style="width: 60%">
                            <form:input type="text" path="insuranceName" class="form-control" required="true" placeholder="InsuranceName" ></form:input>
                            <form:errors path="insuranceName" style="color: red;"></form:errors>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">Amount</th>
                        <td style="width: 60%">
                        <input type="number" name="totalAmount" class="form-control" required="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Start Date</th>
                        <td style="width: 60%">
                        <input type="date" name="startDate" class="form-control" required="true"></input>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">End Date</th>
                        <td style="width: 60%">
                        <input type="date" name="endDate" class="form-control" required="true"></input>
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