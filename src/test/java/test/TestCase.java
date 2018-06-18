package test;

import org.junit.Test;

import dao.UserDAO;
import entity.User;

public class TestCase {
	@Test
	public void test1() {
		UserDAO dao=new UserDAO();
		User user=dao.findByUsername("DMETAL");
		System.out.println(user);
	}
}
