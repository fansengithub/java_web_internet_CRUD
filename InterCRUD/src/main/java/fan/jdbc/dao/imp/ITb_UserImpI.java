package fan.jdbc.dao.imp;

import fan.jdbc.dao.ITb_User;
import fan.jdbc.domain.Tb_User;
import fan.jdbc.unit.UnitMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 ITb_User类
 * 
 * @author Xiao_Zhu
 * 
 */
public class ITb_UserImpI implements ITb_User {
	public Connection conn1 = null;
	public ResultSet rs = null;
	public PreparedStatement ps = null;

	// 查询所有的数据
	@Override
	public List<Tb_User> queryAllData() {
		conn1 = UnitMysql.getConnection();// 链接数据库
		List<Tb_User> list = new ArrayList<Tb_User>();
		try {
			String sqlSelect = "select * from users "; // 查询多条数据
			ps = conn1.prepareStatement(sqlSelect);
			rs = ps.executeQuery();
			Tb_User user = null;
			while (rs.next()) {
				user = new Tb_User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setBirthday(rs.getDate("birthday"));
				user.setSex(rs.getBoolean("sex"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UnitMysql.Close(rs, ps, conn1);
		}
		return list;
	}

	// 新增
	@Override
	public int insertData(Tb_User t) {
		conn1 = UnitMysql.getConnection();
		int i = 0;
		try {
			String sqlInsert = "insert into users(name,password,email,birthday,sex) values(?,?,?,?,?) ;";
			ps = conn1.prepareStatement(sqlInsert,
					PreparedStatement.RETURN_GENERATED_KEYS);
			// 这里的1,2..必须要按上面的新增的顺序来定义
			ps.setString(1, t.getName());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getEmail());
			ps.setDate(4, new java.sql.Date(t.getBirthday().getTime()));
			ps.setBoolean(5, t.isSex());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();// 得到 最新的 ID
			if (rs.next()) {// 是否存在
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UnitMysql.Close(rs, ps, conn1);
		}
		return i;
	}

	// 修改
	@Override
	public int update(Tb_User t) {
		conn1 = UnitMysql.getConnection();
		int i = 0;
		try {
			String sqlUpdate = "update users set name=?, password =? ,sex=?  where id=?";
			ps = conn1.prepareStatement(sqlUpdate);
			ps.setString(1, t.getName());
			ps.setString(2, t.getPassword());
			ps.setBoolean(3, t.isSex());
			ps.setInt(4, t.getId());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UnitMysql.Close(null, ps, conn1);
		}
		return i;
	}

	// 删除
	@Override
	public int delete(int id) {
		conn1 = UnitMysql.getConnection();
		int i = 0;
		try {
			String sqlDelete = "delete from users where id=?";
			ps = conn1.prepareStatement(sqlDelete);
			ps.setInt(1, id);
			i = ps.executeUpdate();
			if (i == 1) {
				return i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UnitMysql.Close(null, ps, conn1);
		}
		return i;
	}

	// 查询一条数据通过ID
	@Override
	public Tb_User queryDataById(int id) {
		conn1 = UnitMysql.getConnection();
		String sql = "select * from users where id=?";
		Tb_User user = null;
		if (id > 0) {
			try {

				ps = conn1.prepareStatement(sql);
				ps.setInt(1, id);
//				ps= conn1.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					user = new Tb_User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setBirthday(rs.getDate("birthday"));
					user.setSex(rs.getBoolean("sex"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				UnitMysql.Close(null, ps, conn1);
			}
		}
		return user;
	}

}
