package spittr.service;

import org.springframework.security.access.prepost.PreAuthorize;

import spittr.domain.Spittle;

public class ExpressionSecuredSpittleService implements SpittleService {

  @Override
  // 了解@PreFilter对传进来的进行过滤，表达式完成过滤操作
  @PreAuthorize("(hasRole('ROLE_SPITTER') and #spittle.text.length() le 140) or hasRole('ROLE_PREMIUM')")
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}
