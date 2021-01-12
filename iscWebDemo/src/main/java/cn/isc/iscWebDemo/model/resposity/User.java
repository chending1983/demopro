 package cn.isc.iscWebDemo.model.resposity;

/**
 * user bean 
 * 
 * @author isc
 * @date 2021/01/12
 */
public class User {
    
    // sequence
    public int id;
    
    // name
    public String userName;
    
    // password
    public String passWord;
    
    // enabled?
    public int enabled;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

}
