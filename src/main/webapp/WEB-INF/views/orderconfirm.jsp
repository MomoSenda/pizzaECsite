<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ピザ屋のネット注文</title>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/piza.css" rel="stylesheet">
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
					<a class="navbar-brand" href="item_list.html"> <!-- 企業ロゴ --> <img
						alt="main log" src="/img/header_logo.png" height="35">
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<p class="navbar-text navbar-right">
						<a href="cart_list.html" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
						<a href="order_history.html" class="navbar-link">注文履歴</a>&nbsp;&nbsp;
						<a href="login.html" class="navbar-link">ログイン</a>&nbsp;&nbsp; <a
							href="item_list.html" class="navbar-link">ログアウト</a>
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>


		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<h3 class="text-center">注文内容確認</h3>
				<table class="table table-striped">
					<tbody>
						<tr>
							<th>
								<div class="text-center">商品名</div>
							</th>
							<th>
								<div class="text-center">サイズ、価格(税抜)、数量</div>
							</th>
							<th>
								<div class="text-center">トッピング、価格(税抜)</div>
							</th>
							<th>
								<div class="text-center">小計</div>
							</th>
						</tr>
						<c:forEach var="orderitem" items="${order.orderItemList}">
							<tr>
								<td>
									<div class="center">
										<img src="${orderitem.item.imagePath}"
											class="img-responsive img-rounded" width="100" height="300"><br>
										<c:out value="${orderitem.item.name}" />
									</div>
								</td>
								<td><span class="price">&nbsp; <c:out
											value="${orderitem.size}" />
								</span>&nbsp;&nbsp; <c:if test="${orderitem.size=='M'.charAt(0)}">
										<fmt:formatNumber value="${orderitem.item.priceM}"
											pattern="###,###円" />
									</c:if> <c:if test="${orderitem.size=='L'.charAt(0)}">
										<fmt:formatNumber value="${orderitem.item.priceL}"
											pattern="###,###円" />
									</c:if> &nbsp;&nbsp; <c:out value="${orderitem.quantity}" />個</td>
								<td>
									<ul>
										<c:forEach var="ordertopping"
											items="${orderitem.orderToppingList}">
											<li><c:out value="${ordertopping.topping.name}" /> <c:if
													test="${orderitem.size=='M'.charAt(0)}">
													<fmt:formatNumber value="${ordertopping.topping.priceM}"
														pattern="###,###円" />
												</c:if> <c:if test="${orderitem.size=='L'.charAt(0)}">
													<fmt:formatNumber value="${ordertopping.topping.priceL}"
														pattern="###,###円" />
												</c:if></li>
										</c:forEach>
									</ul>
								</td>
								<td><fmt:formatNumber value="${orderitem.subTotal}"
										pattern="###,###円" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-offset-2 col-xs-8">
				<div class="form-group text-center">
					<span id="total-price">消費税：<fmt:formatNumber
							value="${order.tax}" pattern="###,###円" /></span><br> <span
						id="total-price">ご注文金額合計：<fmt:formatNumber
							value="${order.calcTotalPrice+order.tax}" pattern="###,###円" />(税込)
					</span>
				</div>
			</div>
		</div>

		<!-- table -->
		<form action="order_finished.html">
			<div class="row">
				<div
					class="table-responsive col-lg-offset-3 col-lg-6 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
					<h3 class="text-center">お届け先情報</h3>
					<table class="table table-striped">
						<tbody>
							<tr>
								<td>
									<div class="text-center">お名前</div>
								</td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>
									<div class="text-center">メールアドレス</div>
								</td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>
									<div class="text-center">郵便番号</div>
								</td>
								<td><input type="text">&nbsp;&nbsp;
									<button>住所検索</button></td>
							</tr>
							<tr>
								<td>
									<div class="text-center">住所</div>
								</td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>
									<div class="text-center">電話番号</div>
								</td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>
									<div class="text-center">配達日時</div>
								</td>
								<td>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-12">
												<label class="control-label" style="color: red"
													for="inputPeriod">配達日時を入力してください</label>
											</div>
											<div class="col-sm-5">
												<input type="date" name="name" id="name"
													class="form-control input-sm" />
											</div>

										</div>
										<div class="row">
											<div class="col-sm-12">
												<label class="radio-inline"> <input type="radio"
													name="responsibleCompany" checked="checked"> 10時
												</label> <label class="radio-inline"> <input type="radio"
													name="responsibleCompany"> 11時
												</label> <label class="radio-inline"> <input type="radio"
													name="responsibleCompany"> 12時
												</label><br> <label class="radio-inline"> <input
													type="radio" name="responsibleCompany"> 13時
												</label> <label class="radio-inline"> <input type="radio"
													name="responsibleCompany"> 14時
												</label> <label class="radio-inline"> <input type="radio"
													name="responsibleCompany"> 15時
												</label><br> <label class="radio-inline"> <input
													type="radio" name="responsibleCompany"> 16時
												</label> <label class="radio-inline"> <input type="radio"
													name="responsibleCompany"> 17時
												</label> <label class="radio-inline"> <input type="radio"
													name="responsibleCompany"> 18時
												</label><br>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<!-- table -->
			<div class="row">
				<div
					class="table-responsive col-lg-offset-3 col-lg-6 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
					<h3 class="text-center">お支払い方法</h3>
					<table class="table table-striped">
						<tbody>
							<tr>
								<td>
									<div class="text-center">代金引換</div>
								</td>
								<td>
									<div class="row">
										<div class="col-sm-12">
											<label class="radio-inline"> <input type="radio"
												name="responsibleCompany" checked="checked"> 代金引換
											</label>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="text-center">クレジットカード決済</div>
								</td>
								<td align="center">
									<div class="row">
										<div class="col-sm-12">
											<label class="radio-inline"> <input type="radio"
												name="responsibleCompany" checked="checked">
												クレジットカード
											</label><br> <br>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-offset-4 col-xs-4">
					<div class="form-group">
						<input class="form-control btn btn-warning btn-block"
							type="submit" value="この内容で注文する">
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>