package com.project.tour.util;

import com.project.tour.ProjectConstant;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@Slf4j
@Component
public class VideoUtils
{
    @Value("${project.ffprobe.run}")
    private String ffprobePath;

    @Value("${project.ffmpeg.run}")
    private String ffmpegPath;

    /**
     * 비디오 파일의 메타 정보를 가져온다
     *
     * @param mediaPath
     * @return
     */
    public FFmpegFormat getVideoFormat(String mediaPath)
    {
        FFprobe ffprobe = null;
        FFmpegProbeResult probeResult = null;
        FFmpegFormat format = null;
        try
        {
            probeResult = initFFprobe(mediaPath);
        } catch (IOException e)
        {
            log.error(e.getMessage());
            return null;
        }

        format = probeResult.getFormat();

        log.debug(String.format("%nFile: '%s' ; Format: '%s' ; Duration: %.3fs",
                format.filename,
                format.format_long_name,
                format.duration
        ));

        return format;
    }

    /**
     * 비디오 영상(스트림)정보를 가져온다
     *
     * @param mediaPath
     * @return
     */
    public FFmpegStream getVideoStream(String mediaPath)
    {
        FFprobe ffprobe = null;
        FFmpegProbeResult probeResult = null;
        FFmpegStream stream = null;

        try
        {
            probeResult = initFFprobe(mediaPath);
        } catch (IOException e)
        {
            log.error(e.getMessage());
            return null;
        }

        stream = probeResult.getStreams().get(0);

        log.debug(String.format("%nCodec: '%s' ; Width: %dpx ; Height: %dpx",
                stream.codec_long_name,
                stream.width,
                stream.height
        ));

        return stream;
    }

    /**
     * init FFmpeg
     *
     * @param mediaPath
     * @return
     * @throws IOException
     */
    private FFmpegProbeResult initFFprobe(String mediaPath) throws IOException
    {
        FFprobe ffprobe = new FFprobe(ffprobePath); ;
        return ffprobe.probe(mediaPath);
    }

    /**
     * 비디오에서 thumbnail 을 만든다
     *
     * @param videoFile
     * @throws IOException
     */
    public void makeThumbnail(File videoFile) throws IOException
    {
        String mimeType = Files.probeContentType(videoFile.toPath());
        String videoPath = videoFile.getCanonicalPath();
        String extensions = FilenameUtils.getExtension(videoFile.getName());

        log.debug("makeThumbnail");
        log.debug(mimeType);

        if (!StringUtils.startsWithIgnoreCase(mimeType, "video"))
        {
            throw new IOException("It's not video file - mimeType");
        }

        boolean isVideo = Arrays.stream(ProjectConstant.ALLOW_VIDEO_EXTENSIONS)
                .anyMatch(t -> t.equalsIgnoreCase(extensions));

        if (!isVideo)
        {
            throw new IOException(String.format("It's not allow video file - extension : %s", extensions));
        }

        String thumbnailPath = getThumbnailFullPath(videoPath);

        log.debug(thumbnailPath);

        File thumbnailFile = getThumbnailTargetPath(thumbnailPath);

        FFmpeg ffmpeg = new FFmpeg(ffmpegPath);

        FFmpegBuilder builder =
                new FFmpegBuilder()
                        .setInput(videoPath)
                        .addOutput(thumbnailPath)
                        .setFrames(1)
                        .setVideoFilter("select='gte(n\\,10)',scale=" + String.valueOf(ProjectConstant.THUMBNAIL_WIDTH) + ":-1")
                        .done();

        // excute command
        try
        {
            ffmpeg.run(builder);
        } catch (IOException e)
        {
            log.warn(e.getMessage());
            // make default thumbnail image ~ black
            makeDefaultThumbnail(thumbnailFile);
        }

    }

    private String getThumbnailFullPath(String videoPath)
    {
        return FilenameUtils.getFullPath(videoPath)
                + File.separator
                + ProjectConstant.THUMBNAIL_PREFIX
                + FilenameUtils.getBaseName(videoPath)
                + "."
                + ProjectConstant.THUMBNAIL_EXTENSION;
    }

    /**
     * 블랭크 썸네일을 만든다
     *
     * @param videoFile
     * @throws IOException
     */
    public void makeDefaultThumbnail(File videoFile) throws IOException
    {
        String videoPath = videoFile.getCanonicalPath();

        String thumbnailPath = getThumbnailFullPath(videoPath);

        log.debug(thumbnailPath);

        File thumbnailFile = getThumbnailTargetPath(thumbnailPath);

        Graphics g;
        BufferedImage image = new BufferedImage(ProjectConstant.THUMBNAIL_WIDTH,
                ProjectConstant.THUMBNAIL_WIDTH / 4 * 3,
                BufferedImage.TYPE_INT_RGB);
        g = image.createGraphics();  // not sure on this line, but this seems more right
        g.setColor(Color.black);
        g.fillRect(0, 0, ProjectConstant.THUMBNAIL_WIDTH,
                ProjectConstant.THUMBNAIL_WIDTH / 4 * 3); // give the whole image a white background
        g.setColor(Color.black);
        ImageIO.write(image, ProjectConstant.THUMBNAIL_EXTENSION, thumbnailFile);
    }

    private File getThumbnailTargetPath(String thumbnailPath)
    {
        File thumbnailFile = new File(thumbnailPath);

        if (thumbnailFile.exists())
        {
            thumbnailFile.delete();
        }
        return thumbnailFile;
    }

}
