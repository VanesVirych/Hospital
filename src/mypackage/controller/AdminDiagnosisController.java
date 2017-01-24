package mypackage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.ManagerDao;
import mypackage.dao.DiagnosisDao;
import mypackage.model.Diagnosis;

/**
 * Servlet implementation class DiagnosesAdminController
 */
@WebServlet("/admin/diagnosiscontroller")
public class AdminDiagnosisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String MAIN_JSP = "/main.jsp";
	private static String DIAGNOSIS_LIST = "/diagnosisList.jsp";
	private static String DIAGNOSIS_FORM_EDIT = "/diagnosisFormEdit.jsp";
	private static String DIAGNOSIS_FORM_ADD = "/diagnosisFormAdd.jsp";
	private static String ADMIN_MENU = "/adminMenu.jsp";
	
	private ManagerDao mDao;
	private DiagnosisDao udDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDiagnosisController() {
        super();
        mDao = new ManagerDao();
        udDao = new DiagnosisDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		request.setAttribute("title", "Список диагнозов");
		request.setAttribute("menu", ADMIN_MENU);
		request.setAttribute("content", DIAGNOSIS_LIST);
		if(null != action) {
			if(action.equalsIgnoreCase("delete")) {
				int diagnosisId = Integer.parseInt(request.getParameter("diagnosisid"));
				mDao.deleteDiagnosis(diagnosisId);
			} else if(action.equalsIgnoreCase("edit")) {
				request.setAttribute("title", "Редактирование данных диагноза");
				request.setAttribute("content", DIAGNOSIS_FORM_EDIT);
				int diagnosisId = Integer.parseInt(request.getParameter("diagnosisid"));
				request.setAttribute("diagnosis", udDao.getDiagnosisById(diagnosisId));
			} else if(action.equalsIgnoreCase("add")) {
				request.setAttribute("title", "Добавление нового диагноза");
				request.setAttribute("content", DIAGNOSIS_FORM_ADD);
			} else {
			}
		}
		request.setAttribute("diagnoses",udDao.getAllDiagnoses());
		
		RequestDispatcher view = request.getRequestDispatcher(MAIN_JSP);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setDiagnosis(request.getParameter("diagnosis"));
		diagnosis.setTreatment(request.getParameter("treatment"));
		String diagnosisId = request.getParameter("diagnosisid");
		
		if(diagnosisId == null || diagnosisId.isEmpty()) {
			mDao.addDiagnosis(diagnosis);
		} else {
			diagnosis.setDiagnosisId(Integer.parseInt(diagnosisId));
			mDao.updateDiagnosis(diagnosis);
		}
		response.sendRedirect(request.getContextPath() + request.getServletPath());
	}

}
