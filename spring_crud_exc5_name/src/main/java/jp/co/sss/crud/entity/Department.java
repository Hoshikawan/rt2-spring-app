package jp.co.sss.crud.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	private Integer deptId;
	
	@Column
	private String deptName;

	@OneToMany
	private List<Employee> employeeList;
//	↑　Employee型のコレクション（List/Set）があるのに、JPAの関連アノテーションが付いていませんよと言っているのは分かったが、
//  ↑　@OneToManyと@ManyToManyのどっちを付ければいいのか分からない
//	↑　URL設計書のエンティティ一覧には載っていなかったけど、クラス図には載っている。設計書に書かれていないからどっちが正しいのかな？
//			となった時に、URL設計書は返ってくる奴らなので、クラス図を見てフィールドがもう一つある！となったのが正解。
	
	
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
}
