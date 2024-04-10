import gr.uoc.csd.hy463.NXMLFileReader;
import mitos.stemmer.Stemmer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map; 


public class Phase_A extends JFrame implements ActionListener{
	static JFrame f;
	static File Document=null;
	static File Folder=null;
	static JLabel Doc_label = new JLabel();
	static JLabel Folder_label = new JLabel();
	public static int globalNXMLSize;
	
	  
	  
	  
	  
	  
	public static void print_every_input(ArrayList<WordsCounter> every_file) {
		
        try {
            FileWriter myWriter = new FileWriter("counter.txt");

            for(int i=0;i<every_file.size();i++) {
                myWriter.write(every_file.get(i).FileName+"\n");

                    for ( Map.Entry<String, Integer> entry : every_file.get(i).WordInfo.entrySet()) {
                        String key = entry.getKey();
                        int tab = entry.getValue();
                        myWriter.write(key+" counter ="+tab+"\n");
                    }


            }
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


   /* public static void print_every_word(ArrayList<different_words> every_word) {

        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            //myWriter.write("Files in Java might be tricky, but it is fun enough!");
            for(int i=0;i<every_word.size();i++) {
                myWriter.write("id="+every_word.get(i).id+"\n");
                for(int j=0;j<every_word.get(i).file_index.size();j++) {
                    myWriter.write(every_word.get(i).file_index.get(j).name+"\n");
                    for ( Map.Entry<String, Integer> entry : every_word.get(i).file_index.get(j).counter_tag.entrySet()) {
                        String key = entry.getKey();
                        int tab = entry.getValue();
                        // do something with key and/or tab
                        myWriter.write(key+"="+tab+"\n");
                    }

                }
            }
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
		//for(int i=0;i<every_word.size();i++) {
			//System.out.println(every_word.get(i).id);
			//for(int j=0;j<every_word.get(i).file_index.size();j++) {
				//System.out.println(every_word.get(i).file_index.get(j).name);
				//System.out.println(every_word.get(i).file_index.get(j).counter_tag);

			//}
		//}
    }*/

    public static int exists(LinkedList<WordsCounter> words_counter,String name)
    {
    	
    	if(words_counter.size()==0) {
    		System.exit(-1);
    		return -1;
    		
    	}
    	Iterator<WordsCounter> itr=words_counter.iterator();  
    	int counter=0;
    	 while(itr.hasNext()){  
    		 
    		  if(name.equals(itr.next().FileName)) {
    			  //System.out.println(name+" "+counter+" "+words_counter.size());
    			  return counter;
    		  }
    		  counter++;
    	  }  
        /*int counter = 0;
        for(WordsCounter i : words_counter)
        {
            if(i.FileName.equals(name))
                return counter;

            counter++;
        }*/
        return -1;
    }
    
    public static  void tokenString(LinkedList<WordsCounter> words_counter,String array,ArrayList<String> stopWords,LinkedList<different_words> every_word,String filePath,String tag,Map<String, Integer> info_of_pos, int i2)
    {
    	//System.out.println("inside");
        StringTokenizer tokenizer = new StringTokenizer(array," ");
        String token;
        int flag=0;
        int flag2=0;
        //int counter=0;
        //
        //int temp = -1;

        

        while(tokenizer.hasMoreTokens()){
        	

            //System.out.println(tokenizer.nextToken());
            token=tokenizer.nextToken();
            //
            for(int i=0;i<stopWords.size();i++) {
                if(token.equals(stopWords.get(i))) {
                    flag=1;
                    //System.out.println(returnedValue);
                }

            }
            
            token=Stemmer.Stem(token);
            
            
            
           // words_counter.get(temp).add_info(token,stopWords);
            //System.out.println(words_counter.get(temp).WordInfo);
            //System.out.println(filePath);
            
           // System.out.println(every_word.size());
            //System.out.println(token);
            
             
            if(flag==0) {
            	
            	if(words_counter.size()==i2) {
            		words_counter.add(new WordsCounter(filePath,token));
            		//temp = 0;
            	}else {
            		
            		words_counter.get(i2).add_info(token,stopWords);
            	}
            	
            	/*if(temp == -1)
                {
                	temp = exists(words_counter,filePath);
                    //WordsCounter new_obj = new WordsCounter();
                   // new_obj.FileName = filePath;
                	if(temp==-1) {
                		words_counter.add(new WordsCounter(filePath,token));
                		temp = words_counter.size()-1;
                	}else {
                		words_counter.get(temp).add_info(token,stopWords);
                	}
                }else {
                	
                	words_counter.get(temp).add_info(token,stopWords);
                }*/

            	
            	
            	if(!info_of_pos.isEmpty()) {
            		 if(info_of_pos.containsKey(token)){
            			 flag2=1;
            			 every_word.get(info_of_pos.get(token)).add_info(filePath, tag);
            	     }else {
            	    	 int j=info_of_pos.size();
            	    	 info_of_pos.put(token, j);
            	     }
            		 
            	}else {
            		info_of_pos.put(token,0);
            	}
            	
            	/*Iterator<different_words> itr=every_word.iterator();  
            	counter=0;
            	 while(itr.hasNext()){  
            		 
            		  if(token.equals(itr.next().id)) {
                          flag2=1;
                          every_word.get(counter).add_info(filePath, tag);
                          //System.out.println(every_word.get(i)+" "+tag);
                         
                          break;
                          
                      }
            		  counter++;
            	  }*/  
                /*for(int i=0;i<every_word.size();i++) {
                	
                	if(every_word.get(i)==null)
                		System.exit(-1);
                	
                    if(token.equals(every_word.get(i))) {
                        flag2=1;
                        //System.out.println(every_word.get(i)+" "+tag);
                    }


                }*/
            	  if(flag2==0) {
            	  		//different_words new_word=new different_words();
            	  		//new_word.id=token;
            	  		//new_word.add_info(filePath,tag);
            	  		//String a=token;
            	  		every_word.add(new different_words(token,filePath,tag));
            	  		
            	  		//every_word.
            	  		
            	  	}else {
            	  			
            	  			flag2=0;
            	  		}
            	}else {
            			
            		flag=0;
            	

            }
        }
        
        //System.out.println("outside");

    }

    public static void indexing(LinkedList<WordsCounter> words_counter,String filePath,ArrayList<String> stopWords,ArrayList<String> symbols,LinkedList<different_words> every_word, Map<String, Integer> info_of_pos, int i2) throws IOException {

    	//System.out.println("in");
        File example = new File(filePath);

        NXMLFileReader xmlFile = new NXMLFileReader(example);

        String pmcid = xmlFile.getPMCID();
        
        String title = xmlFile.getTitle();
        for(String i:symbols)
            title=title.replace(i," ");
        title=title.toLowerCase();
        tokenString(words_counter,title,stopWords,every_word,filePath,"title", info_of_pos,i2);

        String abstr = xmlFile.getAbstr();
        abstr=abstr.toLowerCase();
        for(String i:symbols)
            abstr=abstr.replace(i," ");
        tokenString(words_counter,abstr,stopWords,every_word,filePath,"abstr", info_of_pos, i2);

        String body = xmlFile.getBody();
        body=body.toLowerCase();
        for(String i:symbols)
            body=body.replace(i," ");
        tokenString(words_counter,body,stopWords,every_word,filePath,"body", info_of_pos, i2);

        String journal = xmlFile.getJournal();
        journal=journal.toLowerCase();
        for(String i:symbols)
            journal=journal.replace(i," ");
        tokenString(words_counter,journal,stopWords,every_word,filePath,"journal", info_of_pos, i2);

        String publisher = xmlFile.getPublisher();
        publisher=publisher.toLowerCase();
        for(String i:symbols)
            publisher=publisher.replace(i," ");
        tokenString(words_counter,publisher,stopWords,every_word,filePath,"publisher", info_of_pos, i2);

        //print_every_word(every_word);
        ArrayList<String> authors = xmlFile.getAuthors();
        for(int i=0;i<authors.size();i++) {
        	for(String j:symbols)
        		authors.set(i,authors.get(i).replace(j, " "));
        	tokenString(words_counter,authors.get(i),stopWords,every_word,filePath,"authors", info_of_pos, i2);
        }
        HashSet<String> categories =xmlFile.getCategories();
        
        for(String i : categories) {
        	for(String j:symbols)
        		i=i.replace(j, " ");
        	tokenString(words_counter,i,stopWords,every_word,filePath,"categories", info_of_pos, i2);
        }
        //content content_creator=new content(pmcid,title,abstr,body,journal,publisher,authors,categories);
        //index.add(content_creator);
	        /*System.out.println("- PMC ID: " + pmcid);
	        System.out.println("- Title: " + title);
	        System.out.println("- Abstract: " + abstr);
	        System.out.println("- Body: " + body);
	        System.out.println("- Journal: " + journal);
	        System.out.println("- Publisher: " + publisher);
	        System.out.println("- Authors: " + authors);
	        System.out.println("- Categories: " + categories);*/
        //System.out.println("out");
    }
    public static void listFilesForFolder(final File folder , ArrayList<String> list) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry,list);
            } else {
                list.add(fileEntry.getPath());
                //System.out.println(fileEntry.getPath());
            }
        }
    }
    public static void vocabulary_creation(LinkedList<different_words> every_word, Map<String, Integer> info_of_pos) throws IOException {
        

        FileWriter vocabularyFile = new FileWriter(Folder.getAbsolutePath()+"\\VocabularyFile.txt");
        LinkedList<String> string_words = new LinkedList<String>();
        LinkedList <Integer> counter =new LinkedList<Integer>();
        LinkedList <Integer> pointer =new LinkedList<Integer>();
        Iterator<different_words> itr=every_word.iterator();
		
  	  	while(itr.hasNext()){  
  	  		
  	  		string_words.add(itr.next().id);
  	  		
  	  	}
  	  	
        /*for (different_words i : every_word) {
            string_words.add(i.id);
            //System.out.println(i.id+"   "+i.file_index.);
        }*/
        Collections.sort(string_words);
        int tab=1;

        for(String str : string_words){
        	
        	int temp=0;
        	
        	pointer.add(tab);
        	Iterator<tag_file> itr2=every_word.get(info_of_pos.get(str)).file_index.iterator();
        	temp+=every_word.get(info_of_pos.get(str)).file_index.size();
        	
        	/*if(info_of_pos.containsKey(token)){
       			 	
       			 	every_word.get(info_of_pos.get(token)).add_info(filePath, tag);
       	     }else {
       	     		int j=info_of_pos.size();
       	     		info_of_pos.put(token, j);
       	     }*/
       		 
        	while(itr2.hasNext()){
                //for(int j=0;j<every_word.get(i).file_index.size();j++) {
                	//System.out.print(itr2.next().name);
                    //for ( Map.Entry<String, Integer> entry : every_word.get(i).file_index.get(j).counter_tag.entrySet()){
                	
                    tab += itr2.next().counter_tag.size();
                   // if(str.equals("0")) {
                    	//System.out.println(tab);
                    //}
                    //if(every_word.get(i).id.equals("0"))
                       // System.out.println(every_word.get(i).file_index.get(j).counter_tag.size());

                    //}

                   
           }
        	
        	counter.add(temp);
        	
        	
        	
        	
        	
        	
        	
        	//System.out.println(str);
        	/*Iterator<different_words> itr1=every_word.iterator();
        	//System.out.println(tab);
    		
    		int temp = 0;
        	while(itr1.hasNext()){
        		
            //for(int i=0;i<every_word.size();i++) {
        		different_words dummy=itr1.next();
        		
                if(str.equals(dummy.id)) {
                	//System.out.println(dummy.id);
                   
                    pointer.add(tab);
                    Iterator<tag_file> itr2=dummy.file_index.iterator();
                    
                    //System.out.println(dummy.file_index.size());
                    temp+=dummy.file_index.size();
                    
                    
                    
                    while(itr2.hasNext()){
                    //for(int j=0;j<every_word.get(i).file_index.size();j++) {
                    	//System.out.print(itr2.next().name);
                        //for ( Map.Entry<String, Integer> entry : every_word.get(i).file_index.get(j).counter_tag.entrySet()){
                    	
                        tab += itr2.next().counter_tag.size();
                       // if(str.equals("0")) {
                        	//System.out.println(tab);
                        //}
                        //if(every_word.get(i).id.equals("0"))
                           // System.out.println(every_word.get(i).file_index.get(j).counter_tag.size());

                        //}

                       
                    }

                    counter.add(temp);
                    //break;
                }
              
            }*/
        }
        //System.out.println(string_words.size()+" "+counter.size()+" "+pointer.size());
        
        for(int  i = 0; i < string_words.size(); i++){
            vocabularyFile.write(string_words.get(i)+" "+counter.get(i)+" "+pointer.get(i)+"\n");
        	//vocabularyFile.write(string_words.get(i)+"   "+counter.get(i)+"\n");
        }
        vocabularyFile.close();
        System.out.println("end of vocabulary");
    }


    public static int CalculateDF(LinkedList<different_words> every_word,String name)
    {

    	Iterator<different_words> itr=every_word.iterator();
    	int counter=0;
    	while(itr.hasNext())
        {
        //for (int i = 0; i < every_word.size(); i++)
            if(name.equals(itr.next().id))
            	return every_word.get(counter).file_index.size();
            

            counter++;
        }
        System.out.println(name);
        System.exit(-1);
		return -1;
    }
    
    public static double log2(double N)
    {
 
        // calculate log2 N indirectly
        // using log() method
        
 
        return (double)(Math.log(N) / Math.log(2));
    }


    public static LinkedList<String> Document_creation(ArrayList<String> nxml_files,LinkedList<WordsCounter> words_counter,LinkedList<different_words> every_word) throws IOException {
    	LinkedList<String> paths = new LinkedList<String>();
        FileWriter DocumentsFile = new FileWriter(Folder.getAbsolutePath()+"\\DocumentsFile.txt");
        FileWriter DocFile = new FileWriter(Folder.getAbsolutePath()+"\\Doc.txt");



        String test = "";
        int unique_id = 0;

        double tf = -1;
        double df = -1;
        double idf = -1;
        double weight=-1;
        int temp1=-1;
        double vector_length=0;
        String str="";
            
        Iterator<WordsCounter> itr=words_counter.iterator();  
    	

            //System.out.println("str="+words_counter.size());
        while(itr.hasNext())
        {
        	
        	
        	WordsCounter dummy=itr.next();
        	str=dummy.FileName;
          	StringTokenizer tokenizer = new StringTokenizer(str,"\\");//full path

            while(tokenizer.hasMoreTokens())
            	test=tokenizer.nextToken();

            test = test.substring(0,test.length()-5);//PCMID/
            unique_id++;//unique id
            
            //System.out.println("file="+temp.FileName);
            if(dummy.FileName.equals(str))
            {
            	//System.out.println("tf="+tf);
            	
                vector_length=0;
                for(Map.Entry<String, Integer> entry : dummy.WordInfo.entrySet())
                {
                	
                	temp1 = entry.getValue();
                	//System.out.println("temp1="+temp1);
                	//System.out.println("max="+temp.max_value);
                	
                    tf = ((double)temp1/dummy.max_value);
                    df = CalculateDF(every_word,entry.getKey());
                    //System.out.println(df);
                    idf = log2((double)nxml_files.size()/(double)df);
                    weight = tf * idf;
                    vector_length+=Math.pow(weight, 2);
                    DocFile.write(entry.getKey()+" "+unique_id+" "+str +" "+idf*tf+"\n");
                }
                DocumentsFile.write(unique_id+"    "+str +"    "+test+"    "+Math.sqrt(vector_length)+"\n");
                paths.add(str);
                //System.out.println(unique_id+"    "+tokenizer +"    "+test+"    "+ret);
            }
            

        }

        DocumentsFile.close();
        DocFile.close();
        System.out.println("end of Document Creation");
        return paths;
    }
    
    public static double TfCalculation(LinkedList<WordsCounter> words_counter, String FName ,String word)
    {
        double temp1 = -1,tf = 0,max=-1;
        for(WordsCounter temp : words_counter) {
            if(temp.FileName.equals(FName)){
            	max=Math.max(temp.max_value,max);
                for(Map.Entry<String, Integer> entry : temp.WordInfo.entrySet())
                   if(entry.getKey().equals(word))
                   {
                       temp1 = entry.getValue();
                       tf += (double)temp1;
                   }
                
                return (tf/max);
            }
        }
        if(tf==-1) {
        	System.out.println(word);
        }
        return -1;
    }
    
    public static void Post_creation(LinkedList<different_words> every_word,LinkedList<String> paths,LinkedList<WordsCounter> words_counter) throws IOException {
    	
    	Collections.sort(every_word, new Comparator<different_words>() {
    	   @Override
			public int compare(different_words arg0, different_words arg1) {
				// TODO Auto-generated method stub
				return arg0.id.compareTo(arg1.id);
			}
    	});
    	
    	FileWriter PostingFile = new FileWriter(Folder.getAbsolutePath()+"\\PostingFile.txt");
            


            //System.out.println("str="+words_counter.size());
    	
        for(different_words temp : every_word)
        {
        	
        	//PostingFile.write(temp.id+"\n");
            
            for(tag_file temp1:temp.file_index) {
            	int retval=paths.indexOf(temp1.name);
            	retval+=1;
            	for(Map.Entry<String, Integer> entry : temp1.counter_tag.entrySet())
            		PostingFile.write(retval+" "+temp1.name+" "+temp.id+" "+entry.getKey()+" "+ TfCalculation(words_counter,temp1.name,temp.id) +" "+log2((double)globalNXMLSize/temp.file_index.size())+"\n" );

            }
            
            

        }
        System.out.println("end of post");
        PostingFile.close();
    }

    
    public static void main(String[] argv) throws IOException {
       
    	f = new JFrame("panel");
	 	JButton b;
	 	JButton b1;
	 	JButton save;
	 	
	    //JLabel l;
	    
        // Creating a label to display text
        //l = new JLabel("panel label");
       
        // Creating a new buttons
        JPanel m = new JPanel(new FlowLayout());
        m.setSize(600,100);
        m.setBackground(Color.white);
        m.setLocation(20,10);
        
        
        JPanel m1 = new JPanel(new FlowLayout());
        m1.setSize(600,100);
        m1.setBackground(Color.white);
        m1.setLocation(20,120);
        
        
        b = new JButton("Document");
        b.setBounds(300, 30, 100, 30);
        b.addActionListener(new Phase_A());
        JLabel jlabel = new JLabel();
        jlabel.setText("Select Document Folder");
        jlabel.setBounds(420, 35, 140, 20);    
        m.setLayout(null);
        
        
        
        b1 = new JButton("Folder");
        b1.setBounds(300, 30, 100, 30);
        b1.addActionListener(new Phase_A());
        JLabel jlabel1 = new JLabel();
        jlabel1.setText("Select Output Folder");
        jlabel1.setBounds(420, 35, 140, 20);
        m1.setLayout(null);
        
        // Creating a panel to add buttons
        //JPanel p = new JPanel();
        //p.setSize(500,500);
        // Adding buttons and textfield to panel
        // using add() method
        m.add(b);
        Doc_label.setBounds(20, 35, 250, 20); 
        Doc_label.setText("Not In");
        m.add(Doc_label);
        
        m.add(jlabel);
        
        m1.add(b1);
        m1.add(jlabel1);
        Folder_label.setBounds(20, 35, 250, 20); 
        Folder_label.setText("Not In");
        m1.add(Folder_label);
        
        JPanel saving = new JPanel(new FlowLayout());
        saving.setSize(100,100);
        saving.setBackground(Color.white);
        saving.setLocation(300,300);
        save = new JButton("Save");
        save.setBounds(300, 30, 100, 30);
        save.addActionListener(new Phase_A());
        saving.add(save);
        
        f.add(m);
        f.add(m1);
        f.add(saving);
        
        // Setting the size of frame
       
        f.setSize(650, 500);
        f.setLocation(200,200);
        f.setResizable(false);
        f.getContentPane().setLayout(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.show();
    	
    	
    	
    	
    	
    	
    	

    }

public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand()!="Save") {
			JFileChooser chooser;
		
			chooser = new JFileChooser(); 
			chooser.setCurrentDirectory(new java.io.File("."));
  	 
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
  	       
			if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) { 
  	      
				if(e.getActionCommand()=="Document") {
					Document=chooser.getSelectedFile();
					Doc_label.setText(Document.getAbsolutePath());
				}
				if(e.getActionCommand()=="Folder") {
					Folder=chooser.getSelectedFile();
					Folder_label.setText(Folder.getAbsolutePath());
				}	
  	     
  	      	}
			else {
				System.out.println("No Selection ");
  	      
			}
		}else {
			//System.out.println(Document+" "+Folder);
			if(Document!=null && Folder!=null) {
				ArrayList<String> stopWords = new ArrayList<String>();
				ArrayList<String> symbols = new ArrayList<String>();
				ArrayList<String> nxml_files = new ArrayList<String>();
				//LinkedList<content> index =new LinkedList<content>();
				LinkedList<different_words> every_word =new LinkedList<different_words>();
				LinkedList<WordsCounter> words_counter = new LinkedList<WordsCounter>();
				Stemmer.Initialize();
				symbols.add(";");
				symbols.add("!");
	        	symbols.add(",");
	        	symbols.add(".");
	        	symbols.add("?");
	        	symbols.add("}");
	        	symbols.add("{");
		        symbols.add("[");
		        symbols.add("]");
		        symbols.add("<");
		        symbols.add(">");
		        symbols.add("/");
		        symbols.add(":");
		        symbols.add("(");
		        symbols.add(")");
		        symbols.add("&");
		        symbols.add("#");
		        symbols.add("$");
		        symbols.add("%");
		        symbols.add("+");
		        symbols.add("-");
		        symbols.add("*");
		        symbols.add("_");
		        symbols.add("~");
		        symbols.add("\"");
		        symbols.add("\'");
		        symbols.add("=");
			       
		        String delimiter = "\t\n\r\f ";
	
		        try {
		            File stopWordEnglish = new File("C:\\Users\\Lefteris\\Desktop\\2022-3-EkftonisiResourcesSoftware\\3_Resources_Stoplists\\stopwordsEn.txt");
		            Scanner myReader = new Scanner(stopWordEnglish);
	
	
		            BufferedReader myReader1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Lefteris\\Desktop\\2022-3-EkftonisiResourcesSoftware\\3_Resources_Stoplists\\stopwordsGr.txt"), "UTF-8"));;
		            String line = new String();
		            int g=0;
	
		            while (myReader.hasNextLine()) {
		                String data = myReader.nextLine();
		                //System.out.println(data);
		                stopWords.add(data);
		            }
	
		            while((line = myReader1.readLine()) != null ){
		                StringTokenizer tokenizer = new StringTokenizer(line, delimiter);
	
		                while(tokenizer.hasMoreTokens()){
		                    String a=String.valueOf(tokenizer.nextToken());
		                    if(g==0)
		                    {
		                        a = a.substring(1);
		                    }
	
		                    //System.out.println(a);
		                    stopWords.add(a);
		                    g++;
		                }
		            }
	
			            /*for(int i=0;i<stopWords.size();i++)
			                System.out.println(stopWords.get(i));*/
		            myReader.close();
		            myReader1.close();
		        } catch (Exception e1) {
		            System.out.println(e1);
		        }
		        /*up to this point i have an: ArrayList of strings NAMED:::::stopWords  */
	
		        listFilesForFolder(new File(Document.getAbsolutePath()),nxml_files);
		        System.out.println("end of nxml");
		        globalNXMLSize = nxml_files.size();
		        Map<String, Integer> info_of_pos=new HashMap<String, Integer>();
		        for(int i=0;i<nxml_files.size();i++) {
		            try {
						indexing(words_counter,nxml_files.get(i), stopWords,symbols,every_word,info_of_pos,i);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            //System.out.println(i);
		        }
		        System.out.println("end of index");
			        
		        
		  	  
		        try {
					vocabulary_creation( every_word,info_of_pos);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		        info_of_pos.clear();
		        /*Iterator<WordsCounter> itr3=words_counter.iterator();
		        while(itr3.hasNext()) {
		        	System.out.println(itr3.next().FileName);
		        }*/
		        
		        //print_every_input(words_counter);
		        LinkedList<String> paths = null;
				try {
					paths = Document_creation( nxml_files,words_counter,every_word);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
					Post_creation(every_word,paths,words_counter);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				System.out.println("plz select right paths");
			}
		}
		
  	    
  	    
   }

    
}