import java.util.ArrayList;
import java.util.HashSet;

public class content {
	
		String pmcid,title,abstr,body,journal,publisher;
		ArrayList<String> authors;
		HashSet<String> categories;
		content(String pmcid,String title,String abstr,String body,String journal,String publisher,ArrayList<String> authors,HashSet<String> categories){
			this.pmcid=pmcid;
	        this.title = title;
	        this.abstr = abstr;
	        this.body = body;
	        this.journal = journal;
	        this.publisher = publisher;

	        this.authors = authors;
	        this.categories =categories;
		
	}
}
