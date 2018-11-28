<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ピザ屋のネット注文</title>
<link href="../../css/bootstrap.css" rel="stylesheet">
<link href="../../css/piza.css" rel="stylesheet">
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
						<a href="/" class="navbar-link">ログイン</a>&nbsp;&nbsp;
						<a href="/logout" class="navbar-link">ログアウト</a>
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>

		<!-- search form -->
	<div class="row">
			<div
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">商品を検索する</div>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="searchItemForm" method="post" action="${pageContext.request.contextPath}/SearchItem/search" class="form-horizontal">
							<div class="form-group">
								<label for="code" class="control-label col-sm-2">商品名</label>
								<div class="col-sm-9">
									<form:input path="name" class="form-control input-sm"/>
								</div>
							</div>
							<div class="text-center">
								<button type="submit" value="検索" class="btn btn-primary">検索</button>
								<button type="reset" value="クリア" class="btn btn-default">クリア</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<table class="table table-striped">
					<tbody>
						<tr>
							<c:forEach var="item" items="${itemList}" varStatus="status">
								<td>
									
									<a href="${pageContext.request.contextPath}/ShowItemDetail/detail/${item.id}">
										<img src="<c:out value="${item.imagePath}" />"class="img-responsive img-rounded" width="200" height="200">
									</a><br>
									<a href="${pageContext.request.contextPath}/ShowItemDetail/detail/${item.id}">
										<c:out value="${item.name}"></c:out><br>
									</a><br>
									
									<span class="price">&nbsp;М&nbsp;</span>&nbsp;&nbsp;
									<fmt:formatNumber value="${item.priceM}" pattern="###,###"/>円（税抜き）<br>
									<span class="price">&nbsp;Ｌ&nbsp;</span>&nbsp;&nbsp;
									<fmt:formatNumber value="${item.priceL}" pattern="###,###"/>円（税抜き）<br>
									<br>
								</td>
								<c:if test="${(status.index+1) % 3==0}">
						</tr>
							<tr>
								</c:if>
							</c:forEach>
			
							</tr>
					</tbody>
				</table>

			</div>
		</div>
</div>
</body>
</html>