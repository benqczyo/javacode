package service;

import java.util.List;

import domain.MenuBean;

public interface BussinessService {
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
}
