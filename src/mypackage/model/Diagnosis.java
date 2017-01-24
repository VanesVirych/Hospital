package mypackage.model;

public class Diagnosis {
	private int diagnosisId;
	private String diagnosis;
	private String treatment;
	private int countPatients;
	
	
	
	public int getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public int getCountPatients() {
		return countPatients;
	}
	public void setCountPatients(int countPatients) {
		this.countPatients = countPatients;
	}
}
