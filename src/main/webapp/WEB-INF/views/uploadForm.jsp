<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일업로드 양식</title>
</head>
<body>

	<form 
		action="/uploadFormAction" 
		method="post"
		enctype="multipart/form-data">
			
		1. 이름: <input type="text" name="name"> <br>
		
		<h3>2. 파일선택하세요</h3>
		
		<input 
			type="file" 
			name="uploadFile" 
			multiple="multiple">
		
		<hr/>
		
		<input type="submit" value="파일전송">
		<input type="reset" value="다시입력">
	</form>	


</body>
</html>