package com.quincy.oauth.domain;


import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class UserExt<T> extends User {

    //权限信息
    private List<T> permissions;

    //企业信息
    private String companyId;
}
