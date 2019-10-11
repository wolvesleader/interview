package com.quincy.java.base.clone;

public class MyClone {
	public static void main(String[] args) {
		User user = new User();
		user.setName("quincy");
		user.setAge(25);
		
		User cloneUser= null;
		try {
			cloneUser = (User)user.clone();
			
			System.out.println(cloneUser.getName());
			System.out.println(cloneUser.getAge());
			System.out.println("================");
			cloneUser.setName("miao");
			cloneUser.setAge(24);
			
			System.out.println(cloneUser.getName());
			System.out.println(cloneUser.getAge());
			System.out.println(cloneUser == user);
			

			System.out.println(cloneUser.equals(user));
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}

class User implements Cloneable{
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override

	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}
}