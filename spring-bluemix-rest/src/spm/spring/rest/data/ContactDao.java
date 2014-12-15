package spm.spring.rest.data;

import java.util.List;

import spm.spring.rest.bean.ContactBean;

public interface ContactDao {
	public ContactBean get(int id);
	public List<ContactBean> getAll();
	public ContactBean create(ContactBean contact);
	public int delete(int id);
	public int update(ContactBean contact);
}
