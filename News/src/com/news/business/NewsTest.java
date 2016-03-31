package com.news.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.news.DB.DB;

public class NewsTest {

	
		public static DB db = new DB();
	 
		public static void main(String[] args) throws SQLException, IOException {
			db.runSql2("TRUNCATE Record;");
			processPage("http://www.nytimes.com");
		}
		
		public static void writeFile(String content){
				try {

					//String content = "This is the content to write into file";

					File file = new File("/C:/files/filename.txt");

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
				//	bw.append(content);
					bw.newLine();
					bw.close();

					System.out.println("Done");

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
	 
		public static void processPage(String URL) throws SQLException, IOException{
			//check if the given URL is already in database
			String sql = "select * from Record where URL = '"+URL+"'";
			ResultSet rs = db.runSql(sql);
			if(rs.next()){
	 
			}else{
				//store the URL to database to avoid parsing again
				sql = "INSERT INTO  `newsdb`.`Record` " + "(`URL`) VALUES " + "(?);";
				PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, URL);
				stmt.execute();
	 
				//get useful information
				Document doc = Jsoup.connect("http://www.nytimes.com/").get();
	 
				if(doc.text().contains("Trump")){
					System.out.println(URL);
					writeFile(URL);
				}
				else{
					System.out.println("no article found");
				}
	 
				//get all links and recursively call the processPage method
				Elements questions = doc.select("a[href]");
				for(Element link: questions){
					if(link.attr("href").contains("www.nytimes.com"))
						processPage(link.attr("abs:href"));
				}
			}
		}
	

}
