package elements;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Request {
    private final Method method;
    private final String resourcePath;
    private final String version;
    private final String contentType;


    public Request(String reqString) {
        System.out.println(reqString);
        String[] linedReq = reqString.split("\n");

        String[] firstLine = linedReq[0].split("\s");
        this.method = Method.valueOf(firstLine[0]);
        this.resourcePath = firstLine[1];
        this.version = firstLine[2];

        Map<String, String> headerMap = new HashMap<>();
        for(String line : linedReq){
            String[] part = line.split(":");
            if(part.length > 1) {
                headerMap.put(part[0], part[1]);
            }
        }

        contentType = headerMap.get("Accept").split(",")[0];
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

    public String getContentType() {
        return contentType;
    }
}
