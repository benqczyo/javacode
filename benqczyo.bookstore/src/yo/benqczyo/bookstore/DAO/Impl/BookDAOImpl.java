package yo.benqczyo.bookstore.DAO.Impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import yo.benqczyo.bookstore.domain.Book;

/**
 * 书籍数据库操作DAO实际实现类
 * @author benqcz
 */
public class BookDAOImpl extends AbstractBookDAOImpl {

	private static final String FIND_ALL_BOOKS = "SELECT b.*, a.name AS author FROM book b, author a WHERE b.author = a.id";
	private static final String FIND_BOOK_BY_ID = "SELECT b.*, a.name AS author FROM book b, author a WHERE b.author = a.id AND b.id = ?";

	/**
	 * 根据数据库结果集每一条查询结果，创建Book对象并返回Book对象集合
	 * 根据Book对象类成员类型使用反射获得结果集对象对应类型的get方法，使用PropertyDescriptor创建Book对象
	 * @param rs 数据库结果集
	 * @return 包含结果集中的所有Book对象的Map
	 * @throws Exception
	 * @see yo.benqczyo.bookstore.domain.Book
	 * @see java.beans.PropertyDescriptor
	 */
	private Map<Integer, Book> parseResultSet(ResultSet rs) throws Exception {
		Map<Integer, Book> result = null;
		ResultSetMetaData rsm = null;
		while (rs.next()) {
			if (result == null) result = new HashMap<Integer, Book>();
			if (rsm == null) rsm = rs.getMetaData();
			Book book = new Book();
			for (int index = 1; index <= rsm.getColumnCount(); index++) {
				PropertyDescriptor pd = new PropertyDescriptor(rsm.getColumnName(index).toLowerCase(), book.getClass());
				Method writeMethod = pd.getWriteMethod();
				String type = pd.getPropertyType().getName().toLowerCase();
				type = type.substring(type.lastIndexOf('.') + 1);
				type = type.substring(0, 1).toUpperCase().concat(type.substring(1));
				Method readMethod = rs.getClass().getDeclaredMethod("get" + type, int.class);
				writeMethod.invoke(book, readMethod.invoke(rs, index));
			}
			result.put(book.getId(), book);
		}
		return result;
	}

	/**
	 * findAllBooks 方法具体实现
	 * @see yo.benqczyo.bookstore.DAO.Impl.AbstractBookDAOImpl#findAllBooks(Connection)
	 */
	@Override
	protected Map<Integer, Book> findAllBooks(Connection conn) throws Exception {
		Map<Integer, Book> result = null;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(FIND_ALL_BOOKS);
		result = parseResultSet(rs);
		return result;
	}

	/**
	 * findBookById 方法具体实现
	 * @see yo.benqczyo.bookstore.DAO.Impl.AbstractBookDAOImpl#findBookById(Connection, int)
	 */
	@Override
	protected Book findBookById(Connection conn, int id) throws Exception {
		Book result = null;
		PreparedStatement st = conn.prepareStatement(FIND_BOOK_BY_ID);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		Map<Integer, Book> parseResult = parseResultSet(rs);
		if (parseResult != null) result = parseResult.get(id);
		return result;
	}

	/*public static void main(String[] args) throws Exception {
		System.out.println(new BookDAOImpl().findAllBooks());
	}*/

}
