package com.relevante.vjsantojaca.twitter.search;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.co.tomkdickinson.twitter.search.InvalidQueryException;
import uk.co.tomkdickinson.twitter.search.Tweet;
import uk.co.tomkdickinson.twitter.search.TwitterSearch;


public class Search extends TwitterSearch
{
	private static Scanner scanner;

	@Override
	public boolean saveTweets(List<Tweet> tweets) 
	{
		Logger.getLogger("Search").log(Level.INFO, "Obtenemos los tweets: " + new Date());
		if( tweets != null ) {
			for( Tweet tweet : tweets ) {
				System.out.println(tweet.toString());
			}
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) 
	{
		TwitterSearch twitterSearch = new Search();
		String query = "";
        try 
        {
        	System.out.println("Introduzca las keywords separados por un espacio.");
        	scanner = new Scanner (System.in);
        	String keywordsIn = scanner.nextLine ();
        	String[] keywordsArray = keywordsIn.split(" ");
        	
        	for( int i = 0; i < keywordsArray.length; i++ ) {
        		String keyword = keywordsArray[i];
        		if( keywordsArray.length == 1) {
        			query = keyword;
        		} else if( (i + 1) < keywordsArray.length ) {
            		String keywordNext = keywordsArray[i+1];
        			if (!query.equals("") ) {
        				query = query + " OR ";
        			}
        			query = query + "" + keyword + " AND " + keywordNext;
        		}
        	}
        	Logger.getLogger("Search").log(Level.INFO, "La query es la siguiente: " + query);
        	
			Logger.getLogger("Search").log(Level.INFO, "Comenzamos con la ejecución de la query: " + new Date());
			twitterSearch.search(query, 2000, null);
			Logger.getLogger("Search").log(Level.INFO, "Terminamos con la ejecución: " + new Date());
		} catch (InvalidQueryException e) {
			Logger.getLogger("Search").log(Level.SEVERE, "Tenemos un problema con la query.");
			e.printStackTrace();
		}
	}
}