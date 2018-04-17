package fan.jdbc.service.imp;

import fan.jdbc.dao.ITb_User;
import fan.jdbc.dao.imp.ITb_UserImpI;
import fan.jdbc.domain.Tb_User;
import fan.jdbc.service.ITb_UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
/**
 * 链接与dao层的链接
 * @author Xiao_Zhu
 *
 */
public class ITb_UserServiceImpI  implements ITb_UserService{
	public Connection conn1 = null;
	public ResultSet rs = null;
	public PreparedStatement ps = null;
	boolean b=false;
	//这里的 service层要想玉 dao层(BAL层逻辑层)建立联系那么必须要 创建  dao层的对象  
   ITb_User  myiTb_User=new ITb_UserImpI();//创建了 dao层的ITb_UserImp对象 

    //查询所有数据
	@Override 
	public List<Tb_User> queryAllData() {
		 
		return myiTb_User.queryAllData();
	}
    
	//新增  
	@Override
	public boolean insertData(Tb_User t) {
		if (t!=null) { 
			 if (t!=null) {
				 myiTb_User.insertData(t);
				 b=true; 
			} 
		}
		 return b;
	}
    
	//修改
	@Override
	public boolean update(Tb_User t) {
		if (t!=null) { 
			 if (t!=null) {
				 myiTb_User.update(t);
				 b=true; 
			} 
		}
		 return b;
	}
    //删除
	@Override
	public boolean delete(int id) {
		if (id!=0) {  
				 myiTb_User.delete(id);
				 b=true;  
		}
		 return b;
	}
   //查询一条数据
	@Override
	public Tb_User queryDataById(int id) {
		 if (id!=0) {
		return myiTb_User.queryDataById(id);
		}
		 else {
			return null;
		}
	}

}
