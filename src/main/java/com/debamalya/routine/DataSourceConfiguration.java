package com.debamalya.routine;

import javax.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

// TODO: Auto-generated Javadoc

/**
 * The Class DataSourceConfiguration.
 */
@Configuration
@EnableWebSecurity(debug = true)
public class DataSourceConfiguration extends WebSecurityConfigurerAdapter {

    /** The data source. */
//	@Autowired
//    private DataSource dataSource;

    /**
     * Primary jdbc template.
     *
     * @return the named parameter jdbc template
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().httpBasic();
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
//   @Bean
//    public NamedParameterJdbcTemplate primaryJdbcTemplate() {
//	   System.out.println("named paramter");
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*")
//                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
//            }
//        };
//    }

}































//package com.javasampleapproach.restdata;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class DataSourceConfiguration.
// */
////@Configuration
//public class DataSourceConfiguration {
//
//    /** The data source. */
//    //@Autowired
//    //private DataSource dataSource;
//
//    /**
//     * Primary jdbc template.
//     *
//     * @return the named parameter jdbc template
//     */
//   /* @Bean
//    public NamedParameterJdbcTemplate primaryJdbcTemplate() {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//*/
//}
