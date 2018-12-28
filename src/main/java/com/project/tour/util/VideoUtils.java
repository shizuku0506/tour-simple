package com.project.tour.util;

import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class VideoUtils
{
    @Value("${project.ffprobe.run}")
    private String ffprobePath;

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
            probeResult = initFFmpeg(mediaPath);
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
            probeResult = initFFmpeg(mediaPath);
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
    private FFmpegProbeResult initFFmpeg(String mediaPath) throws IOException
    {
        FFprobe ffprobe = new FFprobe(ffprobePath); ;
        return ffprobe.probe(mediaPath);
    }

}
