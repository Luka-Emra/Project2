package DataObjects;

public class UserData {

    String first_name;
    String last_name;
    String phone_num;
    String password;
    String address;
    String email;
    String state;
    String city;
    String zip_code;

    public UserData(String first_name, String last_name, String phone_num, String password, String address, String email, String state, String city, String zip_code){
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.city = city;
        this.state = state;
        this.address = address;
        this.phone_num = phone_num;
        this.zip_code = zip_code;
        this.email = email;
    }

    public String getFirst_name(){
        return first_name;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getPassword(){
        return password;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone_num(){
        return phone_num;
    }
    public String getZip_code(){
        return zip_code;
    }
    public String getEmail(){ return email; }
}
