var flag;
$(function() {
	layui.use(function() {
		var form = layui.form;
		getOne(form);
	});
	//获取焦点
	$("#aid").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
});
/*******获取汽车品牌********/
function getOne(form) {
	$("#aid").empty();
	$("#aid").append("<option value='0'>----请选择汽车品牌----</option>");
	$.ajax({
		url:"cartype.do?method=cartypeAll",
		data:"",
		dataType:'json',
		type:'post',
		async:false,
		success:function(mydata){
			$.each(mydata,function(index,xx){
				$("#aid").append("<option value="+xx.aid+">"+xx.aname+"</option>");
			});
			form.render('select');
		}
	});
}


/*****失去焦点事件******/
function getBlur() {
	$("#aid").blur(function() {
		var t=$(this).val();
		if (t==0) {
			 layer.tips('对不起，汽车品牌不能为空！','#aid',{tips:[2,'red']});
		}
	});
	$("#xname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，系列名称不能为空！','#xname',{tips:[2,'red']});
		}else {
			$.ajax({
				url:"carxl.do?method=getCkName",
				data:{"xname":t},
				dataType:'json',
				type:'post',
				async:false,
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此系列名称已经存在！','#xname',{tips:[2,'red']});
		    	    	     flag=1;  //标识位!!!
					}else {
						flag=0;
					}
				}
			});
		}
	});
	
}

/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var aid=$("#aid").val();
		var xname=$("#xname").val();
		
		if (aid==0) {
			 layer.tips('对不起，汽车品牌不能为空！','#ad',{tips:[2,'red']});
		}else if (xname.length==0) {
			 layer.tips('对不起，系列名称不能为空！','#xname',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，系列名称已存在！','#xname',{tips:[2,'red']});
		}else {
			var xx="aid="+aid+"&xname="+xname;
			$.post("carxl.do?method=carxlAdd",xx,function(mydata){
				if (mydata==true) {
					//增加成功！
					   layer.msg('添加成功！', {icon : 1,time : 60000});
		    		       setTimeout(function() {
		    		    	  var index = parent.layer.getFrameIndex(window.name); 
			    		      parent.layer.close(index);
		    		    	},
		    		    	2000);
		    		   }
		       },'json');
		}
	});
}
