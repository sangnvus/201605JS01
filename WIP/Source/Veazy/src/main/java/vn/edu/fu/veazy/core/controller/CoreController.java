package vn.edu.fu.veazy.core.controller;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.common.utils.HttpUtils;
import vn.edu.fu.veazy.core.form.FileUploadForm;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.UploadFileResponse;
import vn.edu.fu.veazy.core.service.UserService;

/**
 * Main controller class of Core module.
 * 
 * @author MinhNN
 */
//@CrossOrigin(origins="http://localhost:3003")
@Controller("Core Controller")
public class CoreController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CoreController.class);
    
    @Autowired
    private ServletContext context;
    
    @Autowired
    private UserService userService;

    /**
     * Process request for Core module entry point.
     * 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_HOME, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody String entryPoint() {
        LOGGER.debug("Retrieving name");
        return Const.FINAL_NAME;
    }

    /**
     * アップロードファイル要望を取り扱い
     * @param req servlet要望
     * @param principal 
     * @param uploadForm
     * @return
     */
    @PreAuthorize("hasAnyAuthority(1,2)")
    @RequestMapping(value = Const.URLMAPPING_UPLOADFILE, method = RequestMethod.POST)
    public @ResponseBody String uploadFile(HttpServletRequest req, Principal principal,
            @ModelAttribute("uploadForm") FileUploadForm uploadForm) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
             // アップロードディレクトリパスを作り
             // アップロードデイレクトリパスは「{resource_dir}/upload/{user_id}/{yyyyMMdd}」として表され
            String uname = principal.getName();
            UserModel user = userService.findUserByUsername(uname);
            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String realRes = context.getRealPath(Const.RESOURCE_URL);
            String uploadUrl = "/upload/" + user.getId() + "/" + date;
            // 存在しないかどうかチェックしてなければ新しいのを作り
            File uploadDir = new File(realRes + uploadUrl);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            // ベースURL
            String base = HttpUtils.getContextUrl(req) + Const.RESOURCE_URL + uploadUrl;
            // アップロードファイルを取り扱い
            List<MultipartFile> files = uploadForm.getFile();
            List<UploadFileResponse> fileNames = new ArrayList<UploadFileResponse>();
            if (null != files && files.size() > 0) {
                for (MultipartFile multipartFile : files) {
                    String fileName = multipartFile.getOriginalFilename();
                    String filePath = uploadDir.getAbsolutePath();
                    if (fileName.endsWith(".mp3")) {
                        filePath += "/mp3";
                        base += "/mp3";
                    } else {
                        filePath += "/img";
                        base += "/img";
                    }
                    File f = new File(filePath + "/" + fileName);
                    if (!f.exists()) {
                        f.mkdirs();
                        f.createNewFile();
                    }
                    InputStream is = multipartFile.getInputStream();
                    Files.copy(is, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    is.close();
                    if (fileName.endsWith(".mp3")) {
                        AudioFileFormat format = new MpegAudioFileReader().getAudioFileFormat(f);
                        AudioFormat audioFormat = format.getFormat();
                        long frames = format.getFrameLength();
                        double durationInSeconds = (frames+0.0) / audioFormat.getFrameRate();
                        durationInSeconds += Const.EXAM_LISTENING_TIME_ENTENDED;
                        fileNames.add(new UploadFileResponse(base + "/" + fileName, durationInSeconds));
                    } else {
                        fileNames.add(new UploadFileResponse(base + "/" + fileName));
                    }
                }
                if (fileNames.size() == 1 ) {
                    return fileNames.get(0).toString();
                } else {
                    response.setCode(ResponseCode.SUCCESS);
                    response.setData(fileNames);
                    return response.toResponseJson();
                }
            }
        } catch (Exception e) {
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return response.toResponseJson();
    }

}
