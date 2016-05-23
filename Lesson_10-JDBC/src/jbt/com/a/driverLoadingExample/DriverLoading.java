package jbt.com.a.driverLoadingExample;

public class DriverLoading {

	public static void main(String[] args) {
		//A vendor supplied driver class name. It is not portable. So when loading
		//the class dynamically (using Class.forName()), we only need to set the name of
		//the class (a String). It is also possible to set the driver name using the args[]
		//in the command line when running the program.
		String driverName = "org.apache.derby.jdbc.ClientDriver";

		//Examples for loading the driver without Class.forName():
		//1.Create an instance of the driver class. Less portable - NOT using String.
//		org.apache.derby.jdbc.ClientDriver drvr = new org.apache.derby.jdbc.ClientDriver();

		//2.Configuring the system properties. More portable - using String.
//		System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");

		try {
			//The static method 'Class.forName()' receives a class name and attempts to create
			//a reflection of that class. It loads an instance of the requested class, in this
			//case the 'org.apache.derby.jdbc.ClientDriver' class. Once loaded, all the static 
			//blocks of the class are performed. If the loaded class is unavailable in the given 
			//class path a 'ClassNotFoundException' will be thrown. Later, it will be needed to 
			//be used by the DriverManager class. The DriverManager class holds a list of all 
			//drivers that were located.
			Class.forName(driverName);

			System.out.println("driver loaded");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
