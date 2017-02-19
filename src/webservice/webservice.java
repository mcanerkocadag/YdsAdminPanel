package webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.ExamModel;
import models.ListExamModel;
import models.QuestionModel;
import models.StatisticsModel;
import models.UserModel;

public class webservice {

	private static String url="http://192.168.137.141:9090/donemprojesi";
	
	//----------------User-----------------------------
	
	public static boolean deleteUser(String iduser)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/users/deluser");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
        nameValuePair.add(new BasicNameValuePair("iduser", iduser));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//Kay�t Ba�ar�s�z
                	return false;
                }else {
                	//Kay�t Ba�ar�l�
                	return true;
                }
                
            }
        } catch (IOException | JSONException e) {
            //Mesaj(e.getMessage());
        }
		
		return false;
	}
	
	@SuppressWarnings("null")
	public static List<UserModel> listUsers(){
		
		 JSONArray jsonArray = null;
		 List<UserModel> modelList = new ArrayList<>();
		 HttpClient httpclient = new DefaultHttpClient();

         HttpPost httppost = new HttpPost(url+"/users/getallusers");

         HttpResponse response;
         try {
        	 
             response = httpclient.execute(httppost);
             
             HttpEntity entity = response.getEntity();

             if (entity != null) {
                 InputStream instream = entity.getContent();
                 String result = convertStreamToString(instream);

                 jsonArray = new JSONArray(result);

                 instream.close();
             }
             
             for(int x=0; x<jsonArray.length(); x++){
                 UserModel model = new UserModel();
                 model.setIduser(jsonArray.getJSONObject(x).getString("iduser").toString());
                 model.setUsername(jsonArray.getJSONObject(x).getString("username").toString());
                 model.setPassword(jsonArray.getJSONObject(x).getString("password").toString());
                 model.setEmail(jsonArray.getJSONObject(x).getString("email").toString());
                 model.setStatus(jsonArray.getJSONObject(x).getString("status").toString());
                 
                 modelList.add(model);
             }
             

         } catch (IOException e) {
             //Mesaj(e.getMessage());
         } catch (JSONException e) {
             //Mesaj(e.getMessage());
         }
         
		 return modelList;
		 
	}
	
	public static boolean registerUser(String username,String password,String email)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/users/register");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("username", username));
        nameValuePair.add(new BasicNameValuePair("password", password));
        nameValuePair.add(new BasicNameValuePair("email", email));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//Kay�t Ba�ar�s�z
                	return false;
                }else {
                	//Kay�t Ba�ar�l�
                	
                	return true;
                }
                
            }
        } catch (IOException | JSONException e) {
            //Mesaj(e.getMessage());
        }
        return false;
	}
	
	public static boolean updateUser(String iduser,String username,String password,String email,String status)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/users/updateuser");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
		 nameValuePair.add(new BasicNameValuePair("iduser", iduser));
        nameValuePair.add(new BasicNameValuePair("username", username));
        nameValuePair.add(new BasicNameValuePair("password", password));
        nameValuePair.add(new BasicNameValuePair("email", email));
		 nameValuePair.add(new BasicNameValuePair("status", status));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//G�ncelleme Ba�ar�s�z
                	return false;
                }else {
                	//G�ncelleme Ba�ar�l�
                	return true;
                }
                
            }
        } catch (IOException | JSONException e) {
            //Mesaj(e.getMessage());
        }
        return false;
	}
	
	
	private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
	
	//--------------------Question-------------------------------------------
	
	public static boolean deleteQuestion(String idquestion)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/questions/delquestion");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
        nameValuePair.add(new BasicNameValuePair("idquestion", idquestion));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//Kay�t Ba�ar�s�z
                	return false;
                }else {
                	//Kay�t Ba�ar�l�
                	return true;
                }
                
            }
        } catch (IOException | JSONException e) {
            //Mesaj(e.getMessage());
        }
		
		return false;
	}
	
	public static boolean registerQuestion(String a,String answer,String b,String c,String content,String d,String e,String question,String questionType)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/questions/addquestion");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(9);
        nameValuePair.add(new BasicNameValuePair("a", a));
        nameValuePair.add(new BasicNameValuePair("answer", answer));
        nameValuePair.add(new BasicNameValuePair("b", b));
		nameValuePair.add(new BasicNameValuePair("c", c));
		nameValuePair.add(new BasicNameValuePair("content", content));
        nameValuePair.add(new BasicNameValuePair("d", d));
        nameValuePair.add(new BasicNameValuePair("e", e));
        nameValuePair.add(new BasicNameValuePair("question", question));
        nameValuePair.add(new BasicNameValuePair("questionType", ""+questionType));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//Kay�t Ba�ar�s�z
                	return false;
                }else {
                	//Kay�t Ba�ar�l�
                	return true;
                }
                
            }
        } catch (IOException | JSONException e1) {
            //Mesaj(e.getMessage());
        }
        return false;
	}
	@SuppressWarnings("null")
	public static List<QuestionModel> listQuestions(){
		
		 JSONArray jsonArray = null;
		 List<QuestionModel> modelList = new ArrayList<>();
		 HttpClient httpclient = new DefaultHttpClient();

         HttpPost httppost = new HttpPost(url+"/questions/getallquestions");

         HttpResponse response;
         try {
        	 
             response = httpclient.execute(httppost);
             
             HttpEntity entity = response.getEntity();

             if (entity != null) {
                 InputStream instream = entity.getContent();
                 String result = convertStreamToString(instream);

                 jsonArray = new JSONArray(result);

                 instream.close();
             }
             
             for(int x=0; x<jsonArray.length(); x++){
                 QuestionModel model = new QuestionModel();
                 model.setIdquestion(jsonArray.getJSONObject(x).getString("idquestion").toString());
                 model.setA(jsonArray.getJSONObject(x).getString("a").toString());
                 model.setAnswer(jsonArray.getJSONObject(x).getString("answer").toString());
                 model.setB(jsonArray.getJSONObject(x).getString("b").toString());
                 model.setC(jsonArray.getJSONObject(x).getString("c").toString());
                 model.setContent(jsonArray.getJSONObject(x).getString("content").toString());
                 model.setD(jsonArray.getJSONObject(x).getString("d").toString());
                 model.setE(jsonArray.getJSONObject(x).getString("e").toString());
                 model.setQuestion(jsonArray.getJSONObject(x).getString("question").toString());
                 if(jsonArray.getJSONObject(x).getString("questionType").equals("1"))
  	    	   {
  	    		   model.setQuestionType("Kelime Bilgisi");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("2"))
  	    	   {
  	    		   model.setQuestionType("Dil Bilgisi");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("3"))
  	    	   {
  	    		   model.setQuestionType("Cloze Test");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("4"))
  	    	   {
  	    		   model.setQuestionType("Cümle Tamamlama");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("5"))
  	    	   {
  	    		   model.setQuestionType("İngilizce-Türkçe Çeviri");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("6"))
  	    	   {
  	    		   model.setQuestionType("Türkçe-İngilizce Çeviri");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("7"))
  	    	   {
  	    		   model.setQuestionType("Okuma Parçaları");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("8"))
  	    	   {
  	    		   model.setQuestionType("Diyalog Tamamlama");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("9"))
  	    	   {
  	    		   model.setQuestionType("Anlamca En Yakın Cümleyi Bulma");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("10"))
  	    	   {
  	    		   model.setQuestionType("Paragraf Tamamlama");
  	    	   }
  	    	   else if(jsonArray.getJSONObject(x).getString("questionType").equals("11"))
  	    	   {
  	    		   model.setQuestionType("Anlam Bütünlüğünü Bozan Cümle");
  	    	   }
                 //model.setQuestionType(jsonArray.getJSONObject(x).getString("questionType"));
                 model.setStatus(jsonArray.getJSONObject(x).getString("status").toString());
                 
                 modelList.add(model);
             }
             

         } catch (IOException e) {
             //Mesaj(e.getMessage());
         } catch (JSONException e) {
             //Mesaj(e.getMessage());
         }
         
		 return modelList;
		 
	}
	
	
	public static boolean updateQuestion(String idquestion,String a,String answer,String b,String c,String content,String d,String e,String question,String questionType,String status)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/questions/updatequestion");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(11);
		 nameValuePair.add(new BasicNameValuePair("idquestion", idquestion));
        nameValuePair.add(new BasicNameValuePair("a", a));
        nameValuePair.add(new BasicNameValuePair("answer", answer));
        nameValuePair.add(new BasicNameValuePair("b", b));
		 nameValuePair.add(new BasicNameValuePair("c", c));
		 nameValuePair.add(new BasicNameValuePair("content", content));
		 nameValuePair.add(new BasicNameValuePair("d", d));
		 nameValuePair.add(new BasicNameValuePair("e", e));
		 nameValuePair.add(new BasicNameValuePair("question", question));
		 nameValuePair.add(new BasicNameValuePair("questionType", questionType));
		 nameValuePair.add(new BasicNameValuePair("status", status));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//G�ncelleme Ba�ar�s�z
                	return false;
                }else {
                	//G�ncelleme Ba�ar�l�
                	return true;
                }
                
            }
        } catch (IOException | JSONException e1) {
            //Mesaj(e.getMessage());
        }
        return false;
	}
	
	//-------------------------Exam----------------------------
	
	public static boolean deleteExam(String idexam)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/exams/delexam");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
        nameValuePair.add(new BasicNameValuePair("idexam", idexam));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//Silme ��lemi Ba�ar�s�z
                	return false;
                }else {
                	//Silme ��lemi Ba�ar�l�
                	return true;
                }
                
            }
        } catch (IOException | JSONException e) {
            //Mesaj(e.getMessage());
        }
		
		return false;
	}
	
	
	public static boolean registerExam(String name,String question,String status)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/exams/create");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("name", name));
        nameValuePair.add(new BasicNameValuePair("counts", question));
        nameValuePair.add(new BasicNameValuePair("status", status));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//Kay�t Ba�ar�s�z
                	return false;
                }else {
                	//Kay�t Ba�ar�l�
                	
                	return true;
                }
                
            }
        } catch (IOException | JSONException e) {
            //Mesaj(e.getMessage());
        }
        return false;
	}
	
	public static boolean updateExam(String idexam,String name,String questions,String status)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/exams/updateexam");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(4);
		 nameValuePair.add(new BasicNameValuePair("idexam", idexam));
        nameValuePair.add(new BasicNameValuePair("name", name));
        nameValuePair.add(new BasicNameValuePair("counts", questions));
        nameValuePair.add(new BasicNameValuePair("status", status));
        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                
                	return false;
                }else {
                	
                	return true;
                }
                
            }
        } catch (IOException | JSONException e1) {
            //Mesaj(e.getMessage());
        }
        return false;
	}
	
	
	@SuppressWarnings("null")
	public static List<ExamModel> listExam(){
		
		 JSONArray jsonArray = null;
		 List<ExamModel> modelList = new ArrayList<>();
		 HttpClient httpclient = new DefaultHttpClient();

         HttpPost httppost = new HttpPost(url+"/exams/getallexams");

         HttpResponse response;
         try {
        	 
             response = httpclient.execute(httppost);
             
             HttpEntity entity = response.getEntity();

             if (entity != null) {
                 InputStream instream = entity.getContent();
                 String result = convertStreamToString(instream);

                 jsonArray = new JSONArray(result);

                 instream.close();
             }
             
             for(int x=0; x<jsonArray.length(); x++){
                 ExamModel model = new ExamModel();
                 model.setIdexam(jsonArray.getJSONObject(x).getString("idexam").toString());
                 model.setName(jsonArray.getJSONObject(x).getString("name").toString());
                 model.setQuestions(jsonArray.getJSONObject(x).getString("questions").toString());
                 model.setStatus(jsonArray.getJSONObject(x).getString("status").toString());
                 
                 modelList.add(model);
             }
             

         } catch (IOException e) {
             //Mesaj(e.getMessage());
         } catch (JSONException e) {
             //Mesaj(e.getMessage());
         }
         
		 return modelList;
		 
	}
	@SuppressWarnings("null")
	public static List<ListExamModel> listExam2(){
		
		 JSONArray jsonArray = null;
		 List<ListExamModel> modelList = new ArrayList<>();
		 HttpClient httpclient = new DefaultHttpClient();
         HttpPost httppost = new HttpPost(url+"/exams/getallexamsmodel2");

         HttpResponse response;
         try {
        	 
             response = httpclient.execute(httppost);
             
             HttpEntity entity = response.getEntity();

             if (entity != null) {
                 InputStream instream = entity.getContent();
                 String result = convertStreamToString(instream);

                 jsonArray = new JSONArray(result);

                 instream.close();
             }
             String dummy;
             LocalDateTime date;
             String lastDate;
             for(int x=0; x<jsonArray.length(); x++){
            	 
                 ListExamModel model = new ListExamModel();
                 model.setIdexam(jsonArray.getJSONObject(x).getString("idexam").toString());
                 model.setName(jsonArray.getJSONObject(x).getString("name").toString());
                 model.setKelimeBilgisi(jsonArray.getJSONObject(x).getString("kelimeBilgisi").toString());
                 model.setDilBilgisi(jsonArray.getJSONObject(x).getString("dilBilgisi").toString());
                 model.setClozeTest(jsonArray.getJSONObject(x).getString("clozeTest").toString());
                 model.setCumleTam(jsonArray.getJSONObject(x).getString("cumleTam").toString());
                 model.setIngTur(jsonArray.getJSONObject(x).getString("ingTur").toString());
                 model.setTurIng(jsonArray.getJSONObject(x).getString("turIng").toString());
                 model.setOkumaParca(jsonArray.getJSONObject(x).getString("okumaParca").toString());
                 model.setDiyalogTam(jsonArray.getJSONObject(x).getString("diyalogTam").toString());
                 model.setEnYakin(jsonArray.getJSONObject(x).getString("enYakin").toString());
                 model.setParagrafTam(jsonArray.getJSONObject(x).getString("paragrafTam").toString());
                 model.setAnlamBozan(jsonArray.getJSONObject(x).getString("anlamBozan").toString());
                // model.setStatus(jsonArray.getJSONObject(x).getString("status").toString());
                 dummy=jsonArray.getJSONObject(x).getString("status").toString();
                 date=LocalDateTime.parse(dummy);
                 lastDate=date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear()+" ";
                 if(date.getHour()<10)
                 {
                	 lastDate+="0";
                 }
                 lastDate+=date.getHour()+":";
                 if(date.getMinute()<10)
                 {
                	 lastDate+="0";
                 }
                 lastDate+=date.getMinute();
                 model.setStatus(lastDate);
                 Date dummyDate = new Date(date.getYear()-1900, date.getMonthValue()-1, date.getDayOfMonth(),date.getHour(), date.getMinute());
                 model.setListDate(dummyDate);
                 
                 modelList.add(model);
             }
             

         } catch (IOException e) {
             //Mesaj(e.getMessage());
         } catch (JSONException e) {
             //Mesaj(e.getMessage());
         }
         
		 return modelList;
		 
	}
	
	
	
	//----------------------------Statistics----------------------------------
	
	public static boolean deleteStatistic(String idstatistics)
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/statistics/delstatistics");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
        nameValuePair.add(new BasicNameValuePair("idstatistics", idstatistics));

        HttpResponse response;
 
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//Silme Ba�ar�s�z
                	return false;
                }else {
                	//Silme Ba�ar�l�
                	return true;
                }
                
            }
        } catch (IOException | JSONException e) {
            //Mesaj(e.getMessage());
        }
		
		return false;
	}
	
	
	public static boolean resetStatistic()
	{
		JSONObject jsonObject = null;
    	HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url+"/statistics/resetstatistics");


        HttpResponse response;
 
        try {
           
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                jsonObject = new JSONObject(result);

                instream.close();
                
                String returnValue;
                returnValue = jsonObject.getString("return").toString();

                if(!returnValue.equals("success")){
                	//Silme Ba�ar�s�z
                	return false;
                }else {
                	//Silme Ba�ar�l�
                	return true;
                }
                
            }
        } catch (IOException | JSONException e) {
            //Mesaj(e.getMessage());
        }
		
		return false;
	}
	
	@SuppressWarnings("null")
	public static List<StatisticsModel> adminlistStatistics(){
		
		 JSONArray jsonArray = null;
		 List<StatisticsModel> modelList = new ArrayList<>();
		 HttpClient httpclient = new DefaultHttpClient();

         HttpPost httppost = new HttpPost(url+"/statistics/getallstatistics");

         HttpResponse response;
         try {
        	 
             response = httpclient.execute(httppost);
             
             HttpEntity entity = response.getEntity();

             if (entity != null) {
                 InputStream instream = entity.getContent();
                 String result = convertStreamToString(instream);

                 jsonArray = new JSONArray(result);

                 instream.close();
             }
             
             for(int x=0; x<jsonArray.length(); x++){
                 StatisticsModel model = new StatisticsModel();
                 model.setIdstatistics(jsonArray.getJSONObject(x).getString("idstatistics").toString());
                 model.setUsername(jsonArray.getJSONObject(x).getString("username").toString());
                 model.setExamname(jsonArray.getJSONObject(x).getString("examname").toString());
                 model.setDogru(jsonArray.getJSONObject(x).getString("dogru").toString());
                 model.setStatus(jsonArray.getJSONObject(x).getString("status").toString());
                 
                 modelList.add(model);
             }
             

         } catch (IOException e) {
             //Mesaj(e.getMessage());
         } catch (JSONException e) {
             //Mesaj(e.getMessage());
         }
         
		 return modelList;
		 
	}
	@SuppressWarnings("null")
	public static List<StatisticsModel> topStatistics(){
		
		 JSONArray jsonArray = null;
		 List<StatisticsModel> modelList = new ArrayList<>();
		 HttpClient httpclient = new DefaultHttpClient();

         HttpPost httppost = new HttpPost(url+"/statistics/listall");

         HttpResponse response;
         try {
        	 
             response = httpclient.execute(httppost);
             
             HttpEntity entity = response.getEntity();

             if (entity != null) {
                 InputStream instream = entity.getContent();
                 String result = convertStreamToString(instream);

                 jsonArray = new JSONArray(result);

                 instream.close();
             }
             
             for(int x=0; x<jsonArray.length(); x++){
                 StatisticsModel model = new StatisticsModel();
                 model.setIdstatistics(jsonArray.getJSONObject(x).getString("idstatistics").toString());
                 model.setUsername(jsonArray.getJSONObject(x).getString("username").toString());
                 model.setExamname(jsonArray.getJSONObject(x).getString("examname").toString());
                 model.setDogru(jsonArray.getJSONObject(x).getString("dogru").toString());
                 model.setStatus(jsonArray.getJSONObject(x).getString("status").toString());
                 
                 modelList.add(model);
             }
             

         } catch (IOException e) {
             //Mesaj(e.getMessage());
         } catch (JSONException e) {
             //Mesaj(e.getMessage());
         }
         
		 return modelList;
		 
	}
	
}
