<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">

    <c:param name="content">

        <c:if test="${flush != null}">
            <div id ="flush_success">
                <c:out value="${flush}"></c:out>
         </div>
        </c:if>

        <h2>タスク一覧</h2>
        <ul>
            <!-- 取得データを変数（task）に格納 -->
            <c:forEach var="task" items="${tasks}">
                <li>
                    <!-- 取得データの表示 -->
                    <%-- idにリンクを貼る（「${pageContext.request.contextPath}」がコンテキストパスに置き換わる） --%>
                    <a href="${pageContext.request.contextPath}/show?id=${task.id}">
                        <c:out value="${task.id}" />
                    </a>
                    ：<c:out value="${task.content}" />

                </li>
            </c:forEach>
        </ul>

        <!-- 「/new」へのリンク表示 -->
        <p><a href="${pageContext.request.contextPath}/new">新規タスクの投稿</a></p>


    </c:param>
</c:import>