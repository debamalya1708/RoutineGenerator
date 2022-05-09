package com.debamalya.routine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

// TODO: Auto-generated Javadoc

/**
 * The Class DataSourceConfiguration.
 */
@Configuration
public class DataSourceConfiguration {

    /** The data source. */
//	@Autowired
//    private DataSource dataSource;

    /**
     * Primary jdbc template.
     *
     * @return the named parameter jdbc template
     */
//   @Bean
//    public NamedParameterJdbcTemplate primaryJdbcTemplate() {
//	   System.out.println("named paramter");
//        return new NamedParameterJdbcTemplate(dataSource);
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/home/newRoutine").allowedOrigins("https://routinev1.herokuapp.com");
            }
        };
    }

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
