<%@ page import="com.fiesta.model.vo.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.fiesta.model.dao.CompanyDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	ArrayList<Comcategory> comCategory = new ArrayList<>();
	comCategory = CompanyDaoImpl.getInstance().showAllComcategory();
	request.setAttribute("comCategory", comCategory);
	
	String[] categoryIcon = {"supervisor_account", "hotel", "local_bar", "directions_bus", "headset", 
			                 "business_center", "local_dining", "accessibility", "image_aspect_ratio", "attach_money"};
	request.setAttribute("categoryIcon", categoryIcon);
%>  	
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fiesta</title>
    <link href="resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        #requestFiesta {
            background-image: url('https://source.unsplash.com/collection/10914958/1600x900');
            background-size: cover;
            position: relative;
            min-height:90vh;
            
        }
        #requestFiesta .blackBox {
            background: black;
            height: 100%;
            opacity: 50%;
            min-height:90vh;
        }
        #requestFiesta .contentBox {
            color: white;
            position: absolute;
            top: 92px;
            bottom: 0;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #requestFiesta .contentBox h2 {
            font-size: 2rem;
            font-weight: bold;
            text-align: center;
            margin-bottom: 30px;
        }
        #requestFiesta .comCategoryList {
            width: 600px;
            display: flex;
            flex-wrap : wrap;
            justify-content: center;
        }
        #requestFiesta .comCategoryList a {
            color: white;
            display: block;
            margin: 10px 20px;
        }
        #requestFiesta .comCategoryList a:hover {
        	color: #009688;
        }
        #requestFiesta .comCategoryList a p {
        	text-align: center;
            font-weight: bold;
        }

        #comCategoryByCompany {
            width: 1080px;
            margin: auto;
        }
        #comCategoryByCompany h3{
            font-size: 1.5rem;
            margin: 0;
            font-weight: bold;
            margin-bottom: 15px;
            float: left;
        }
        #comCategoryByCompany .addList {
            color: black;
            float: right;
            margin-right: 30px;
        }
        #comCategoryByCompany .addList:hover {
            color: #009688;
        }
        #comCategoryByCompany .companyList{
            display: flex;
            margin-bottom: 30px;
            clear: both;
        }
        #comCategoryByCompany .companyList .company {
            margin-right: 30px;
            display: block;
            width: 100%;
            color: black;
        }
        #comCategoryByCompany .companyList .company:hover {
            color: #009688;
        }
        #comCategoryByCompany .companyList .company div {
            width: 100%;
            height: 180px;
            overflow: hidden;
        }
        #comCategoryByCompany .companyList .company div img {
            width: 100%;
        }
        #comCategoryByCompany .companyList .company h4 {
        	font-size: 1.2rem;
        	font-weight: bold;
        }
        
        /* responsive */
        @media (max-width: 600px) {
        	#requestFiesta .comCategoryList {
        		width: auto;
        	}
        }
        @media (max-width: 1080px) {
            #comCategoryByCompany {
                width: auto;
            }
            #comCategoryByCompany h3 {
                margin-left: 20px;
            }
            #comCategoryByCompany .addList {
                margin-right: 20px;
            }
            #comCategoryByCompany .companyList{
                display: block;
                margin: 20px;
                margin-bottom: 70px;
            }
            #comCategoryByCompany .companyList .company {
                margin-bottom: 40px;
            }
            #comCategoryByCompany .companyList .company div {
                height: 260px;
            }
        }
    </style>
</head>
<body>
    <!-- header import -->
    <jsp:include page = "header.jsp" />
    
    <!-- 항상 section에서 시작 -->
    <!-- 고객 의뢰 : 고객으로 로그인 했을 때만 이동, 나머지는 a 링크 막아버리기 -->
	<section id="requestFiesta"> 	
        <div class="blackBox"></div>
        <div class="contentBox">
            <div>
                <h2>딱! 맞는 업체를 소개해드립니다</h2>
                <div class="comCategoryList">
	                <c:forEach items="${comCategory}" var="category" varStatus="status">
                        <!-- 고객으로 로그인 했을 때만 이부분 링크 주소, 비회원인 경우 가입 페이지로, 업체인 경우 막아버리고 알람 띄우기 -->
	                	<!-- <a href="request.do?requestFiesta=${category.comCategoryCode}">-->
	                		<a href="customer/customerRequest.do?command=begin&requestFiesta=${category.comCategoryCode}">
	                		<i class="medium material-icons">${categoryIcon[status.index]}</i>
	                    	<p>${category.comCategoryDesc}</p>
	                	</a>
	                </c:forEach>
                </div>
            </div>
        </div>    
    </section>

    <!-- 카테고리별 업체 리스트 간략하게 보여주기 -->
    <section id="comCategoryByCompany">
    	<%
    		for(int i=0; i<comCategory.size(); i++) {
    			ArrayList<Company> company = new ArrayList<>();
    			company = CompanyDaoImpl.getInstance().showMainCompanyByCategory(comCategory.get(i).getComCategoryCode());
    			request.setAttribute("company", company);
    	%>
    		<h3><%=comCategory.get(i).getComCategoryDesc() %></h3>
    		<a class="addList" href="ShowAllCompanyByCategory.do?category=<%=comCategory.get(i).getComCategoryCode()%>">더 많은 업체 보기 ></a>
    		<div class="companyList">
    			<!-- 카테고리별로 업체 3개 -->
    			<c:forEach begin="0" end="2" var="cnt">
    				<a href="ServiceAllShow.do?companycode=${company[cnt].comCode}" class="company">
    					<div>
    						<img src="${company[cnt].comImg}">
    					</div>
    					<h4>${company[cnt].comName}</h4>
    					<p>${company[cnt].comDesc}</p>
    				</a>
    			</c:forEach>
    		</div>
    	<%
    		}
    	%>
    </section>

	<!-- footer import -->
	<jsp:include page = "footer.jsp" />
	
	
	
    <c:import url="footer.jsp" charEncoding="UTF-8"></c:import>
</body>
</html> 