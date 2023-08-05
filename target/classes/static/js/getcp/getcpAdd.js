var fid;
var sz=/^[0-9]*$/;
$(function() {
	
	fid=$("#fid1").val();
	//获取焦点
	$("#gcount").focus();
	
	getOne();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
});


function getOne() {
	$.ajax({
		url:'mytf.do?method=mytfAll',
		data:{"fid":fid},
		dataType:'json',
		type:'post',
		async:false,
		success:function(mydata){
			$("#fid").val(mydata.fid);
			$("#fname").val(mydata.fname);
			$("#faddress").val(mydata.faddress);
			
		}
		
	});
}

/*****失去焦点事件******/
function getBlur() {
	$("#gcount").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，入库数量不能为空！','#gcount',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('入库数量'+'格式有误！','#gcount',{tips:[2,'red']});
		}else if (t==0) {
			layer.tips('对不起，入库数量不能为0！','#gcount',{tips:[2,'red']});
		}
	});
	
}

/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var fid=$("#fid").val();
		var gcount=$("#gcount").val();
		if (gcount.length==0) {
			 layer.tips('对不起，入库数量不能为空！','#gcount',{tips:[2,'red']});
		}else if (!sz.test(gcount)) {
			layer.tips('入库数量'+'格式有误！','#gcount',{tips:[2,'red']});
		}else if (gcount==0) {
			layer.tips('对不起，入库数量不能为0！','#gcount',{tips:[2,'red']});
		}else {
			var xx="fid="+fid+"&gcount="+gcount;
			$.post("getcp.do?method=getcpAdd",xx,function(mydata){
				if(mydata==true){
					var xx="fid="+fid+"&fcount="+gcount;
					$.post("mytf.do?method=updFcount",xx,function(mydata){
						if (mydata==1) {
							//修改成功！
							   layer.msg('新增成功！', {icon : 1,time : 60000});
				    		       setTimeout(function() {
				    		    	  var index = parent.layer.getFrameIndex(window.name); 
					    		      parent.layer.close(index);
				    		    	},
				    		    	2000);
				    		   }
				       },'json');
				}else{
					layer.msg('新增失败！',{icon:1,time:60000});
					setTimeout(function(){
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					},1000);
				}
			},'json');
		}
		
	});
}
