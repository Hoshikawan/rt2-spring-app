package jp.co.sss.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.EmployeeRepository;
import jp.co.sss.crud.util.BeanManager;

/**
 * 全従業員検索サービスクラス。
 * データベースから全ての従業員情報を取得し、EmployeeBeanリストとして返却します。
 * 従業員情報は従業員IDの昇順でソートされます。
 * 
 * @author SystemShared Co., Ltd.
 * @version 1.0
 * @since 1.0
 */
@Service
public class SearchAllEmployeesService {

	/**
	 * 従業員データアクセス用リポジトリ。
	 * Spring DIによって自動注入されます。
	 */
	//TODO ここに記述
	@Autowired
	public EmployeeRepository repository;

	/**
	 * 全従業員情報を取得します。
	 * 
	 * データベースから全ての従業員エンティティを従業員ID昇順で取得し、
	 * BeanManagerを使用してEmployeeBeanリストに変換して返却します。
	 * @param sort 
	 * 
	 * @return 全従業員のEmployeeBeanリスト（従業員ID昇順）。データが存在しない場合は空のリストを返却
	 */
	//TODO ここに記述
	public List<EmployeeBean> execute(String sort){
		List<Employee> empList;
		// sort の値によって処理を分岐する
		if ("desc".equals(sort)) {
			empList = repository.findAllByOrderByEmpIdDesc(); // 降順で社員を取得
		} else {
			empList = repository.findAllByOrderByEmpIdAsc(); // asc / null / 不正値はすべて昇順
		}
		// Entity（データベース用） → Bean（画面用）に変換
		List<EmployeeBean> empBeans = BeanManager.copyEntityListToBeanList(empList);
		
		return empBeans;
	}
	
//	public List<EmployeeBean> execute(){
//		List<Employee> empList = repository.findAllByOrderByEmpIdAsc();
//		
//		// BeanManagerクラスの中に、{@codeList<Employee> -> List<EmployeeBean>} これがあった。
//		List<EmployeeBean> empBeans = BeanManager.copyEntityListToBeanList(empList);
//		
//		return empBeans;
//		
//	}

}