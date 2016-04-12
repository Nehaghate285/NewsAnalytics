package domain

class Link(val parent: String, val link: String, val description: String) {
	override def toString = {
	  //"Parent: " + parent + "\nLink: " + link + "\n"
	  
	  link +"\n"
	}
}
