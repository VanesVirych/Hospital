package mypackage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.DiagnosisDao;

/**
 * Servlet implementation class UserDiagnosisList
 */
@WebServlet("/diagnosislist")
public class UserDiagnosisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String MAIN_JSP = "/main.jsp";
	private static String DIAGNOSIS_LIST = "/dList.jsp";
	private static String DIAGNOSIS_DETAILS = "/dDetails.jsp";
	private DiagnosisDao udDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDiagnosisController() {
        super();
        udDao = new DiagnosisDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Список диагнозов");
		request.setAttribute("content", DIAGNOSIS_LIST);
		request.setAttribute("diagnoses", udDao.getAllDiagnoses());
		String diagnosisId = request.getParameter("diagnosisid");
		if(diagnosisId != null) {
			request.setAttribute("title", "Детальная информация о диагнозе");
			request.setAttribute("content", DIAGNOSIS_DETAILS);
			request.setAttribute("diagnosis", udDao.getDiagnosisById(Integer.parseInt(diagnosisId)));
		}
		RequestDispatcher view = request.getRequestDispatcher(MAIN_JSP);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
