import java.util.*;

public class Database
{
	private List <Employee> employees;
	
	Database()
	{
		Employee Tomten = new Employee("Tomten", 2);
		Employee Glader = new Employee("Glader", Tomten, 3);
		Employee Butter = new Employee("Butter", Tomten, 4);
		Employee Tröger = new Employee("Tröger", Glader);
		Employee Trötter = new Employee("Trötter", Glader, 1);
		Employee Blyger = new Employee("Blyger", Glader);
		Employee Rådjuret = new Employee("Rådjuret", Butter);
		Employee Nyckelpigan = new Employee("Nyckelpigan", Butter);
		Employee Haren = new Employee("Haren", Butter);
		Employee Räven = new Employee("Räven", Butter, 2);
		Employee Skumtomten = new Employee("Skumtomten", Trötter, 1);
		Employee Gråsuggan = new Employee("Gråsuggan", Räven);
		Employee Myran = new Employee("Myran", Räven, 1);
		Employee Dammråttan = new Employee( "Dammråttan", Skumtomten);
		Employee Bladlusen = new Employee("Bladlusen", Myran);
		
		Tomten.addLowMap(Glader);
		Tomten.addLowMap(Butter);
		Glader.addLowMap(Tröger);
		Glader.addLowMap(Trötter);
		Glader.addLowMap(Blyger);
		Butter.addLowMap(Rådjuret);
		Butter.addLowMap(Nyckelpigan);
		Butter.addLowMap(Haren);
		Butter.addLowMap(Räven);
		Trötter.addLowMap(Skumtomten);
		Räven.addLowMap(Gråsuggan);
		Räven.addLowMap(Myran);
		Skumtomten.addLowMap(Dammråttan);
		Myran.addLowMap(Bladlusen);
		
		employees = Arrays.asList(Tomten, Glader, Butter, Tröger, Trötter, Blyger, Rådjuret, Nyckelpigan, Haren,
		Räven, Skumtomten, Gråsuggan, Myran, Dammråttan, Bladlusen);
		
	}
	
	public List getAllEmployees()
	{
		return employees;
	}
	
}