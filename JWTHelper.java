package com.ursdentist.utils;

import java.security.Key;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JWTHelper {
	Key secretKey = MacProvider.generateKey();
	
	public String createSignedToken(String id, String issuer, String subject, long ttlMillis) {
		String compactJws = Jwts.builder()
				  .setSubject(subject)
				  .setId(id)
				  .setIssuer(issuer)
				  .signWith(SignatureAlgorithm.HS512, secretKey)
				  .compact();
		return compactJws;
	}

	
	public boolean verifySignedToken(String jwt) {
		try {
			 Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
			 String id = claims.getHeader().getKeyId();
			 //System.out.println(id);
		    //OK, we can trust this JWT
		    return true;

		} catch (SignatureException e) {
		    //don't trust the JWT!
			return false;
		}
		
	}
	
	public static void main(String args[]){
		int idx = 10;

		String[] tks = new String[idx];
		
		JWTHelper helper = new JWTHelper();
		for(int i = 0; i < idx; i++){
			String s = helper.createSignedToken("id" + i, "issuer" + i, "subject" + i, 1000);
			System.out.println(s);
			tks[i] = s;
		}
		
		for(int i = 0; i < idx; i++){
			System.out.println(helper.verifySignedToken(tks[i]));
		}
	}
}
