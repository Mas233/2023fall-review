package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .formLogin()
        .loginPage("/login") // 如果登录则跳转登录
      .and()
        .logout()
          .logoutSuccessUrl("/") // 如果登出则跳出
      .and()
      .rememberMe()
        .tokenRepository(new InMemoryTokenRepositoryImpl())
        .tokenValiditySeconds(2419200)
        .key("spittrKey")
      .and()
       .httpBasic()
         .realmName("Spittr")
      .and()
      .authorizeRequests()
        .antMatchers("/").hasAnyRole("USER") // 如果访问根url必须有USER这个角色
        .antMatchers("/spitter/me").authenticated() // 访问这个url则必须要完成认证
        .antMatchers(HttpMethod.POST, "/spittles").authenticated()
        .anyRequest().permitAll()
//    .and()
//            .requiresChannel()
//            .antMatchers("/spitter/register").requiresSecure()
//    .and().csrf().disable()
    ;
    // JAAS java本身的认证授权模块 将认证和其他部分进行了隔离
    // requireSecure(必须借助于安全通道才可以传输)，可以自行配置
    // 增加了Authorization的headers，是对用户名和密码的加密信息

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 对用户进行USER角色授权，用户名和密码存储在内存中 sessionId存储了相关的信息
    auth
      .inMemoryAuthentication()
        .withUser("user").password("password").roles("USER");
  }


}
