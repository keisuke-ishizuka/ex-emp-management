package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AddministratorService;

/**
 * コントローラークラス
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorContoroller {
	
	@Autowired
	private AddministratorService addministratorService;
	
	/**
	 * InsertAdministratorFormをインスタンス化
	 *
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm administratorForm = new InsertAdministratorForm();
		return administratorForm;
	}
	
	/**
	 * administrator/insert.htmlへフォワード
	 *
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}

	/**
	 * insertの呼び出し
	 *
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		
		addministratorService.insert(administrator);
		return "/";
	}
	
	/**
	 * LoginFormをインスタンス化
	 *
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}
	
	/**
	 * administrator/login.htmlへフォワード
	 *
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}
	
	@Autowired
	private HttpSession session;
	
	/**
	 * login
	 *
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator 
			= addministratorService.login(form.getMailAddress(), form.getPassword());
		
		if(administrator == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
		} else {
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
		}
	}
	
	/**
	 * logout
	 *
	 */
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
