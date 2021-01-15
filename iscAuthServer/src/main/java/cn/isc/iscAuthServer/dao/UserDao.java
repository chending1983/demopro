 package cn.isc.iscAuthServer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.isc.iscAuthServer.model.resposity.User;

/**
 * @author isc
 * @date 2021/01/12
 */
public interface UserDao {

    @Select("select * from Users")
    public List<User> selectAllUser();

}
