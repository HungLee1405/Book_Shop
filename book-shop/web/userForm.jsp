<%-- 
    Document   : user-account
    Created on : Jul 8, 2025, 8:48:11 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="checkError" value="${requestScope.checkError}" />
<c:set var="message" value="${requestScope.message}" />
<c:set var="user" value="${requestScope.user}" />
<c:set var="isUpdate" value="${requestScope.isUpdate}" />
<c:set var="keyword" value="${requestScope.keyword}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.user == null}">

                <div class="header">
                    <a href="login.jsp" class="back-link">← Back to login page</a>
                    <h1>${isUpdate ? "UPDATE USER INFORMATION" : "REGISTER USER"}</h1>
                </div>

                <div class="form-container">
                    <form action="MainController" method="post">
                        <input type="hidden" name="action" value="${isUpdate ? 'updateProfile' : 'register'}"/>

                        <c:if test="${isUpdate}">
                            <div class="form-group">
                                <label for="id">ID <span class="required">*</span></label>
                                <input type="text" id="id" name="id" value="${user.userID}" readonly />
                            </div>
                        </c:if>

                        <div class="form-group">
                            <label for="userName">User Name <span class="required">*</span></label>
                            <input type="text" id="userName" name="userName" required value="${user.userName}" />
                        </div>

                        <div class="form-group">
                            <label for="fullName">Full Name<span class="required">*</span></label>
                            <input type="text" id="fullName" name="fullName" value="${user.fullName}" />
                        </div>

                        <div class="form-group">
                            <label for="password">Password<span class="required">*</span></label>
                            <input type="password" id="password" name="password" value="${user.password}" />
                        </div>

                        <div class="form-group">
                            <label for="email">Email <span class="required">*</span></label>
                            <input type="text" id="email" name="email" value="${user.email}" />
                        </div>

                        <div class="form-group">
                            <label for="birthDay">BirthDay</label>
                            <input type="date" id="birthDay" name="birthDay" value="${user.birthDay}" />
                        </div>

                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" id="address" name="address" value="${user.address}" />
                        </div>
                        
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" id="phone" name="phone" value="${user.phone}" />
                        </div>


                            <div class="button-group">
                                <input type="hidden" name="keyword" value="${keyword}" />
                            <input type="submit" value="${isUpdate ? 'Update user' : 'Create Account'}"/>
                            <input type="reset" value="Reset"/>
                        </div>
                    </form>

                    <c:if test="${not empty checkError}">
                        <div class="error-message">${checkError}</div>
                    </c:if>
                    <c:if test="${empty checkError && not empty message}">
                        <div class="success-message">${message}</div>
                    </c:if>
                </div>

            </c:when>
            <c:otherwise>
                <div class="header">
                    <h1>ACCESS DENIED</h1>
                </div>
                <div class="access-denied">
                    🚫 You do not have permission to access this page.
                </div>
            </c:otherwise>
        </c:choose>    

    </body>
</html>
