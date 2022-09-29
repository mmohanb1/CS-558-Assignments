import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Auto {
	private char[][] cipherArr;
	private int[][] indexArr;
	
	public Auto() {
		super();
		this.cipherArr = generateCipherArr();
		this.indexArr = generateIndexArr();

	}
	public static void main(String[] args) {
		Auto akc = new Auto();
		String inFileName = args[0];
		String outFileName = args[1];
		String origText = "";
		try {
            FileReader r = new FileReader(inFileName);
            BufferedReader br = new BufferedReader(r);
 
            String line; 
            while ((line = br.readLine()) != null)                
                origText = line;
            
            r.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		String key = args[2];
		
		int type = Integer.parseInt(args[3]);
		String contentToWrite = "";
		if(type == 1) //encrypt
		{
			contentToWrite = akc.encrypt(key, origText);
		}
		else if(type == 0) //decrypt
		{
			contentToWrite = akc.decrypt(key, origText);
		}
		try {
            FileWriter w = new FileWriter(outFileName, true);
            BufferedWriter bw = new BufferedWriter(w); 
            bw.write(contentToWrite);
    
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
