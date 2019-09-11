package com.quincy.oauth.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
/*@Entity
@Table(name="xc_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")*/
public class User {

    /*@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)*/
    private String id;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String utype;
    private String birthday;
    private String userpic;
    private String sex;
    private String email;
    private String phone;
    private String status;
    //@Column(name="create_time")
    private Date createTime;
    //@Column(name="update_time")
    private Date updateTime;


}
