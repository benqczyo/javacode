package yo.benqczyo.bookstore.DAO.Impl;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import yo.benqczyo.bookstore.domain.Book;

public class DAOImpl extends AbstractDAOImpl {

	private static final String FIND_ALL_BOOKS = "SELECT b.*, a.name AS author FROM book b, author a WHERE b.author = a.id";
	private static final String FIND_BOOK_BY_ID = "SELECT b.*, a.name AS author FROM book b, author a WHERE b.author = a.id AND b.id = ?";

	private Map<Integer, Book> parseResultSet(ResultSet rs) throws Exception {
		Map<Integer, Book> result = null;
		if (rs != null) {
			ResultSetMetaData rsm = rs.getMetaData();
			while (rs.next()) {
				Book book = new Book();
				for (int index = 1; index <= rsm.getColumnCount(); index++) {
					PropertyDescriptor pd1 = new PropertyDescriptor(rsm.getColumnName(index).toLowerCase(), book.getClass());
					String type = pd1.getPropertyType().getName().toLowerCase();
					if (type.equalsIgnoreCase(Integer.class.getName())) type = type.substring(0, 3);
					System.out.println(type);
					/*PropertyDescriptor pd2 = new PropertyDescriptor(type.substring(type.lastIndexOf(".") + 1), rs.getClass());
					pd1.getWriteMethod().invoke(book, pd2.getReadMethod().invoke(rs, null));*/
				}
				result.put(book.getId(), book);
			}
		}

		return result;
	}

	@Override
	protected Map<Integer, Book> findAllBooks(Connection conn) throws Exception {
		Map<Integer, Book> result = null;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(FIND_ALL_BOOKS);
		result = parseResultSet(rs);
		return result;
	}

	@Override
	protected Book findBookById(Connection conn, int id) throws Exception {
		Book result = null;
		PreparedStatement st = conn.prepareStatement(FIND_BOOK_BY_ID);
		st.setInt(0, id);
		ResultSet rs = st.executeQuery();
		Map<Integer, Book> parseResult = parseResultSet(rs);
		if (parseResult != null) result = parseResult.get(id);
		return result;
	}

	public static void main(String[] args) throws Exception {
		new DAOImpl().findAllBooks();
	}
	
}
