<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib tagdir="/WEB-INF/tags/widget" prefix="widget" %>
<%@ taglib uri="http://edu.ecm.blog/test" prefix="test" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head></head>
   <body><widget:hello name="${name}" /><br/><test:hello name="${name}" /></body>
   
</html>