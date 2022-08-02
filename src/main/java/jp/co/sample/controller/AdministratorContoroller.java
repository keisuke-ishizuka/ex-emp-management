package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministratorForm;
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
		InsertAdministratorForm form = new InsertAdministratorForm();
		
		return form;
	}
	
	/**
	 * administrator/insert.htmlへフォワード
	 *
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}

}
