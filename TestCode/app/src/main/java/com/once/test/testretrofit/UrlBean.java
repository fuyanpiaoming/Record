package com.once.test.testretrofit;

public class UrlBean {
    private String msg;
    private String errorCode;
    private boolean successed;
    private Robot robot;
    private Server server;


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

    public boolean isSuccessed() {
        return successed;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }



    public static class Robot{
        //近程登录使用的url地址
        public String http_url;
        //websocket配置的地址
        public String websocket_url;

        public void setHttp_url(String http_url) {
            this.http_url = http_url;
        }

        public String getHttp_url() {
            return http_url;
        }

        public void setWebsocket_url(String websocket_url) {
            this.websocket_url = websocket_url;
        }

        public String getWebsocket_url() {
            return websocket_url;
        }
    }



    public static class Server{
        //Mqtt服务器地址
        public String mqtt_url;
        //远程登录使用的url地址
        public String http_url;

        public void setHttp_url(String http_url) {
            this.http_url = http_url;
        }

        public String getHttp_url() {
            return http_url;
        }

        public void setMqtt_url(String mqtt_url) {
            this.mqtt_url = mqtt_url;
        }

        public String getMqtt_url() {
            return mqtt_url;
        }
    }

    @Override
    public String toString(){
        return "ServerUr:msg=" + msg + "\n" +
                "errorCode=" + errorCode + "\n" +
                "successed=" + successed + "\n" +
                "Robot.http_url=" + robot.http_url + "\n" +
                "Robot.websocket_url=" + robot.websocket_url + "\n" +
                "Server.http_url=" + server.http_url + "\n" +
                "Server.mqtt_url=" + server.mqtt_url + "\n" +
                "";
    }

}
