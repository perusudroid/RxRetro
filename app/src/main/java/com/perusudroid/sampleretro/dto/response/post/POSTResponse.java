package com.perusudroid.sampleretro.dto.response.post;
/**
 * Awesome Pojo Generator
 * */
public class POSTResponse{
  private Data data;
  private Boolean success;
  private String message;
  public void setData(Data data){
   this.data=data;
  }
  public Data getData(){
   return data;
  }
  public void setSuccess(Boolean success){
   this.success=success;
  }
  public Boolean getSuccess(){
   return success;
  }
  public void setMessage(String message){
   this.message=message;
  }
  public String getMessage(){
   return message;
  }
}