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

import com.gcit.lms.entity.LibBranch;


public class BranchDAO extends BaseDAO<LibBranch> implements ResultSetExtractor<List<LibBranch>>{
	private static final String SELECT_ALL = "select * from tbl_library_branch";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_library_branch where branchName like ?";
	private static final String SELECT_ONE = "select * from tbl_library_branch where branchId = ?";
	private static final String SELECT_ID_BY_NAME = "select authorId from tbl_author where authorName = ?";
	private static final String INSERT = "insert into tbl_library_branch(branchName, branchAddress)values(?,?)";
	private static final String DELETE = "delete from tbl_library_branch where branchId = ?";
	private static final String UPDATE = "update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?";
	private static final String GET_COUNT = "select count(*) from tbl_library_branch";
	private static final String SELECT_ALL_RETURN = "SELECT * FROM tbl_library_branch lb"
			+ " join tbl_book_loans bl on bl.branchId = lb.branchId"
			+ " join tbl_borrower b on b.cardNo = bl.cardNo"
			+ " where b.cardNo = ? and bl.dateIn = null";

	public void create(LibBranch branch) throws SQLException {
		template.update(INSERT, new Object[]{branch.getBranchName(), branch.getBranchAddress()});

	}

	public void update(LibBranch branch)throws SQLException  {
		template.update(UPDATE, new Object[]{branch.getBranchName(),branch.getBranchAddress(), branch.getBranchId()});

	}

	public void delete(LibBranch branch) throws SQLException {
		template.update(DELETE, new Object[]{branch.getBranchId()});

	}

	public LibBranch readByID(int branchId) throws SQLException {
		List<LibBranch> branchs = template.query(SELECT_ONE, new Object[]{branchId}, this);
		if(branchs != null){
			return branchs.get(0);
		}
		return null;
	}


	public List<LibBranch> readAllBranchDAO(Integer pageNo, Integer pageSize, String searchString) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);

		if(searchString !=null && !"".equals(searchString)){
			List<Object> valueList = new ArrayList<Object>();
			searchString = "%"+searchString+"%";
			valueList.add(searchString);
			return template.query(SELECT_ALL_SEARCH, new Object[]{searchString}, this);
		}else{
			return template.query(SELECT_ALL, this);
		}
	}
	public List<LibBranch> readAllBranchToReturnDAO(int cardNo) throws SQLException {
		return template.query(SELECT_ALL_RETURN, new Object[]{cardNo}, this);

	}

	public Integer getCount() throws SQLException{
		return template.queryForObject(GET_COUNT, new Object[]{}, Integer.class);
	}

	public List<LibBranch> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<LibBranch> list = new ArrayList<LibBranch>();
		while(rs.next()){
			LibBranch br = new LibBranch();
			br.setBranchId(rs.getInt("branchId"));
			br.setBranchName(rs.getString("branchName"));
			br.setBranchAddress(rs.getString("branchAddress"));

			list.add(br);
		}

		return list;
	}
}
