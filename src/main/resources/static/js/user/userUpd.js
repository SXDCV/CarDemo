var flag;
var flag1;
var uname;
var utel;
var zu=/^[\u0391-\uFFE5]+$/;
var mycphone=/(^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$)|(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)/;//电话格式
$(function() {
	layui.use(function() {
		var form = layui.form;
		getsex(form);
	});
	uname=$("#uname").val();
	utel=$("#utel").val();
	//获取焦点
	$("#uname").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
	
});

function getsex(form) {
	var sex=$("#sex").val();
	$("#usex").empty();
	if (sex==1) {
		$("#usex").append("<option value='1' selected>男</option>");
		$("#usex").append("<option value='0' >女</option>");
		form.render('select');
	}else {
		$("#usex").append("<option value='1' >男</option>");
		$("#usex").append("<option value='0' selected>女</option>");
		form.render('select');
	}
}

/*****失去焦点事件******/
function getBlur() {
	$("#uname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，员工名称不能为空！','#uname',{tips:[2,'red']});
		}else if(uname!=t){
			
			$.ajax({
				url:"User.do?method=getCkName",
				data:{"uname":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此员工名称已经存在！','#uname',{tips:[2,'red']});
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
	$("#upwd").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，密码不能为空！','#upwd',{tips:[2,'red']});
		}
	});
	$("#username").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，真实姓名不能为空！','#username',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('真实姓名'+'格式有误！','#username',{tips:[2,'red']});
		}
	});
	$("#utel").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，电话不能为空！','#utel',{tips:[2,'red']});
		}else if (!mycphone.test(t)) {
			layer.tips('电话'+'格式有误！','#utel',{tips:[2,'red']});
		}else if(utel!=t){
			$.ajax({
				url:"User.do?method=getCkName",
				data:{"utel":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，电话已经存在！','#utel',{tips:[2,'red']});
		    	    	     flag1=1;  //标识位!!!
					}else {
						flag1=0;
					}
				}
			});
		}else{
			flag1=0;
		}
	});
	
}

/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var uid=$("#uid").val();
		var uname=$("#uname").val();
		var upwd=$("#upwd").val();
		var username=$("#username").val();
		var utel=$("#utel").val();
		var usex=$("#usex").val();
		if (uname.length==0) {
			 layer.tips('对不起，员工名称不能为空！','#uname',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，员工名称已存在！','#uname',{tips:[2,'red']});
		}else if (upwd.length==0) {
			 layer.tips('对不起，密码不能为空！','#upwd',{tips:[2,'red']});
		}else if (username.length==0) {
			 layer.tips('对不起，真实姓名不能为空！','#username',{tips:[2,'red']});
		}else if (!zu.test(username)) {
			layer.tips('真实姓名'+'格式有误！','#username',{tips:[2,'red']});
		}else if (utel.length==0) {
			 layer.tips('对不起，电话不能为空！','#utel',{tips:[2,'red']});
		}else if (!mycphone.test(utel)) {
			layer.tips('电话'+'格式有误！','#utel',{tips:[2,'red']});
		}else if (flag1==1) {
			layer.tips('对不起，电话已存在！','#utel',{tips:[2,'red']});
		}else {
			var xx="uname="+uname+"&upwd="+upwd+"&username="+username+"&utel="+utel+"&usex="+usex+"&uid="+uid;
			$.post("User.do?method=UserUpd",xx,function(mydata){
				if (mydata==true) {
					//修改成功！
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
