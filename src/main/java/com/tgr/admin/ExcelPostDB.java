package com.tgr.admin;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@Api(tags = {"excel导入数据库"})
@RestController
@RequestMapping(value = "excelPostDB")
public class ExcelPostDB extends BaseController {
	
	/*@Autowired
	private KnowledgeService knowledgeService;

    //导入
	@ApiOperation(value = "excel导入")
    @PostMapping(value = "batchImport")
    public ResponseResult<Boolean> batchImportUserKnowledge(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response
            ) throws IOException{
		
        //判断文件是否为空
        if(file==null){
       	 return ResponseResult.getResult(false,"文件为空");
        }
        
        //获取文件名
        String fileName=file.getOriginalFilename();
        
        //验证文件名是否合格
        if(!ExcelImportUtils.validateExcel(fileName)){
       	 return ResponseResult.getResult(false,"文件名不合格");
        }
        
        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(StringUtils.isEmpty(fileName) || size==0){
       	 return ResponseResult.getResult(false,"文件内容为空");
        }
        
        //批量导入
        return knowledgeService.batchImport(fileName,file);
}*/
}
