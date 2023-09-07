package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/jspservlet-app-friends-calculator/friends" })
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MainController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String myName = request.getParameter("friend1");
        String friendName = request.getParameter("friend2");

        int result = calculate(myName, friendName);
        String calculate = request.getParameter("calculate");
        System.out.println(result);

        if (calculate != null) {
            // PrintWriter out = response.getWriter();
            // out.println("<h3>Based on the name similarity, the friendship strength between "
            //         + myName + " and " + friendName + " is: " + result + "</h3>");

            request.setAttribute("myName", myName);
            request.setAttribute("friendName", friendName);
            request.setAttribute("result", result);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    // Don't change the above lines of code

    // Start entering your code here...
    public static int findSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum = sum + (num % 10);
            num = num / 10;
        }
        return sum;
    }

    public int calculate(String name1, String name2) {
		String combined = name1 + name2;
		int count = 0;
	
		for (int i = 0; i < "FRIENDS".length(); i++) {
			count += countOccurrences(combined, "FRIENDS".charAt(i));
		}
	
		int total = name1.length() + name2.length();
		int percentage = count * 100 / total;
	
		return percentage;
	}
	
	// Utility method to count the occurrences of a character in a string
	private int countOccurrences(String str, char ch) {
		int count = 0;
	
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ch) {
				count++;
			}
		}
	
		return count;
	}
	
}
