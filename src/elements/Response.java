package elements;

import java.nio.charset.StandardCharsets;

public class Response {
    private final String version = "HTTP/1.0";
    private int code;
    private String message;
    private String contentType = "text/html";
    private int contentLength;
    private final String separator = "\n";
    private String body;

    public Response(int code, String message, String body, Request req) {
        this.code = code;
        this.message = message;
        this.body = body;
        if(req != null){
            this.contentType = req.getContentType();
        }
    }

    /**
     * Return the request as a byte array.
     * @return
     */
    public byte[] toBytes(){
        return toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(version).append(" ").append(code).append(" ").append(message).append("\n");
        builder.append("Content-Type: ").append(contentType).append("\n");
        builder.append("Content-Length: ").append(body.length()).append("\n");
        builder.append(separator);
        builder.append(body).append("\n");

        return builder.toString();
    }
}
