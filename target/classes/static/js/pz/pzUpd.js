var flag;

var zid;
var zname;

var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	zid=$("#zid").val();
	zname=$("#zname").val();
	
	//获取焦点
	$("#zname").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
	
});

/*****失去焦点事件******/
function getBlur() {
	$("#zname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，凭证名称不能为空！','#zname',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('凭证名称'+'格式有误！','#zname',{tips:[2,'red']});
		}else if(zname!=t) {
			$.ajax({
				url:"pz.do?method=getCkName",
				data:{"zname":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此凭证名称已经存在！','#zname',{tips:[2,'red']});
		    	    	     flag=1;  //标识位!!!
					}else {
						flag=0;
					}
				}
			});
		}else{
			flag=0;
		}
	});
	
}

/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var zid=$("#zid").val();
		var zname=$("#zname").val();
		
		if (zname.length==0) {
			 layer.tips('对不起，凭证名称不能为空！','#zname',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，凭证名称已存在！','#zname',{tips:[2,'red']});
		}else if (!zu.test(zname)) {
			layer.tips('凭证名称'+'格式有误！','#zname',{tips:[2,'red']});
		}else {
			var xx="zname="+zname+"&zid="+zid;
			$.post("pz.do?method=pzUpd",xx,function(mydata){
				if (mydata==true) {
					//增加成功！
					   layer.msg('修改成功！', {icon : 1,time : 60000});
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
