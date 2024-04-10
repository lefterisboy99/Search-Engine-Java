import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class tag_file {
	String name=new String();
	Map<String, Integer> counter_tag;
	
	
	tag_file(String name,String tag,int i){
		this.name=name;
		counter_tag = new HashMap<String, Integer>();
		counter_tag.put(tag,i);
		
	}
	//ArrayList<Integer> counter=new ArrayList<Integer>();
	//ArrayList<String> tag=new ArrayList<String>();
	//Map<String, Integer> counter_tag= new HashMap<String, Integer>();
	public void add_info(String tag) {
		//System.out.println("Bbbbbb");
		if(counter_tag.containsKey(tag)) {
			//System.out.println("Dbbbbb");
			counter_tag.put(tag, counter_tag.get(tag) + 1);
		}else {
			counter_tag.put(tag,1);
		}
	}
	
}
