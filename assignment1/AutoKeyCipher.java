package com.ics.assignments;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;



public class AutoKeyCipher {
	private char[][] cipherArr;
	private int[][] indexArr;
	
	public AutoKeyCipher() {
		super();
		this.cipherArr = generateCipherArr();
		this.indexArr = generateIndexArr();
//		for(int i =0;i< indexArr.length; i++)
//		{
//			for(int j=0; j<indexArr[0].length; j++)
//				System.out.print(indexArr[i][j]+" ");
//			System.out.println();
//		}
	}
	public static void main(String[] args) {
		AutoKeyCipher akc = new AutoKeyCipher();
		String origText = "backinthedaysweusedtochill";
		String key = "uahfiluawbhdlfawghdfkjawghj";
		String incry = akc.encrypt(key, origText);
		System.out.println("original text = "+origText+", key = "+key);
		System.out.println("incrypted text = "+incry);
		System.out.println("decypted text = "+akc.decrypt(key, incry));
	}
	private int[][] generateIndexArr()
	{
		int[][] cipherArr = new int[26][26];
		Deque<Integer> dq = new LinkedList<>();
		for(int i=0; i<26; i++)
			dq.addLast(i);
				
		for(int i=0; i<26; i++)
		{
			int j=0;
				Iterator<Integer> itr = dq.iterator();
				while(itr.hasNext())
				{
					cipherArr[i][j++] = itr.next();
				}
			int first = dq.removeLast();
			dq.addFirst(first);
		}
		return cipherArr;
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

	private String decrypt(String key, String encryptedText)
	{
		StringBuilder decryptedText = new StringBuilder("");
		StringBuilder keySeries = new StringBuilder(key);
		
		for(int i=0; i<encryptedText.length(); i++)
		{
			char currKeyLetter = keySeries.charAt(i);
			char currEncyptedLetter = encryptedText.charAt(i);
			int row = currKeyLetter-'a';
			int col = indexArr[row][currEncyptedLetter-'A'];
		//	System.out.println("row = "+row+", col = "+col);
			char currDecryptedLetter = (char)(col+97);
		//	System.out.println(currDecryptedLetter);
			decryptedText.append(currDecryptedLetter);
			keySeries.append(currDecryptedLetter);
		}
		return decryptedText.toString();
	}

	private String encrypt(String key, String plainText)
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

