package com.wavemaker.employee.repository;

import com.wavemaker.employee.model.UserAuthentication;

public interface UserAuthenticationRepository
{

    public boolean addUserLogin(UserAuthentication userAuthentication);
}
