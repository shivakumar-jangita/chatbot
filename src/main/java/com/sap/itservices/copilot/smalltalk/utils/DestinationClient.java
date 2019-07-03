package com.sap.itservices.copilot.smalltalk.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import javax.annotation.PreDestroy;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.sap.cloud.account.TenantContext;
import com.sap.core.connectivity.api.authentication.AuthenticationHeader;
import com.sap.core.connectivity.api.authentication.AuthenticationHeaderProvider;
import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import com.sap.security.um.user.UserProvider;

@Service
public class DestinationClient {
    final public static String RECAST_DESTINATION = "RECAST";
    final public static String ISP_DESTINATION = "PGD";
    final public static String NO_DESTINATION = "NO_DESTINATION";
    final public static String TRACKING_DESTINATION = "TRACKING_DESTINATION";
    final public static String INTEGRATION_MOCKUP="INTEGRATION_MOCKUP";
    final public static String NORMAL_MODE="NORMAL_MODE";
    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationClient.class);
    private CloseableHttpClient httpClientISP;
    private CloseableHttpClient httpClientISPBasic;

    private CloseableHttpClient httpClientNormal;
    private String locationId;
    private String sapClient;
    private DestinationConfiguration ispDestination;
    private DestinationConfiguration ispDestinationBasic;

    private DestinationConfiguration trackingShallWeDestination;
    private String consumerAccount;
    private AuthenticationHeaderProvider authenticationHeaderProvider;
    private DestinationConfiguration applicationConfiguration;
    private String recastURL;
    private String recastToken;
