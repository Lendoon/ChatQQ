package com.qq.client.model;

import com.qq.common.*;

public class QqClientUser {
   public int checkUser( User u){
   return new QqClientConServer().SendLoginInfoToServer(u);
   }
}
