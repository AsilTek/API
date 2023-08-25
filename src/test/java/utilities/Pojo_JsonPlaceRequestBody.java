package utilities;

public class Pojo_JsonPlaceRequestBody {

    /*
{
        "title": "Ahmet",
        "body": "Merhaba",
        "userId": 10,
        "id": 70
    }
     */
    //1- Tum key degerleri class levelda private seklinde, value'sunin data type neyse ona gore olusturulur.
    private String title;
    private String body;
    private int userId;
    private int id;
    //2- tum variable larin getter setterlari hazirlanir.

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //3-tum parametreleri iceren const olusturulur.


    public Pojo_JsonPlaceRequestBody(String title, String body, int userId, int id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }

    //4- parametresiz default const olusturulur.
    Pojo_JsonPlaceRequestBody(){}

    //5- toString() methodu olusturulur

    @Override
    public String toString() {
        return "Pojo_JsonPlaceRequestBody{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
