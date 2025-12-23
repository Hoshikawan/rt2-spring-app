package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	Employee findByEmpIdAndEmpPass(Integer empId, String empPass);
	
	// 昇順
	List<Employee> findAllByOrderByEmpIdAsc();
	// 降順
	List<Employee> findAllByOrderByEmpIdDesc();
	
	List<Employee> findByEmpNameContainingOrderByEmpId(String empName);
	
	List<Employee> findByDepartmentOrderByEmpId(Department department);

}
