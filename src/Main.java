import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class Main implements ActionListener
{
    private static List <Employee> allEmployees;
    private static Database db;
    private static int listSize;
    private static String[] allEmployeeNames;
    private static JRadioButton[] allEmployeeButtons;
    private static JRadioButton superOrdinateButton;
    private static JRadioButton subOrdinateButton;
    private static JRadioButton choseAllButton;
    private static JRadioButton choseOneButton;
    private static JButton execute;
    private static JFrame windowMain;
    private static JPanel windowForEmployeeButtons;
    private static JPanel windowForTextToUser;
    private static JPanel windowForRulesButtons;
    private static JPanel windowForExecuteButton;
    private static JLabel textToUser;
    private static GridBagConstraints formForComponents;
    private static Border blackline;
    
    
    
    public static void main(String[] args)
    {
        Main main = new Main();
        
    }
    
    
    
    public Main()
    {
        JRadioButton
        tempRButton;
    
        ButtonGroup
        tempGroupUsage;
        
        String
        tempEmployeeName;
        
        
        db = new Database();
        allEmployees = db.getAllEmployees();
        listSize = allEmployees.size();
        allEmployeeNames = new String[listSize];
        allEmployeeButtons = new JRadioButton[listSize];
    
    
        windowMain = new JFrame();
        
        windowForTextToUser = new JPanel();
        windowForTextToUser.setLayout(new GridLayout(1, 1));
        
        windowForEmployeeButtons = new JPanel();
        windowForEmployeeButtons.setLayout(new GridLayout(3, 5));
        
        windowForRulesButtons = new JPanel();
        windowForRulesButtons.setLayout(new GridLayout(2, 2));
        
        windowForExecuteButton = new JPanel();
        windowForExecuteButton.setLayout(new GridLayout(1, 0));
    
        blackline = BorderFactory.createLineBorder(Color.black);
    
       
    
        windowMain.setVisible(true);
        windowMain.setEnabled(true);
        windowMain.setSize(564, 526);
        windowMain.setLayout(new GridBagLayout());
        windowMain.setLocationRelativeTo(null);
        windowMain.setResizable(false);
        windowMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
        windowForTextToUser.setVisible(true);
        windowForTextToUser.setEnabled(false);
        windowForTextToUser.setBorder(blackline);
        
        
        windowForEmployeeButtons.setVisible(true);
        
        
        windowForRulesButtons.setVisible(true);
        windowForRulesButtons.setEnabled(true);
        
        
        windowForExecuteButton.setVisible(true);
        windowForExecuteButton.setEnabled(true);
    
    
        formForComponents = new GridBagConstraints();
    
        formForComponents.fill = GridBagConstraints.HORIZONTAL;
        formForComponents.gridx = 0;
        formForComponents.gridy = 0;
        formForComponents.ipady = 300;
        formForComponents.weightx = 564;
        formForComponents.gridwidth = 564;
        windowMain.add(windowForTextToUser, formForComponents);
    
    
        formForComponents = new GridBagConstraints();
    
        formForComponents.fill = GridBagConstraints.CENTER;
        formForComponents.gridx = 1;
        formForComponents.gridy = 1;
        formForComponents.ipady = 10;
        formForComponents.weightx = 564;
        formForComponents.gridwidth = 564;
        windowMain.add(windowForEmployeeButtons, formForComponents);
    
    
        formForComponents = new GridBagConstraints();
    
        formForComponents.fill = GridBagConstraints.CENTER;
        formForComponents.gridx = 2;
        formForComponents.gridy = 2;
        formForComponents.ipady = 10;
        formForComponents.weightx = 564;
        formForComponents.gridwidth = 564;
        windowMain.add(windowForRulesButtons, formForComponents);
    
        
        formForComponents = new GridBagConstraints();
    
        formForComponents.fill = GridBagConstraints.HORIZONTAL;
        formForComponents.gridx = 3;
        formForComponents.gridy = 3;
        formForComponents.ipady = 20;
        formForComponents.weightx = 564;
        formForComponents.gridwidth = 564;
        windowMain.add(windowForExecuteButton, formForComponents);
    
    
        tempGroupUsage = new ButtonGroup();
    
        for(int i = 0; i < listSize; i++)
        {
            tempEmployeeName = allEmployees.get(i).getName();
            allEmployeeNames[i] = tempEmployeeName;
            
            tempRButton = new JRadioButton(tempEmployeeName);
            tempRButton.setVisible(true);
            
            tempGroupUsage.add(tempRButton);
            windowForEmployeeButtons.add(tempRButton);
    
            allEmployeeButtons[i] = tempRButton;
            
        }
        
    
        superOrdinateButton = new JRadioButton("Hämta chefer");
        subOrdinateButton = new JRadioButton("Hämta understående");
    
        tempGroupUsage = new ButtonGroup();
        tempGroupUsage.add(superOrdinateButton);
        tempGroupUsage.add(subOrdinateButton);
        windowForRulesButtons.add(superOrdinateButton);
        windowForRulesButtons.add(subOrdinateButton);
    
        
        choseAllButton = new JRadioButton("Hämta alla");
        choseOneButton = new JRadioButton("Hämta ett snäpp ifrån");
    
        tempGroupUsage = new ButtonGroup();
        tempGroupUsage.add(choseAllButton);
        tempGroupUsage.add(choseOneButton);
        windowForRulesButtons.add(choseAllButton);
        windowForRulesButtons.add(choseOneButton);
    
    
        execute = new JButton("Sök");
        execute.addActionListener(this);
        
        windowForExecuteButton.add(execute);
    
    
        textToUser = new JLabel();
        
        windowForTextToUser.add(textToUser);
        
        
        windowMain.pack();
        
    }
    
    
    
    public static String getSubordinates(Employee employee, int stepsToGo)
    {
        String
        finalMessage ,
        messageSubs ,
        tempEmployee ,
        levelsOfAssociation;
        
        List<Employee>
        fullList;
        
        Employee
        masterEmployee;
        
        int
        fullListSize;
    
        
        finalMessage = "";
        fullList = new ArrayList <Employee>();
        fullList.add(employee);
        
        
        if(stepsToGo == 1)
        {
            levelsOfAssociation = "en nivå ifrån sig själv";
            
        }
    
        else if(stepsToGo == -1)
        {
            levelsOfAssociation = "alla nivåer ifrån sig själv";
        
        }
        
        else
        {
            throw new UnsupportedOperationException();
            
        }
    
        
        fullList = recursionDown(fullList, stepsToGo);
    
    
        masterEmployee = fullList.get(0);
        fullList.remove(masterEmployee);
        fullListSize = fullList.size();
        
    
        finalMessage += "Nissen " + masterEmployee.getName() + " har så här många understående på " + levelsOfAssociation + " : " + fullListSize +
                        "<br/>";
    
    
        if(fullListSize == 0)
        {
            messageSubs = "Det finns inga understående att visa.";
        }
    
        else
        {
            messageSubs =
            "Dessa är Nissen " +  masterEmployee.getName() + " följande understående på " + levelsOfAssociation + ": " + "<br/>";
        
            for(int i = 0; i < fullListSize; i++)
            {
                tempEmployee = fullList.get(i).getName();
    
                messageSubs += tempEmployee;
                messageSubs += "<br/>";
            
            }
        
        }
    
    
        finalMessage += messageSubs;
    
        return finalMessage;
    
    }
    
    
    
    public static List<Employee> recursionDown(List<Employee> subordinates, int stepsToGo)
    {
        List<Employee>
        currentList;
        
        Employee
        currentCheckedEmployee ,
        nextEmployee;
        
        boolean
        employeeAlreadyUsed;
        
        int
        numberOfSubs ,
        sizeOfList;
 
        
        currentList = subordinates;
        currentCheckedEmployee = currentList.get(currentList.size() - 1);
        sizeOfList = currentList.size();
        employeeAlreadyUsed = false;
        
        
        if(currentCheckedEmployee.getAllLowMap() != null && stepsToGo != 0)
        {
            numberOfSubs = currentCheckedEmployee.getLowMapsSlots();
            
            for(int i1 = 0; i1 < numberOfSubs; i1++)
            {
                int finalI1 = i1;
    
                nextEmployee = currentCheckedEmployee.getSpecificLowMap(finalI1);
    
                for(int i2 = 0; i2 < sizeOfList; i2++)
                {
                    int finalI2 = i2;
                    Employee finalNextEmployee = nextEmployee;
                    List <Employee> finalCurrentList = currentList;
                    
                    
                    employeeAlreadyUsed = currentList.stream()
                    .distinct()
                    .anyMatch(db -> finalNextEmployee.equals(finalCurrentList.get(finalI2)));
                    
                    if(employeeAlreadyUsed)
                    {
                        break;
                    }
                    
                }
                
                if(!employeeAlreadyUsed)
                {
                    if(stepsToGo == 0)
                    {}
                    
                    else
                    {
                        --stepsToGo;
                    }
                    
                    
                    currentList.add(nextEmployee);
                    currentList = recursionDown(currentList, stepsToGo);
                }
            }
        }
        
        
        return currentList;
        
    }
    
    
    
    public static String getSuperordinates(Employee employee, int stepsToGo)
    {
        String
        finalMessage ,
        messageBosses ,
        tempEmployee ,
        levelsOfAssociation;
        
        List<Employee>
        fullList;
    
        Employee
        masterEmployee;
    
        int
        fullListSize;
    
    
        finalMessage = "";
        fullList = new ArrayList <Employee>();
        fullList.add(employee);
    
    
        fullList = recursionUp(fullList, stepsToGo);
    
    
        masterEmployee = fullList.get(0);
        fullList.remove(masterEmployee);
        fullListSize = fullList.size();
    
    
        if(stepsToGo == 1)
        {
            levelsOfAssociation = "en nivå ifrån sig själv";
        
        }
    
        else if(stepsToGo == -1)
        {
            levelsOfAssociation = "alla nivåer ifrån sig själv";
        
        }
    
        else
        {
            throw new UnsupportedOperationException();
        
        }
    
        
        finalMessage += "Nissen " + masterEmployee.getName() + " har så här många chefer på " + levelsOfAssociation + " : " + fullListSize +
                        "<br/>";
        
    
        if(fullListSize == 0)
        {
            messageBosses = "Det finns inga chefer att visa.";
        }
        
        else
        {
            messageBosses =
            "Dessa är Nissen " +  masterEmployee.getName() + " följande chefer på " + levelsOfAssociation + ": " +
            "<br/>";
    
            for(int i = 0; i < fullListSize; i++)
            {
                tempEmployee = fullList.get(i).getName();
    
                messageBosses += tempEmployee;
                messageBosses += "<br/>";
                
            }
            
        }
    
        
        finalMessage += messageBosses;
        
        return finalMessage;
        
    }
    
    
    
    public static List<Employee> recursionUp(List<Employee> superordinates, int stepsToGo)
    {
        List<Employee>
        currentList;
        
        Employee
        currentCheckedEmployee ,
        nextEmployee;
        
        boolean
        employeeAlreadyUsed;
        
        int
        sizeOfList;
        
        
        currentList = superordinates;
        currentCheckedEmployee = currentList.get(currentList.size() - 1);
        sizeOfList = currentList.size();
        employeeAlreadyUsed = false;
        
        
        if(currentCheckedEmployee.getTopMap() != null && stepsToGo != 0)
        {
            nextEmployee = currentCheckedEmployee.getTopMap();
            
            for(int i1 = 0; i1 < sizeOfList; i1++)
            {
                int finalI1 = i1;
                Employee finalNextEmployee = nextEmployee;
                List <Employee> finalCurrentList = currentList;
                
                
                employeeAlreadyUsed = currentList.stream()
                .distinct()
                .anyMatch(db -> finalNextEmployee.equals(finalCurrentList.get(finalI1)));
                
                if(employeeAlreadyUsed)
                {
                    break;
                }
                
            }
            
            if(!employeeAlreadyUsed)
            {
                if(stepsToGo == 0)
                {}
                
                else
                {
                    --stepsToGo;
                }
                
                currentList.add(nextEmployee);
                currentList = recursionUp(currentList, stepsToGo);
                
            }
            
        }
        
        
        return currentList;
        
    }
    
    
    
    public static void writeToScreen(String information)
    {
        textToUser.setText(information);
        windowMain.pack();
        
    }
    
    
    
    public void actionPerformed(ActionEvent e)
    {
        boolean
        employeeChosen ,
        mapTypeChosen ,
        mapSizeChosen ,
        mapTypeHigh ,
        mapTypeLow ,
        mapSizeAll ,
        mapSizeOne;
        
        int
        stepsToGo;
    
        String
        chosenEmployeeName ,
        finalEmployeeList ,
        finalReturnValue;
        
        Employee
        chosenEmployee;
        
        
        employeeChosen = false;
        mapTypeChosen = false;
        mapTypeHigh = false;
        mapTypeLow = false;
        mapSizeChosen = false;
        mapSizeAll = false;
        mapSizeOne = false;
        chosenEmployeeName = "";
        finalEmployeeList = "";
        finalReturnValue = "";
        chosenEmployee = new Employee();
        
        
        for(int i = 0; i < listSize; i++)
        {
            employeeChosen = allEmployeeButtons[i].isSelected();
            
            if(employeeChosen)
            {
                chosenEmployeeName = allEmployeeButtons[i].getText();
                break;
                
            }
            
        }
        
        
        if(employeeChosen)
        {
            for(int i = 0; i < listSize; i++)
            {
                if(chosenEmployeeName.equals(allEmployees.get(i).getName()))
                {
                    chosenEmployee = allEmployees.get(i);
                    break;
                    
                }
                
            }
            
        }
    
        
        if(superOrdinateButton.isSelected())
        {
            mapTypeChosen = true;
            mapTypeHigh = true;
        }
        
        else if(subOrdinateButton.isSelected())
        {
            mapTypeChosen = true;
            mapTypeLow = true;
        }
    
    
        if(choseAllButton.isSelected())
        {
            mapSizeChosen = true;
            mapSizeAll = true;
        }
        
        else if(choseOneButton.isSelected())
        {
            mapSizeChosen = true;
            mapSizeOne = true;
        }
        
        
        if(employeeChosen && mapTypeChosen && mapSizeChosen)
        {
            if(mapSizeAll)
            {
                stepsToGo = -1;
                
            }
            
            else if(mapSizeOne)
            {
                stepsToGo = 1;
                
            }
            
            else
            {
                throw new UnsupportedOperationException();
                
            }
            
            
            if(mapTypeHigh)
            {
                finalEmployeeList = getSuperordinates(chosenEmployee, stepsToGo);
                
            }
            
            else if(mapTypeLow)
            {
                finalEmployeeList = getSubordinates(chosenEmployee, stepsToGo);
                
            }
            
            else
            {
                throw new UnsupportedOperationException();
                
            }
            
        }
        
        
        if(!employeeChosen)
        {
            finalReturnValue += "Ingen Nisse har valts." + "<br/>";
            
        }
    
        if(!mapTypeChosen)
        {
            finalReturnValue += "Ingen anställningstyp har valts." + "<br/>";
        
        }
    
        if(!mapSizeChosen)
        {
            finalReturnValue += "Ingen önskad strolek på listan har valts." + "<br/>";
        
        }
    
        
        finalReturnValue += finalEmployeeList;
        finalReturnValue =  "<html>" + finalReturnValue + "<html>";
        finalReturnValue = finalReturnValue.trim();
    
    
        writeToScreen(finalReturnValue);
        
    }
    
}