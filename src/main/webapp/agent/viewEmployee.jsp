<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/admin/employees'"> Employee </h2>
        <h3 class="ms-2">${employee.employeeId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Employee Id</th>
                    <td style="width: 50%">${employee.employeeId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Employee Name</th>
                    <td style="width: 50%">${employee.firstName} ${employee.middleName} ${employee.lastName}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Address</th>
                    <td style="width: 50%">${employee.employeeAddress}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Email</th>
                    <td style="width: 50%">${employee.email}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Date of Birth</th>
                    <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${user.dateOfBirth}" /></td>
                 </tr>

                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">Join Date</th>
                    <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${employee.joinDate}" /></td>
                </tr>
               <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%">End Date</th>
                    <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${employee.endDate}" /></td>
               </tr>

               <tr>
                <td style="width: 15%"></td>
                <th style="width: 35%">Role</th>
                <td style="width: 50%">${user.role}</td>
               </tr>

               <table id="example" class="table table-hover">
                       <thead>
                           <tr>
                               <th>Client Number</th>
                               <th>First Name</th>
                               <th>Middle Name</th>
                               <th>Last Name</th>
                               <th>Contact</th>
                               <th>Email</th>
                               <th>Employee Id</th>
                           </tr>
                       </thead>
                       <tbody>
                       <c:forEach items="${Client}" var="client">
                           <tr>
                                <td class="align-middle">${client.clientNo}</td>
                                <td class="align-middle">${client.firstName}</td>
                                <td class="align-middle">${client.middleName}</td>
                                <td class="align-middle">${client.lastName}</td>
                                <td class="align-middle">${client.clientContact}</td>
                                <td class="align-middle">${client.clientEmail}</td>
                                <td class="align-middle">${client.employeeId}</td>
                              <td class="align-middle">
                              <button class="btn btn-dark btn-block" onclick="location.href = '/admin/clients/${client.clientNo}'" >
                                 ViewProfile
                               </button>
                               </td>
                           </tr>

                       </c:forEach>
               </tbody>

            </table>

            <table id="example" class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Agent Id</th>
                                    <th>First Name</th>
                                    <th>Middle Name</th>
                                    <th>Last Name</th>
                                    <th>Employee Id </th>
                                    <th>Address</th>
                                    <th>View Profile</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${agents}" var="agent">
                                <tr>
                                    <td class="align-middle">${agent.agentId}</td>
                                    <td class="align-middle">${agent.firstName}</td>
                                    <td class="align-middle">${agent.middleName}</td>
                                    <td class="align-middle">${agent.lastName}</td>
                                    <td class="align-middle">${agent.employeeId}</td>
                                    <td class="align-middle">${agent.houseNo} ${agent.landmark} ${agent.city}</td>

                                    <td class="align-middle">
                                       <button class="btn btn-dark btn-block" onclick="location.href = '/agent/agents/${agent.agentId}'" >
                                          View Agents
                                       </button>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>


        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>