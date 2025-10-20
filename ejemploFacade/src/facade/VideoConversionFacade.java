
package facade;

import java.io.File;
import some_complex_media_library.*;//importar todo del paquete

/**
 *
 * En esta clase simplemente pareciera algo sencillo ya que los procesos largos se hacen en cada clase
 */

public class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: conversion started.");
        VideoFile file = new VideoFile(fileName);// clase videofile
        Codec sourceCodec = CodecFactory.extract(file);//clase Codec
        Codec destinationCodec;
        if (format.equals("mp4")) {
            destinationCodec = new MPEG4CompressionCodec();//clase mpeg4CompressionCodec
        } else {
            destinationCodec = new OggCompressionCodec();//clase ogg
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);//clase bitrateReader
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);//Audiomixer
        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }
}