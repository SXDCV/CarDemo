var flag=1;
$(function(){
	//获取焦点
	$("#uname").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
});

/********失去焦点*************/
function getBlur() {
	//账号不为空
	$("#uname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			layer.tips('对不起，账号不能为空!','#uname',{tips:[2,'red']});
		}else {
			$.ajax({
				url:"user.do?method=getName",
				data:{"uname":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==0) {
						   layer.tips('对不起，此账号不存在！','#uname',{tips:[2,'red']});
		    	    	     flag=1;  //标识位!!!
					}else {
						flag=0;
					}
				}
			});
		}
	});
	
	//密码不为空
	$("#upwd").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			layer.tips('对不起，密码不能为空!','#upwd',{tips:[2,'red']});
		}
	});
}

/*************提交***************/
function getBtn() {
	$("#btn").click(function() {
		var uname=$("#uname").val();
		var upwd=$("#upwd").val();
		if (uname.length==0) {
			layer.tips('对不起，账号不能为空!','#uname',{tips:[2,'red']});
		}else if (upwd.length==0) {
			layer.tips('对不起，密码不能为空!','#upwd',{tips:[2,'red']});
		}else {
			//提交
			var xx="uname="+uname+"&upwd="+upwd;
			$.post("user.do?method=login",xx,function(mydata){
				if (mydata==1) {
					window.location.href="user.do?method=tomain";
		    	 }else {
	    		   layer.tips('对不起，该密码错误!','#upwd',{tips:[2,'red']});
	    		   setTimeout(function() {
	    			   $("#upwd").val("");
	    		    	},
	    		    	1000);
				}
		       },'json');
		}
	});
}