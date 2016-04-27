package yo.benqczyo.bookstore.DAO;

import java.util.Map;

import yo.benqczyo.bookstore.domain.Book;

/**
 * 书籍数据库操作DAO接口
 * findAllBooks 查询所有书籍
 * findBookById 根据书籍ID查询指定书籍
 */
public interface BookDAO {
	/**
	 * findAllBooks 查询所有书籍
	 * @return 返回Map<Integer, Book>对象,键为书籍id,值为书籍对象本身
	 * @throws Exception
	 * @see java.util.Map
	 * @see yo.benqczyo.bookstore.domain.Book
	 */
	public abstract Map<Integer, Book> findAllBooks() throws Exception;
	/**
	 * findBookById 根据书籍ID查询指定书籍
	 * @param id 书籍ID
	 * @return 指定书籍的Book对象
	 * @throws Exception
	 * @see yo.benqczyo.bookstore.domain.Book
	 */
	public abstract Book findBookById(int id) throws Exception;
}
