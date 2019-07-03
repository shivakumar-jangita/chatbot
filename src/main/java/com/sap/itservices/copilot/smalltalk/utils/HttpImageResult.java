package com.sap.itservices.copilot.smalltalk.utils;

import java.nio.ByteBuffer;

/**
 * Created by Georgiana Copil (georgiana.copil@sap.com) on 16/11/2018.
 */

public  class HttpImageResult {
    private ByteBuffer byteBuffer;
    private String mediaType;

    public HttpImageResult(ByteBuffer byteBuffer, String mediaType){
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