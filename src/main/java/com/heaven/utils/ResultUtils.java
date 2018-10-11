package com.heaven.utils;

import com.heaven.vo.ResultVo;

public class ResultUtils {

	public static ResultVo success(Object data){
		ResultVo resultVo = new ResultVo();
		resultVo.setCode(0);
		resultVo.setMsg("success");
		resultVo.setData(data);
		return resultVo;
	}
	public static ResultVo success(){
		return success(null);
	}
	
	public static ResultVo error(Integer code,String msg){
		ResultVo resultVo = new ResultVo();
		resultVo.setCode(code);
		resultVo.setMsg(msg);
		return resultVo;
	}
}
