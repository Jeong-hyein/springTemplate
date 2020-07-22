package com.dbal.app.emp;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter //get만
@Data    // get,set,toString
@NoArgsConstructor //인수가 없는 빈 생성자
public class EmpVO {
	
	MultipartFile[] uploadFile; //파일업로드에 필요
	String msg; 
	String profile;  //파일 업로드에 필요
	
	//@JsonProperty(value = "id")
	String employeeId;
	
	String firstName;
	String lastName;
	String email;
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH")
	// Date hireDate;		//LocalDateTimem, Date  -> String은 안된다.
	
	String hireDate;
	
//	@JsonIgnore 안쓰는거에 붙이면 안나옴
	@JsonIgnore 
	String jobId;
	
	@JsonIgnore 
	String departmentId;
	
	@JsonIgnore 
	Integer salary;
	
	@JsonIgnore 
	Integer[] employeeIds;
	

	
	/*
	 * @Builder public EmpVO(String employee_id, String first_name, String
	 * last_name, String email, String hire_date, String job_id, String
	 * department_id) { this.employee_id = employee_id; this.first_name =
	 * first_name; this.last_name = last_name; this.email = email; this.hire_date =
	 * hire_date; this.job_id = job_id; this.department_id = department_id; }
	 */
	
	/* 이걸 대신해주는게 lombok, jar 파일이 필요함(maven repository)
	 * public String getEmployee_id() { return employee_id; }
	 * 
	 * public void setEmployee_id(String employee_id) { this.employee_id =
	 * employee_id; }
	 * 
	 * public String getFirst_name() { return first_name; }
	 * 
	 * public void setFirst_name(String first_name) { this.first_name = first_name;
	 * }
	 * 
	 * public String getLast_name() { return last_name; }
	 * 
	 * public void setLast_name(String last_name) { this.last_name = last_name; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * 
	 * public String getHire_date() { return hire_date; }
	 * 
	 * public void setHire_date(String hire_date) { this.hire_date = hire_date; }
	 * 
	 * public String getJob_id() { return job_id; }
	 * 
	 * public void setJob_id(String job_id) { this.job_id = job_id; }
	 * 
	 * @Override public String toString() { return "EmpVO [employee_id=" +
	 * employee_id + ", first_name=" + first_name + ", last_name=" + last_name +
	 * ", email=" + email + ", hire_date=" + hire_date + ", job_id=" + job_id + "]";
	 * }
	 */
	


	
	

}
