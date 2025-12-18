package jp.co.sss.crud.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.service.SearchForEmployeesByEmpIdService;
import jp.co.sss.crud.service.UpdateEmployeeService;
import jp.co.sss.crud.util.BeanManager;

@Controller
public class UpdateController {

	@Autowired
	SearchForEmployeesByEmpIdService searchForEmployeesByEmpIdService;

	@Autowired
	UpdateEmployeeService updateEmployeeService;

	/**
	 * 社員情報の変更内容入力画面を出力
	 *
	 * @param empId
	 *            社員ID
	 * @param model
	 *            モデル
	 * @return 遷移先のビュー
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/update/input", method = RequestMethod.GET)
	public String inputUpdate(HttpServletRequest request,Integer empId, @ModelAttribute EmployeeForm employeeForm, Model model) {
		
		// headerの「ようこそ」リンクから来た場合はempIdが送られてこないためnullになりエラーが出てきてしまう。
		// ログイン中ユーザーのempIdを使用するとエラーが出てこなくなる。
		
		// このリクエストが一覧画面経由ではなくてheader経由で来たと分かるコードを記述
		if(empId == null) {
			HttpSession session = request.getSession();
			// ログイン中の本人情報を取り出している
			EmployeeBean loginUser = (EmployeeBean)session.getAttribute("loginUser");
			// 更新対象の社員IDを、ログインしている本人のIDに置き換える
			empId = loginUser.getEmpId();
		}
		

		EmployeeBean employee = null;

		//TODO SearchForEmployeesByEmpIdService完成後にコメントを外す
		employee = searchForEmployeesByEmpIdService.execute(empId);

		employeeForm = BeanManager.copyBeanToForm(employee);
		model.addAttribute("employeeForm", employee);

		return "update/update_input";
	}

	/**
	 * 社員情報の変更確認画面を出力
	 *
	 * @param employeeForm
	 *            変更対象の社員情報
	 * @param model
	 *            モデル
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/check", method = RequestMethod.POST)
	public String checkUpdate(@Valid @ModelAttribute EmployeeForm employeeForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "update/update_input";
		}

		return "update/update_check";
	}

	/**
	 * 変更内容入力画面に戻る
	 *
	 * @param employeeForm 変更対象の社員情報
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/back", method = RequestMethod.POST)
	public String backInputUpdate(@ModelAttribute EmployeeForm employeeForm) {
		return "update/update_input";
	}

	/**
	 * 社員情報の変更
	 *
	 * @param employeeForm
	 *            変更対象の社員情報
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/complete", method = RequestMethod.POST)
	public String completeUpdate(EmployeeForm employeeForm) {

		//TODO UpdateEmployeeService完成後にコメントを外す
		updateEmployeeService.execute(employeeForm);

		return "redirect:/update/complete";
	}

	/**
	 * 完了画面の表示
	 * 
	 * @return 遷移先ビュー
	 */
	@RequestMapping(path = "/update/complete", method = RequestMethod.GET)
	public String completeUpdate() {
		return "update/update_complete";
	}

}
