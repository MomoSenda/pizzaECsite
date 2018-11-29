<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ピザ屋のネット注文</title>
<link href="../../css/bootstrap.css" rel="stylesheet">
<link href="../../css/piza.css" rel="stylesheet">
<link href="../../css/500.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/viewItemList/list"> <!-- 企業ロゴ --> <img
						alt="main log" src="../../img/header_logo.png" height="35">
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					
					<p class="navbar-text navbar-right">

							<a href="/viewCart" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
						<a href="/orderhistory/history" class="navbar-link">注文履歴</a>&nbsp;&nbsp;
						
					<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
					<sec:authentication var="userName" property="principal.user.name" />
					</sec:authorize>
						
						<c:choose>
							<c:when test="${not empty userName}">
								<span style="color:red">
									<c:out value="${userName}　様" ></c:out>&nbsp;&nbsp;
								</span>
									<a href="/logout" class="navbar-link">ログアウト</a>
							</c:when>
							<c:otherwise>
									<a href="/" class="navbar-link">ログイン</a>&nbsp;&nbsp;

							</c:otherwise>
						</c:choose>
						
							
						
							
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>

		<!-- search form -->

		<!-- table -->
		<div class="row">
				<div class="form-group">
				<div class="form-group text-center">

				<table class="table table-striped">
		<span style="font-size:15pt;">Page not found 該当商品が見つかりませんでした。</span>
		<br>
		<br>
		<br>
		<br>
				<div class="imageContainer">
					<img src="../../img/building_food_pizza.png" class="image" width="230" height="230">
					<img src="../../img/pizza_mawashi.png" class="image" width="230" height="230">
					<img src="../../img/pizza_nisou_kama.png" class="image" width="230" height="230">
					<img src="../../img/pizza.png" class="image" width="230" height="230">
					<img src="../../img/bike_sanrin_man.png" class="image" width="230" height="230">
				</div>
		<br>
		<br>
		<br>
		<br>
		<br>
				
			<div class="col-xs-offset-5 col-xs-3">
					<form:form action="${pageContext.request.contextPath}/viewItemList/list" method="post">
						<input class="form-control btn btn-warning btn-block"
							type="submit" value="商品一覧に戻る">
					</form:form>
				</div>
			</div>
			</div>
				</table>

			</div>
		</div>
</div>
</body>
</html>