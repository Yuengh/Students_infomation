package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO;
import service.impl.UsersDAOImpl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users users = new Users();
	//用户登陆动作
	public String login(){
		UsersDAO uDao = new UsersDAOImpl();
		if (uDao.usersLogin(users)){
			//在session中保存登陆成功的用户名
			session.setAttribute("loginUserName", users.getUsername());
			return 	"login_success";
		}
		else {
			return "login_failure";
		}
	}
	@SkipValidation
	//表示该注销方法不需要用到表单验证
	//用户注销方法
	public String logout(){
		if (session.getAttribute("loginUserName")!=null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	//表单验证
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		//用户名不能为空
		if ("".equals(users.getUsername().trim())) {
			this.addFieldError("usernameError", "用户名不能为空");
		}
		if (users.getPassword().length()<3) {
			this.addFieldError("passwordError", "密码长度不能少于3位");
		}
	}

	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.users;
	}
	
}
