var flag;
var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	//获取焦点
	$("#sname").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
});

/*****失去焦点事件******/
function getBlur() {
	$("#sname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，类别名称不能为空！','#sname',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('类别名称'+'格式有误！','#sname',{tips:[2,'red']});
		}else {
			
			$.ajax({
				url:"servicetype.do?method=getCkName",
				data:{"sname":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此类别名称已经存在！','#sname',{tips:[2,'red']});
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
		var sname=$("#sname").val();
		
		if (sname.length==0) {
			 layer.tips('对不起，类别名称不能为空！','#sname',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，类别名称已存在！','#sname',{tips:[2,'red']});
		}else if (!zu.test(sname)) {
			layer.tips('类别名称'+'格式有误！','#sname',{tips:[2,'red']});
		}else {
			var xx="sname="+sname;
			$.post("servicetype.do?method=servicetypeAdd",xx,function(mydata){
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
