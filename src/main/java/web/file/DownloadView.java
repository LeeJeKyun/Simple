package web.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import web.dto.BoardFile;

public class DownloadView extends AbstractView {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired ServletContext context;
	
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("응답테스트");
		
		BoardFile downFile = (BoardFile)model.get("downFile");
		
		String path = context.getRealPath("upload");
		String filename=downFile.getStoredname();
		
		File realFile = new File(path, filename);
		
		response.setContentType("application/octet-stream");
		response.setContentLengthLong( realFile.length() );
		response.setCharacterEncoding("UTF-8");
		String outputName = URLEncoder.encode(downFile.getOriginname(), "UTF-8");
		
		outputName = outputName.replace("+", "%20");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\"");

		FileInputStream in = new FileInputStream(realFile);
		
		OutputStream out = response.getOutputStream();
		
		FileCopyUtils.copy(in, out);
		
	}
	
	
	
}
