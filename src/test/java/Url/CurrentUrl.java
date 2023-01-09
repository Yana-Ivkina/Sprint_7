package Url;

public class CurrentUrl {
    public static String baseUrl(TypeUrl typeUrl){
        switch (typeUrl){
            case Test_Url: return "http://qa-scooter.praktikum-services.ru";
            default: return null;
        }
    }
}
