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
                Edit Agent
            </c:when>
            <c:otherwise>
                Add Agent
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

            <form:form action="${submiturl}" method="post" modelAttribute="user">
            <div class="row mt-3">
                 <div class="col-lg-6">
                <table class="table table-borderless mb-0">
                    <tr>
                        <th style="width: 40%">Username</th>
                        <td style="width: 60%">
                        <form:input type="text" path="username" class="form-control" required="true" disabled="true"></form:input>
                        <form:errors path="username" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">First Name</th>
                        <td style="width: 60%">
                            <form:input type="text" path="firstName" class="form-control" required="true" placeholder="First Name"></form:input>
                            <form:errors path="firstName" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Middle Name</th>
                        <td style="width: 60%">
                            <input type="text" name="middleName" class="form-control" required="true" placeholder="Middle Name"></input>
                            <errors name="middleName" style="color: red;"></errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Last Name</th>
                        <td style="width: 60%">
                            <form:input type="text" path="lastName" class="form-control" required="true" placeholder="Last Name"></form:input>
                            <form:errors path="lastName" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Email Address</th>
                        <td style="width: 60%">
                            <form:input type="email" path="emailAddress" class="form-control" required="true" placeholder="Email"></form:input>
                            <form:errors path="emailAddress" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Date of Birth</th>
                        <td style="width: 60%">
                            <form:input type="date" path="dateOfBirth" class="form-control" required="true"></form:input>
                            <form:errors path="dateOfBirth" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 40%">Gender</th>
                        <td style="width: 60%">
                            <form:select class="form-control" path="gender" required="true">
                                <form:option value="Male">Male</form:option>
                                <form:option value="Female">Female</form:option>
                                <form:option value="Not Specified">Not Specified</form:option>
                            </form:select>
                            <form:errors path="gender" style="color: red;"></form:errors>
                        </td>
                     </tr>
                    <tr>
                        <th style="width: 40%">Role</th>
                        <td style="width: 60%">
                            <form:select class="form-control" path="role" required="true" disabled="true">
                                <form:option value="Agent">Agent</form:option>
                                <form:option value="Client">Client</form:option>
                                <form:option value="Employee">Employee</form:option>
                            </form:select>
                            <form:errors path="gender" style="color: red;"></form:errors>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">Address</th>
                        <td style="width: 60%">
                            <form:input type="text" path="address" class="form-control" required="true" placeholder="Address"></form:input>
                            <form:errors path="address" style="color: red;"></form:errors>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 40%">Commission</th>
                        <td style="width: 60%">
                            <input type="number"  name="commision" class="form-control" required="true" placeholder="Commision"></input>
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