<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="../bootstrap.css" rel="stylesheet">

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
					<a class="navbar-brand" href="item_list.html"> <!-- 企業ロゴ --> <img
						alt="main log" src="img/header_logo.png" height="35">
					</a>
				</div>
 
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<p class="navbar-text navbar-right">
						<a href="cart_list.html" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	<div class="row">
			<div class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
				<div class="well">
					<form:form modelAttribute="resisterUserForm" method="post" action="${pageContext.request.contextPath}/registeruser/create">
						<fieldset>
							<legend>ユーザ登録</legend>
							<div class="form-group">
								<label for="inputName">名前:</label><label
									class="control-label" style="color: red" for="inputError"></label>
								<form:errors path="name" cssStyle="color:red" element="div"/>
								<input type="text" id="inputName" class="form-control"
									placeholder="Name" path="name">
							</div>
							<div class="form-group">
								<label for="inputEmail">メールアドレス:</label><label
									class="control-label" style="color: red" for="inputError">メールアドレスを入力してください</label>
								<input type="text" id="inputEmail" class="form-control"
									placeholder="Email">
							</div>
							<div class="form-group">
								<label for="inputZipcode">郵便番号:</label>
								<label
									class="control-label" style="color: red" for="inputError">郵便番号を入力してください</label>
									<input type="button" value="住所検索">
								<input type="text" id="inputZipcode" class="form-control"
									placeholder="Zipcode">
								
							</div>
							<div class="form-group">
								<label for="inputAddress">住所:</label>
								<label
									class="control-label" style="color: red" for="inputError">住所を入力してください</label>
								<input type="text" id="inputAddress" class="form-control"
									placeholder="Address">
							</div>
							<div class="form-group">
								<label for="inputTel">電話番号:</label>
								<label
									class="control-label" style="color: red" for="inputError">電話番号を入力してください</label>
								<input type="text" id="inputTel" class="form-control"
									placeholder="Tell">
							</div>
							<div class="form-group">
								<label for="inputPassword">パスワード:</label>
								<label
									class="control-label" style="color: red" for="inputError">パスワードを入力してください</label>
								<input type="text" id="inputPassword" class="form-control"
									placeholder="Password">
							</div>
							<div class="form-group">
								<label for="inputConfirmationPassword">確認用パスワード:</label>
								<label
									class="control-label" style="color: red" for="inputError">確認用パスワードを入力してください</label>
								<input type="text" id="inputConfirmationPassword" class="form-control"
									placeholder="Confirmation Password">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">登録</button>
								<button type="reset" class="btn btn-primary">クリア</button>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>	
</div>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>


</body>
</html>