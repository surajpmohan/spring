package spm.spring.rest.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spm.spring.rest.bean.ContactBean;

@Controller
@RequestMapping("/contact")
public class Contact {
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ContactBean get(){
		ContactBean bean = new ContactBean();
		bean.setDob(new Date());
		bean.setEmail("s@abc.com");
		bean.setFirstName("Andand");
		//bean.setId(id);
		bean.setLastName("adnanan");
		bean.setPhone("123456789");
		return bean;
	}
	@RequestMapping(method=RequestMethod.GET, value="/{id}",produces="application/json")
	public @ResponseBody ContactBean get(@PathVariable int id){
		ContactBean bean = new ContactBean();
		bean.setDob(new Date());
		bean.setEmail("s@abc.com");
		bean.setFirstName("Andand");
		bean.setId(id);
		bean.setLastName("adnanan");
		bean.setPhone("123456789");
		return bean;
	}
}
