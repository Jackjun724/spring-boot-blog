package com.jacknoob.blog.web.rest;

import com.jacknoob.blog.service.ManageService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author JackJun
 * 2019/7/30 16:02
 * Life is just about survival.
 */
@RestController
@RequestMapping("/api/manage")
public class ManagerResource {
    private final ManageService manageService;

    public ManagerResource(ManageService manageService) {
        this.manageService = manageService;
    }

    @GetMapping
    @ApiParam("仪表盘")
    public ResponseEntity dashboard() {
        return ResponseEntity.ok(manageService.getDashboardData());
    }
}
