package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.LoginResultBean;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.service.LoginService;

@Controller
public class IndexController {

	@Autowired
	LoginService loginService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm) {
		return "index";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model, HttpSession sesson) {
		
		// 新しく入力チェックのために入れたが書く場所が合っているか分からない（下３行）
		if (result.hasErrors()) {
			return "index";
		}

		String path = "index";

		//TODO LoginServiceが完成後にコメントを外す
		LoginResultBean loginResultBean = loginService.execute(loginForm);
		
		if (loginResultBean.isLogin()) {
			sesson.setAttribute("loginUser", loginResultBean.getLoginUser());
			path="redirect:/list";
		} else {
			model.addAttribute("errMessage", loginResultBean.getErrorMsg());
		}

		return path;

	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}

}
