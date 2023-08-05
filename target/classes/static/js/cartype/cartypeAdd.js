var flag;

var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	//获取焦点
	$("#aname").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
});

/*****失去焦点事件******/
function getBlur() {
	$("#aname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，产品名称不能为空！','#aname',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('产品名称'+'格式有误！','#aname',{tips:[2,'red']});
		}else {
			
			$.ajax({
				url:"cartype.do?method=getCkName",
				data:{"aname":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此产品名称已经存在！','#aname',{tips:[2,'red']});
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
		var aname=$("#aname").val();
		
		if (aname.length==0) {
			 layer.tips('对不起，产品名称不能为空！','#aname',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，产品名称已存在！','#aname',{tips:[2,'red']});
		}else if (!zu.test(aname)) {
			layer.tips('产品名称'+'格式有误！','#aname',{tips:[2,'red']});
		}else {
			var xx="aname="+aname;
			$.post("cartype.do?method=cartypeAdd",xx,function(mydata){
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
