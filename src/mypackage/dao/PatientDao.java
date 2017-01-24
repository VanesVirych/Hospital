package mypackage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mypackage.model.Patient;
import mypackage.util.DbConnect;

public class PatientDao {
	private Connection connection;
	private String sql;

	public PatientDao() {
		super();
		connection = DbConnect.getConnection();
	}

	public List<Patient> getAllPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		sql = "SELECT p.`patientid`, p.`fio`, p.`age`, p.`dor`, " +
				"d.`diagnosis`, d.`treatment`, p.`state` " +
				"FROM `patients` p, `diagnoses` d " +
				"WHERE p.`diagnosisid` = d.`diagnosisid`;";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Patient patient = new Patient();
				patient.setPatientId(resultSet.getInt("patientid"));
				patient.setFio(resultSet.getString("fio"));
				patient.setAge(resultSet.getInt("age"));
				patient.setDor(resultSet.getDate("dor"));
				patient.setDiagnosis(resultSet.getString("diagnosis"));
				patient.setTreatment(resultSet.getString("treatment"));
				patient.setState(resultSet.getString("state"));
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patients;		
	}
	
	public List<Patient> getAllPatients(java.util.Date dor) {
		List<Patient> patients = new ArrayList<Patient>();
		sql="SELECT p.`patientid`, p.`fio`, p.`age`, p.`dor`, " +
				"d.`diagnosis`, d.`treatment`, p.`state` " +
				"FROM `patients` p, `diagnoses` d " +
				"WHERE p.`diagnosisid` = d.`diagnosisid` " +
				"AND `dor`=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, new java.sql.Date(dor.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Patient patient = new Patient();
				patient.setPatientId(resultSet.getInt("patientid"));
				patient.setFio(resultSet.getString("fio"));
				patient.setAge(resultSet.getInt("age"));
				patient.setDiagnosis(resultSet.getString("diagnosis"));
				patient.setTreatment(resultSet.getString("treatment"));
				patient.setDor(resultSet.getDate("dor"));
				patient.setState(resultSet.getString("state"));
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}
	
	public List<Patient> getAllPatients(String patientFio) {
		List<Patient> patients = new ArrayList<Patient>();
		sql="SELECT p.`patientid`, p.`fio`, p.`age`, p.`dor`, " +
				"d.`diagnosis`, d.`treatment`, p.`state` " +
				"FROM `patients` p, `diagnoses` d " +
				"WHERE p.`diagnosisid` = d.`diagnosisid` " +
				"AND `fio`=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, patientFio);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Patient patient = new Patient();
				patient.setPatientId(resultSet.getInt("patientid"));
				patient.setFio(resultSet.getString("fio"));
				patient.setAge(resultSet.getInt("age"));
				patient.setDiagnosis(resultSet.getString("diagnosis"));
				patient.setTreatment(resultSet.getString("treatment"));
				patient.setDor(resultSet.getDate("dor"));
				patient.setState(resultSet.getString("state"));
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}
	
	public Patient getPatientById(int patientId) {
		Patient patient = new Patient();
		sql = "SELECT p.`patientid`, p.`fio`, p.`age`, p.`dor`, p.`diagnosisid`, " +
				"d.`diagnosis`, d.`treatment`, p.`state` " +
				"FROM `patients` p, `diagnoses` d " +
				"WHERE p.`diagnosisid` = d.`diagnosisid` " +
				"AND `patientid`=?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, patientId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				patient.setPatientId(resultSet.getInt("patientid"));
				patient.setFio(resultSet.getString("fio"));
				patient.setAge(resultSet.getInt("age"));
				patient.setDiagnosisId(resultSet.getInt("diagnosisid"));
				patient.setDiagnosis(resultSet.getString("diagnosis"));
				patient.setTreatment(resultSet.getString("treatment"));
				patient.setDor(resultSet.getDate("dor"));
				patient.setState(resultSet.getString("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patient;
	}
}
