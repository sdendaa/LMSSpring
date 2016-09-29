/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibBranch;

public class BorrowerDAO extends BaseDAO<Borrower> implements ResultSetExtractor<List<Borrower>>{
	private static final String SELECT_ALL = "select * from tbl_borrower";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_borrower where name like ?";
	private static final String SELECT_ONE = "select * from tbl_borrower where cardNo = ?";
	private static final String SELECT_ID_BY_NAME = "select authorId from tbl_author where authorName = ?";
	private static final String INSERT = "insert into tbl_borrower(name, address, phone)values(?,?,?)";
	private static final String DELETE = "delete from tbl_borrower where cardNo = ?";
	private static final String UPDATE = "update tbl_borrower set name = ?,address = ?, phone = ? where cardNo = ?";
	private static final String GET_COUNT = "select count(*) from tbl_borrower";

	public void create(Borrower bro) throws SQLException {
		template.update(INSERT, new Object[]{ bro.getName(), bro.getAddress(), bro.getPhone()});

	}

	public void update(Borrower bro) throws SQLException{
		template.update(UPDATE, new Object[]{bro.getName(),bro.getAddress(), bro.getPhone(), bro.getCardNo()});

	}

	public void delete(Borrower bro)throws SQLException {
		template.update(DELETE, new Object[]{bro.getCardNo()});

	}

	public Borrower read(Borrower bor) throws SQLException {
		return (Borrower) template.query(SELECT_ONE, new Object[]{bor.getCardNo()}, this);
	}
	public Borrower getBorrowerById(int cardNo) throws SQLException {
		List<Borrower> borrowers = template.query(SELECT_ONE, new Object[]{cardNo}, this);
		if(borrowers != null){
			return borrowers.get(0);
		}
		return null;
	}

	public List<Borrower> readAllBorrowerDAO(int pageNo, int pageSize, String searchString) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);

		if(searchString != null && !"".equals(searchString)){	
			searchString = "%"+searchString+"%";
			return template.query(SELECT_ALL_SEARCH, new Object[]{searchString}, this);
		}else{
			return template.query(SELECT_ALL, new Object[]{}, this);
		}
	}

	public Integer getCount() throws SQLException{
		return template.queryForObject(GET_COUNT, new Object[]{}, Integer.class);
	}

	public List<Borrower> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Borrower> list = new ArrayList<Borrower>();
		while(rs.next()){
			Borrower bo = new Borrower();
			bo.setCardNo(Integer.parseInt(rs.getString("cardNo")));
			bo.setName(rs.getString("name"));
			bo.setAddress(rs.getString("address"));
			bo.setPhone(rs.getString("phone"));

			list.add(bo);
		}

		return list;
	}
}
