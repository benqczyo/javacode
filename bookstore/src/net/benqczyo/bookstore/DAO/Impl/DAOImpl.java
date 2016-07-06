package net.benqczyo.bookstore.DAO.Impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import net.benqczyo.bookstore.domain.Book;

public class DAOImpl extends AbstractDAO {
	
	private static final String FIND_ALL_BOOKS = "select a.*, b.name as author from book a, author b where a.author = b.id";
	private static final String FIND_BOOK_BY_ID = "select a.*, b.name as author from book a, author b where a.author = b.id and a.id = ?";

	private Map<String, Book> makeBook(ResultSet rs) throws Exception {
		
		Map<String, Book> result = null;
		ResultSetMetaData rsm = null;
		
		while (rs.next()) {
			if (result == null) result = new HashMap<String, Book>();
			if (rsm == null) rsm = rs.getMetaData();
			Book book = new Book();
			for (int i = 1; i <= rsm.getColumnCount(); i++) {
				String name = rsm.getColumnName(i).toLowerCase();
				PropertyDescriptor dp = new PropertyDescriptor(name, Book.class);
				Method writeMethod = dp.getWriteMethod();
				String fieldTypeName = Book.class.getDeclaredField(name).getType().getName();
				if (fieldTypeName.equalsIgnoreCase("int")) {
					writeMethod.invoke(book, rs.getInt(i));
				} else if (fieldTypeName.equalsIgnoreCase("java.lang.String")) {
					writeMethod.invoke(book, rs.getString(i));
				} else if (fieldTypeName.equalsIgnoreCase("java.util.Date")) {
					writeMethod.invoke(book, rs.getDate(i));
				}
			}
			result.put(String.valueOf(book.getId()), book);
		}
		
		return result;
	
	}
	
	@Override
	protected Map<String, Book> findAllBooks(Connection conn)
			throws Exception {
		
		Map<String, Book> result = null;
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(FIND_ALL_BOOKS);
		result = makeBook(rs);
		
		return result;
	}

	@Override
	protected Book findBookById(Connection conn, int id) throws Exception {
		
		Book result = null;

		PreparedStatement st = conn.prepareStatement(FIND_BOOK_BY_ID);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		Map<String, Book> temp = makeBook(rs);
		if (temp != null) result = temp.get(String.valueOf(id));
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new DAOImpl().findBookById(1003));
	}
}
