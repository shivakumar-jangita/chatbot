package com.sap.itservices.copilot.smalltalk.services;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.itservices.copilot.smalltalk.utils.GifImageUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import sun.awt.image.GifImageDecoder;

import java.io.IOException;
import java.nio.ByteBuffer;


@Service
public class HttpImageFetcherService {

    private Cache<String, HttpImageResult> imageCache;

    private static final long MAX_SIZE_OF_IMAGE = 4005304l;

    public HttpImageFetcherService(){
        imageCache = new Cache2kBuilder<String , HttpImageResult>(){}
                .name("imageCaches")
                .eternal(true)
                .entryCapacity(20)
                .build();

    }
    
    public HttpImageResult getImage(String url) throws IOException{
        HttpImageResult image;
        if (imageCache.containsKey(url)){
            image = imageCache.get(url);
        } else{
            image = getImageFromInternet(url);

            imageCache.put(url, image);
        }
        return image;
    }
                                                          
    
    
    private HttpImageResult getImageFromInternet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        String contentType = response.header("Content-Type");

        byte[] imageBytes = response.body().bytes();

        long sizeOfImage = imageBytes.length;

        if (sizeOfImage > MAX_SIZE_OF_IMAGE) {
            double ratio =(double)MAX_SIZE_OF_IMAGE / sizeOfImage;
            ratio = Math.floor(ratio * 10) / 10;
            imageBytes = GifImageUtils.resize(imageBytes, ratio);
        }

        HttpImageResult imageResult =
                new HttpImageResult(ByteBuffer.wrap(imageBytes),contentType);
        

        return imageResult;
    }

    

    public class HttpImageResult {
        private ByteBuffer byteBuffer;
        private String mediaType;

        private HttpImageResult(ByteBuffer byteBuffer, String mediaType){
            this.byteBuffer = byteBuffer;
            this.mediaType = mediaType;
        }

        public ByteBuffer getByteBuffer() {
            return byteBuffer;
        }

        public String getMediaType() {
            return mediaType;
        }
    }
    
    
}
