package service;

import java.util.List;

import domain.AccountBean;
import domain.MenuBean;
import domain.Page;
import domain.RoleBean;
import formbean.impl.UpdateRoleFormBean;

public interface BussinessService {
	/**
	 * ���ز˵���¼��
	 * @return �˵���¼��
	 */
	int getNumberOfMenus();
	int getNumberOfRoles();
	int getNumberOfAccounts();
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
	 * ����ҳid�õ���ҳ����
	 * @param pageId ҳid
	 * @return ��ҳ����
	 */
	Page getPage(Object target, int pageRange, int pageRecords, String currentPageId);
	
	boolean addRole(RoleBean role);
	boolean delRoleById(String id);
	RoleBean findRoleById(String id);
	boolean updateRole(RoleBean role);
	boolean delRolesByIds(String[] ids);
	boolean assignMenu(String roleId, String[] menuIds);
	boolean delAssignedMenus(String roleId);
	List<AccountBean> findAllAccount();
}
