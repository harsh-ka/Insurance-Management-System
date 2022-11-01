<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Sections</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">
        
        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Course Id</th>
                        <th>Section Id</th>
                        <th>Course Name</th>
                        <th>Credits</th>
                        <th>Year</th>
                        <th>Semester</th>
                        <th>Room Number</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${sections}" var="section">
                    <tr>
                        <td class="align-middle">${section.course.courseId}</td>
                        <td class="align-middle">${section.sectionId}</td>
                        <td class="align-middle">${section.course.name}</td>
                        <td class="align-middle">${section.course.credits}</td>
                        <td class="align-middle">${section.year}</td>
                        <td class="align-middle">${section.semester}</td>
                        <td class="align-middle">${section.roomNumber}</td>
                        <td class="align-middle">${section.isLocked}</td>
                        <td class="align-middle">
                            <button class="btn btn-success me-2" onclick="location.href='/professor/sections/${section.course.courseId}/${section.sectionId}'">
                                Manage
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