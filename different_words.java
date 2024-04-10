import java.util.Iterator;
import java.util.LinkedList;

public class different_words {
	//int counter;
	String id=new String();
	LinkedList<tag_file> file_index;
	//ArrayList<String> tag;
	//ArrayList<String> file;
	different_words(String id,String filePath,String tag){
		this.id=id;
		this.file_index=new LinkedList<tag_file>();
		//System.out.println(file_index.size());
		file_index.add(new tag_file(filePath,tag,1));
		//System.out.println(file_index.size());
	}
	
	
	
	public void add_info(String filePath,String tag) {
		
		//file_index.add(new tag_file(filePath,tag,1));
		
		Iterator<tag_file> itr=file_index.iterator();
		int counter=0;
		
		
		
  	  	while(itr.hasNext()){  
  	  		
  	  		tag_file dummy=itr.next();
  	  		//System.out.println(dummy.name);
  	  		if(filePath.equals(dummy.name)) {
  	  			//System.out.println(dummy.name+" "+file_index.size());
  	  			file_index.get(counter).add_info(tag);
  	  			
  	  			return;
  	  		}
  	  		counter++;
            
  	  	}  
		
  	  	//tag_file new_file=new tag_file();
		//new_file.name=filePath;
		//new_file.counter_tag.put(tag, 1);
		file_index.add(new tag_file(filePath,tag,1));
		//if(id.equals("0"))
				//System.out.println("hey");
		//Iterator<tag_file> itr1=file_index.iterator();
		
		
  	  	/*while(itr1.hasNext()){  
  	  		
  	  	
  	  	System.out.println(itr1.next().name);  
  	  	}*/
		//System.out.println(filePath);
		
	}
}



/*OLD (delete for better opt)
 int flag=0;
 
for(int i=0;i<file_index.size();i++) {
	if(filePath.equals(file_index.get(i).name)) {
		flag=1;
		file_index.get(i).add_info(tag);
		//System.out.println(tag+"Cbbbbb"+i);
	}
}
if(flag==0) {
	//System.out.println("Abbbbb");
	tag_file new_file=new tag_file();
	new_file.name=filePath;
	new_file.counter_tag.put(tag, 1);
	file_index.add(new_file);
}else {
	flag=0;
}*/