package dao;

import java.util.List;

import domain.MenuBean;
import domain.RoleBean;

public interface MenuDao {
	/**
	 * ���ز˵���¼��
	 * @return �˵���¼��
	 */
	int getNumberOfMenus();
	/**
	 * ��Ӳ˵�
	 * @param menu �˵�����
	 * @return ��ӳɹ�trueʧ��false
	 */
	boolean addMenu(MenuBean menu);
	/**
	 * ���ݲ˵�idɾ������
	 * @param id �˵�id
	 * @return ɾ���ɹ�trueʧ��false
	 */
	boolean delMenuById(String id);
	/**
	 * һ��ɾ������˵���
	 * @param ids �˵���id����
	 * @return ɾ���ɹ�trueʧ���׳��쳣
	 */
	boolean delMenusByIds(String[] ids);
	/**
	 * ���²˵�
	 * @param menu �˵�����
	 * @return ���³ɹ�trueʧ��false
	 */
	boolean updateMenu(MenuBean menu);
	/**
	 * �������в˵�
	 * @return �˵�����List
	 */
	List<MenuBean> findAllMenus();
	/**
	 * ����id���Ҳ˵�
	 * @param id �˵�id
	 * @return ���ҳɹ����ز˵�����ʧ�ܷ���null
	 */
	MenuBean findMenuById(String id);
	/**
	 * ���ݼ�¼��ŷ�Χ���Ҽ�¼
	 * @param startRowId ��ʼ���к�
	 * @param endRowId �����к�
	 * @return �ɹ����ؼ�¼list����ʧ�ܷ���null,�����׳��쳣
	 */
	List<MenuBean> findMenusByRange(int startRowId, int endRowId);
	
}
