// this program is using serialization to create a menu driven program that takes entries for the object person

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Person implements Serializable{
	
	String name; 
	String phoneNum;
	String dob;
	String email;
	
	public Person() {
		this.name = "";
		this.phoneNum = "";
		this.dob = "";
		this.email = "";
	}

	public Person(String name, String phoneNum, String dob, String email) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
		this.dob = dob;
		this.email = email;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return String.format("Personal Info for %s\n Phone: %s\n Birthday: %s\n Email: %s", name,phoneNum,dob,email);
	}
}

public class Serialization {
	
	public static void writeToFile(ArrayList<Person> ps) throws IOException{ 
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/Person.bin"));
		objectOutputStream.writeObject(ps);
	}				
	public static ArrayList<Person> readFile() throws IOException, ClassNotFoundException{
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/Person.bin"));
			
			ArrayList<Person> ps = new ArrayList<Person>();
			
			ps = (ArrayList) objectInputStream.readObject(); 
			return ps;
}	
	public static void printMenu( ) {
		System.out.printf("0) Add a person. \r\n 1) Add information into a file.\r\n"
				+ " 2) Retrieve information from a file and display them.\r\n"
				+ "3) Delete information.\r\n"
				+ "4) Update information.\r\n"
				+ " 5) Exit.\r\n");
		System.out.println("Enter your choice:");
	}
	
	
	public static ArrayList<Person> updateInfo(int person, Scanner scan, ArrayList<Person> ps) {
		String name, phoneNum, dob, email = "";
		System.out.println("Would you like to update the name: y/n");
		String choice = scan.next().toLowerCase();
		if(choice.equals("y")) {
			System.out.println("Enter the new name: ");
			ps.get(person).name = scan.next().trim();		
		} 
			
	
		System.out.println("Would you like to update the Phone Number: y/n");
		choice = scan.next().toLowerCase();
		if(choice.equals("y")) {
			System.out.println("Enter the new phone number: ");
			ps.get(person).phoneNum = scan.next().trim();		
		} 
		
		System.out.println("Would you like to update the Date of Birth : y/n");
		choice = scan.next().toLowerCase();
		if(choice.equals("y")) {
			System.out.println("Enter the new Date of Birth: ");
			ps.get(person).dob = scan.next().trim();		

		}
		
		System.out.println("Would you like to update the email: y/n");
		choice = scan.next().toLowerCase();
		
		if(choice.equals("y")) {
			System.out.println("Enter the new email: ");
			ps.get(person).email = scan.next().trim();
		}
		
		return ps;
	}
	
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Person> ps = new ArrayList<Person>(); 
		
		int choice = 0;
		
		while(choice != 5) {
			
			printMenu();
			
			choice = scan.nextInt();
			
			if (choice == 1) {
				
				System.out.println("Enter info associated to Person");
				System.out.println("Enter the Name of the person: "); 
				String name = scan.next();
				System.out.println("Enter the Phone Number of the person: "); 
				String phoneNum = scan.next();
				System.out.println("Enter the Date of Birth of the person: ");
				String dob = scan.next();
				System.out.println("Enter the Email of the person: ");
				String email = scan.next();
				Person p = new Person(name,phoneNum,dob,email);
				ps.add(p);
				try {
					
					writeToFile(ps);
					System.out.println("The file is saved");
					
				} catch (Exception ex) {
					System.out.println("error saving");
					ex.printStackTrace();
				}
			} else if (choice == 2) {
				try {
					ps = readFile();
					for (Person p : ps) {
						System.out.println(p.toString());
						
					}
					
				} catch (Exception ex) {
					System.out.println("File could not be read");
					ex.printStackTrace();
				}
				
			} else if (choice == 3) { 
				try {
					ps = readFile();
					int counter = 0;
					for (Person p : ps) {
						System.out.printf("%d. %s\n", counter,p.name);
						counter ++;
					}
					System.out.println("Enter the number of the person you would like to delete: ");
					int person = scan.nextInt();
					ps.remove(person);
					writeToFile(ps);
				} catch (Exception ex) {
					System.out.println("File could not be read");
					ex.printStackTrace();
					}
			} else if (choice == 4) {
				try {
					ps = readFile();
					int counter = 0;
					for (Person p : ps) {
						System.out.printf("%d. %s\n", counter,p.name);
						counter ++;
					}
					System.out.println("Enter the number of the person you would like to update: ");
					int person = scan.nextInt();
					ps = updateInfo(person,scan,ps);
					writeToFile(ps);
					System.out.println("Information Updated");
					
				} catch (Exception ex) {
					System.out.println("File could not be read/inforamtion could not update");
					ex.printStackTrace();
				}
			} else if(choice == 0) { 
					System.out.println("Enter info associated to Person");
					System.out.println("Enter the Name of the person: "); 
					String name = scan.next();
					System.out.println("Enter the Phone Number of the person: "); 
					String phoneNum = scan.next();
					System.out.println("Enter the Date of Birth of the person: ");
					String dob = scan.next();
					System.out.println("Enter the Email of the person: ");
					String email = scan.next();
					Person p = new Person(name,phoneNum,dob,email);
					ps.add(p);
					
			} else if (choice ==5 ) {
				System.out.println("Complete"); 
				
			} else {
				System.out.println("please enter valid number");
			}
		}	
	}
}
	