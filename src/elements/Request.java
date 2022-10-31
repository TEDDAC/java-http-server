package elements;

public class Request {
    private final Method method;
    private final String resourcePath;
    private final String version;
    //private final String contentType;


    public Request(String reqString) {
        System.out.println(reqString);
        String[] splitReq = reqString.split("\s");
        this.method = Method.valueOf(splitReq[0]);
        this.resourcePath = splitReq[1];
        this.version = splitReq[2];
    }

    public Method getMethod() {
        return method;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getVersion() {
        return version;
    }
}
