package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
      
	ArrayList<Article> articles = new ArrayList<>();
	int lastArticleId = 1;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("add")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Article article = new Article(lastArticleId, title, content);
			articles.add(article);
			out.println("게시물 등록이 완료되었습니다.");
//			printArticles(articles, response);// v1. 직접 함수 호출
			
			//v2. list url을 다시 호출
			
			// 주소값을 바꾸기 -> 리다이렉트 
			response.sendRedirect("/servlet-test/TestServlet?cmd=list");
						
		} else if(cmd.equals("list")) {
//			for(Article a : articles) {
//				out.println(String.format("번호 : %d <br>", a.getId()));
//				out.println(String.format("제목 : %s <br>", a.getTitle()));
//				out.println(String.format("내용 : %s <br>", a.getContent()));
//				out.println("<a href='test.html'>등록 화면으로</a>");
//				
//			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/list.jsp");
			request.setAttribute("articles", articles);
			dispatcher.forward(request, response);
			
		} else if(cmd.equals("test")) {
			String num2 = request.getParameter("num2");
			System.out.println(num2);
		} else if(cmd.equals("gugu")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/gugudan.jsp");
			dispatcher.forward(request, response);
		}
	}
}
