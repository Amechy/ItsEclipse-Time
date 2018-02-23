package cifradoAes;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


public class CifradoAes {
	private static String cifrado = "AES";
	
	public static SecretKey obtenerClaveOpaca(int longitud) throws Exception{
		
		KeyGenerator claveInstancia = KeyGenerator.getInstance(cifrado);
		claveInstancia.init(longitud);//por defecto es 128 bits
		return claveInstancia.generateKey();
	}
	
	public static SecretKeySpec obtenerClaveTransparente(String miClave) throws Exception{
		byte[] miClaveEnBytes = miClave.getBytes("utf-8");		
		System.out.println("El hash SHA2 de la clave es: " + DigestUtils.sha256Hex(miClaveEnBytes));
		byte[] miClaveSha256 = Arrays.copyOf(DigestUtils.sha256(miClaveEnBytes),16);
		
		return new SecretKeySpec(miClaveSha256, cifrado);
	}
	public static String encriptar(String mensaje, SecretKey clave) throws Exception{
		Cipher cipher = Cipher.getInstance(cifrado);
		cipher.init(Cipher.ENCRYPT_MODE, clave);
		byte[] encVal = cipher.doFinal(mensaje.getBytes("utf-8"));
		
		byte[] criptogramaEnBytes = Base64.encodeBase64(encVal);
		
		return new String(criptogramaEnBytes);
		
	}
	
	public static String desencriptar(String criptograma, SecretKey clave) throws Exception{
		Cipher cipher = Cipher.getInstance(cifrado);
		cipher.init(Cipher.DECRYPT_MODE, clave);
		
		byte[] decrypVal = Base64.decodeBase64(criptograma.getBytes("utf-8"));
		byte[] decryptedValue = cipher.doFinal(decrypVal);
		
		
		
		return new String(decryptedValue);
		
	}
	
	
	public static void main(String[] args) {
		String mensaje ="Vaya melón que tiene cicerdón un viernes por la tarde";
		String miClave = "123;78";
		
		try {
			SecretKey miClaveOpaca = CifradoAes.obtenerClaveOpaca(128);
			System.out.println("Mensaje en claro: "+mensaje);
			String criptograma = CifradoAes.encriptar(mensaje,miClaveOpaca);
			System.out.println("Criptograma: "+criptograma);
			System.out.println("");
			System.out.println("Desencriptando: "+CifradoAes.desencriptar(criptograma, miClaveOpaca));
			
			
			SecretKeySpec claveT = CifradoAes.obtenerClaveTransparente(miClave);
			criptograma = CifradoAes.encriptar(mensaje,claveT);
			System.out.println("Criptograma: "+criptograma);
			
			
			System.out.println("Desencriptando: "+CifradoAes.desencriptar(criptograma,claveT));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
