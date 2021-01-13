 package cn.isc.iscWebDemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.isc.iscWebDemo.model.resposity.User;

/**
 * @author isc
 * @date 2021/01/12
 */
public interface UserDao {

    @Select("select * from Users")
    public List<User> selectAllUser();

}
