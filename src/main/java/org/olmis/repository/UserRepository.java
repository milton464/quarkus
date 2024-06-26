//package org.olmis.repository;
//
//import org.olmis.model.User;
//
//import io.quarkus.hibernate.orm.panache.PanacheRepository;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.transaction.Transactional;
//
//@Transactional
//@ApplicationScoped
//public class UserRepository implements PanacheRepository<User>{
//
//	public User findByEmail(String email) {
//		return find("email", email).firstResult();
//	}
//
//}
