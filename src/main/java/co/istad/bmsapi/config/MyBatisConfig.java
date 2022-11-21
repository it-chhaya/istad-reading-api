package co.istad.bmsapi.config;

import javax.sql.DataSource;

import com.github.pagehelper.PageInterceptor;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan(value = "co.istad.bmsapi.data.repository")
@RequiredArgsConstructor
public class MyBatisConfig {
    
    private final DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }
    
}
