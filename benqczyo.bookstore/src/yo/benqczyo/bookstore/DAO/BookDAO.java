package yo.benqczyo.bookstore.DAO;

import java.util.Map;

import yo.benqczyo.bookstore.domain.Book;

/**
 * �鼮���ݿ����DAO�ӿ�
 * findAllBooks ��ѯ�����鼮
 * findBookById �����鼮ID��ѯָ���鼮
 */
public interface BookDAO {
	/**
	 * findAllBooks ��ѯ�����鼮
	 * @return ����Map<Integer, Book>����,��Ϊ�鼮id,ֵΪ�鼮������
	 * @throws Exception
	 * @see java.util.Map
	 * @see yo.benqczyo.bookstore.domain.Book
	 */
	public abstract Map<Integer, Book> findAllBooks() throws Exception;
	/**
	 * findBookById �����鼮ID��ѯָ���鼮
	 * @param id �鼮ID
	 * @return ָ���鼮��Book����
	 * @throws Exception
	 * @see yo.benqczyo.bookstore.domain.Book
	 */
	public abstract Book findBookById(int id) throws Exception;
}
