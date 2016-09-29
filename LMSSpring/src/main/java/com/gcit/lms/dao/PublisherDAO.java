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

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;


public class PublisherDAO extends BaseDAO<Publisher> implements ResultSetExtractor<List<Publisher>> {
	private static final String SELECT_ALL = "select * from tbl_publisher";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_publisher where publisherName like ?";
	private static final String SELECT_ONE = "select * from tbl_publisher where publisherId = ?";
	private static final String SELECT_ID_BY_NAME = "select authorId from tbl_author where authorName = ?";
	private static final String INSERT = "insert into tbl_publisher(publisherName, publisherAddress,publisherPhone)values(?,?,?)";
	private static final String DELETE = "delete from tbl_publisher where publisherId = ?";
	private static final String UPDATE = "update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?";
	private static final String GET_COUNT = "select count(*) from tbl_publisher";
	private static final String SELECT_ALL_FROM_BOOK=
			"SELECT * FROM tbl_publisher p"
					+ "join tbl_book b on p.publisherId = b.pubId"
					+ "where b.bookId = ?";

	public void create(Publisher publ) throws SQLException {
		template.update(INSERT, new Object[]{publ.getPublisherName(), publ.getPublisherAddress(), publ.getPublisherPhone()});

	}

	public void update(Publisher publ) throws SQLException{
		template.update(UPDATE, new Object[]{publ.getPublisherName(), publ.getPublisherAddress(), publ.getPublisherPhone(), publ.getPublisherId()});

	}

	public void delete(Publisher publ) throws SQLException{
		template.update(DELETE, new Object[]{publ.getPublisherId()});

	}

	public Publisher read(int publisherId) throws SQLException {
		List<Publisher> publishers = template.query(SELECT_ONE, new Object[] {publisherId}, this);
		if(publishers!=null){
			return publishers.get(0);
		}
		return null;
	}

	public List<Publisher> readAllPublisher(int pageNo, int pageSize, String searchString) throws SQLException {
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

	public Integer getCount() throws SQLException{
		return template.queryForObject(GET_COUNT, new Object[]{}, Integer.class);
	}


	public List<Publisher> readAllPublishers() throws SQLException {
		Book myBo = new Book();
		return template.query(SELECT_ALL_FROM_BOOK, new Object[]{myBo.getBookId()}, this);
	}

	public List<Publisher> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Publisher> list = new ArrayList<Publisher>();
		while(rs.next()){
			Publisher pub = new Publisher();
			pub.setPublisherId(rs.getInt("publisherId"));
			pub.setPublisherName(rs.getString("publisherName"));
			pub.setPublisherAddress(rs.getString("publisherAddress"));
			pub.setPublisherPhone(rs.getString("publisherPhone"));

			list.add(pub);
		}
		return list;	
	}

}
