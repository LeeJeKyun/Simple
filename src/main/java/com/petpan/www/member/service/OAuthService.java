package com.petpan.www.member.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@Service
public class OAuthService {
	
	public String getKakaoAccessToken (String code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqUrl = "https://kauth.kakao.com/oauth/token";
		
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=9e72a89439807704c06d91bdfb11064b"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:8888/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
            
            int responseCode = conn.getResponseCode();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while((line = br.readLine()) != null) {
            	result += line;
            }
            
            System.out.println("response body : " + result);
            
            Gson gson = new Gson();
            
            JsonElement je = gson.fromJson(result, JsonElement.class);
            
            access_Token = je.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = je.getAsJsonObject().get("refresh_token").getAsString();
            
            System.out.println("oauthService() access_Token : " + access_Token);
            System.out.println("oauthService() refresh_Token : " + refresh_Token);
            
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return access_Token;
	}

	public void bringKaKaoInfo(String access_Token) {
		
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		
		 URL url;
		try {
			url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			 conn.setRequestMethod("POST");
		     conn.setDoOutput(true);
		     conn.setRequestProperty("Authorization", "Bearer " + access_Token);
		     
		     //결과 코드가 200이라면 성공
		     int responseCode = conn.getResponseCode();
		     System.out.println("responseCode : " + responseCode);

		     BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		     String line = "";
		     String result = "";
		     
		     while ((line = br.readLine()) != null) {
		         result += line;
		     }
		     System.out.println("response body : " + result);
		     
		     Gson gson = new Gson();
	         
		     JsonElement je = gson.fromJson(result, JsonElement.class);
		     long id = je.getAsJsonObject().get("id").getAsLong();
		     String email = je.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
		     
		     System.out.println("kakaoIdNo : " + id);
		     System.out.println("kakoEmail : " + email);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getNaverAccessToken(String code) {
		
		String access_Token = "";
		String refresh_Token = "";
		String reqUrl = "https://nid.naver.com/oauth2.0/token";
		
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=a4rcAlMYkSCWs6VhXPqs"); //
            sb.append("&client_secret=afIVUM3uAJ"); // 
            sb.append("&state=9kgsGTfH4j7IyAkg");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
            
//            int responseCode = conn.getResponseCode();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while((line = br.readLine()) != null) {
            	result += line;
            }
            
            System.out.println("response body : " + result);
            
            Gson gson = new Gson();
            
            JsonElement je = gson.fromJson(result, JsonElement.class);
            
            access_Token = je.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = je.getAsJsonObject().get("refresh_token").getAsString();
            
            System.out.println("oauthService() access_Token : " + access_Token);
            System.out.println("oauthService() refresh_Token : " + refresh_Token);
            
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return access_Token;
		
	}

	public void bringNaverInfo(String access_Token) {
		
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		
		 URL url;
		try {
			url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			 conn.setRequestMethod("POST");
		     conn.setDoOutput(true);
		     conn.setRequestProperty("Authorization", "Bearer " + access_Token);
		     
		     //결과 코드가 200이라면 성공
		     int responseCode = conn.getResponseCode();
		     System.out.println("responseCode : " + responseCode);

		     BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		     String line = "";
		     String result = "";
		     
		     while ((line = br.readLine()) != null) {
		         result += line;
		     }
		     System.out.println("response body : " + result);
		     
		     Gson gson = new Gson();
//	         
		     JsonElement je = gson.fromJson(result, JsonElement.class);
		     String email = je.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
		     String id = je.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();
		     
		     System.out.println("naverId : " + id);
		     System.out.println("naverEmail : " + email);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
