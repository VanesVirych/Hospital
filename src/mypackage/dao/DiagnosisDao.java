package mypackage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mypackage.model.Diagnosis;
import mypackage.util.DbConnect;

public class DiagnosisDao {
	private Connection connection;
	private String sql;

	public DiagnosisDao() {
		super();
		connection = DbConnect.getConnection();
	}
	
	public List<Diagnosis> getAllDiagnoses() {
		List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();
		sql = "SELECT d.`diagnosisid`, d.`diagnosis`, d.`treatment`, " +
				"(SELECT COUNT(*) FROM `patients` p " +
				"WHERE p.`diagnosisid` = d.`diagnosisid` ) count " +
				"FROM `diagnoses` d;";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setDiagnosisId(resultSet.getInt("diagnosisid"));
				diagnosis.setDiagnosis(resultSet.getString("diagnosis"));
				diagnosis.setTreatment(resultSet.getString("treatment"));
				diagnosis.setCountPatients(resultSet.getInt("count"));
				diagnoses.add(diagnosis);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diagnoses;
	}
	
	public Diagnosis getDiagnosisById(int diagnosisId) {
		Diagnosis diagnosis = new Diagnosis();
		sql = "SELECT * FROM `diagnoses` WHERE `diagnosisid` = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, diagnosisId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				diagnosis.setDiagnosisId(resultSet.getInt("diagnosisid"));
				diagnosis.setDiagnosis(resultSet.getString("diagnosis"));
				diagnosis.setTreatment(resultSet.getString("treatment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return diagnosis;
	}
}
