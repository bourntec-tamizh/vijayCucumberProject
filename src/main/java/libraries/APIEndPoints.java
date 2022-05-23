package libraries;

public enum APIEndPoints {

    UsersAPI("/api/users"),
    newAPI(""),
    newAPI2("")
    ;

    private  final String resource;

    APIEndPoints(String resource)
    {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

}
