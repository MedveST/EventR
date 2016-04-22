package aut.bme.hu.eventr.model;

import com.orm.dsl.Table;

@Table
public class UserModel
{
    private Long id;
    private String email;
    private String pass;

    public UserModel(){
    }

    public UserModel(String email, String pass){
        this.email = email;
        this.pass = pass;
    }

    public Long getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPass()
    {
        return pass;
    }
}
