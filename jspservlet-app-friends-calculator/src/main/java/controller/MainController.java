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

    public static int calculate(String myName, String friendName) {
        if (myName == null || friendName == null) {
            throw new IllegalArgumentException("Input names cannot be null.");
        }
    
        String combined = myName + friendName;
        int totalLength = combined.length();
        int keywordLength = "FRIENDS".length();
        int count = 0;
    
        for (int i = 0; i <= totalLength - keywordLength; i++) {
            if (combined.substring(i, i + keywordLength).equalsIgnoreCase("FRIENDS")) {
                count++;
            }
        }
    
        int percentage = (count * keywordLength * 100) / totalLength;
        return percentage;
    }
    
    
    
}
