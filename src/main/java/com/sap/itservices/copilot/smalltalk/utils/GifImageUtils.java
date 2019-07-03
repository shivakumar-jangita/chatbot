package com.sap.itservices.copilot.smalltalk.utils;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GifImageUtils {

    

    
    private static BufferedImage resize(BufferedImage originalImage, int newW, int newH) throws IOException {
        return Thumbnails.of(originalImage).forceSize(newW, newH).asBufferedImage();
    }

    public static byte[] resize(byte[] originalImage, double ratio) throws IOException{

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        GifDecoder gifDecoder = new GifDecoder();
        AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(originalImage);

        gifDecoder.read(inputStream);
        int frameCount = gifDecoder.getFrameCount();
        int loopCount = gifDecoder.getLoopCount();
        gifEncoder.setRepeat(loopCount);
        gifEncoder.start(outputStream);
        
        int width = (int) (gifDecoder.getFrameSize().getWidth() * ratio);
        int height = (int) (gifDecoder.getFrameSize().getHeight() * ratio);

        for (int i = 0; i < frameCount; i++) {
            BufferedImage frame = gifDecoder.getFrame(i); // frame i
            int t = gifDecoder.getDelay(i);
            gifEncoder.setDelay(t);
            gifEncoder.addFrame(resize(frame, width, height));
        }
        gifEncoder.finish();

        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return outputStream.toByteArray();
        
    }
}
