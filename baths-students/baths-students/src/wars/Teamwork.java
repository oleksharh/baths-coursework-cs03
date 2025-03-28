package wars; 


/**
 * Details of your team
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "CS03";
        
        details[1] = "Nabijanov";
        details[2] = "Islombek";
        details[3] = "22099767";

        details[4] = "Sharhorodskyi";
        details[5] = "Oleksandr";
        details[6] = "23020326";

        details[7] = "Skakun";
        details[8] = "Oleh";
        details[9] = "23025986";


        details[10] = "Zaborskikh";
        details[11] = "Aleksandr";
        details[12] = "22104971";

	
	   // only if applicable
        details[13] = "surname of member5";
        details[14] = "first name of member5";
        details[15] = "SRN of member5";


    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
      