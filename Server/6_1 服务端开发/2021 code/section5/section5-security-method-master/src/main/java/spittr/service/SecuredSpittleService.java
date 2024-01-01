package spittr.service;

import org.springframework.security.access.annotation.Secured;

import spittr.domain.Spittle;

public class SecuredSpittleService implements SpittleService {

  /**
   * 对方法进行保护，则需要检查当前用户身份是否有权
   *
   * @param spittle
   */
  @Override
  @Secured({"ROLE_SPITTER", "ROLE_ADMIN"}) // spring提供的
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}
