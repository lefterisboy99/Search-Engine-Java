import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WordsCounter {

    String FileName=new String();
    int max_value=0;
    

    Map<String, Integer> WordInfo;

    WordsCounter(String FileName,String word_name){
    	this.FileName=FileName;
		
		//System.out.println(file_index.size());
		WordInfo=new HashMap<String, Integer>();
		WordInfo.put(word_name,1);
		if(max_value == 0)
            max_value = 1;
		
    }
    public static int ifstopWord(ArrayList<String> stopWords,String name)
    {
        int counter = 0;
        for(String i : stopWords)
        {
            if(i.equals(name))
                return counter;

            counter++;
        }
        return -1;
    }
    
    public void add_info(String word_name,ArrayList<String> stopWords) {
    	int temp=ifstopWord(stopWords,word_name);
    	if(temp!=-1) {
    		return;
    	}
    	
        if(WordInfo.containsKey(word_name))
        {
        	//System.out.println("ok"+" "+word_name);
            WordInfo.put(word_name,WordInfo.get(word_name)+1);

            if(max_value < WordInfo.get(word_name))
                max_value = WordInfo.get(word_name);
        }
        else {
            if(max_value == 0)
                max_value = 1;


            WordInfo.put(word_name,1);
        }


    }
}