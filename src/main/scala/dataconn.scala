
 
import java.sql.DriverManager
import java.sql.Connection
object dataconn {
  def main(args: Array[String]) {
    // connect to the database named "mysql" on the localhost
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost/news_analytics"
    val username = "root"
    val password = "root"
 
    // there's probably a better way to do this
    var connection:Connection = null
 
    try {
      // make the connection
       Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
 
      
      
         val prep = connection.prepareStatement("INSERT INTO urls (url) VALUES (?) ")
   prep.setString(1, "Nothing great was ever achieved without enthusiasm.")
   prep.executeUpdate
      
     
      
    } catch {
      case e => e.printStackTrace
    }
    connection.close()
  }
 
}