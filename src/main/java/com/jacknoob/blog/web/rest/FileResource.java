package com.jacknoob.blog.web.rest;

import com.jacknoob.blog.entity.File;
import com.jacknoob.blog.service.FileService;
import com.jacknoob.blog.web.response.Page;
import com.jacknoob.blog.web.response.PageResponse;
import com.jacknoob.blog.web.response.RestResponse;
import com.jacknoob.blog.web.util.ResponseUtils;
import com.jacknoob.blog.web.util.ServiceExecuteResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author JackJun
 * 09/09/2019 18:52
 * Life is not just about survival.
 */
@RestController
@RequestMapping("/api/file")
public class FileResource {

    private final FileService fileService;

    public FileResource(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    @ApiOperation("获取文件列表")
    public ResponseEntity getFileList(Pageable page) {
        Page<File> res = ResponseUtils.pageHelperEntity2RespPage(fileService.getFileList(page));
        return ResponseEntity.ok(PageResponse.getResp("获取成功！", res));
    }

    @GetMapping("/type")
    @ApiOperation("根据类型获取文件")
    public ResponseEntity getFileList(Pageable page, @RequestParam("type") Integer type) {
        Page<File> res = ResponseUtils.pageHelperEntity2RespPage(fileService.getFileList(page, type));
        return ResponseEntity.ok(PageResponse.getResp("获取成功！", res));
    }

    @PostMapping
    @ApiOperation("添加记录")
    public ResponseEntity addFileRecord(@RequestBody File file) {
        ServiceExecuteResult result = fileService.save(file);
        return new ResponseEntity<>(RestResponse.getResp(result.getMessage()), result.getHttpStatusCode());
    }

    @PostMapping("/upload")
    @ApiOperation("文件上传接口")
    public ResponseEntity uploadInterface(MultipartFile file) {
        ServiceExecuteResult result = fileService.upload(file);
        return new ResponseEntity<>(RestResponse.getResp(result.getMessage(), result.getData()), result.getHttpStatusCode());
    }

    @GetMapping("/download")
    @ApiOperation("文件下载接口")
    public ResponseEntity<Resource> download(@RequestParam("key") String key, HttpServletRequest request) throws IOException {
        ServiceExecuteResult<FileSystemResource> result = fileService.loadFileAsResource(key);
        if (result.isSuccess()) {
            FileSystemResource resource = result.getData();
            String contentType;
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(new InputStreamResource(resource.getInputStream()));
        }
        return new ResponseEntity<>(null, result.getHttpStatusCode());
    }

}
