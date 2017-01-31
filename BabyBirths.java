/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource("data/example-small.csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
                
    
    public int getRank(int year, String name, String gender){
        int currentRank = 1;
        int theRank = -1;
 
        FileResource fr = new FileResource("data/yob"+year+".csv");
        if (gender=="F"){
          for (CSVRecord rec : fr.getCSVParser(false)){
              if(rec.get(1).equals(gender) && rec.get(0).equals(name)){
                    theRank = currentRank;
                    return theRank;
              }
          currentRank += 1;
         }
        return theRank;
        }else {  //it's M
          currentRank=1;
            for (CSVRecord rec : fr.getCSVParser(false)){
              if(rec.get(1).equals(gender) && rec.get(0).equals(name)){
                    theRank = currentRank;
                    return theRank;
              }
          if(rec.get(1).equals("M")){
              currentRank += 1;
          }
         }
        return theRank;            
            
        }    
    }
        
    public void testgetRank(){
          int year = 1961;
          String name = "Lorraine";
          String gender = "F";
         
          int theRank=getRank(year, name, gender);
          System.out.println(name + " rank is " + theRank + " in " + year);
    }

    
    
    public String getName(int year, int rank, String gender){
     String theName = "NO NAME";
     
        FileResource fr = new FileResource("data/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
               if(rec.get(1).equals(gender) && (getRank(year,rec.get(0),gender)==rank)){
                   theName = rec.get(0);
                   return theName;
               }
        }
        return theName;
    }
    
    public void testgetName(){
          int year = 1960;
          int rank = 9;
          String gender = "M";
          String theName=getName(year, rank, gender);
          System.out.println("Name with rank of " + rank+ " is "+ theName + " in " + year);
    }
    
    
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
     int rank=getRank(year, name, gender);
     String theName = getName(newYear, rank, gender);
     System.out.println(name + " born in " + year + " would be " + theName + " if born in "+newYear);
    }
    
    public void test_whatIsNameInYear(){
    String name = "Tom";
    int year = 1961;
    int newYear=2014;
    String gender ="M";    
    whatIsNameInYear(name, year,newYear, gender);
    }
    
    
    
    public int yearOfHighestRank(String name, String gender){
    int bestYear=-1;  //in case name or gender isn't found
    int bestRank=-1;  //in case rank never changes
    //select files
    
    //loop through files. 
        
        //if currentRank>bestRank, then update bestYear and bestRank
        
    // return bestYear
    
    }
    
    public void test_yearOfHighestRank(){
    String name="Thomas";
    String gender="M";
    int theYear=yearOfHighestRank(name, gender);
    System.out.println(name + " had highest ranking in year "+ theYear);
    }
    
    
    public void checkDir(){
    DirectoryResource dr = new DirectoryResource();
    
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f.getName());   
        }
    }
    
}

