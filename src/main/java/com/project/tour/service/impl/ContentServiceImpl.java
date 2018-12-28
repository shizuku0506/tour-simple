package com.project.tour.service.impl;

import com.project.tour.ProjectConstant;
import com.project.tour.domain.Content;
import com.project.tour.domain.Storage;
import com.project.tour.exception.InternalServerException;
import com.project.tour.mapper.ContentMapper;
import com.project.tour.service.ContentService;
import com.project.tour.util.ProjectUtils;
import com.project.tour.util.VideoUtils;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ContentServiceImpl implements ContentService
{
    @Resource
    private ContentMapper contentMapper;

    @Value("${project.storage.path}")
    private String defFilePath;

    @Autowired
    private VideoUtils videoUtils;

    @Override
    public ResponseEntity<Content> addContent(Content content)
    {
        // 1. 파일 메터데이터 삽입
        if (contentMapper.insertContent(content) != 1)
        {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // 2. 파일 업로드
        try
        {
            uploadFile(content);
        } catch (IOException e)
        {
            log.error(e.getMessage());
            throw new InternalServerException();
        }

        // 3. 파일 메타정보 삽입
        if (CollectionUtils.isNotEmpty(content.getStorageList()))
        {
            contentMapper.insertStorage(content.getStorageList());
        }

        Content resultContent = contentMapper.selectContent(content).get(0);

        return new ResponseEntity(resultContent, HttpStatus.CREATED);
    }

    private void uploadFile(Content content) throws IOException
    {
        List<Storage> storageList = new ArrayList<>();

        if (content.getFiles() != null)
        {
            File uFile = null;
            String folder = ProjectUtils.getDate(ProjectConstant.DEF_DATE_FORMAT);
            String destPath = defFilePath + File.separatorChar + folder;
            Storage storage = null;

            for (MultipartFile multipartFile : content.getFiles())
            {
                String fileName = FilenameUtils.getBaseName(multipartFile.getOriginalFilename());
                String rFileName = String.valueOf(System.nanoTime());
                String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

                uFile = new File(destPath);
                if (!uFile.exists())
                {
                    uFile.mkdir();
                }

                uFile = new File(destPath, rFileName + "." + ext);

                // excute upload
                multipartFile.transferTo(uFile);

                storage = new Storage();
                storage.setConSeq(content.getSeq());
                storage.setFileSize(multipartFile.getSize());
                storage.setFilePath(destPath);
                storage.setFileNm(fileName);
                storage.setRealFileNm(rFileName);
                storage.setFileExt(ext);
                if (StringUtils.startsWith(multipartFile.getContentType(), "video"))
                {
                    FFmpegFormat format = videoUtils.getVideoFormat(uFile.getCanonicalPath());
                    if (format == null)
                    {
                        // error!
                        storage.setRunningSec(0);
                        storage.setFileStatus(ProjectConstant.ENCODING_STATUS.init.getCode());
                    } else
                    {
                        storage.setRunningSec((long) format.duration);
                        storage.setFileStatus(ProjectConstant.ENCODING_STATUS.complate.getCode());
                    }
                } else
                {
                    // image or etc files
                    storage.setRunningSec(0);
                    storage.setFileStatus(ProjectConstant.ENCODING_STATUS.complate.getCode());
                }
                storage.setMimeTp(multipartFile.getContentType());

                storageList.add(storage);

            }
        }
        content.setStorageList(storageList);
    }


    @Override
    public ResponseEntity<List<Content>> getAllContent(String lang)
    {
        lang = ProjectUtils.getAccpetLanguage(lang);

        Content content = new Content();
        content.setLang(lang);
        List<Content> contentList = contentMapper.selectContent(content);

        if (CollectionUtils.isEmpty(contentList))
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(contentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Content> getContent(int seq)
    {
        Content content = new Content();
        content.setSeq(seq);

        List<Content> contentList = contentMapper.selectContent(content);

        return new ResponseEntity(contentList.get(0), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Content> editContent(Content content)
    {
        return null;
    }

    @Override
    public ResponseEntity<Content> removeContent(int seq)
    {
        return null;
    }

}
