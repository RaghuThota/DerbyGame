package com.project.derby.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.project.derby.bean.Player;

public class PlayerDao {

	public void insertPlayer(Player player) {
		// inserts user
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//session.save(entries);
		session.save(player);
		
		session.getTransaction().commit();
		getPlayer();
	}

	public void getPlayer() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Player");
		List<Player> list = query.list();
		for (Player user1 : list) {
			System.out.println("****************************");
			System.out.println(user1.getPlayerId() + "  " + user1.getUname() + "  " + user1.getGender() + ""
					+ user1.getContact() + " " + user1.getEmailId());

		}

		session.getTransaction().commit();
	}
}
