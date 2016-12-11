package me.Markyroson.MarkyCraft.lib;

public class Books {
	/**
	 * Books
	 * @author Markyroson
	 *
	 */
	public static enum BookType
	{
		WEB_BOOK
	}
	/**
	 * Pages of Web Book
	 * @author Markyroson
	 *
	 */
	public static enum Web_BookPages
	{
		PAGE1,
		PAGE2,
		PAGE3,
		PAGE4
	}
	
	/**
	 * Get page from book
	 * @author Markyroson
	 * @param book Book to get page from
	 * @param page Page to get
	 * @return Page contents
	 */
	public static String getBookPage(BookType book, Web_BookPages page)
	{
		if(book == BookType.WEB_BOOK)
		{
			switch(page)
			{
				case PAGE1:
					return "Hello! This is the book of web addresses!";
				case PAGE2:
					return "http://www.youtube.com/Markyroson";
				case PAGE3:
					return "http://www.twitter.com/Markyroson";
				case PAGE4:
					return "http://www.facebook.com/Markyroson";
				default:
					return "Page not found";
			}
		}
		return null;
	}
	/**
	 * Get book author(s)
	 * @author Markyroson
	 * @param book Book to get author(s) of
	 * @return Author(s)
	 */
	public static String getAuthor(BookType book)
	{
		if(book == BookType.WEB_BOOK)
		{
			return "The Server";
		}
		return null;
	}
	/**
	 * Get book title
	 * @author Markyroson
	 * @param book Book to get title of
	 * @return Book title
	 */
	public static String getTitle(BookType book)
	{
		if(book == BookType.WEB_BOOK)
		{
			return "Websites";
		}
		return null;
	}
}	//End of file
