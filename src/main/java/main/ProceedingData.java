package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

public class ProceedingData {
    
    private static ArrayList<Word> words = new ArrayList();
    public static void main(String[] args) {
        
        //Properties pr = getProperties();
        //ReadFile(pr);
    
    
    }
    public Properties getProperties(){
        try {
            Properties pr = new Properties();
            InputStream input = new FileInputStream("D:\\Java eclipse workspace\\netbeans\\SpringFileUpload\\config.properties");
            pr.load(input);
            return pr;            
         } catch (Exception e) {
            return null;
         }
    }
    
    public ArrayList readFiles (Properties pr) throws Exception {
        String fromLocation = pr.getProperty("FilesSavedIn");
        File folder = new File(fromLocation);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles){ 
            if (file.isFile()) {               
                Scanner sc = new Scanner(file);
                String newWord;
                while(sc.hasNext()) {
                    newWord = sc.next();
                    registerWord(newWord);
                }
            }
        }
        //FileUtils.cleanDirectory(folder); //is it really needed?
        sorter(words);
        return words;
    }
    
    public void outputToFiles (Properties pr) throws Exception {
        String fileName="A-G";
        boolean firstFile=false,
                secondFile=false,
                thirdFile=false,
                fourthFile=false;
        String toLocation = pr.getProperty("SaveFilesIn");
        File file= new File(toLocation+File.separator+fileName+".txt");
        BufferedWriter outputStream = null;      
            outputStream = new BufferedWriter(new FileWriter(fileName + ".txt"));
        for (Word w:words){
            if (w.getWord().startsWith("h")){                
                fileName="H-N";
            }
            if (w.getWord().startsWith("o")){
                fileName="O-U";
            }
            if (w.getWord().startsWith("v")){
                fileName="V-Z";
            }
            outputStream.write(w.getWord()+" "+w.getCount());
            outputStream.newLine();
        }
        outputStream.close();
    }
    
    public void registerWord(String newWord){
        boolean registered=false;
        newWord.toLowerCase();
        for (Word w:words){
            if (newWord.equals(w.getWord())){
                w.addCount();
                registered=true;
            }                
        }
        if(!registered){
            Word w= new Word(newWord,1);
            words.add(w);
        }
    }
    
    public static ArrayList sorter (ArrayList words) {
        words.sort(Comparator.comparing(Word::getWord)); 
        return words;
    }
}

