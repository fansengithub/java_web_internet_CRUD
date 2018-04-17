package fan.jdbc.servlet;

import fan.jdbc.domain.Tb_User;
import fan.jdbc.service.ITb_UserService;
import fan.jdbc.service.imp.ITb_UserServiceImpI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Servlet_TbUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 //这里建立 与service层的  联系  创建一个service层imp的某个的对   
	ITb_UserService myITb_UserService=new ITb_UserServiceImpI();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");//解决乱码
		 String type=request.getParameter("who"); 
		 //新增
		 if("Insert".equals(type)){
			 Insert(request, response);
			}
			else if("update".equals(type)){
				update(request, response);
			} 
			else if("queryById".equals(type)){
				queryById(request, response);
			} 
			else if("delete".equals(type)){
				delete(request, response);
			} 
			else if("queryAll".equals(type)){
//				queryAll(request,response);
				queryAll(request, response);
			} 
	}
//新增
	 public void Insert(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		 //这里jsp中name专递过来的参数
		 String name=request.getParameter("name");
		 String birthday=request.getParameter("birthday");
		 String password=request.getParameter("password");
		 String email=request.getParameter("email");
		 String sex=request.getParameter("sex");
		  //把获取到的这些值放到user里
		Tb_User user =new Tb_User();
		
		 try {
			//下面两句是把 string 转换为  sql类型的 时间格式
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(new Date(sdf.parse(birthday).getTime()));
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		 
		 user.setEmail(email);
		 user.setName(name) ;
		 user.setPassword(password);  
		 if ("1".equals(sex)) {
			 user.setSex(true);
		}
		 else if ("0".equals(sex)) {
			 user.setSex(false);
		} 
		 //最后调用服务来添加
		String message=null; 
		 if (myITb_UserService.insertData(user)==true) { 
			 queryAll(request, response); 
		}
		 else {
			 message="新增失败!!!";
			 request.setAttribute("msg", message);
			 request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		
	 }
	//修改 
	 public void update(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		  String name=request.getParameter("name");
		  String birthday=request.getParameter("birthday");
		 String password=request.getParameter("password");
//	 String email=request.getParameter("email");
		 String sex=request.getParameter("sex");
		 String id=request.getParameter("id");
		  //把获取到的这些值放到user里
		 Tb_User user =new Tb_User(); 
		 try {
			//下面两句是把 string 转换为  sql类型的 时间格式
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			user.setBirthday(new Date(sdf.parse(birthday).getTime()));
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		 user.setId(Integer.parseInt(id));
	//	 user.setEmail(email);
		 user.setName(name) ;
		 user.setPassword(password);  
		 if ("1".equals(sex)) {
			 user.setSex(true);
		}
		 else if ("0".equals(sex)) {
			 user.setSex(false);
		}  
		 boolean b=  myITb_UserService.update(user); 
		  if (b==true) { 
				 queryAll(request, response); 
			}
			 else { 
				 request.setAttribute("msg", "修改失败!!");
					request.getRequestDispatcher("/index.jsp").forward(request, response); 
			}
		 
	 }
	 //查询一条数据
	 public void queryById(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException { 
		     Tb_User user=null; 
		      String  id=  request.getParameter("id");   
		      System.out.println(id);
		      user=   myITb_UserService.queryDataById(Integer.parseInt(id) ); 
		    request.setAttribute("user", user);
		    request.getRequestDispatcher("/jsp/User.jsp").forward(request, response);
	 }
	 //删除
	 public void delete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {  
		    String  id= request.getParameter("id");   
		     System.out.println(id);
		     boolean  message=myITb_UserService.delete(Integer.parseInt(id)); 
		     if (message==true) { 
				 queryAll(request, response); 
			}
			 else {
				 
				 request.setAttribute("msg", "删除失败!!");
					request.getRequestDispatcher("/index.jsp").forward(request, response); 
			}
	 }
	 //查询所有的数据
	 public void queryAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
			 List<Tb_User> lis=myITb_UserService.queryAllData(); 
			 request.setAttribute("list", lis);
		     	request.getRequestDispatcher("/jsp/User.jsp").forward(request, response);
		}
	  
}
