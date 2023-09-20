package lockncharge;

public class User {
	
	private String firstName;
	private String lastName;
	
	public User(String fn, String ln)
	{
		firstName = fn;
		lastName = ln;
	}

	
	public User(String fullName)
	{
		formatName(fullName);
	}
	
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public void setFirstName(String fn) {firstName = fn;}
	public void setLastName(String ln) {lastName = ln;}
	
	private void formatName(String fullName)
	{
		String[] names = fullName.split(" ");
		
		if(names.length == 3)
		{
			firstName = names[0];
			lastName = names[2];
		}
		else
		{
			firstName = names[0];
			lastName = names[1];
		}
	}
	
	public String toString()
	{
		return firstName + " " + lastName;
	}

}
