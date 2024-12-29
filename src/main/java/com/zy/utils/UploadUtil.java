package com.zy.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadUtil {
    // 允许的文件类型
    private static final String[] ALLOWED_TYPES = {"jpg", "jpeg", "png", "gif"};
    // 最大文件大小 (10MB)
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    /**
     * 处理文件上传
     * @param request HTTP请求
     * @param uploadType 上传类型（user/product）
     * @param newFileName 新文件名（不包含扩展名）
     * @return Map包含表单数据和文件名
     */
    public static Map<String, String> uploadFile(HttpServletRequest request, String uploadType, String newFileName)
            throws Exception {

        Map<String, String> resultMap = new HashMap<>();

        // 1. 创建DiskFileItemFactory对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置缓冲区大小
        factory.setSizeThreshold(1024 * 1024);
        // 设置临时文件目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // 2. 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置文件大小限制
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 3. 解析请求
        List<FileItem> items = upload.parseRequest(request);

        // 4. 处理上传的文件和表单数据
        String fileName = "";
        for (FileItem item : items) {
            if (item.isFormField()) {
                // 处理普通表单字段
                resultMap.put(item.getFieldName(), item.getString("UTF-8"));
            } else {
                // 处理文件上传
                if (item.getSize() > 0) {
                    // 获取文件扩展名
                    String fileExt = getFileExtension(item.getName());
                    // 验证文件类型
                    if (!isAllowedFileType(fileExt)) {
                        throw new ServletException("不支持的文件类型");
                    }

                    // 构建新文件名
                    fileName = newFileName + "." + fileExt;

                    // 确保上传目录存在
                    String uploadPath = request.getServletContext().getRealPath("/upload/" + uploadType);
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    // 保存文件
                    File file = new File(uploadDir, fileName);
                    item.write(file);
                }
            }
        }

        // 将文件名添加到结果Map
        resultMap.put("fileName", fileName);
        return resultMap;
    }

    /**
     * 获取文件扩展名
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 验证文件类型
     */
    private static boolean isAllowedFileType(String fileExt) {
        for (String type : ALLOWED_TYPES) {
            if (type.equals(fileExt.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除文件
     */
    public static boolean deleteFile(HttpServletRequest request, String uploadType, String fileName) {
        String filePath = request.getServletContext().getRealPath("/upload/" + uploadType + "/" + fileName);
        File file = new File(filePath);
        return file.exists() && file.delete();
    }
}