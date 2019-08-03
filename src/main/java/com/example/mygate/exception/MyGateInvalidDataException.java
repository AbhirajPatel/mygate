package com.example.mygate.exception;

public class MyGateInvalidDataException
        extends Exception
{
   public MyGateInvalidDataException(String message){
       super(message);
   }

    public MyGateInvalidDataException(String message, Throwable cause){
        super(message, cause);
    }

    public MyGateInvalidDataException(Throwable cause){
        super(cause);
    }

    public MyGateInvalidDataException(){
        super();
    }

}
