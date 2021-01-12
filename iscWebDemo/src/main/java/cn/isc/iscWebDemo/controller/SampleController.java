 package cn.isc.iscWebDemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.isc.iscWebDemo.dao.UserDao;
import cn.isc.iscWebDemo.model.resposity.User;

/**
  * sample Controller
  * 
 * @author charlie
 * @date 2021/01/11
 */
@RestController
public class SampleController {

    @RequestMapping(value = "/sss")
    public String error(HttpServletResponse resp, HttpServletRequest req) {
        // 错误处理逻辑
        int i = 5/0;
        return "出异常了么？" + i;
    }
    
    @Autowired
    private UserDao userDao;
    
    @RequestMapping(value = "/user/{name}")
    public String getUser(@PathVariable String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("${name} is null");
        }
        List<User> users = userDao.selectAllUser();
        for (User user : users) {
           if (name.equals(user.getUserName())) {
               return "users " + name + "PassWord is "+ user.getPassWord();
           }
        }
        return "查无此人";
    }

    
}
