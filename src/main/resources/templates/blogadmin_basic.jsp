<%@page contentType="text/html; charset=EUC-KR"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JBlog 기본정보 관리</title>
<Link rel="stylesheet" href="css/theme.css">
</head>

<body background="images/kubrickbgcolor.jpg">
	<center>
		<table background="images/kubrickheader.jpg" width="760" height="200" border="0" cellpadding="0" cellspacing="0">
			<tr><td height="60">&nbsp;</td></tr>
			<!-- 블로그 제목과 태그 시작 -->
			<tr><td height="60" class="blogtitle">Gurum의 블로그</td></tr>
			<tr><td height="20" class="blogtag">자바, 서블릿, 스프링</td></tr>
			<!-- 블로그 제목과 태그 끝 -->
			
			<tr>
				<td align="right" height="60">
				<a href="logout.do">로그아웃</a>&nbsp;&nbsp;
				<a href="blogMain.do">내 블로그 메인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		<table background="images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
			<tr><td height="10" colspan="10">&nbsp;</td></tr>
			<tr>
				<td height="10" width="20">&nbsp;</td>
				<td width="530" valign="top" class="tdcontent">
					<!-- 메뉴 시작 --> 
					<a class="title" href="blogadmin_basic.jsp"><b>기본설정</b></a>&nbsp;&nbsp;
					<a class="title" href="getCategoryList.do"><b>카테고리</b></a>&nbsp;&nbsp;
					<a class="title" href="addPostView.do"><b>글작성</b></a>&nbsp;&nbsp; 
					<a class="title" href="javascript:popup();"><b>블로그삭제</b></a>&nbsp;&nbsp;
					<!-- 메뉴 끝 -->
				</td>
			</tr>
			<tr>
				<td height="5">&nbsp;</td>
			</tr>
			<tr>
				<td height="10">&nbsp;</td>
				<td>
					<!-- 작업 화면  시작 -->
					<form action="updateBlog.do" method="post">
						<table width="720" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="150" class="inputlabel">블로그 제목 :</td>
								<td><input class="inputtext" type="text" size="40" name="title"></td>
							</tr>
							<tr>
								<td width="150" class="inputlabel">블로그 태그 :</td>
								<td><input class="inputtext" type="text" size="50" name="tag"></td>
							</tr>
							<tr>
								<td width="150" class="inputlabel">메인페이지 포스트 :</td>
								<td><input class="inputtext" type="text" size="4" name="cntDisplayPost"></td>
							</tr>
							<tr>
								<td width="150" class="inputlabel">로고이미지 :</td>
								<td>&nbsp;<img height="80" src="images/j2eelogo.jpg" border="0"></td>
							</tr>
							<tr>
								<td width="150" class="inputlabel">&nbsp;</td>
								<td>
									<input class="inputtext" type="text" size="40" name="logoFile"> 
									<input type="submit" value="찾아보기"></td>
							</tr>
							<tr>
								<td height="40" colspan="10" align="center">
								<input type="submit" value="확인"></td>
							</tr>
						</table>
					</form> <!-- 작업 화면  끝 -->
				</td>
			</tr>
			<tr>
				<td height="10" colspan="10">&nbsp;</td>
			</tr>
		</table>
		
		<table background="images/kubrickfooter.jpg" width="760" height="63" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="blogfooter">J2EE 이야기 is powered by JBlog</td>
			</tr>
		</table>
	</center>
</body>
</html>