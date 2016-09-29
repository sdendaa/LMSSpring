package com.gcit.lms;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.LibrarianService;
import com.gcit.lms.service.BorrowerService;

@Configuration
public class LMSConfig {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/librarynew";
	private static String user = "root";
	private static String pass = "Odaa@430";
	
	@Bean
	public BasicDataSource dataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);
		
		return ds;
	}
	
	@Bean
	public JdbcTemplate template(){
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource());
		
		return template;
	}
	
	@Bean
	public AuthorDAO aDAO(){
		return new AuthorDAO();
	}
	@Bean
	public BookDAO bDAO(){
		return new BookDAO();
	}
	@Bean
	public BranchDAO brDAO(){
		return new BranchDAO();
	}
	@Bean
	public BorrowerDAO boDAO(){
		return new BorrowerDAO();
	}
	@Bean
	public BookLoanDAO blDAO(){
		return new BookLoanDAO();
	}
	@Bean
	public BookCopiesDAO bcDAO(){
		return new BookCopiesDAO();
	}
	@Bean
	public PublisherDAO pDAO(){
		return new PublisherDAO();
	}
	@Bean(name="AdminService")
	public AdminService AdminService(){
		return new AdminService();
	}
	@Bean(name="LibrarianService")
	public LibrarianService LibrService(){
		return new LibrarianService();
	}
	@Bean(name="BorrowerService")
	public BorrowerService BorrowerService(){
		return new BorrowerService();
	}
	@Bean
	public PlatformTransactionManager txManager(){
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(dataSource());
		
		return tx;
	}
	
}
