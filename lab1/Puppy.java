
public class Puppy{
	private int age;
	public String name;

	public Puppy(){
		this.age = 0;
		this.name = "Fido";
	}





	public Puppy(int a, String n)
	{
		this.age = a;
		this.name = n;
	}



	public void setName(String n)
	{  this.name = n; }

	public void setAge(int age)
	{ this.age = age; }

	public String getName()
	{ return this.name; } // return name;

	public int getAge()
	{ return this.age;}   // return age;

} // end of class