//    private String ticketDestinationURL;
//    private DestinationConfiguration ticketDestination;
    private UserProvider userProvider;
    private int maxRetries = 4;
    private String mode ;

    private String ticketCreationPath="";

    public DestinationClient() {
        try {
            Context ctx = new InitialContext();
            ConnectivityConfiguration configuration = (ConnectivityConfiguration) ctx
                    .lookup("java:comp/env/connectivityConfiguration");
            TenantContext tenantContext = (TenantContext) ctx.lookup("java:comp/env/tenantContext");

            authenticationHeaderProvider = (AuthenticationHeaderProvider) ctx.lookup("java:comp/env/authHeaderProvider");
            applicationConfiguration = configuration.getConfiguration("int_pc");

            userProvider = (UserProvider) ctx.lookup("java:comp/env/user/Provider");
            String ispDestinationName = applicationConfiguration.getProperty("isp_destination");
            
            
            
            /*try {
                String ispDestinationBasicName = applicationConfiguration.getProperty("isp_destination_basic");
                ispDestinationBasic = configuration.getConfiguration(ispDestinationBasicName);
            }catch (Exception e){
                LOGGER.debug("No basic auth available");
            }*/
                String trackingDestinationName = applicationConfiguration.getProperty("tracking_destination");

            recastURL = applicationConfiguration.getProperty("recast_url");
            recastToken = applicationConfiguration.getProperty("recast_token");
            try {
                mode = applicationConfiguration.getProperty("mode");
                if (mode==null || !mode.toLowerCase().contains("mockup")){
                    mode=NORMAL_MODE;
                }else{
                    mode=INTEGRATION_MOCKUP;
                }
            }catch (Exception e){
                mode=NORMAL_MODE;
            }
            ispDestination = configuration.getConfiguration(ispDestinationName);
            System.err.println("ispDestinationName>>>>>>>>>"+ispDestinationName);
           trackingShallWeDestination = configuration.getConfiguration(trackingDestinationName);
           System.err.println("trackingShallWeDestination>>>>>>>>>"+trackingShallWeDestination);
           // ticketCreationPath = applicationConfiguration.getProperty("ticket_path");

            consumerAccount = tenantContext.getTenant().getAccount().getId();


        String proxyHost = System.getenv("HC_OP_HTTP_PROXY_HOST");
        int proxyPort = Integer.parseInt(System.getenv("HC_OP_HTTP_PROXY_PORT"));


        locationId = ispDestination.getProperty("CloudConnectorLocationId");
        HttpHost host = new HttpHost(proxyHost, proxyPort);
        System.out.println("host<<<<<<"+host);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(20000)
                .setConnectTimeout(20000)
                .setConnectionRequestTimeout(20000)
                .setStaleConnectionCheckEnabled(true)
                .build();

        httpClientISP = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .setRetryHandler(new HttpRequestRetryHandler() {
                    @Override
                    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                        return executionCount <= maxRetries &&
                                exception instanceof SocketException;
                    }
                })
                .setServiceUnavailableRetryStrategy(new ServiceUnavailableRetryStrategy() {
                    @Override
                    public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
                        return executionCount <= maxRetries &&
                                response.getStatusLine().getStatusCode() == HttpStatus.SC_SERVICE_UNAVAILABLE;
                    }

                    @Override
                    public long getRetryInterval() {
                        return 100;
                    }
                }).setProxy(host).build();

        httpClientISPBasic = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .setRetryHandler(new HttpRequestRetryHandler() {
                    @Override
                    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                        return executionCount <= maxRetries &&
                                exception instanceof SocketException;
                    }
                })
                .setServiceUnavailableRetryStrategy(new ServiceUnavailableRetryStrategy() {
                    @Override
                    public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
                        return executionCount <= maxRetries &&
                                response.getStatusLine().getStatusCode() == HttpStatus.SC_SERVICE_UNAVAILABLE;
                    }

                    @Override
                    public long getRetryInterval() {
                        return 100;
                    }
                }).setProxy(host).build();

        httpClientNormal = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .setRetryHandler(new HttpRequestRetryHandler() {
                    @Override
                    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                        return executionCount <= maxRetries &&
                                exception instanceof SocketException;
                    }
                })
                .setServiceUnavailableRetryStrategy(new ServiceUnavailableRetryStrategy() {
                    @Override
                    public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
                        return executionCount <= maxRetries &&
                                response.getStatusLine().getStatusCode() == HttpStatus.SC_SERVICE_UNAVAILABLE;
                    }

                    @Override
                    public long getRetryInterval() {
                        return 100;
                    }
                }).build();
        } catch (Exception e) {
           LOGGER.error("Failed getting the destinations.");
        }

    }


    @PreDestroy
    public void closeClients() {
        try {
            httpClientNormal.close();
            httpClientISP.close();
            httpClientISPBasic.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getMode(){
    	mode="normal";
    	return mode;
    	}
    public String getApplicationProperty(String property){
        try{
            return applicationConfiguration.getProperty(property);
        }catch (Exception e){
            LOGGER.error("Failed getting "+property+" from application configuration.");
            return "";
        }
        };

    public String getTicketCreationPath() {
        return ticketCreationPath;
    }

    public String postRequest(String reqPath, String body){
        String token = getPPToken(reqPath);
        if (token == null){

            LOGGER.error("Failed getting token from the SSF backend.");

            return "Failed connecting to SSF backend.";
        }else{
            return postRequestToIsp(reqPath,body,token);
        }
    }


    private String postRequestToIsp(String reqPath, String body, String token){
        final String baseUrl = ispDestinationBasic.getProperty("URL").toString().replace("https","http") ;
        LOGGER.error("URL is "+baseUrl+ reqPath);
        try {
            Context ctx = new InitialContext();

            userProvider = (UserProvider) ctx.lookup("java:comp/env/user/Provider");



        }catch (Exception e){
            e.printStackTrace();
        }
        HttpPost request = new HttpPost(baseUrl+ reqPath);
        StringEntity entity = null;
        try {
            entity = new StringEntity(body);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        request.setEntity(entity);

        request.setHeader("Content-Type", "application/json");
        request.setHeader("x-csrf-token",token);
        request.setHeader("Accept","application/json");
        String user = ispDestinationBasic.getProperty("User");
        String password =ispDestinationBasic.getProperty("Password");
        request.setHeader("Authorization","Basic "+ Base64.encodeBase64((user+":"+password).getBytes()));

        request.setEntity(entity);
        try {
            HttpResponse response =   httpClientISPBasic.execute(request);
            String respBody = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(respBody);
            for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                JSONObject dres = (JSONObject) jsonObject.get(key);

                return "Created ticket with id: "+dres.get("ObjectId");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            request.releaseConnection();
        }
        return null;
    }

    private String getToken(String reqPath){
        final String baseUrl = ispDestination.getProperty("URL").toString().replace("https","http") ;
        LOGGER.error("URL is "+baseUrl+ reqPath);
        try {
            Context ctx = new InitialContext();

            userProvider = (UserProvider) ctx.lookup("java:comp/env/user/Provider");



        }catch (Exception e){
            e.printStackTrace();
        }
        HttpGet request = new HttpGet(baseUrl+ reqPath);
        request.setHeader("x-csrf-token","fetch");
        request.setHeader("SAP-Connectivity-ConsumerAccount", consumerAccount);
        String user = ispDestinationBasic.getProperty("User");
        String password =ispDestinationBasic.getProperty("Password");




        request.setHeader("Authorization","Basic "+ Base64.encodeBase64((user+":"+password).getBytes()));
        try {
            HttpResponse response =   httpClientISPBasic.execute(request);
            Header[] headers=response.getAllHeaders();
            for (Header header:headers){
                if (header.getName().equalsIgnoreCase("x-csrf-token")){
                    return header.getValue();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.releaseConnection();
        }
        return null;
    }
    private String getPPToken(String reqPath){
        final String baseUrl = ispDestinationBasic.getProperty("URL").toString().replace("https","http") ;
        LOGGER.error("URL is "+baseUrl+ reqPath);
        try {
            Context ctx = new InitialContext();

            userProvider = (UserProvider) ctx.lookup("java:comp/env/user/Provider");



        }catch (Exception e){
            e.printStackTrace();
        }
        HttpGet request = new HttpGet(baseUrl+ reqPath);
        applyDefaultHeaders(request);
        request.setHeader("x-csrf-token","fetch");
       request.setHeader("SAP-Connectivity-ConsumerAccount", consumerAccount);
//        request.setHeader("Content-Type","application/json");
        try {
            HttpResponse response =   httpClientISPBasic.execute(request);
            LOGGER.error("Response is "+ response.getStatusLine());
            LOGGER.error("Resp is "+IOUtils.toString(response.getEntity().getContent()));
            Header[] headers=response.getAllHeaders();
            for (Header header:headers){
                if (header.getName().equalsIgnoreCase("x-csrf-token")){
                    return header.getValue();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.releaseConnection();
        }
        return null;
    }

    public String getFromISP(String reqPath) {
        final String baseUrl = ispDestination.getProperty("URL").toString().replace("https","http") ;
        LOGGER.error("URL is "+baseUrl+ reqPath);
        System.err.println("URL is "+baseUrl+ reqPath);
       try {
           Context ctx = new InitialContext();

           userProvider = (UserProvider) ctx.lookup("java:comp/env/user/Provider");



       }catch (Exception e){
           e.printStackTrace();
       }
        HttpGet request = new HttpGet(baseUrl+ reqPath);
        applyDefaultHeaders(request);
        try {
           HttpResponse response =   httpClientISP.execute(request);
            String respBody = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            System.out.println("ISP responded with "+respBody);
            return respBody;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.releaseConnection();
        }
        return null;
    }


    public String getStringFromUrl(String url) {
        HttpGet request = new HttpGet(url);


        try {
            HttpResponse response= httpClientNormal.execute(request);
            String respBody = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            LOGGER.error("Received response " + respBody);
            return respBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpImageResult getImageFromUrl(String url, long MAX_SIZE_OF_IMAGE) {
        HttpGet request = new HttpGet(url);


        try {
            HttpResponse response= httpClientNormal.execute(request);
            String contentType = response.getEntity().getContentType().getName();

            byte[] imageBytes = IOUtils.toByteArray(response.getEntity().getContent());

            long sizeOfImage = imageBytes.length;

            if (sizeOfImage > MAX_SIZE_OF_IMAGE) {
                double ratio =(double)MAX_SIZE_OF_IMAGE / sizeOfImage;
                ratio = Math.floor(ratio * 10) / 10;
                imageBytes = GifImageUtils.resize(imageBytes, ratio);
            }
            HttpImageResult imageResult = new HttpImageResult(ByteBuffer.wrap(imageBytes),contentType);
            return imageResult;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.releaseConnection();
        }
        return null;
    }
    public String postToRecastDestination(String message, String reqPath) {
        HttpPost request = new HttpPost(recastURL + reqPath);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Token " + recastToken);
        StringEntity entity = null;
        try {
            entity = new StringEntity(message);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        LOGGER.debug("Sending " + message + " to " + request.getURI().toString());
        request.setEntity(entity);
        try {
            HttpResponse response= httpClientNormal.execute(request);
            String respBody = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            LOGGER.debug("Received response " + respBody);
            return respBody;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.releaseConnection();
        }
        return null;
    }

    public String postToTrackingDestination(String message, String reqPath) {
        String requestUrl = trackingShallWeDestination.getProperty("URL") + reqPath;
        HttpPost request = new HttpPost(requestUrl);
        request.setHeader("Content-Type", "application/json");
        AuthenticationHeader apptoAppSSOHeader = authenticationHeaderProvider.getApptoAppSSOHeader(requestUrl, trackingShallWeDestination);
        request.setHeader(apptoAppSSOHeader.getName(), apptoAppSSOHeader.getValue());

        StringEntity entity = null;
        try {
            entity = new StringEntity(message);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        request.setEntity(entity);
        try {
            HttpResponse response =httpClientNormal.execute(request);
            String respBody = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            return respBody;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.releaseConnection();
        }
        return null;
    }


    private String buildUrlISP(String baseUrl, String reqPath) {

        final URIBuilder builder;
        try {
            builder = new URIBuilder(baseUrl.replace("https","http"));

            builder.setPath(builder.getPath() + reqPath);

            return builder.build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void applyDefaultHeaders(HttpGet request) {
        final AuthenticationHeader principalPropagationHeader = authenticationHeaderProvider.getPrincipalPropagationHeader();

        try{
            LOGGER.error("Principal propagation assertion authorization"+principalPropagationHeader.getName() + "- "+principalPropagationHeader.getValue());
        }catch (Exception e){
            LOGGER.error("Failed getting pp authorization "+e.getMessage());
        }
        request.setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        request.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);


        if (locationId != null && locationId != "") {
            request.setHeader("SAP-Connectivity-SCC-Location_ID", locationId);
        }
        request.setHeader("SAP-Connectivity-ConsumerAccount", consumerAccount);
        request.addHeader(principalPropagationHeader.getName(), principalPropagationHeader.getValue());

    }

    private void applyDefaultHeaders(HttpPost request) {
        final AuthenticationHeader principalPropagationHeader = authenticationHeaderProvider.getPrincipalPropagationHeader();

        try{
            LOGGER.debug("Principal propagation assertion authorization"+principalPropagationHeader.getName() + "- "+principalPropagationHeader.getValue());
        }catch (Exception e){
            LOGGER.error("Failed getting pp authorization "+e.getMessage());
        }
        request.setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        request.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);


        if (locationId != null && locationId != "") {
            request.setHeader("SAP-Connectivity-SCC-Location_ID", locationId);
        }
        request.setHeader("SAP-Connectivity-ConsumerAccount", consumerAccount);
        request.addHeader(principalPropagationHeader.getName(), principalPropagationHeader.getValue());
    }


}

