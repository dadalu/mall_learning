//package com.wxc.mall_learning.config;
//
///**
// * configure(HttpSecurity httpSecurity)：用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器；
// * configure(AuthenticationManagerBuilder auth)：用于配置UserDetailsService及PasswordEncoder；
// * RestfulAccessDeniedHandler：当用户没有访问权限时的处理器，用于返回JSON格式的处理结果；
// * RestAuthenticationEntryPoint：当未登录或token失效时，返回JSON格式的结果；
// * UserDetailsService:SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现；
// * UserDetails：SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限），需要自行实现；
// * PasswordEncoder：SpringSecurity定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder；
// * JwtAuthenticationTokenFilter：在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。
// * */
//import com.wxc.mall_learning.component.RestAuthenticationEntryPoint;
//import com.wxc.mall_learning.component.RestfulAccessDeniedHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UmsAdminService adminService;
//    @Autowired
//    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
//    @Autowired
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
//                .disable()
//                .sessionManagement()// 基于token，所以不需要session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,// 允许对于网站静态资源的无授权访问
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-resources/**",
//                        "/v2/api-docs/**"
//                )
//                .permitAll()
//                .antMatchers("/admin/login","/admin/register")// 对登录注册要允许匿名访问
//                .permitAll()
//                .antMatchers(HttpMethod.OPTIONS)
//                .permitAll()
//                .antMatchers("/**")//测试时全部运行访问
//                .permitAll()
//                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
//                .authenticated();
//        //禁用缓存
//        httpSecurity.headers().cacheControl();
//        //添加JWT filter
//        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        //添加自定义未授权和未登录的结果返回
//        httpSecurity.exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)
//                .authenticationEntryPoint(restAuthenticationEntryPoint);
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        //获取登录用户信息
//        return  username->{
//            UmsAdmin admin = adminService.getAdminByUsername(usename);
//            if(admin!=null){
//                List<Umspermission> umspermissionList = adminService.getPermissionList(admin.getId());
//                return new AdminUserDetails(admin,umspermissionList);
//            }
//            throw new UsernameNotFoundException("用户名密码错误")；
//        };
//    }
//
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
