package controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/jspservelt-app-friends-calculator/friends"})
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

        if (calculate != null) {
            request.setAttribute("myName", myName);
            request.setAttribute("friendName", friendName);
            request.setAttribute("result", result);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    // Method to calculate friendship percentage
    private int calculate(String myName, String friendName) {
        int similarityScore = calculateSimilarity(myName, friendName);
        int friendshipPercentage = similarityScore * 10; // Assuming a simple formula

        return friendshipPercentage;
    }

    // Method to calculate similarity score (You need to implement this)
    private int calculateSimilarity(String name1, String name2) {
        int similarityScore = 0;

        // Implement your logic to calculate similarity between two names
        // For example, you could compare characters, calculate Levenshtein distance, etc.
        // Return a similarity score (a higher score indicates higher similarity)

        // For this example, let's just compare the characters
        for (int i = 0; i < Math.min(name1.length(), name2.length()); i++) {
            if (name1.charAt(i) == name2.charAt(i)) {
                similarityScore++;
            }
        }

        return similarityScore;
    }
}
