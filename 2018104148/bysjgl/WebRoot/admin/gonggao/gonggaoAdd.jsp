<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false" %> 

<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <base target="_self"/>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
        
        <script language="javascript">
              function check()
              {
                  if(document.formAdd.ggTitle.value=="")
                  {
                      alert("请输入标题");
                      return false;
                  }
                  if(document.formAdd.ggContent.value=="")
                  {
                      alert("请输入内容");
                      return false;
                  }
                  
                  document.formAdd.submit();
              }
              
              function quxiao()
              {
                  window.close();
              }
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'>
			<form action="<%=path %>/gonggaoAdd.action" name="formAdd" method="post">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title'><span>&nbsp;</span></td>
					    </tr>
						
						<tr align='center' bgcolor="#FFFFFF"
							onMouseMove="javascript:this.bgColor='red';"
							onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="25%" bgcolor="#FFFFFF" align="right">
								标题：
							</td>
							<td width="75%" bgcolor="#FFFFFF" align="left">
								<input type="text" id="title" name="ggTitle" maxlength="100" style="width:350px;"/>
		
							</td>
						</tr>
						
						<tr align='center' bgcolor="#FFFFFF"
							onMouseMove="javascript:this.bgColor='red';"
							onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="25%" bgcolor="#FFFFFF" align="right">
								内容：
							</td>
							<td width="75%" bgcolor="#FFFFFF" align="left">
							
							<textarea rows="15" cols="80" id="contents" name="ggContent"></textarea>	
							</td>
						</tr>
						
					
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						       <input type="button" value="提交" onclick="check()"/>&nbsp; 
						       <input type="button" value="取消" onclick="quxiao()"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>
