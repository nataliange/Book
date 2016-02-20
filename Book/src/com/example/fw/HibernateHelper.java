package com.example.fw;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.tests.GroupData;

public class HibernateHelper extends HelperBase {

	public HibernateHelper(ApplicationManager manager) {
	  super(manager);
	}

	public List<GroupData> listGroups() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return (List<GroupData>) session.createQuery("from GroupData").list();
		} finally {
          trans.commit();
		}
	}
	
}
