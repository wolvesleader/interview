package com.quincy.java.base.clone;
/**
 *
 * @author Quincy
 *
 */
public class DepClone {
	
    public static void main(String[] args) {
	     	People p = new People();
	     	p.setAge(30);
	     	p.setName("people");
	     	
	     	User2 user2 = new User2();
	     	user2.setAge(12);
	     	user2.setName("user2");
	     	user2.setPeople(p);
	     	
	     	
	     	try {
				User2 u2 = (User2)user2.clone();
				System.out.println(u2.getAge());
				System.out.println(u2.getName());
				
				p.setName("clone ");
				System.out.println(u2.getPeople() == p);
				System.out.println(u2.getPeople().getName());
				System.out.println(u2.getPeople().getAge());
			} catch (CloneNotSupportedException e) {
				
				e.printStackTrace();
			}
	}
}

class User2 implements Cloneable{
	private int age;
	private String name;
	private People people;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		User2 user2 = (User2)super.clone();
		user2.setPeople((People)user2.getPeople().clone());
		return user2;
	}
}
class People implements Cloneable{
	private int age ;
	private String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
