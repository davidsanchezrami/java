<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
	</head>
	<body onload="foco()">
		<script Language="JavaScript">
		function foco(){
			if(document.getElementById("accion").value == "insertar"){
				document.getElementById("name").value="";
				document.getElementById("email").value="";
				document.getElementById("phone").value="";
				document.getElementById("surname").value="";
				document.getElementById("foco").value="";
				alert("Registro insertado");
			}
			valorFoco = document.getElementById("foco").value;
			document.getElementById(valorFoco).focus();
			document.getElementById("accion").value="insertar";
        }
        function checkForm(campo) {
        	var valorName = document.getElementById("name").value.length;
        	var valorEmail = document.getElementById("email").value.length;
        	var valorPhone = document.getElementById("phone").value.length;
        	var valorSurname = document.getElementById("surname").value.length;
        	form = document.forms[0] 
        	if(valorName > 2 || valorEmail > 2 || valorPhone > 2 || valorSurname > 2){
        		document.getElementById("accion").value="buscar";
        		document.getElementById("foco").value=campo;
        		form.submit();
        	}
            	
            return (true);
        }
        function cambiar(){
        	document.getElementById("oculto1").value="insertar";
        }
    </script>
    	<div align="center">
        	<form:form id="form" action="register" method="post" commandName="userForm">
        		<form:input id="accion" path="accion" cssStyle="visibility:hidden" />
        		<form:input id="foco" path="foco" cssStyle="visibility:hidden" />
            	<table border="0">
                	<tr>
                    	<td colspan="2" align="center"><h2>Hazelcast Demo</h2></td>
                	</tr>
                	<tr>
                    	<td>User Name:</td>
                    	<td><form:input id="name" path="name" onkeyup="return checkForm(1);" /></td>
                	</tr>
                	<tr>
                    	<td>E-mail:</td>
                    	<td><form:input id="email" path="email" onkeyup="return checkForm(2);" /></td>
                	</tr>
                	<tr>
                    	<td>Phone:</td>
                    	<td><form:input id="phone" path="phone" onkeyup="return checkForm(3);"/></td>
                	</tr>
                	<tr>
                    	<td>Surname:</td>
                    	<td><form:input id="surname" path="surname" onkeyup="return checkForm(4);"/></td>
                	</tr>
                	<tr>
                    	<td colspan="2" align="center">
                    		<input type="submit" value="Agregar"/>
                    		
                    	</td>
               	 	</tr>
            	</table>
        	</form:form>
        	<table border=1>
        <thead>
            <tr>
                <th>User Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Surname</th>
            </tr>
        </thead>
        <tbody>
        	
        	<c:forEach items="${users}" var="user1">
                <tr>
                    <td><c:out value="${user1.name}" /></td>
                    <td><c:out value="${user1.email}" /></td>
                    <td><c:out value="${user1.phone}" /></td>
                    <td><c:out value="${user1.surname}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    	</div>
	</body>
</html>
