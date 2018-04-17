package fan.jdbc.dao;

import fan.jdbc.command.IDaoCommand;
import fan.jdbc.domain.Tb_User;

/**
 * 这里这个类 是为了 ,后续 添加自己需要的方法. 如:模糊查询, 分页查询....
 * 这个必须要继承ICommand类,那样就可以调用增删查改的方法了
 * @author
 *
 */
public interface ITb_User extends IDaoCommand<Tb_User> {
   //自己需要的方法
}
