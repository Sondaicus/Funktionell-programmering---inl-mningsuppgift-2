public class Employee
{
	private String name;
	private Employee topMap;
	private Employee[] lowMap;
	private int lowMapsSlots;
	private int usedLowerMapSlots;
	private int freeMapSlots;
	
	
	Employee()
	{
		usedLowerMapSlots = 0;
	}
	
	Employee(String name)
	{
		usedLowerMapSlots = 0;
		setName(name);
	}
	
	Employee(String name, Employee topMap)
	{
		usedLowerMapSlots = 0;
		setName(name);
		setTopMap(topMap);
	}
	
	Employee(String name, Employee topMap, int lowerMaps)
	{
		usedLowerMapSlots = 0;
		setName(name);
		setTopMap(topMap);
		setTotalLowerMaps(lowerMaps);
	}
	
	Employee(String name, int lowerMaps)
	{
		usedLowerMapSlots = 0;
		setName(name);
		setTotalLowerMaps(lowerMaps);
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public Employee getTopMap()
	{
		return topMap;
	}
	
	public Employee[] getAllLowMap()
	{
		return lowMap;
	}
	
	public int getLowMapsSlots()
	{
		return lowMapsSlots;
	}
	
	public int getUsedLowerMapSlots()
	{
		return usedLowerMapSlots;
	}
	
	public int getFreeMapSlots()
	{
		setFreeMapSlots();
		return freeMapSlots;
	}
	
	public Employee getSpecificLowMap(int slot)
	{
		return lowMap[slot];
	}
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setTotalLowerMaps(int lowerMaps)
	{
		this.lowMapsSlots = lowerMaps;
		lowMap = new Employee[lowerMaps];
	}
	
	public void addLowMap(Employee lowMap)
	{
		setLowMap(lowMap, usedLowerMapSlots);
		++usedLowerMapSlots;
	}
	
	public void addSpecificLowMap(Employee lowMap, int slot)
	{
		setLowMap(lowMap, slot);
		++usedLowerMapSlots;
	}
	
	public void setTopMap(Employee topMap)
	{
		this.topMap = topMap;
	}
	
	private void setFreeMapSlots()
	{
		this.freeMapSlots = lowMapsSlots - usedLowerMapSlots;
	}
	
	private void setLowMap(Employee lowMap, int slot)
	{
		this.lowMap[slot] = lowMap;
	}

	
}