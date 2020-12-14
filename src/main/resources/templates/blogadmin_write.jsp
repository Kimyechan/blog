<%@page contentType="text/html; charset=EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JBlog 글 작성</title>
<Link rel="stylesheet" href="css/theme.css">
</head>

<body background="images/kubrickbgcolor.jpg">
	<center>
		<table background="images/kubrickheader.jpg" width="760" height="200"
			border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="60">&nbsp;</td>
			</tr>
			<!-- 블로그 제목과 태그 시작 -->
			<tr>
				<td height="60" class="blogtitle">Gurum 블로그</td>
			</tr>
			<tr>
				<td height="20" class="blogtag">자바, 스프링, 아무거나</td>
			</tr>
			<!-- 블로그 제목과 태그 끝 -->
			<tr>
				<td align="right" height="60">
					<a href="logout.do">로그아웃</a>&nbsp;&nbsp;
					<a href="blogMain.do">내 블로그 메인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		
		<table background="images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="10" colspan="10">&nbsp;</td>
			</tr>
			<tr>
				<td height="10" width="20">&nbsp;</td>
				<td width="530" valign="top" class="tdcontent">
					<!-- 메뉴 시작 --> 
					<a class="title" href="#"><b>기본설정</b></a>&nbsp;&nbsp;
					<a class="title" href="#"><b>카테고리</b></a>&nbsp;&nbsp;
					<b>글작성</b>&nbsp;&nbsp; 
					<a class="title" href="#"><b>블로그삭제</b></a>&nbsp;&nbsp;
					<!-- 메뉴 끝 -->
				</td>
			</tr>
			<tr>
				<td height="5">&nbsp;</td>
			</tr>
			<tr>
				<td height="10">&nbsp;</td>
				<td>
					<form action="addPost.do" method="post">
						<table width="720" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="50" class="inputlabel">제목 :</td>
								<td width="390"><input class="inputtext" type="text" size="60" name="title"></td>
								<td width="300">
									<select class="inputtextarea">
										<option>미분류</option>
										<option>자바</option>
										<option>스프링 프레임워크</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="50" class="inputlabel">내용 :</td>
								<td colspan="10"><textarea name="content" class="inputtextarea" rows="10" cols="100"></textarea></td>
							</tr>
							<tr>
								<td height="5">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="10" align="center">&nbsp;<input type="submit" value="확인"></td>
							</tr>
						</table>
					</form> 
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