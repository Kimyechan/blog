<%@page contentType="text/html; charset=EUC-KR"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JBlog 시스템관리</title>
<Link rel="stylesheet" href="css/theme.css">
</head>

<body>
	<center>
		<form action="#">
			<table width="100%" height=320 border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height=40 colspan="10">&nbsp;</td>
				</tr>
				<tr>
					<td width="100%" height="120" colspan="10" align="center">
					<img src="images/logo.jpg" border="0"></td>
				</tr>
				<tr>
					<td width="30%" height="30">&nbsp;</td>
					<td width="70%" colspan="2">
					<a href="#"><b>로그인</b></a>&nbsp;&nbsp;
					<a href="#"><b>블로그등록</b></a>&nbsp;&nbsp; 
					<a href="#"><b>내 블로그로 가기</b></a></td>
				</tr>
			</table>

			<!-- 블로그 검색 시작  -->
			<table width="760" height="40" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="5" colspan="10">&nbsp;</td>
				</tr>
				<tr>
					<td width="30%" height="20">&nbsp;</td>
					<td width="40%"><input type="text" name="searchKeyword" size="50"></td>
					<td width="30%">&nbsp;<input type="submit" value="검색"></td>
				</tr>
				<tr>
					<td height="20" colspan="10" align="center" class="tdcontent">
						<input type="radio" name="searchCondition" checked>블로그	제목&nbsp;&nbsp; 
						<input type="radio" name="searchCondition">블로거&nbsp;&nbsp;
						<input type="radio" name="searchCondition">상태
					</td>
				</tr>
				<tr>
					<td height="10" colspan="10">&nbsp;</td>
				</tr>
			</table>
			<!-- 블로그 검색 끝 -->

			<!-- 작업 화면  시작 -->
			<table width="720" height="100" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td width="50" class="tablelabel">번호</td>
					<td width="270" class="tablelabel">블로그 제목</td>
					<td width="100" class="tablelabel">블로거</td>
					<td width="100" class="tablelabel">로고</td>
					<td width="100" class="tablelabel">상태</td>
					<td width="100" class="tablelabel">삭제</td>
				</tr>
				<tr>
					<td class="tablecontent" align="center">1</td>
					<td class="tablecontent">J2EE 이야기</td>
					<td class="tablecontent" align="center">김삼식</td>
					<td class="tablecontent" align="center"><img height="32"
						src="images/j2eelogo.jpg" border="0"></td>
					<td class="tablecontent" align="center">운영</td>
					<td class="tablecontent" align="center">&nbsp;</td>
				</tr>
				<tr>
					<td class="tablecontent" align="center">2</td>
					<td class="tablecontent">자바 이야기</td>
					<td class="tablecontent" align="center">홍길동</td>
					<td class="tablecontent" align="center"><img height="32"
						src="images/javalogo.jpg" border="0"></td>
					<td class="tablecontent" align="center">삭제요청</td>
					<td class="tablecontent" align="center">&nbsp;<img height="9"
						src="images/delete.jpg" border="0"></td>
				</tr>
				<tr>
					<td class="tablecontent" align="center">3</td>
					<td class="tablecontent">편의점에서 라면 맛있게 먹기</td>
					<td class="tablecontent" align="center">강호동</td>
					<td class="tablecontent" align="center"><img height="32"
						src="images/noddlelogo.jpg" border="0"></td>
					<td class="tablecontent" align="center">운영</td>
					<td class="tablecontent" align="center">&nbsp;</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>