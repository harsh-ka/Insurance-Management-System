<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/admin/dashboard'"> Admin </h2>
        <h3 class="ms-2">${admin.admin_id}</h3>

    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless mt-3">
                <tr>
                   <td style="width: 15%"></td>
                   <th style="width: 35%">Username</th>
                   <td style="width: 50%">${user.username}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Admin Id</th>
                    <td style="width: 50%">${admin.admin_id}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Admin Name</th>
                    <td style="width: 50%">${admin.admin_name}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Admin Email</th>
                    <td style="width: 50%">${user.emailAddress}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Address</th>
                    <td style="width: 50%">${user.address}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Contact</th>
                    <td style="width: 50%">${admin.contactNo}</td>
                </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Date of birth</th>
                    <td style="width: 50%">${user.dateOfBirth}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Gender</th>
                    <td style="width: 50%">${user.gender}</td>
                </tr>

                <tr>
                   <td style="width: 15%"></td>
                   <th style="width: 35%">Role</th>
                   <td style="width: 50%">${user.role}</td>
                </tr>

                <tr>
                   <td style="width: 15%"></td>
                   <th style="width: 35%">Token</th>
                   <td style="width: 50%">${user.token}</td>
                </tr>

            </table>

        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>


