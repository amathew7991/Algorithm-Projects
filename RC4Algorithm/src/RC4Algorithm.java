import java.util.*; 


public class RC4Algorithm {
	
	public static void main(String args[]) throws Exception{
		
		//Data and key
		byte[] k = "key".getBytes(); 
		byte[] test = "Test Message".getBytes(); 
		byte[] key = {(byte) 0x1A, (byte) 0x2B, (byte) 0x3C, (byte) 0x4D, (byte) 0x5E, (byte) 0x6F, (byte) 0x77};
		
		StringBuilder stringBuilder = new StringBuilder();
		
		Random random = new Random(System.currentTimeMillis());
		
		RC4Algorithm rc4 = new RC4Algorithm(key); 
		System.out.println("After initialization ");
		rc4.print();
		
		// Loop for 100 bytes of stream
		for(int i=0; i<100; i++)
		{
			stringBuilder.append((char)(random.nextInt(26) + 'a')); 
		}
		
		// Get bytes of a string
		byte[] val = stringBuilder.toString().getBytes();
		// Encrpyt string
		byte[] cipher = rc4.encrypt(val);
		System.out.println("100 bytes of keystream: ");
		rc4.print();
		
		stringBuilder = new StringBuilder();
		// Loop for 1000 bytes of stream
		for(int i=0; i<1000; i++)
		{
			stringBuilder.append((char)(random.nextInt(26) + 'a')); 
		}
		
		byte[] val1 = stringBuilder.toString().getBytes(); 
		rc4 = new RC4Algorithm(key); 
		// Encrpyt plaintext
		byte[] plain = rc4.encrypt(val1); 
		System.out.println("1000 bytes of keystream: "); 
		rc4.print(); 
 	}
	
	// Helper functions
	public void print(){
		
		System.out.format("%5s", " "); 
		
		for(int j=0; j<16; j++)
		{
			System.out.format("%5d", j); 
		}
		
		// Loop until end of S
		for(int i=0; i<S.length; i++) 
		{
			if(i%16 == 0)
			{
				System.out.format("\n%5d", 1/16); 
			}
			System.out.format("%5d", S[i]); 
		}
	}
	
	public RC4Algorithm(final byte[] key) {
		
		// Check key is correct length
		if(key.length < 1 || key.length > 256)
		{
			throw new IllegalArgumentException("key must be between 1 and 256 bytes");
		}
		else
		{
			keyLength = key.length; 
			for(int i=0; i<256; i++)
			{
				S[i] = (byte) i;
				T[i] = key[i%keyLength]; 
			}
			int j=0; 
			byte temp; 
			
			for(int i=0; i<256; i++)
			{
				j = (j + S[i] + T[i]) & 0xFF; 
				// Swap indices
				temp = S[j]; 
				S[j] = S[i]; 
				S[i] = temp; 
				
			}
		}
	}
	
	public byte[] encrypt(final byte[] plaintext) {
		
		final byte[] ciphertext = new byte[plaintext.length]; 
		int i = 0;
		int j = 0; 
		int k;
		int t;
		byte temp;
		
		for (int count=0; count<plaintext.length; count++)
		{
			i = (i+1) & 0xFF;
			j = (j + S[i]) & 0XFF; 
			
			// Swap
			temp = S[j];
			S[j] = S[i]; 
			S[i] = temp; 
			
			t = (S[i] + S[j]) & 0xFF; 
			k = S[t]; 
			
			ciphertext[count] = (byte)(plaintext[count]^k);
		}
		return ciphertext;
	}
	
	public byte[] decrypt(final byte[] ciphertext) {
		return encrypt(ciphertext);
	}

private final byte[] S = new byte[256]; 
private final byte[] T = new byte[256]; 
private int keyLength = 0; 


}















