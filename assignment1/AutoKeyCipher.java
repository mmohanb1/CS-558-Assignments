package com.ics.assignments;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class AutoKeyCipher {
	private char[][] cipherArr;
	
	public AutoKeyCipher() {
		super();
		this.cipherArr = generateCipherArr();
	}
	private char[][] generateCipherArr()
	{
		char[][] cipherArr = new char[26][26];
		Deque<Character> dq = new LinkedList<>();
		for(int i=65; i<65+26; i++)
			dq.addLast((char)i);
				
		for(int i=0; i<26; i++)
		{
			int j=0;
				Iterator<Character> itr = dq.iterator();
				while(itr.hasNext())
				{
					cipherArr[i][j++] = itr.next();
				}
			char first = dq.removeFirst();
			dq.addLast(first);
		}
		return cipherArr;
	}
	public static void main(String[] args) {
		AutoKeyCipher akc = new AutoKeyCipher();
		System.out.println(akc.encrypt("deceptive", "wearediscoveredsaveyourself"));
	}
	String decrypt(String key, String decryptedText)
	{
		for(int i=0; i<decryptedText.length(); i++)
		{
			
		}
		return "";
	}
	String encrypt(String key, String plainText)
	{
		StringBuilder keySeries = new StringBuilder(key);
		keySeries.append(plainText);
		StringBuilder encryptedText = new StringBuilder("");
		for(int i=0; i<plainText.length(); i++)
		{
			char textLetter = plainText.charAt(i);
			char keyLetter = keySeries.charAt(i);
			char encryptedLetter = this.cipherArr[keyLetter-'a'][textLetter-'a'];
			encryptedText.append(encryptedLetter);			
		}
		
		return encryptedText.toString();
	}
}

