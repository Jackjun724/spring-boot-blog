package com.jacknoob.blog.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jacknoob.blog.common.Constants;
import com.jacknoob.blog.common.FileType;
import com.jacknoob.blog.entity.File;
import com.jacknoob.blog.entity.FileMapping;
import com.jacknoob.blog.mapper.FileMapper;
import com.jacknoob.blog.mapper.FileMappingMapper;
import com.jacknoob.blog.web.util.ServiceExecuteResult;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

/**
 * @author JackJun
 * 09/09/2019 18:56
 * Life is not just about survival.
 */
@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileMapper fileMapper;
    private final FileMappingMapper fileMappingMapper;

    public FileService(FileMapper fileMapper, FileMappingMapper fileMappingMapper) {
        this.fileMapper = fileMapper;
        this.fileMappingMapper = fileMappingMapper;
    }

    public Page<File> getFileList(@NotNull Pageable page) {
        Page<File> pageHelperEntity = PageHelper.startPage(page.getPageNumber(), page.getPageSize(), true);
        fileMapper.getFileList();
        return pageHelperEntity;
    }

    public Page<File> getFileList(@NotNull Pageable page, Integer type) {
        Page<File> pageHelperEntity = PageHelper.startPage(page.getPageNumber(), page.getPageSize(), true);
        fileMapper.getFileListByType(type);
        return pageHelperEntity;
    }

    public ServiceExecuteResult upload(@NotNull MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return ServiceExecuteResult.createFailResult("文件名错误！不能为空！");
        }
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + fileExtensionName;
        String path = Constants.GLOBAL_UPLOAD_PATH;
        logger.info("Upload File [{}] to [{}].", fileName, path);

        java.io.File fireDir = new java.io.File(path, fileName);

        try {
            file.transferTo(fireDir);
        } catch (IOException io) {
            io.printStackTrace();
            logger.error(io.toString());
            return ServiceExecuteResult.createFailResult("上传文件失败！IO错误！");
        }
        FileMapping mapping = new FileMapping(fireDir.getAbsolutePath(), uuid);
        fileMappingMapper.insert(mapping);

        return ServiceExecuteResult.createSuccessResult("上传成功！", uuid);
    }

    public ServiceExecuteResult save(File file) {
        if (file.getStatus() == null || !FileType.validType(file.getStatus())) {
            return ServiceExecuteResult.createFailResult("新增失败！文件类型错误！");
        }
        fileMapper.insert(file);
        return ServiceExecuteResult.createSuccessResult("新增成功！");
    }

    @SuppressWarnings("all")
    public ServiceExecuteResult<String> getUrl(String key) {
        String path = fileMappingMapper.getPathByKey(key);
        return ServiceExecuteResult.createSuccessResult("获取成功!", path);
    }

    @SuppressWarnings("all")
    public ServiceExecuteResult<FileSystemResource> loadFileAsResource(String key) throws MalformedURLException {
        String path = fileMappingMapper.getPathByKey(key);
        if (StringUtils.isEmpty(path)) {
            return ServiceExecuteResult.createFailResult("获取失败！未找到文件！");
        }
        FileSystemResource resource = new FileSystemResource(path);
        if (resource.exists()) {
            return ServiceExecuteResult.createSuccessResult("获取成功!", resource);
        }
        return ServiceExecuteResult.createFailResult("获取失败！未找到文件！");
    }
}
