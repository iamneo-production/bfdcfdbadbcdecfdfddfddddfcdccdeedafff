package controller;

import java.io.IOException;
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
            request.setAttribute("myName", myName);
            request.setAttribute("friendName", friendName);
            request.setAttribute("result", result);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    // Iteration - 1: FindSumMethod
    public static int findSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum = sum + (num % 10);
            num = num / 10;
        }
        return sum;
    }

    // Iteration - 2: Calculate Friendship Percentage
    public static int calculate(String myName, String friendName) {
        if (myName == null || friendName == null) {
            return 0; // You can choose a default value or handle it differently
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
