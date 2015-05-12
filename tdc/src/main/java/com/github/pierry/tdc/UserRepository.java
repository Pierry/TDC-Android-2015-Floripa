package com.github.pierry.tdc;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean
public class UserRepository implements IUserRepository {

  @Override public String create() {
    return "Criado com sucesso";
  }

}




