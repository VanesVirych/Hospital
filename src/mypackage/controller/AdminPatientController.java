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

import mypackage.dao.ManagerDao;
import mypackage.dao.DiagnosisDao;
import mypackage.dao.PatientDao;
import mypackage.model.Diagnosis;
import mypackage.model.Patient;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin/patientcontroller")
public class AdminPatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String MAIN_JSP = "/main.jsp";
	private static String PATIENT_LIST = "/patientsList.jsp";
	private static String PATIENT_FORM_EDIT = "/patientFormEdit.jsp";
	private static String PATIENT_FORM_ADD = "/patientFormAdd.jsp";
	private static String ADMIN_MENU = "/adminMenu.jsp";
	private ManagerDao mDao;
	private PatientDao upDao;
	private DiagnosisDao udDao;

	/**
	 * Default constructor.
	 */
	public AdminPatientController() {
		mDao = new ManagerDao();
		upDao = new PatientDao();
		udDao = new DiagnosisDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		request.setAttribute("title", "Список пациентов");
		request.setAttribute("menu", ADMIN_MENU);
		request.setAttribute("content", PATIENT_LIST);
		
		if (null != action ) {
			if (action.equalsIgnoreCase("delete")) {
				int patientId = Integer.parseInt(request.getParameter("patientid"));
				mDao.deletePatient(patientId);
			} else if (action.equalsIgnoreCase("edit")) {
				request.setAttribute("title", "Редактирование данных пациента");
				request.setAttribute("content", PATIENT_FORM_EDIT);
				int patientId = Integer.parseInt(request.getParameter("patientid"));
				request.setAttribute("patients", upDao.getAllPatients());
				request.setAttribute("patient", upDao.getPatientById(patientId));
				request.setAttribute("diagnoses", udDao.getAllDiagnoses());
			} else if (action.equalsIgnoreCase("add")) {
				request.setAttribute("title", "Добавление нового пациента");
				request.setAttribute("content", PATIENT_FORM_ADD);
				request.setAttribute("diagnoses", udDao.getAllDiagnoses());
			} else {
			}
		}
		request.setAttribute("patients", upDao.getAllPatients());
		RequestDispatcher view = request.getRequestDispatcher(MAIN_JSP);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Patient patient = new Patient();

		patient.setFio(request.getParameter("fio"));
		patient.setAge(Integer.parseInt(request.getParameter("age")));

		try {
			Date dor = new SimpleDateFormat("MM/dd/yy").parse(request.getParameter("dor"));
			patient.setDor(dor);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		patient.setState(request.getParameter("state"));
		
		String diagnosisId = request.getParameter("diagnosisid");
		if(diagnosisId == null || diagnosisId.isEmpty()) {
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosis(request.getParameter("diagnosis"));
			diagnosis.setTreatment(request.getParameter("treatment"));
			patient.setDiagnosisId(mDao.addDiagnosis(diagnosis));
		} else {
			patient.setDiagnosisId(Integer.parseInt(diagnosisId));
		}
		
		String patientId = request.getParameter("patientid");
		if (patientId == null || patientId.isEmpty()) {
			mDao.addPatient(patient);
		} else {
			patient.setPatientId(Integer.parseInt(patientId));
			mDao.updatePatient(patient);
		}

		response.sendRedirect(request.getContextPath() + request.getServletPath());
	}
}
