package utilities;

public class BaseUrl {

    public static String reqresInUsers(){

        return "https://reqres.in/api/users";
    }

    public static String reqresInUserId(int id){


        return "https://reqres.in/api/users/"+id;
    }

    public static String herokuappUserId(int id){

        return " https://restful-booker.herokuapp.com/booking/"+id;
    }
    public static String createBookingUrl() {

        return "https://restful-booker.herokuapp.com/booking";
    }

    public static String createBooking() {
        String url = "https://restful-booker.herokuapp.com/booking";
        return url;
    }
}
