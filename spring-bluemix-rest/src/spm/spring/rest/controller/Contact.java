package spm.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spm.spring.rest.bean.ContactBean;
import spm.spring.rest.data.ContactDao;

@Controller
@RequestMapping("/contact")
public class Contact {
	@Autowired
	private ContactDao contactDao;
	public ContactDao getContactDao() {
		return contactDao;
	}
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public @ResponseBody List<ContactBean> get(){
		return contactDao.getAll();
	}
	@RequestMapping(method=RequestMethod.GET, value="/{id}",produces="application/json")
	public @ResponseBody ContactBean get(@PathVariable int id){
		return contactDao.get(id);
	}
}
