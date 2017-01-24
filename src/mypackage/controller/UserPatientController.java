package mypackage.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.PatientDao;

/**
 * Servlet implementation class UserPatientController
 */
@WebServlet("/patientlist")
public class UserPatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String MAIN_JSP = "/main.jsp";
	private static String PATIENT_LIST = "/pList.jsp";
	private static String PATIENT_DETAILS = "/pDetails.jsp";
	private PatientDao upDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPatientController() {
        super();
        upDao = new PatientDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.setAttribute("title", "Cписок больных");
		request.setAttribute("content", PATIENT_LIST);
		String action = request.getParameter("action");
		request.setAttribute("patients", upDao.getAllPatients());
	
		try {
			if(action.equalsIgnoreCase("showlist")) {
				String fio = request.getParameter("fio");
				if(fio!=null) {
					request.setAttribute("patients", upDao.getAllPatients(fio));
				}
				
				String dor = request.getParameter("dor");
				if(dor!=null) {
					Date tempDor = null;
					try {
			            tempDor = new SimpleDateFormat("MM/dd/yy").parse(dor);
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
					request.setAttribute("patients", upDao.getAllPatients(tempDor));
				}
			} else if(action.equalsIgnoreCase("show")) {
				request.setAttribute("title", "Детальная информация о пациенте");
				request.setAttribute("content", PATIENT_DETAILS);
				int patientId = Integer.parseInt(request.getParameter("patientid"));
				request.setAttribute("patient", upDao.getPatientById(patientId));
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
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
