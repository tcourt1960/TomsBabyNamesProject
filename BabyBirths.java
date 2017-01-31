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
     
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String currentYearString = f.getName();
            
            if(currentYearString.contains(Integer.toString(year))){
            
                for (CSVRecord rec : fr.getCSVParser(false)){
                    if(rec.get(1).equals(gender) && (getRank(year,rec.get(0),gender)==rank)){
                        theName = rec.get(0);
                        System.out.println(theName);
                        return theName;
                    }
                }
            }
        }
        return theName;
    }
    
    public void testgetName(){
          int year = 2010;
          int rank = 3;
          String gender = "F";
          String theName=getName(year, rank, gender);
          
          System.out.println(" Name is " + theName + " in " + year);
    }
    
    
    public void checkDir(){
    DirectoryResource dr = new DirectoryResource();
    
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f.getName());   
        }
    }
    
}

