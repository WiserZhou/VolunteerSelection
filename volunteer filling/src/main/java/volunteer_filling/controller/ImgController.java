package volunteer_filling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import volunteer_filling.entity.Img;
import volunteer_filling.mapper.ImgMapper;

@CrossOrigin
@RestController
@RequestMapping("/img")
public class ImgController {

  private final ImgMapper imgMapper;
  private final ObjectMapper mapper = new ObjectMapper();

  public ImgController(ImgMapper imgMapper) {
    this.imgMapper = imgMapper;
  }

  @GetMapping("/hello")
  public String root() {
    return "/hello.html";
  }

  @PostMapping("/uploadImg")
  public String uploadImg(
      @RequestParam("fileName") MultipartFile file, @RequestParam("owner") String owner)
      throws IOException {

    String pathRepeat = imgMapper.getPath(owner);
    if (pathRepeat != null) {
      File file1 = new File(pathRepeat);

      if (file1.exists()) {
        if (file1.delete()) {
          System.out.println("文件删除成功");
        } else {
          System.out.println("文件删除失败");
        }
      } else {
        System.out.println("文件不存在");
      }
      imgMapper.deleteData(owner);
    }

    String result; // 上传结果信息
    Map<String, Object> map = new HashMap<String, Object>();

    if (file.getSize() / 1000 > 500) {
      result = "图片大小不能超过500KB";
    } else {
      // 判断上传文件格式
      String fileType = file.getContentType();
      assert fileType != null;
      if (fileType.equals("image/jpeg")
          || fileType.equals("image/png")
          || fileType.equals("image/jpg")) {
        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件后缀名
        assert fileName != null;
        int index = fileName.lastIndexOf(".");
        String suffixName;
        if (index > 0) {
          suffixName = fileName.substring(fileName.lastIndexOf("."));
        } else {
          suffixName = ".png";
        }

        // 重新生成文件名
        fileName = UUID.randomUUID() + suffixName;

        // 获取服务器路径(springboot虚拟服务器文件不适用)
        //        String realPath = getServletContext().getRealPath("img"); // 文件的上传路径
        //        String realPath = getServletContext().getRealPath("/");

        // 获取项目路径
        String projectPath = "/data/img";
        System.out.println(projectPath);

        if (upload(projectPath, file, fileName)) {
          // 文件存放的相对路径(一般存放在数据库用于img标签的src)
          String relativePath = "/data/img/" + fileName;
          int row = saveImg(owner, relativePath);
          if (row > 0) result = "图片上传成功";
          else result = "图片上传数据库失败";
        } else {
          result = "图片上传失败";
        }
      } else {
        result = "图片格式不正确";
      }
    }

    // 结果用json形式返回
    map.put("result", result);
    String resultJson;
    try {
      resultJson = mapper.writeValueAsString(map);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return e.toString();
    }

    System.out.println(resultJson);
    return resultJson;
  }

  private boolean upload(String realPath, MultipartFile file, String fileName) {
    // 将img文件存入本地
    String path = realPath + "/" + fileName;
    System.out.println("$$$$$$$$$$$$$$$$$$$$$" + path);

    File dest = new File(path);
    System.out.println("****************" + System.getProperty("user.dir"));
    //     判断文件父目录是否存在
    File parentDir = dest.getParentFile();
    if (parentDir != null && !parentDir.exists()) { // 先判断父目录是否为空
      boolean created = parentDir.mkdirs(); // 递归创建父目录
      if (!created) {
        return false;
      }
    } else if (parentDir == null) System.out.println("&!*!&&!!&!!&&!&!&!&" + dest.getPath());
    System.out.println("&!*!&&!!&!!&&!&!&!&" + dest.getPath());
    //     保存文件
    try {
      file.transferTo(dest);
      return true;
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @GetMapping("/getImgPath/{owner}")
  public String getImgPathByOwner(@PathVariable String owner) {
    List<Img> imgs = imgMapper.getImgByOwner(owner);
    HashMap<String, List> map = new HashMap<>();
    ArrayList<String> paths = new ArrayList<>();
    if (imgs != null && !imgs.isEmpty()) {
      for (Img i : imgs) {
        paths.add(i.getPath());
        System.out.println("#################" + i.getPath());
      }
    }
    map.put("paths", paths);

    String result;
    try {
      result = mapper.writeValueAsString(map);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return e.toString();
    }

    return result;
  }

  private int saveImg(String owner, String path) {

    // 将图片信息存入数据库
    Img img = new Img();
    img.setOwner(owner);
    img.setPath(path);

    return imgMapper.insertImg(img);
  }
}
