package mypackage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mypackage.model.Diagnosis;
import mypackage.model.Patient;
import mypackage.util.DbConnect;

public class ManagerDao {
	private Connection connection;
	private String sql;

	public ManagerDao() {
		super();
		connection = DbConnect.getConnection();
	}

	public void addPatient(Patient patient) {
		sql = "INSERT INTO `patients` (`fio`, `age`, `diagnosisid`, `dor`, `state`) "
				+ "VALUES (?, ?, ?, ?, ?);";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, patient.getFio());
			preparedStatement.setInt(2, patient.getAge());
			preparedStatement.setInt(3, patient.getDiagnosisId());
			preparedStatement.setDate(4, new java.sql.Date(patient.getDor().getTime()));
			preparedStatement.setString(5, patient.getState());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updatePatient(Patient patient) {
		sql = "UPDATE `patients` SET `fio`=?, `age`=?, "
				+ "`diagnosisid`=?, `dor`=?, `state`=? "
				+ "WHERE  `patientid`=?;";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, patient.getFio());
			preparedStatement.setInt(2, patient.getAge());
			preparedStatement.setInt(3, patient.getDiagnosisId());
			preparedStatement.setDate(4, new java.sql.Date(patient.getDor().getTime()));
			preparedStatement.setString(5, patient.getState());
			preparedStatement.setInt(6, patient.getPatientId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePatient(int patientId) {
		sql = "DELETE FROM `patients` WHERE  `patientid`=?;";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, patientId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addDiagnosis(Diagnosis diagnosis) {
		sql = "INSERT INTO `diagnoses` (`diagnosis`, `treatment`) "
				+ "VALUES (?, ?);";
		int diagnosisId = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, diagnosis.getDiagnosis());
			preparedStatement.setString(2, diagnosis.getTreatment());
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				diagnosisId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diagnosisId;
	}
	
	public void deleteDiagnosis(int diagnosisId) {
		sql = "DELETE FROM `diagnoses` WHERE  `diagnosisid`=?;";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, diagnosisId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateDiagnosis(Diagnosis diagnosis) {
		sql = "UPDATE `diagnoses` SET `diagnosis`=?, `treatment`=? "
				+ "WHERE  `diagnosisid`=?;";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, diagnosis.getDiagnosis());
			preparedStatement.setString(2, diagnosis.getTreatment());
			preparedStatement.setInt(3, diagnosis.getDiagnosisId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
