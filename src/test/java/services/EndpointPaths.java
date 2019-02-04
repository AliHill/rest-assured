package services;

public class EndpointPaths extends BaseUrl
{
        public static String Get_Bookings = Base_Url + "/booking";
        public static String Get_Booking_By_Id = Base_Url + "/booking/{bookingId}";

        public static String Create_Booking = Base_Url + "/booking";
}