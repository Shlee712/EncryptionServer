
class Encryption {
	
	private static String c1 (String str) {
		char[] cipher = str.toCharArray();
		int z = 1;
		for (int i = 0; i < cipher.length;i++) {
			if( ( (cipher[i] >= 65 && cipher[i] < 91) || (cipher[i] >= 97 && cipher[i] < 123) ) == false ) {
				continue;
			}
			if( z == 6) {
				z = 1;
			}
			
			
			
			if(z == 1 || z == 4) {
				if( cipher[i] >= 65 && cipher[i] <91) {
					//c is a capital letter
					if(cipher[i] + 5 >= 91) {
						cipher[i] = (char) ((cipher[i] + 5) - 26);
					}
					else {
						cipher[i] = (char) (cipher[i]+5);
					}
				}
				else if ( cipher[i] >= 97 && cipher[i] < 123) {
					//c is lowercase letter
					if ( cipher[i] + 5 >= 123) {
						cipher[i] = (char) ((cipher[i] +5) -26);
					}
					else {
						cipher[i] = (char) (cipher[i]+5);
					}
				}
			}
			else {
				if( cipher[i] >= 65 && cipher[i] <91) {
					//c is a capital letter
					if(cipher[i] + 19 >= 91) {
						cipher[i] = (char) ((cipher[i] +19) - 26);
					}
					else {
						cipher[i] = (char) (cipher[i]+19);
					}
				}
				else if ( cipher[i] >= 97 && cipher[i] < 123) {
					//c is lowercase letter
					if ( cipher[i] + 19 >= 123) {
						cipher[i] = (char) ((cipher[i] +19) -26);
					}
					else {
						cipher[i] = (char) (cipher[i]+19);
					}
				}
			}
			z++;
		}
		String encrypted = new String(cipher);
		return encrypted;
	}

	public static String encrypt (String msg) {
		msg = c1(msg);
		return msg;
		
	}
}
